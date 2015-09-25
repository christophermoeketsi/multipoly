package org.multipoly.commonutilties;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by chris on 2015/09/23.
 */
public class MUtil {

    public static void setJettySystemProperties() {
        if (StringUtils.isEmpty(System.getProperty("etlserver.host"))) {
            System.setProperty("etlserver.host", "localhost");
        }
        if (StringUtils.isEmpty(System.getProperty("etlserver.port"))) {
            System.setProperty("etlserver.port", "8081");
        }
    }

    public static void setEtlServerSystemProperties() {
        if (StringUtils.isEmpty(System.getProperty("cmjetty.host"))) {
            System.setProperty("mjetty.host", "localhost");
        }
        if (StringUtils.isEmpty(System.getProperty("cmjetty.port"))) {
            System.setProperty("mjetty.port", "8080");
        }
    }

    public static String getStackTrace(Throwable aThrowable) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        aThrowable.printStackTrace(printWriter);
        return result.toString();
    }

    public static boolean isValidJSON(final String json) {
        boolean valid = false;
        try {
            final JsonParser parser = new ObjectMapper().getFactory().createParser(json);
            while (parser.nextToken() != null) {
            }
            valid = true;
        } catch (JsonParseException jpe) {
        } catch (IOException ioe) {
        }
        return valid;
    }
}
