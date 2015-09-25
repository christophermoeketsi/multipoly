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

@Resource("/schema/{schemaName}")
public class SchemaResource extends ServerResource {

    @Get
    public Representation getSchema() {
        List<String> result = new ArrayList<>();

        try {
            SqlgGraph sqlG = UMLG.get().getUnderlyingGraph();
            ResultSet schemas = sqlG.tx().getConnection().getMetaData().getSchemas();

            String schemaName = getAttribute("schemaName");

            if (schemaName.equals("all")) {
                while (schemas.next()) {
                    String tableSchema = schemas.getString(1);
                    result.add(tableSchema);
                }

                return new JsonRepresentation(new JSONArray(result));
            } else {
                String[] tableType = {"TABLE"};

                ResultSet tables = sqlG.tx().getConnection().getMetaData().getTables(null, schemaName, null, tableType);

                while(tables.next()) {
                    String tableName = tables.getString(3);
                    result.add(tableName);
                }

                return new JsonRepresentation(new JSONArray(result));
            }
        } catch (Exception e) {
            return new JsonRepresentation(new JSONArray());
        }
    }
}

