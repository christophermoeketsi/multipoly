package org.multipoly.restlet;

/**
 * Date: 2013/02/22
 * Time: 10:12 AM
 */
public class RestletClientValidator {

    public static void validateEtlServerHostAndPort() {
        if (System.getProperty("etlserver.host") == null || System.getProperty("etlserver.port") == null) {
            throw new RuntimeException("The system property etlserver.host and etlserver.port is not set!");
        }
    }

    public static void validateCmJettyHostAndPort() {
        if (System.getProperty("cmjetty.host") == null || System.getProperty("cmjetty.port") == null) {
            throw new RuntimeException("The system property cmjetty.host and cmjetty.port is not set!");
        }
    }

}
