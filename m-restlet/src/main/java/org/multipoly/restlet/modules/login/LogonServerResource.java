package org.multipoly.restlet.modules.login;

import com.hazelcast.com.eclipsesource.json.JsonObject;
import org.multipoly.restlet.app.BaseServerResource;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;

/**
 * Created by chris on 2015/12/12.
 */
public class LogonServerResource extends BaseServerResource {
    public final static String PATH = "/logonPost";

    @Override
    protected Representation post(Representation entity) {
        try {
            JsonObject response = new JsonObject();
            response.add("redirect", "dashboard");
            return new JsonRepresentation(response.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
