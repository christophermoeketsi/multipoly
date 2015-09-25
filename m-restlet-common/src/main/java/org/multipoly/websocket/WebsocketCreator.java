package org.multipoly.websocket;

import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;
import org.multipoly.restlet.BaseMApplication;

import java.lang.reflect.Field;

/**
 * Date: 2015/04/07
 * Time: 7:23 PM
 */
public class WebsocketCreator implements WebSocketCreator {

    @Override
    public Object createWebSocket(ServletUpgradeRequest req, ServletUpgradeResponse resp) {
        try {
            Class c = Class.forName("com.rorotika.cm.restlet.EtlServerRestletApplication");
            Field f = c.getDeclaredField("INSTANCE");
            BaseMApplication baseMApplication = (BaseMApplication) f.get(null);
            if (NotificationWebsocketSessionManager.INSTANCE == null) {
                new NotificationWebsocketSessionManager(baseMApplication.getRealm(), 100);
            }
            return new NotificationWebsocket(NotificationWebsocketSessionManager.INSTANCE);
        } catch (ClassNotFoundException e) {
            try {
                Class c = Class.forName("com.rorotika.cm.restlet.app.CmRestletApplication");
                Field f = c.getDeclaredField("INSTANCE");
                BaseMApplication baseMApplication = (BaseMApplication) f.get(null);
                if (NotificationWebsocketSessionManager.INSTANCE == null) {
                    new NotificationWebsocketSessionManager(baseMApplication.getRealm(), 10);
                }
                return new NotificationWebsocket(NotificationWebsocketSessionManager.INSTANCE);
            } catch (Exception ex) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
