package org.multipoly.restlet.cmApplication;

import org.multipoly.restlet.app.Resource;
import com.rorotika.cm.user.Label;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.ServerResource;
import org.umlg.runtime.adaptor.UMLG;

@Resource("/cmapplication/labels/{labelType}/{id}")
public class CmApplicationLabelsDelete extends ServerResource {

    @Delete
    public Representation deleteLabel () throws JSONException {
        Label labelToDelete = UMLG.get().getEntity(getAttribute("id"));

        labelToDelete.delete();

        UMLG.get().commit();

        return new JsonRepresentation(new JSONObject()
            .put("success", true));
    }
}

