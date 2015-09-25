package org.multipoly.restlet.activityAudit;

import com.rorotika.cm.activity.ActivityInstance;
import com.rorotika.cm.activity.ActivityRecord;
import com.rorotika.cm.admin.CmUserThreadVar;
import org.multipoly.restlet.app.Resource;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.umlg.runtime.adaptor.UMLG;
import org.umlg.runtime.collection.UmlgOrderedSet;
import org.umlg.runtime.collection.UmlgSet;

@Resource("/activityAudit/activityInstance/{id}")
public class ActivityInstanceResource extends ServerResource {

    private JSONObject getActivityInstanceAsJson(ActivityInstance activityInstance) throws JSONException {
        return new JSONObject()
                .put("id", activityInstance.getId().toString())
                .put("createdBy", activityInstance.getCreatedBy().getUsername())
                .put("title", activityInstance.getTitle())
                .put("activityRecords", getActivityRecordsAsJson(activityInstance));
    }

    private JSONArray getActivityRecordsAsJson(ActivityInstance activityInstance) throws JSONException {
        JSONArray result = new JSONArray();

        UmlgOrderedSet<ActivityRecord> activityRecords = activityInstance.getActivityRecord().sortedBy((o1, o2) -> o1.getNodeName().compareTo(o2.getNodeName()));

        for (ActivityRecord activityRecord : activityRecords) {
            result.put(getActivityRecordAsJson(activityRecord));
        }

        return result;
    }

    public static JSONObject getActivityRecordAsJson(ActivityRecord activityRecord) throws JSONException {
        return new JSONObject()
                    .put("id", activityRecord.getId())
                    .put("nodeName", activityRecord.getNodeName())
                    .put("executedBy", activityRecord.getExecutedBy() == null ? "" : activityRecord.getExecutedBy().getUsername())
                    .put("createdBy", activityRecord.getActivityInstance().getCreatedBy().getUsername())
                    .put("mmlCommand", activityRecord.getMmlCommand())
                    .put("xmlCommand", activityRecord.getXmlCommand())
                    .put("executedOn", activityRecord.getExecutedOn())
                    .put("executed", activityRecord.getExecuted())
                    .put("output", activityRecord.getOutput())
                    .put("hasMml", activityRecord.getMmlCommand() != null)
                    .put("hasXml", activityRecord.getXmlCommand() != null)
                    .put("title", activityRecord.getNodeName());
    }

    @Get
    public Representation getActivityInstance() throws JSONException {
        String id = getAttribute("id");

        if (id.equals("me") || id.equals("other")) {
            UmlgSet<? extends ActivityInstance> activityInstances = ActivityInstance.allInstances();
            UmlgSet<? extends ActivityInstance> filteredByUser;

            if (id.equals("me"))
                filteredByUser = activityInstances.select(ai -> ai.getCreatedBy().equals(CmUserThreadVar.INSTANCE.get()));
            else
                filteredByUser = activityInstances.select(ai -> !ai.getCreatedBy().equals(CmUserThreadVar.INSTANCE.get()));

            JSONArray result = new JSONArray();

            for (ActivityInstance userActivityInstance : filteredByUser.sortedBy((o1, o2) -> o1.getTitle().compareTo(o2.getTitle())))
                result.put(getActivityInstanceAsJson(userActivityInstance));

            return new JsonRepresentation(result);
        } else {
            return new JsonRepresentation(getActivityInstanceAsJson(UMLG.get().getEntity(id)));
        }
    }
}

