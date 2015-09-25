package org.multipoly.restlet.modules.login;

import org.apache.commons.lang.StringUtils;
import org.restlet.data.MediaType;
import org.restlet.ext.freemarker.TemplateRepresentation;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import java.util.HashMap;
import java.util.Map;

/**
 * Date: 2013/04/24
 * Time: 9:43 AM
 */
public class LoginFormServerResource extends BaseServerResource {

    public final static String PATH = "/logonPost";

    public LoginFormServerResource() {
        setNegotiated(false);
    }

    @Override
    protected Representation get() throws ResourceException {
        Map<String, Object> dataModel = new HashMap<>();
        String redirectUri = getQueryValue("redirectUri");
        if (StringUtils.isEmpty(redirectUri)) {
            redirectUri = "/netcm";
        }
        dataModel.put("redirectUri", redirectUri);
        new CMFreemarker();
        return new TemplateRepresentation("modules/login/login.ftl", CMFreemarker.getConfiguration(), dataModel, MediaType.TEXT_HTML);
    }

    @Override
    protected Representation post(Representation entity) {
        String redirectUri = getQueryValue("redirectUri");
        if (redirectUri==null)
            redirectUri = "/netcm";
        return new JsonRepresentation("{\"status\":\"success\", \"redirect\":\"" + redirectUri + "\"}");
    }
}
