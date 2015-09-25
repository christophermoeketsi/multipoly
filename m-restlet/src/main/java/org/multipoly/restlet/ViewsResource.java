package org.multipoly.restlet;

import org.multipoly.restlet.app.BaseServerResource;
import org.multipoly.restlet.app.MFreemarker;
import org.restlet.data.MediaType;
import org.restlet.ext.freemarker.TemplateRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;

/**
 * Date: 2013/04/18
 * Time: 10:28 AM
 */
public class ViewsResource extends BaseServerResource {

    public final static String PATH = "/views/{viewName}";

    public ViewsResource(){
        setNegotiated(false);
    }

    @Override
    protected Representation get()throws ResourceException {
        return new TemplateRepresentation("views/" + getAttribute("viewName") + ".html", MFreemarker.getConfiguration(), getDataModelWithUser(), MediaType.TEXT_HTML);
    }
}
