package org.multipoly.restlet.user;

import com.rorotika.cm.network.Network;
import com.rorotika.cm.restlet.app.BaseServerResource;
import com.rorotika.cm.restlet.network.NetworkResource;
import com.rorotika.cm.user.User;
import com.rorotika.cm.user.UserGroup;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.umlg.runtime.adaptor.UMLG;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Date: 2014/10/15
 * Time: 1:42 PM
 */
public class UserNetworksResource extends BaseServerResource {

    public final static String PATH = "/user/{id}/networks";

    public UserNetworksResource() {
        setNegotiated(false);
    }

    @Override
    protected Representation get() throws ResourceException {
        String userId = (String) getRequestAttributes().get("id");

        JSONObject result = new JSONObject();
        JSONArray columns = new JSONArray();

        User user = UMLG.get().getEntity(userId);

        try {
            Set<Network> allNetworksForUser = new HashSet<>();

            for (UserGroup userGroup : user.getUserGroup())
                allNetworksForUser.addAll(userGroup.getNetwork());

            result.put("data", NetworkResource.getNetworksAsJSON(allNetworksForUser));

            JSONObject nameColumn = new JSONObject();
            nameColumn.put("id", "network");
            nameColumn.put("field", "name");
            nameColumn.put("name", "Network");
            columns.put(nameColumn);

            result.put("columns", columns);

            return new JsonRepresentation(result);
        } catch (JSONException e) {
            throw new RuntimeException("Error receiving networks for user '" + user.getUsername() + "'");
        }
    }
}
