package org.multipoly;

import org.umlg.generation.JavaGenerator;
import org.umlg.restlet.generation.RestletVisitors;

import java.io.File;
import java.net.URISyntaxException;

/**
 * Date: 2014/07/28
 * Time: 2:29 PM
 */
public class MGenerator {

    public static void main(String[] args) throws URISyntaxException {
        if (args.length == 0) {
            args = new String[]{"."};
        }
        JavaGenerator javaGenerator = new JavaGenerator();
        javaGenerator.generate(
                new File(args[0] + "/m-entity/src/main/model/model.uml"),
                new File(args[0] + "/m-entity"),
                new File(args[0] + "/m-entity-restlet"),

                RestletVisitors.getDefaultJavaVisitors());
    }
}
