package org.multipoly.restlet.user;

import com.rorotika.cm.restlet.app.BaseServerResource;
import org.multipoly.restlet.module.ModuleResource;
import com.rorotika.cm.user.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.umlg.runtime.adaptor.UMLG;

/**
 * Date: 2014/10/15
 * Time: 1:42 PM
 */
public class UserModulesResource extends BaseServerResource {

    public final static String PATH = "/user/{id}/modules";

    public UserModulesResource() {
        setNegotiated(false);
    }

    @Override
    protected Representation get() throws ResourceException {
        String userId = (String) getRequestAttributes().get("id");

        User user = UMLG.get().getEntity(userId);

        JSONObject result = new JSONObject();
        JSONArray columns = new JSONArray();

        try {
            result.put("data", ModuleResource.getModulesAsJSON(user.getRoles()));

            JSONObject nameColumn = new JSONObject();
            nameColumn.put("id", "module");
            nameColumn.put("field", "name");
            nameColumn.put("name", "Module");
            columns.put(nameColumn);

            result.put("columns", columns);

            return new JsonRepresentation(result);
        } catch (JSONException e) {
            throw new RuntimeException("Error receiving user modules for user '" + user.getUsername() + "'");
        }
    }
}
