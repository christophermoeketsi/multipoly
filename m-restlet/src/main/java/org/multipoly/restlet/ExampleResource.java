package org.multipoly.restlet;

import org.multipoly.restlet.app.Auth;
import org.multipoly.restlet.app.Resource;
import com.rorotika.cm.user.ROLE;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

@Resource("/bla/{id}") // specify path for resource here
public class ExampleResource extends ServerResource {

    @Post
    // specify authentication, if not specified then anybody can access the resource - or logic is used between modules specified
    @Auth({ROLE.ADMIN, ROLE.POLICY})
    public Representation post(JsonRepresentation entity) throws JSONException {
        JSONObject user = entity.getJsonObject(); // get body of json request by changing post param to JsonRepresentation

        // getAttribute("id"); // get id path paramter
        // getQueryValue("some?value") // get some query value

        int dummy = 1;

        if (dummy == 0) {
            throw new RuntimeException("This message will be displayed on the front end by default.");
        }

        return new JsonRepresentation(
                new JSONObject() // JSONObject is chainable
                        .put("column", "")
                        .put("", "")
        );
    }
}

