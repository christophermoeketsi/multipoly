package org.multipoly.restlet;

import com.rorotika.cm.restlet.app.BaseServerResource;
import com.rorotika.cm.restlet.app.CMFreemarker;
import org.restlet.data.MediaType;
import org.restlet.ext.freemarker.TemplateRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;

/**
 * Date: 2013/04/18
 * Time: 10:28 AM
 */
public class ViewTemplateResource extends BaseServerResource {

    public final static String PATH = "/views/templates/{viewName}";

    public ViewTemplateResource(){
        setNegotiated(false);
    }

    @Override
    protected Representation get()throws ResourceException {
        return new TemplateRepresentation("views/templates/" + getAttribute("viewName") + ".html", CMFreemarker.getConfiguration(), getDataModelWithUser(), MediaType.TEXT_HTML);
    }
}
