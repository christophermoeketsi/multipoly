package org.multipoly.restlet.activityAudit;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rorotika.cm.activity.ActivityRecord;
import com.rorotika.cm.admin.CmUserThreadVar;
import com.rorotika.cm.network.node.NodeRipperGuru;
import com.rorotika.cm.network.node.RipToFileGuru;
import com.rorotika.cm.network.node.connection.EisApplicationPool;
import com.rorotika.cm.network.node.connection.NodeConnectionPool;
import com.rorotika.cm.network.node.connection.PrintIndexedRecord;
import com.rorotika.cm.nodedefinition.NetworkNode;
import org.multipoly.restlet.app.Resource;
import com.rorotika.cm.restlet.modules.nodeconnector.NodeConnectorRunCommandResource;
import com.rorotika.cm.user.ROLE;
import com.rorotika.cm.user.User;
import org.apache.tinkerpop.gremlin.process.traversal.Compare;
import org.joda.time.DateTime;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.engine.util.Base64;
import org.restlet.ext.crypto.internal.CryptoUtils;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.umlg.runtime.adaptor.UMLG;
import org.umlg.runtime.util.ObjectMapperFactory;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Map;

@Resource("/activityAudit/activityRecord/{id}")
public class ActivityRecordResource extends ServerResource {

    @Get
    public Representation getActivityRecord() throws JSONException {
        ActivityRecord activityRecord = UMLG.get().getEntity(getAttribute("id"));
        return new JsonRepresentation(ActivityInstanceResource.getActivityRecordAsJson(activityRecord));
    }

    @Post
    public Representation post(JsonRepresentation representation) throws JSONException, GeneralSecurityException, IOException {
        JSONObject postData = representation.getJsonObject();
        JSONObject result = new JSONObject();

        ActivityRecord activityRecordExecuted = UMLG.get().getEntity(postData.getString("activityRecordId"));
        if (activityRecordExecuted != null && activityRecordExecuted.getExecuted()) {
            result.put("success", false);
            result.put("failureCode", 0);
            result.put("message", "Activity record already executed.");
            return new JsonRepresentation(result);
        }

        boolean activityCreatedByExecutingUser = CmUserThreadVar.INSTANCE.get().getUsername().equals(activityRecordExecuted.getActivityInstance().getCreatedBy().getUsername());
        boolean userHasSuperWorkspacePermission = CmUserThreadVar.INSTANCE.get().getRoles().contains(ROLE.WORKSPACE_SUPERUSER);

        if (!activityCreatedByExecutingUser && !userHasSuperWorkspacePermission) {
            result.put("success", false);
            result.put("failureCode", 2);
            result.put("message", "Not permitted to execute MML created by another user.");
            return new JsonRepresentation(result);
        }

        String nodeName = postData.getString("networkNodeName");
        NetworkNode networkNode = NetworkNode.networkNode_findByName(nodeName);

        if (networkNode == null) {
            result.put("success", false);
            result.put("failureCode", 1);
            result.put("message", "Node named \"" + nodeName +  "\" has not been configured yet.");
            return new JsonRepresentation(result);
        }

        NodeRipperGuru nodeRipperGuru = new NodeRipperGuru();
        User user = CmUserThreadVar.INSTANCE.get();

        ObjectMapper mapper = ObjectMapperFactory.INSTANCE.getObjectMapper();
        String decryptedExecutorCredentials = CryptoUtils.decrypt("AES", NodeConnectorRunCommandResource.ENCRYPTION_KEY, Base64.decode(postData.getString("executorCredentials")));
        Map<String, String> executorCredentials = mapper.readValue(decryptedExecutorCredentials, Map.class);

        NodeConnectionPool nodeConnectionPool = EisApplicationPool.INSTANCE.getPoolForSession(executorCredentials.get("username"), executorCredentials.get("password"), networkNode.getVendorTechnology());
        PrintIndexedRecord printIndexedRecord = nodeRipperGuru.executeForUser(nodeConnectionPool, user, networkNode, postData.getString("command"));
        String output = RipToFileGuru.formatResult(printIndexedRecord, new Date()).toString();

        // If all is successful then conclude the activityRecord
        ActivityRecord activityRecord = UMLG.get().getEntity(getAttribute("id"));
        activityRecord.setExecutedBy(user);
        activityRecord.setExecuted(true);
        activityRecord.setExecutedOn(new DateTime());
        activityRecord.setOutput(output);

        result.put("data", output);
        result.put("executedOn", new DateTime());
        result.put("success", true);

        UMLG.get().commit();

        return new JsonRepresentation(result);
    }
}
