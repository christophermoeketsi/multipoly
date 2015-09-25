package org.multipoly.restlet.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rorotika.cm.admin.initialize.CmInitialize;
import com.rorotika.cm.restlet.app.BaseServerResource;
import com.rorotika.cm.user.User;
import com.rorotika.cm.user.UserGroup;
import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.umlg.runtime.adaptor.UMLG;
import org.umlg.runtime.collection.UmlgSet;
import org.umlg.runtime.collection.memory.UmlgMemorySet;
import org.umlg.runtime.types.Password;
import org.umlg.runtime.util.ObjectMapperFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Date: 2014/10/15
 * Time: 1:42 PM
 */
public class UserResource extends BaseServerResource {

    public final static String PATH = "/user/{id}";

    public UserResource() {
        setNegotiated(false);
    }

    public static JSONArray getUsersAsJSON (Set<? extends User> users) {
        JSONArray usersJSON = new JSONArray();

        for (User user : users) {
            usersJSON.put(getUserAsJSON(user));
        }

        return usersJSON;
    }

    public static JSONObject getUserAsJSON (User user) {
        JSONObject userJSON = new JSONObject();

        try {
            userJSON.put("id", user.getId());
            userJSON.put("username", user.getUsername());
            userJSON.put("name", user.getName());
            userJSON.put("surname", user.getSurname());
            userJSON.put("email", user.getEmail());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return userJSON;
    }

    @Override
    protected Representation get() throws ResourceException {
        String userId = (String) getRequestAttributes().get("id");

        try {
            if (userId.compareToIgnoreCase("all") == 0 || userId == "") {
                UmlgSet<? extends User> users = User.allInstances().select(u -> !u.getDeleted());

                JSONArray usersJSON = getUsersAsJSON(users);
                JSONObject result = new JSONObject();

                ArrayList<JSONObject> columns = new ArrayList<>();

                JSONObject usernameColumn = new JSONObject();
                usernameColumn.put("id", "username");
                usernameColumn.put("field", "username");
                usernameColumn.put("name", "Username");
                columns.add(usernameColumn);

                JSONObject nameColumn = new JSONObject();
                nameColumn.put("id", "name");
                nameColumn.put("field", "name");
                nameColumn.put("name", "Name");
                columns.add(nameColumn);

                JSONObject surnameColumn = new JSONObject();
                surnameColumn.put("id", "surname");
                surnameColumn.put("field", "surname");
                surnameColumn.put("name", "Surname");
                columns.add(surnameColumn);

                JSONObject emailColumn = new JSONObject();
                emailColumn.put("id", "email");
                emailColumn.put("field", "email");
                emailColumn.put("name", "Email");
                columns.add(emailColumn);

                result.put("data", usersJSON);
                result.put("columns", new JSONArray(columns));

                return new JsonRepresentation(result);
            } else {
                return new JsonRepresentation(getUserAsJSON(UMLG.get().getEntity(userId)));
            }
        } catch (JSONException e) {
            throw new RuntimeException("Error receiving users");
        }
    }

    @Override
    protected Representation delete() throws ResourceException {
        String userId = (String) getRequestAttributes().get("id");

        User user = UMLG.get().getEntity(userId);

        getRealm().removeUser(user);

        user.setDeleted(true);
        user.setDeletedOn(new DateTime());

        UMLG.get().commit();

        return new JsonRepresentation("{\"success\": true}");
    }

    @Override
    protected Representation post(Representation entity) throws ResourceException {
        String userId = (String) getRequestAttributes().get("id");

        User user;

        if (userId.equals("new")) {
            user = new User();
            CmInitialize.addFavReportAndPolicyLabel(user);
        } else {
            user = UMLG.get().getEntity(userId);
        }

        try {
            String json = entity.getText();
            ObjectMapper mapper = ObjectMapperFactory.INSTANCE.getObjectMapper();
            Map<String, Object> data = mapper.readValue(json, Map.class);

            user.setUsername((String) data.get("username"));
            user.setName((String) data.get("name"));
            user.setSurname((String) data.get("surname"));
            user.setEmail((String) data.get("email"));

            if (userId.equals("new")) user.setPassword(new Password((String) data.get("password")));

            List<String> newGroups = (List<String>) data.get("groupIds");
            UmlgSet<UserGroup> newGroupsToSave = new UmlgMemorySet<>();

            for (String newGroup : newGroups) {
                UserGroup userGroup = UMLG.get().getEntity(newGroup);
                newGroupsToSave.add(userGroup);
            }

            user.setUserGroup(newGroupsToSave);

            UMLG.get().commit();

            if (userId.equals("new")) {
                getRealm().addUser(user);
            } else {
                getRealm().resetUser(user);
            }

            return new JsonRepresentation("{\"success\": true}");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
