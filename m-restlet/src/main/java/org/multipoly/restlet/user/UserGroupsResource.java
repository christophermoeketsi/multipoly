package org.multipoly.restlet.user;

import com.rorotika.cm.restlet.app.BaseServerResource;
import com.rorotika.cm.user.User;
import com.rorotika.cm.user.UserGroup;
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
public class UserGroupsResource extends BaseServerResource {

    public final static String PATH = "/user/{id}/groups";

    public UserGroupsResource() {
        setNegotiated(false);
    }

    @Override
    protected Representation get() throws ResourceException {
        String userId = (String) getRequestAttributes().get("id");

        JSONObject result = new JSONObject();
        JSONArray groups = new JSONArray();
        JSONArray columns = new JSONArray();

        User user = UMLG.get().getEntity(userId);

        try {
            for (UserGroup userGroup : user.getUserGroup()) {
                userGroup.getNetwork();
                JSONObject userGroupJSON = new JSONObject();

                userGroupJSON.put("id", userGroup.getId());
                userGroupJSON.put("name", userGroup.getName());

                groups.put(userGroupJSON);
            }

            result.put("data", groups);

            JSONObject nameColumn = new JSONObject();
            nameColumn.put("id", "group");
            nameColumn.put("field", "name");
            nameColumn.put("name", "Group");
            columns.put(nameColumn);

            result.put("columns", columns);

            return new JsonRepresentation(result);
        } catch (JSONException e) {
            throw new RuntimeException("Error receiving user groups for user '" + user.getUsername() + "'");
        }
    }
}
