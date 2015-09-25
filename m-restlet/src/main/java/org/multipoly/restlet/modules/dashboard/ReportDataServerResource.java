package org.multipoly.restlet.modules.dashboard;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rorotika.cm.report.Report;
import com.rorotika.cm.report.SqlReportExecutor;
import com.rorotika.cm.report.RealizedReport;
import com.rorotika.cm.restlet.app.BaseServerResource;
import org.apache.commons.lang3.StringEscapeUtils;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ResourceException;
import org.umlg.runtime.util.ObjectMapperFactory;
import org.umlg.sqlg.structure.RecordId;
import org.umlg.sqlg.structure.SchemaTable;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

/**
 * Date: 2015/03/16
 * Time: 7:43 PM
 */
public class ReportDataServerResource extends BaseServerResource {

    public final static String PATH = "/dashboard/getDashboardReportData";

    public ReportDataServerResource() {
        setNegotiated(false);
    }

    @Override
    protected Representation post(Representation entity) throws ResourceException {
        try {
            String json = entity.getText();
            ObjectMapper mapper = ObjectMapperFactory.INSTANCE.getObjectMapper();
            Map<String, Object> data = mapper.readValue(json, Map.class);
            String clickedReportId = (String) data.get("clickedReportId");
            Integer clickedRowIndex = (Integer) data.get("clickedRowIndex");
            String parentOrChild = (String) data.get("parentOrChild");

            Report dashboardReport = new Report(getRecordIdFromStringId(clickedReportId));
            Report newDashboardReport = null;
            if(parentOrChild.equals("parent")){
                newDashboardReport = dashboardReport.getParentReport();
            } else if(parentOrChild.equals("child")) {
                newDashboardReport = dashboardReport.getChidlReport().any(
                        cr -> cr.getDashboardReportIndex().equals(clickedRowIndex) && cr.getIsDashboardReport()
                );
            } else if (parentOrChild.equals("this")) {
                newDashboardReport = dashboardReport;
            } else {
                throw new RuntimeException("F-idiot. It must be of type parent, child or this!");
            }
            StringBuilder sb = new StringBuilder();
            sb.append("{\"status\":\"1\",\"message\":\"Report data retrieved successfully\",");
            StringBuilder reports = new StringBuilder();
            reports.append("\"report\": [");
            if(newDashboardReport != null) {
                reports.append(getReportData(newDashboardReport));
            }
            reports.append("]");
            sb.append(reports.toString());
            sb.append("}");
            return new StringRepresentation(sb.toString());
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

    private RecordId getRecordIdFromStringId(String idString) {
        List<String> tableAndId = Arrays.asList(idString.split(":::"));
        List<String> schemaAndTable = Arrays.asList(tableAndId.get(0).split("\\."));
        Long id = Long.parseLong(tableAndId.get(1));
        return RecordId.from(SchemaTable.of(schemaAndTable.get(0), schemaAndTable.get(1)), id);
    }

}
