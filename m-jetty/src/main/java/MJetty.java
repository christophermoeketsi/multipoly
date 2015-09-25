import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.multipoly.commonutilties.MProperties;
import org.multipoly.commonutilties.MUtil;
import org.restlet.ext.servlet.ServerServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.io.File;

/**
 * Created by chris on 2015/09/23.
 */
public class MJetty {

    private static Logger logger = LoggerFactory.getLogger(MJetty.class);
    static {
        Runtime.getRuntime().addShutdownHook(new MJetty.ShutdownHook());
    }


    public static void main(String[] args) throws Exception {

        MUtil.setJettySystemProperties();
        File log4jPropertiesFile = new File("./m2-common/src/assembly/properties/log4j.properties");
        if (!log4jPropertiesFile.exists()) {
            log4jPropertiesFile = new File("./../m2-common/src/assembly/properties/log4j.properties");
        }
        logger.info(String.format("loading log4j.properties from %s", log4jPropertiesFile.getAbsolutePath()));
        System.setProperty("log4j.configuration", "file:" + log4jPropertiesFile.getAbsolutePath());
        System.setProperty("org.restlet.engine.loggerFacadeClass", "org.restlet.ext.slf4j.Slf4jLoggerFacade");

        Server server = new Server(MProperties.INSTANCE.getCmJettyPort());


        // Setup HTTP Configuration
        HttpConfiguration httpConf = new HttpConfiguration();
        httpConf.setSecurePort(MProperties.INSTANCE.getCmJettyPort());
        /*httpConf.setSecureScheme("https");
        httpConf.addCustomizer(new SecureRequestCustomizer());
*/
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        // Restlet servlet
        ServletHolder cmUmlgServletHolder = new ServletHolder(new ServerServlet());
        cmUmlgServletHolder.setName("org.umlg.cm.CmApplication");
        cmUmlgServletHolder.setInitParameter("org.restlet.application", "org.umlg.cm.CmApplication");
        cmUmlgServletHolder.setInitParameter("org.restlet.clients", "HTTP FILE CLAP");
        context.addServlet(cmUmlgServletHolder, "/cm/*");

        // Websocket servletpieter
        ServletHolder websocketServletHolder = new ServletHolder(new NotificationWebsocketServlet());
        websocketServletHolder.setName("Umlg WebSocket Servlet");
        context.addServlet(websocketServletHolder, "/netcm/broadcastWebsocket");


        ContextHandlerCollection contextHandlers = new ContextHandlerCollection();
        contextHandlers.setHandlers(new Handler[] {context});
    }




        static class ShutdownHook extends Thread {
        public ShutdownHook() {
            super();
        }

        public void run() {
            logger.debug("Shutting down notification guru");
            // NotificationGuru.shutdown();
        }
    }
}
