import org.eclipse.jetty.server.*;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.multipoly.admin.Inicialise;
import org.multipoly.commonutilties.MProperties;
import org.multipoly.commonutilties.MUtil;
import org.multipoly.restlet.app.MRestletApplication;
import org.restlet.ext.servlet.ServerServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.umlg.runtime.adaptor.UMLG;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by chris on 2015/09/23.
 */
public class MJetty {

    private static Logger logger = LoggerFactory.getLogger(MJetty.class);

    static {
        Runtime.getRuntime().addShutdownHook(new MJetty.ShutdownHook());
    }


    public static void main(String[] args) throws Exception {
        showClassesInCLASSPATH();
        MUtil.setJettySystemProperties();
        File log4jPropertiesFile = new File("./m-common/src/assembly/properties/log4j.properties");
        if (!log4jPropertiesFile.exists()) {
            log4jPropertiesFile = new File("./../m-common/src/assembly/properties/log4j.properties");
        }
        logger.info(String.format("loading log4j.properties from %s", log4jPropertiesFile.getAbsolutePath()));
        System.setProperty("log4j.configuration", "file:" + log4jPropertiesFile.getAbsolutePath());

        Server server = new Server(MProperties.INSTANCE.getCmJettyPort());

        // Setup HTTP Configuration
        HttpConfiguration httpConf = new HttpConfiguration();
        httpConf.setSecurePort(MProperties.INSTANCE.getCmJettyPort());
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        // Restlet servlet
        ServletHolder servletHolder = new ServletHolder(new ServerServlet());
        servletHolder.setName("MultipolyApplication");
        servletHolder.setInitParameter("org.restlet.application",MRestletApplication.class.getName());//This makes sure that the class is loaded!!!
        servletHolder.setInitParameter("org.restlet.clients", "HTTP FILE CLAP");
        context.addServlet(servletHolder, "/m/*");

        // Websocket servletpieter

        //I should come back to this
/*        ServletHolder websocketServletHolder = new ServletHolder(new NotificationWebsocketServlet());
        websocketServletHolder.setName("Umlg WebSocket Servlet");
        context.addServlet(websocketServletHolder, "/m/broadcastWebsocket");*/

        ContextHandlerCollection contextHandlers = new ContextHandlerCollection();
        contextHandlers.setHandlers(new Handler[]{context});

        ServerConnector serverConnector = new ServerConnector(server, new HttpConnectionFactory(httpConf)); // <-- use it!
        serverConnector.setPort(MProperties.INSTANCE.getCmJettyPort());

        server.setConnectors(new Connector[]
                {serverConnector});
        server.setHandler(contextHandlers);
        setUpStart();
        server.start();
        server.join();
    }


    private static void setUpStart() {
        Inicialise.main();
        UMLG.get().commit();
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

    private static void showClassesInCLASSPATH(){
        //This is the show the classes that are in the class path
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        URL[] urls = ((URLClassLoader)cl).getURLs();
        for(URL url: urls){
            System.out.println(url.getFile());
        }
    }
}
