package org.multipoly.restlet.sync;

/**
 * Date: 1/8/13
 * Time: 12:46 PM
 */
public class CmJettyClient {

    public static final CmJettyClient INSTANCE = new CmJettyClient();

    private CmJettyClient() {

    }

    public String getVirtualHost() {
        return "/netcm";
    }

    public String getServer() {
        return "https://" + System.getProperty("cmjetty.host") + ":" + System.getProperty("cmjetty.port") + "/";
    }

}
