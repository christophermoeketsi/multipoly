package org.multipoly.restlet.modules.register;

import org.json.JSONObject;
import org.multipoly.User.ROLE;
import org.multipoly.User.User;
import org.multipoly.User.UserGroup;
import org.multipoly.restlet.app.BaseServerResource;
import org.multipoly.restlet.app.MFreemarker;
import org.restlet.data.MediaType;
import org.restlet.ext.freemarker.TemplateRepresentation;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.umlg.runtime.adaptor.UMLG;
import org.umlg.runtime.collection.UmlgSet;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chris on 2015/11/18.
 */
public class RegisterFormServerResource extends BaseServerResource {
    public final static String PATH = "/register";

    public RegisterFormServerResource() {
        setNegotiated(false);
    }

    @Override
    protected Representation get() throws ResourceException {
        Map<String, Object> dataModel = new HashMap<>();
        return new TemplateRepresentation("/login.html", MFreemarker.getConfiguration(), dataModel, MediaType.TEXT_HTML);
    }

    @Override
    protected Representation post(Representation entity) {
        try {
            JSONObject jsonCreds = new JSONObject(entity.getText());
            JSONObject jsonResponse = new JSONObject();
            if (checkIfExsits(jsonCreds)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("User with email address ").append(jsonCreds.get("email")).
                        append(" already exist. A password for ").
                        append(jsonCreds.get("email")).
                        append("has been sent to your email");
                jsonResponse.append("message", stringBuilder.toString());
                return new JsonRepresentation(jsonResponse.toString());
            } else {
                createUser(jsonCreds);
                jsonResponse.append("message", "go start");
                return new TemplateRepresentation("/dashboard.html", MFreemarker.getConfiguration(), MediaType.TEXT_HTML);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean checkIfExsits(JSONObject userJsonObject) {
        try {
            UmlgSet<User> allUsers = (UmlgSet<User>) User.allInstances();
            for (User user : allUsers) {
                if (user.getEmail().equals(userJsonObject.getString("email"))) {
                    return true;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    private boolean createUser(JSONObject jsonObject) {
        try {
            User user = new User();
            user.setEmail(jsonObject.getString("email"));
            user.setUsername(jsonObject.getString("email"));
            user.setPassword(jsonObject.getString("password"));
            user.setSurname("null");
            user.setName(jsonObject.getString("email"));
            user.setLastLoggedIn(Math.toIntExact(new Date().getTime() / 1000));
            user.setLastLoggedOut(Math.toIntExact(new Date().getTime() / 1000));
            user.setRole(ROLE.PALYER);
            UserGroup  playersUserGroup = createPlayersUserGroup();
            playersUserGroup.addToUser(user);
            UMLG.get().commit();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private UserGroup  createPlayersUserGroup  () {
        //This is should be move to the init methods
        for(UserGroup group :UserGroup.allInstances()){
            if (group.getName().equals("Players")){
                return group;
            }
        }
        UserGroup palyerGroup = new UserGroup();
        palyerGroup.setName("Players");
        return palyerGroup;
    }

}
