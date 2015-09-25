package org.multipoly.restlet.schema;

import org.multipoly.restlet.app.Resource;
import org.json.JSONArray;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.umlg.runtime.adaptor.UMLG;
import org.umlg.sqlg.structure.SqlgGraph;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Resource("/columnMetaData/{schema}/{table}")
public class ColumnMetaDataResource extends ServerResource {

    @Get
    public Representation getSchema() {
        List<String> result = new ArrayList<>();

        try {
            SqlgGraph sqlG = UMLG.get().getUnderlyingGraph();

            String schema = getAttribute("schema");
            String table = getAttribute("table");

            ResultSet columns = sqlG.tx().getConnection().getMetaData().getColumns(null, schema, table, null);

            while (columns.next()) {
                String tableSchema = columns.getString("COLUMN_NAME");
                result.add(tableSchema);
            }

            return new JsonRepresentation(new JSONArray(result));

        } catch (Exception e) {
            return new JsonRepresentation(new JSONArray());
        }
    }
}

