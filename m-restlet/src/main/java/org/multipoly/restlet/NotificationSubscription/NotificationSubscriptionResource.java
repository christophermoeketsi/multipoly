package org.multipoly.restlet.NotificationSubscription;

import com.rorotika.cm.admin.notification.Channel;
import com.rorotika.cm.admin.notification.NotificationSubscription;
import com.rorotika.cm.network.Network;
import com.rorotika.cm.restlet.app.BaseServerResource;
import freemarker.template.utility.StringUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.umlg.runtime.adaptor.UMLG;

import java.util.Set;

public class NotificationSubscriptionResource extends BaseServerResource {

    public final static String PATH = "/notificationSubscription/{id}";

    public NotificationSubscriptionResource() {
        setNegotiated(false);
    }

    public static JSONObject getNotificationSubscriptionAsJSON(NotificationSubscription item) {
        JSONObject result = new JSONObject();

        try {
            result.put("id", item.getId());
            result.put("name", item.getName());
            result.put("severity", StringUtil.capitalize(item.getNotificationSeverity().name()));

            String commandGroupChannels = "";

            for (Channel channel : item.getChannel()) {
                commandGroupChannels += channel.name();
            }

            result.put("channels", commandGroupChannels);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public static JSONArray getNotificationSubscriptionsAsJSON(Set<NotificationSubscription> items) {
        JSONArray result = new JSONArray();

        for (NotificationSubscription item : items)
            result.put(getNotificationSubscriptionAsJSON(item));

        return result;
    }

    @Override
    protected Representation get() throws ResourceException {
        String id = (String) getRequestAttributes().get("id");

        if (id.equals("all") || id.equals("")){
            JSONObject result = new JSONObject();

            try {
                result.put("data", getNotificationSubscriptionsAsJSON((Set<NotificationSubscription>) NotificationSubscription.allInstances()));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            return new JsonRepresentation(result);
        } else {
            return new JsonRepresentation(getNotificationSubscriptionAsJSON(UMLG.get().getEntity(id)));
        }
    }
}
