package org.multipoly.restlet.modules;

import org.multipoly.restlet.app.BaseServerResource;
import org.multipoly.restlet.app.MFreemarker;
import org.restlet.data.MediaType;
import org.restlet.ext.freemarker.TemplateRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;

/**
 * Created by chris on 2015/12/18.
 */
public class LandingPageServerResource extends BaseServerResource {
    public static String PATH  = "/landingPage";

    public  LandingPageServerResource (){
        setNegotiated(true);
    }


    @Override
    protected Representation get() throws ResourceException {
        try {
            return new TemplateRepresentation("/pages/landingPage.html", MFreemarker.getConfiguration(), MediaType.TEXT_HTML);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected Representation post(Representation entity) {
        try {
            return new TemplateRepresentation("/pages/landingPage.html", MFreemarker.getConfiguration(), MediaType.TEXT_HTML);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
