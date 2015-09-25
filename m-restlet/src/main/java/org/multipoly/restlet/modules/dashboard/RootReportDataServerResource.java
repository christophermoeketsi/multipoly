package org.multipoly.restlet.modules.dashboard;

import com.rorotika.cm.report.Report;
import com.rorotika.cm.report.SqlReportExecutor;
import com.rorotika.cm.report.RealizedReport;
import com.rorotika.cm.restlet.app.BaseServerResource;
import org.apache.commons.lang3.StringEscapeUtils;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

/**
 * Date: 2015/03/13
 * Time: 10:24 AM
 */
public class RootReportDataServerResource extends BaseServerResource {

    public final static String PATH = "/dashboard/getRootReportData";

    public RootReportDataServerResource() {
        setNegotiated(false);
    }

    @Override
    protected Representation post(Representation entity) throws ResourceException {
        try {
            StringBuilder sb = new StringBuilder();
            Set<Report> rootDashboardReports = new HashSet<>();
            for (Report dashboardReport : Report.allInstances()) {
                if(dashboardReport.getParentReport() == null && dashboardReport.getIsDashboardReport()){
                    rootDashboardReports.add(dashboardReport);
                }
            }
            sb.append("{\"status\":\"1\",\"message\":\"Report data retrieved successfully\",");
            StringBuilder reports = new StringBuilder();
            reports.append("\"reports\": [");
            int reportCounter = 0;
            for (Report dashboardReport : rootDashboardReports) {
                reports.append(getReportData(dashboardReport));
                if(++reportCounter < rootDashboardReports.size()){
                    reports.append(",");
                }
            }
            reports.append("]");
            sb.append(reports.toString());
            sb.append("}");
            System.out.println(sb.toString());
            return new JsonRepresentation(sb.toString());
        } catch (Exception e) {
            getLogger().log(Level.WARNING, e.getMessage(), e);
            return new JsonRepresentation("{\"status\":\"0\",\"message\":\"" + StringEscapeUtils.escapeJson(e.getMessage()) + "\"}");
        }
    }

    private String getReportData(Report report) {
        SqlReportExecutor sqlReportExecutor = new SqlReportExecutor();
        RealizedReport realizedReport = sqlReportExecutor.executeReport(report);
        return realizedReport.toJson();
    }
}