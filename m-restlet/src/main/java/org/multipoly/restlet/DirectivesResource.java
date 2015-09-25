package org.multipoly.restlet;

import com.rorotika.cm.restlet.app.CMFreemarker;
import com.rorotika.cm.restlet.app.BaseServerResource;
import org.multipoly.restlet.app.BaseServerResource;
import org.restlet.data.MediaType;
import org.restlet.ext.freemarker.TemplateRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;

/**
 * Date: 2013/04/18
 * Time: 10:28 AM
 */
public class DirectivesResource extends BaseServerResource {

    public final static String PATH = "/directives/{viewName}";

    public DirectivesResource(){
        setNegotiated(false);
    }

    @Override
    protected Representation get()throws ResourceException {
        return new TemplateRepresentation("directives/" + getAttribute("viewName") + ".html", CMFreemarker.getConfiguration(), getDataModelWithUser(), MediaType.TEXT_HTML);
    }
}
