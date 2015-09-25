package org.multipoly.restlet.user;

import com.rorotika.cm.admin.CmUserThreadVar;
import com.rorotika.cm.report.Report;
import com.rorotika.cm.restlet.app.BaseServerResource;
import com.rorotika.cm.user.User;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.umlg.runtime.adaptor.UMLG;
import org.umlg.runtime.collection.UmlgSet;
import org.umlg.runtime.domain.json.ToJsonUtil;

/**
 * Date: 2014/09/02
 * Time: 11:46 AM
 */
public class FavouriteReportsResource extends BaseServerResource {

    public final static String PATH = "/favourites/report/{id}";

    public FavouriteReportsResource() { setNegotiated(false); }

    @Override
    protected Representation delete() throws ResourceException {
        User user = CmUserThreadVar.INSTANCE.get();

        String reportId = (String) getRequestAttributes().get("id");
        Report report = UMLG.get().getEntity(reportId);

        report.removeFromReportLabel(user.getFavouriteReportLabel());
        UMLG.get().commit();

        return new JsonRepresentation("{ \"success\": true }");
    }

    @Override
    protected Representation post(Representation entity) throws ResourceException {
        User user = CmUserThreadVar.INSTANCE.get();

        String reportId = (String) getRequestAttributes().get("id");
        Report report = UMLG.get().getEntity(reportId);

        report.addToReportLabel(user.getFavouriteReportLabel());
        UMLG.get().commit();

        return new JsonRepresentation("{ \"success\": true }");
    }

    @Override
    protected Representation get() throws ResourceException {
        User user = CmUserThreadVar.INSTANCE.get();

        StringBuilder json = new StringBuilder();

        UmlgSet<Report> resource = user.getFavouriteReports();

        json.append("[");
        json.append(ToJsonUtil.toJsonWithoutCompositeParent(resource));
        json.append("]");

        return new JsonRepresentation(json.toString());
    }
}