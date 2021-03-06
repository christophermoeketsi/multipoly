package org.multipoly.restlet.modules.login;

import org.multipoly.restlet.app.BaseServerResource;
import org.multipoly.restlet.app.MFreemarker;
import org.restlet.data.MediaType;
import org.restlet.ext.freemarker.TemplateRepresentation;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;

import java.util.HashMap;
import java.util.Map;

/**
 * Date: 2013/04/24
 * Time: 9:43 AM
 */
public class LoginFormServerResource extends BaseServerResource {

    public final static String PATH = "/login";

    public LoginFormServerResource() {
        setNegotiated(false);
    }

    @Override
    protected Representation get() throws ResourceException {
        Map<String, Object> dataModel = new HashMap<>();
        return new TemplateRepresentation("/login.html", MFreemarker.getConfiguration(), dataModel, MediaType.TEXT_HTML);
    }

    @Override
    protected Representation post(Representation entity) {
        String redirectUri = getQueryValue("redirectUri");
        if (redirectUri == null)
            redirectUri = "/m";
        return new JsonRepresentation("{\"status\":\"success\", \"redirect\":\"" + redirectUri + "\"}");
    }
}
