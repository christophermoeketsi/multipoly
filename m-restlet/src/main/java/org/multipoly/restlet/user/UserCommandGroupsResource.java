package org.multipoly.restlet.user;

import com.rorotika.cm.nodedefinition.CommandGroup;
import com.rorotika.cm.restlet.app.BaseServerResource;
import com.rorotika.cm.restlet.commandGroup.CommandGroupResource;
import com.rorotika.cm.user.User;
import com.rorotika.cm.user.UserGroup;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.umlg.runtime.adaptor.UMLG;

import java.util.HashSet;
import java.util.Set;

/**
 * Date: 2014/10/15
 * Time: 1:42 PM
 */
public class UserCommandGroupsResource extends BaseServerResource {

    public final static String PATH = "/user/{id}/commandGroups";

    public UserCommandGroupsResource() {
        setNegotiated(false);
    }

    @Override
    protected Representation get() throws ResourceException {
        String userId = (String) getRequestAttributes().get("id");

        JSONObject result = new JSONObject();
        JSONArray columns = new JSONArray();

        User user = UMLG.get().getEntity(userId);

        try {
            Set<CommandGroup> allCommandGroupsForUser = new HashSet<>();

            for (UserGroup userGroup : user.getUserGroup())
                allCommandGroupsForUser.addAll(userGroup.getCommandGroup());

            result.put("data", CommandGroupResource.getCommandGroupsAsJSON(allCommandGroupsForUser));

            JSONObject nameColumn = new JSONObject();
            nameColumn.put("id", "name");
            nameColumn.put("field", "name");
            nameColumn.put("name", "Command Group");
            columns.put(nameColumn);

            JSONObject accessColumn = new JSONObject();
            accessColumn.put("id", "access");
            accessColumn.put("field", "access");
            accessColumn.put("name", "Access");
            columns.put(accessColumn);

            JSONObject vendorTechnologyColumn = new JSONObject();
            vendorTechnologyColumn.put("id", "vendorTechnology");
            vendorTechnologyColumn.put("field", "vendorTechnology");
            vendorTechnologyColumn.put("name", "Vendor Technology");
            columns.put(vendorTechnologyColumn);

            result.put("columns", columns);

            return new JsonRepresentation(result);
        } catch (JSONException e) {
            throw new RuntimeException("Error receiving command groups for user '" + user.getUsername() + "'");
        }
    }
}
