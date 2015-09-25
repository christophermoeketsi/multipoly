package org.multipoly.restlet.user;


import org.json.JSONException;
import org.json.JSONObject;
import org.multipoly.restlet.app.Auth;
import org.multipoly.restlet.app.Resource;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.EmptyRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.umlg.runtime.adaptor.UMLG;
import org.umlg.runtime.types.Password;

@Resource("/user/{id}/changePassword")
public class UserChangePasswordResource extends BaseServerResource {

    @Post
    @Auth({ROLE.ADMIN})
    public Representation post (JsonRepresentation rep) throws JSONException {
        JSONObject postData = rep.getJsonObject();

        String password = postData.getString("password");
        String username = postData.getString("username");
        String userId = getAttribute("id");

        ((User) UMLG.get().getEntity(userId))
                .setPassword(new Password(password));

        getRealm().findUser(username).setPassword(new Password(password));

        UMLG.get().commit();

        return new EmptyRepresentation();
    }
}
