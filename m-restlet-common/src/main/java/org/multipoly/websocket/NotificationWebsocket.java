package org.multipoly.websocket;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.umlg.runtime.adaptor.UMLG;

/**
 * Date: 2014/04/04
 * Time: 6:32 PM
 */
public class NotificationWebsocket implements WebSocketListener {

    private static Logger logger = LoggerFactory.getLogger(NotificationWebsocket.class.getName());
    private Session outbound;
    private BaseNotificationWebsocketSessionManager baseNotificationWebsocketSessionManager;

    public NotificationWebsocket(BaseNotificationWebsocketSessionManager baseNotificationWebsocketSessionManager) {
        this.baseNotificationWebsocketSessionManager = baseNotificationWebsocketSessionManager;
    }

    @Override
    public void onWebSocketBinary(byte[] payload, int offset, int len) {
        logger.debug("EtlServerNotificationWebsocket.onWebSocketBinary received something!");
    }

    @Override
    public void onWebSocketClose(int statusCode, String reason) {
        logger.debug(String.format("EtlServerNotificationWebsocket.onWebSocketClose statusCode %d, reason %s", statusCode, reason));
        this.baseNotificationWebsocketSessionManager.removeSession(this.outbound);
        this.outbound = null;
    }

    @Override
    public void onWebSocketConnect(Session session) {
        logger.debug("EtlServerNotificationWebsocket.onWebSocketConnect");
        this.outbound = session;
        this.baseNotificationWebsocketSessionManager.addSession(
                session,
                session.getRemoteAddress().toString());
        UMLG.get().rollback();
    }

    @Override
    public void onWebSocketError(Throwable cause) {
        logger.error("RemoteIP: " + this.outbound.getRemoteAddress().toString() + " ; Reason: " + cause.getMessage());
    }

    @Override
    public void onWebSocketText(String message) {
        if ((outbound != null) && (outbound.isOpen())) {
            logger.debug(String.format("EtlServerNotificationWebsocket.onWebSocketText %s Echoing back message", message));
            outbound.getRemote().sendString(message, null);
        }
    }

}
