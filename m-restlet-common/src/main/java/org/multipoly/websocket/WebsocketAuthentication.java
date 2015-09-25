package org.multipoly.websocket;

import org.eclipse.jetty.websocket.api.Session;
import org.multipoly.User.User;
import org.multipoly.security.MMemoryRealm;
import org.multipoly.security.SecurityUtil;
import org.restlet.engine.util.Base64;
import org.restlet.ext.crypto.internal.CryptoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.HttpCookie;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Date: 2014/11/14
 * Time: 8:51 PM
 */
public class WebsocketAuthentication {

    public final static String CREDENTIALS = "Credentials";
    private static Logger logger = LoggerFactory.getLogger(WebsocketAuthentication.class);

    public static User authenticate(Session session, MMemoryRealm realm) {
        List<HttpCookie> httpCookies = session.getUpgradeRequest().getCookies();
        List<String> credentials = httpCookies.stream().filter(c -> c.getName().equals(CREDENTIALS)).map(c -> c.getValue()).collect(Collectors.toList());
        if (credentials.size() == 1) {
            return parseCredentials(realm, credentials.get(0));
        } else {
            return null;
        }
    }

    /**
     * Decodes the credentials stored in a cookie into a proper
     * {@link org.restlet.data.ChallengeResponse} object.
     *
     *
     * @param realm
     * @param cookieValue The credentials to decode from cookie value.
     * @return The authenticated user or null if authentication failed
     */
    private static User parseCredentials(MMemoryRealm realm, String cookieValue) {
        // 1) Decode Base64 string
        byte[] encrypted = Base64.decode(cookieValue);
        if (encrypted == null) {
            logger.warn("Cannot decode cookie credentials : " + cookieValue);
        }
        // 2) Decrypt the credentials
        try {
            String decrypted = CryptoUtils.decrypt("AES", "My Server KeyXXX".getBytes(), encrypted);
            // 3) Parse the decrypted cookie value
            int lastSlash = decrypted.lastIndexOf('/');
            String[] indexes = decrypted.substring(lastSlash + 1).split(",");
            int identifierIndex = Integer.parseInt(indexes[0]);
            int secretIndex = Integer.parseInt(indexes[1]);
            String identifier = decrypted.substring(identifierIndex + 1, secretIndex);
            String secret = decrypted.substring(secretIndex + 1, lastSlash);
            User user = realm.findUser(identifier);
            if (user != null) {
                if (SecurityUtil.verify(user, secret.toCharArray())) {
                    return user;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error("Unable to decrypt cookie credentials", e);
            return null;
        }
    }
}
