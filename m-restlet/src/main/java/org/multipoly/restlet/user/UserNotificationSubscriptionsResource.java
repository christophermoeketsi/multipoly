package org.multipoly.restlet.user;

import com.rorotika.cm.admin.notification.Channel;
import com.rorotika.cm.admin.notification.NotificationSubscription;
import org.multipoly.restlet.NotificationSubscription.NotificationSubscriptionResource;
import com.rorotika.cm.restlet.app.BaseServerResource;
import com.rorotika.cm.user.User;
import com.rorotika.cm.user.UserGroup;
import freemarker.template.utility.StringUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.umlg.runtime.adaptor.UMLG;

import java.util.HashSet;
import java.util.Set;

/**
 * Date: 2014/10/15
 * Time: 1:42 PM
 */
public class UserNotificationSubscriptionsResource extends BaseServerResource {

    public final static String PATH = "/user/{id}/notificationSubscriptions";

    public UserNotificationSubscriptionsResource() {
        setNegotiated(false);
    }

    @Override
    protected Representation get() throws ResourceException {
        String userId = (String) getRequestAttributes().get("id");

        JSONObject result = new JSONObject();
        JSONArray columns = new JSONArray();

        User user = UMLG.get().getEntity(userId);

        try {
            Set<NotificationSubscription> allNotificationSubscriptionsForUser = new HashSet<>();

            for (UserGroup userGroup : user.getUserGroup())
                allNotificationSubscriptionsForUser.addAll(userGroup.getNotificationSubscription());

            JSONArray notificationSubscriptionsJSON = NotificationSubscriptionResource.getNotificationSubscriptionsAsJSON(allNotificationSubscriptionsForUser);
            result.put("data", notificationSubscriptionsJSON);

            JSONObject nameColumn = new JSONObject();
            nameColumn.put("id", "network");
            nameColumn.put("field", "name");
            nameColumn.put("name", "Network");
            columns.put(nameColumn);

            JSONObject severityColumn = new JSONObject();
            severityColumn.put("id", "severity");
            severityColumn.put("field", "severity");
            severityColumn.put("name", "Severity");
            columns.put(severityColumn);

            JSONObject channelsColumn = new JSONObject();
            channelsColumn.put("id", "channels");
            channelsColumn.put("field", "channels");
            channelsColumn.put("name", "Channels");
            columns.put(channelsColumn);

            result.put("columns", columns);

            return new JsonRepresentation(result);
        } catch (JSONException e) {
            throw new RuntimeException("Error receiving notification subscriptions for user '" + user.getUsername() + "'");
        }
    }
}
