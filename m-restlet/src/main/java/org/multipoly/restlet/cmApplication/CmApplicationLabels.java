package org.multipoly.restlet.cmApplication;

import com.rorotika.cm.admin.CmApplication;
import org.multipoly.restlet.app.Resource;
import com.rorotika.cm.user.Label;
import com.rorotika.cm.user.LabelType;
import com.rorotika.cm.user.PolicyLabel;
import com.rorotika.cm.user.ReportLabel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import org.umlg.runtime.adaptor.UMLG;

@Resource("/cmapplication/labels/{labelType}")
public class CmApplicationLabels extends ServerResource {

    @Put
    public Representation put(JsonRepresentation entity) throws JSONException {
        JSONObject requestBody = entity.getJsonObject();

        Label labelToAddOrEdit;

        if (requestBody.isNull("editId")) {
            if (getAttribute("labelType").equals("report")) {
                labelToAddOrEdit = new ReportLabel();
                labelToAddOrEdit.setLabelType(LabelType.REPORT);
            } else {
                labelToAddOrEdit = new PolicyLabel();
                labelToAddOrEdit.setLabelType(LabelType.POLICY);
            }
        } else {
            labelToAddOrEdit = UMLG.get().getEntity(requestBody.getString("editId"));
        }

        labelToAddOrEdit.setName(requestBody.getString("name"));

        CmApplication.allInstances().any(c -> true).getLabel().add(labelToAddOrEdit);

        UMLG.get().commit();

        return new JsonRepresentation(new JSONObject()
            .put("success", true)
            .put("id", labelToAddOrEdit.getId().toString())
            .put("name", labelToAddOrEdit.getName()));
    }

    @Get
    public Representation get() {
        LabelType labelType = LabelType.valueOf(getAttribute("labelType").toUpperCase());

        JSONArray result = new JSONArray();

        CmApplication.allInstances().any(c -> true).getLabel()
                .select(l -> l.getLabelType().equals(labelType))
                .forEach(l -> {
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("id", l.getId());
                        jsonObject.put("name", l.getName());

                        result.put(jsonObject);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                });

        return new JsonRepresentation(result);
    }
}

