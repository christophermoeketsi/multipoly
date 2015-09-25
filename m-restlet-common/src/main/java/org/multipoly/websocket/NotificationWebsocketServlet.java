package org.multipoly.websocket;

/**
 * Date: 2014/04/04
 * Time: 6:50 PM
 */

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import java.util.logging.Logger;

public class NotificationWebsocketServlet extends WebSocketServlet {

    private static Logger logger = Logger.getLogger(NotificationWebsocketServlet.class.getPackage().getName());

    @Override
    public void configure(WebSocketServletFactory factory) {
        logger.fine("EtlServerWebsocketServlet start");
        factory.getPolicy().setIdleTimeout(60000);
        factory.setCreator(new WebsocketCreator());
    }

}
