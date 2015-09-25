package org.multipoly.restlet.app;

import freemarker.template.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * Date: 2013/04/23
 * Time: 1:39 PM
 */
public class MFreemarker {

    private static Configuration configuration;
    private final Logger logger;

    public MFreemarker() {
        this.logger = LoggerFactory.getLogger(MFreemarker.class);;
        this.configuration = new Configuration();
        try {
            File file = new File("./../m-web/src/main/resources/");
            if (file.exists()) {
                this.configuration.setDirectoryForTemplateLoading(file);
                logger.info(String.format("loading freemarker resources from %s", new String[]{file.getAbsolutePath()}));
            } else {
                file = new File("./m-web/src/main/resources/");
                if (file.exists()) {
                    this.configuration.setDirectoryForTemplateLoading(file);
                    logger.info(String.format("loading freemarker resources from %s", new String[]{file.getAbsolutePath()}));
                } else {
                    throw new IllegalStateException("Can not find the web resources");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Configuration getConfiguration() {
        return configuration;
    }
}
