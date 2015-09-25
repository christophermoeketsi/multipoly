package org.multipoly.restlet;

import org.multipoly.restlet.app.BaseServerResource;
import org.multipoly.restlet.app.MFreemarker;
import org.restlet.data.MediaType;
import org.restlet.ext.freemarker.TemplateRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IndexResource extends BaseServerResource {

    public final static String PATH = "";

    public IndexResource(){
        setNegotiated(false);
    }

    private List<String> getFiles (String directory) {
        List<String> result = new ArrayList<>();

        String pathName = "./../cm-web/src/main/resources/";
        File file = new File("./../cm-web/src/main/resources/" + directory);

        if (!file.exists()) {
            pathName = "./cm-web/src/main/resources/";
            file = new File("./cm-web/src/main/resources/" + directory);
        }

        File[] files = file.listFiles();

        for (int i = 0; i < files.length; i++)
            if (files[i].isFile() && files[i].getName().endsWith(".js")) {
                String relativePath = files[i].getPath().substring(files[i].getPath().indexOf(pathName) + pathName.length());
                result.add(relativePath);
            }

        return result;
    }

    @Override
    protected Representation get()throws ResourceException {
        Map<String, Object> dataModelWithUser = getDataModelWithUser();

        dataModelWithUser.put("directives", getFiles("js/directives"));
        dataModelWithUser.put("services", getFiles("js/services"));
        dataModelWithUser.put("controllers", getFiles("js/controllers"));

        return new TemplateRepresentation("index.ftl", MFreemarker.getConfiguration(), dataModelWithUser, MediaType.TEXT_HTML);
    }
}
