package org.multipoly.restlet.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.DateTime;
import org.multipoly.User.User;
import org.multipoly.admin.MUserThreadVar;
import org.multipoly.security.SecurityUtil;
import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.*;
import org.restlet.ext.crypto.CookieAuthenticator;
import org.umlg.runtime.adaptor.UMLG;
import org.umlg.runtime.util.ObjectMapperFactory;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;

/**
 * Date: 2013/04/24
 * Time: 6:38 PM
 */
public class MCookieAuthenticator extends CookieAuthenticator {

    private static MCookieAuthenticator mCookieAuthenticator;

    public static MCookieAuthenticator get(Context context, String realm, byte[] encryptSecretKey) {
        if (mCookieAuthenticator == null) {
            mCookieAuthenticator = new MCookieAuthenticator(context, realm, encryptSecretKey);
        }
        return mCookieAuthenticator;
    }

    public static MCookieAuthenticator get() {
        return mCookieAuthenticator;
    }

    private MCookieAuthenticator(Context context, String realm, byte[] encryptSecretKey) {
        super(context, realm, encryptSecretKey);
    }

    @Override
    protected int beforeHandle(Request request, Response response) {
        if(request.getMethod() == Method.OPTIONS){
            /*
                According to Cross-Origin Request with Preflight specs credentials, i.e. session cookie, is not sent within the preflight request,
                therefore the solution is to disable security on OPTIONS requests on your REST server side and allow requests without session cookie
                only for OPTIONS requests.
             */
            return CONTINUE;
        }
        if (request.getResourceRef().getRemainingPart().endsWith(".css") ||
                request.getResourceRef().getRemainingPart().startsWith("/img") ||
                request.getResourceRef().getRemainingPart().startsWith("/js") ||
                request.getResourceRef().getRemainingPart().startsWith("/login") ||
                request.getResourceRef().getRemainingPart().startsWith("/modules/login") ||
                request.getResourceRef().getRemainingPart().startsWith("/js")
                ) {
            return CONTINUE;
        } else {
            setLoginFormPath("/netcm/login");
            setLoginPath("/logonPost");
            setLogoutPath("/logout");
            int result = super.beforeHandle(request, response);
            if (response.getStatus() == Status.REDIRECTION_SEE_OTHER) {
                return STOP;
            } else {
                return result;
            }
        }
    }

    /**
     * Processes the login request.
     *
     * @param request  The current request.
     * @param response The current response.
     */
    protected void login(Request request, Response response) {
        // Login detected
        String usernameAndPassword = request.getEntityAsText();
        ObjectMapper objectMapper = ObjectMapperFactory.INSTANCE.getObjectMapper();
        try {
            Map<String, String> overloaded = objectMapper.readValue(usernameAndPassword, Map.class);
            String username = overloaded.get("username");
            String password = overloaded.get("password");
            ChallengeResponse cr = new ChallengeResponse(getScheme(),
                    username,
                    password);
            request.setChallengeResponse(cr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected int unauthenticated(Request request, Response response) {
        request.getCookies().remove(request.getCookies().getFirst("executorCredentials"));
        return super.unauthenticated(request, response);
    }

    @Override
    protected boolean authenticate(Request request, Response response) {
        //Only use the cookie if not busy logging in
        if (isLoggingIn(request, response)) {
            //Remove the cookie
            Cookie credentialsCookie = request.getCookies().getFirst(getCookieName());
            request.getCookies().remove(credentialsCookie);
        }
        //The CmSessionInfo instance is created in CMVerifier.verify
        if (super.authenticate(request, response)) {
            if (isLoggingIn(request, response)) {
                captureLoginDetails();
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void attemptRedirect(Request request, Response response) {
        String targetUri = request.getResourceRef().getQueryAsForm()
                .getFirstValue(getRedirectQueryName());
        if (targetUri != null) {
            response.redirectSeeOther(Reference.decode(targetUri));
        }
    }

    /**
     * This only get called when the user is actually busy logging in.
     */
    private void captureLoginDetails() {
        User user = MUserThreadVar.INSTANCE.get();
        user.setLastLoggedIn((new DateTime()).getYear());
        UMLG.get().commit();
    }

    @Override
    protected int logout(Request request, Response response) {
        //authenticate to put the user on the WebUserThreadVar.
        //It can then be used to set the logged out date
        authenticate(request, response);
        User user = MUserThreadVar.INSTANCE.get();
        if (user != null) {
            //Todo Need to come and change this
            user.setLastLoggedOut((new DateTime()).getDayOfMonth());
            UMLG.get().commit();
        }
        return super.logout(request, response);
    }

    @Override
    protected CookieSetting getCredentialsCookie(Request request,
                                                 Response response) {
        CookieSetting credentialsCookie = response.getCookieSettings()
                .getFirst(getCookieName());

        if (credentialsCookie == null) {
            credentialsCookie = new CookieSetting(getCookieName(), null);
            credentialsCookie.setPath("/");
            response.getCookieSettings().add(credentialsCookie);
        }

        return credentialsCookie;
    }

    @Override
    public ChallengeResponse parseCredentials(String cookieValue) {
        return super.parseCredentials(cookieValue);
    }

    @Override
    /**
     * Formats the raws credentials to store in the cookie.
     *
     * @param challenge
     *            The challenge response to format.
     * @return The raw credentials.
     * @throws GeneralSecurityException
     */
    protected String formatCredentials(ChallengeResponse challenge)
            throws GeneralSecurityException {
        return SecurityUtil.formatCredentials(challenge.getIdentifier(), challenge.getSecret());
    }

}
