package org.multipoly.restlet.activityAudit;

import com.rorotika.cm.activity.ActivityRecord;
import org.multipoly.restlet.app.Resource;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.umlg.runtime.adaptor.UMLG;

@Resource("/activityReport/output/{id}")
public class ActivityRecordOutputResource extends ServerResource {

    @Get
    public Representation getOutput(JsonRepresentation entity) throws JSONException {
        ActivityRecord activityRecord = UMLG.get().getEntity(getAttribute("id"));

        return new JsonRepresentation(
                new JSONObject().put("output", activityRecord.getOutput())
        );
    }
}

