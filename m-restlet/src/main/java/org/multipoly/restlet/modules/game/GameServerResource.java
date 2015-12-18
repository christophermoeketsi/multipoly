package org.multipoly.restlet.modules.game;

import org.multipoly.restlet.app.BaseServerResource;
import org.multipoly.restlet.app.MFreemarker;
import org.restlet.data.MediaType;
import org.restlet.ext.freemarker.TemplateRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;

/**
 * Created by chris on 2015/12/18.
 */
public class GameServerResource extends BaseServerResource {
    public static String PATH  = "/gameView";

    public GameServerResource  (){
        setNegotiated(true);
    }

    @Override
    protected Representation get() throws ResourceException {
        try {
            return new TemplateRepresentation("/pages/gameView.html", MFreemarker.getConfiguration(), MediaType.TEXT_HTML);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected Representation post(Representation entity) {
        try {
            return new TemplateRepresentation("/pages/gameView.html", MFreemarker.getConfiguration(), MediaType.TEXT_HTML);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
