package org.multipoly.restlet.user;

import org.multipoly.restlet.app.Resource;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

@Resource("/myUser") // specify path for resource here
public class MyUserResource extends ServerResource {

    @Get
    public Representation getMyUser() throws JSONException {
        return new JsonRepresentation(
                new JSONObject() // JSONObject is chainable
                        .put("id", getClientInfo().getUser().getIdentifier())
                        .put("username", getClientInfo().getUser().getName())
                        .put("roles", new JSONArray(getClientInfo().getRoles()))
        );
    }
}

