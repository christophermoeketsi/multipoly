package org.multipoly.restlet.modules.about;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.multipoly.commonutilties.MProperties;
import org.multipoly.restlet.app.BaseServerResource;
import org.multipoly.restlet.app.Resource;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.umlg.runtime.util.ObjectMapperFactory;
import org.umlg.sqlg.structure.SqlgDataSource;

/**
 * Date: 2015/07/23
 * Time: 11:58 AM
 */
@Resource("/about")
public class AboutServerResource extends BaseServerResource {

    public final static String PATH = "/about";
    private static Logger logger = LoggerFactory.getLogger(AboutServerResource.class);

    @Override
    protected Representation get() throws ResourceException {
        ObjectMapper objectMapper = ObjectMapperFactory.INSTANCE.getObjectMapper();
        ObjectNode result = new ObjectNode(objectMapper.getNodeFactory());
        String database = "Not Available";
        try{
            JSONArray sqlgProperties = new JSONArray(SqlgDataSource.INSTANCE.getPoolStatsAsJson());
            database = ((JSONObject)sqlgProperties.get(0)).getString("jdbcUrl");
            int lastIndexOf = database.lastIndexOf("/");
            database = database.substring(lastIndexOf+1, database.length());
        } catch (JSONException e) {
            database = "Not Available";
            logger.warn("Warning: " + e.getMessage());
        }
        result.put("version", MProperties.INSTANCE.getAboutVersion());
        result.put("database", database);
        result.put("support",MProperties.INSTANCE.getAboutSupport());
        return new JsonRepresentation(result.toString());
    }




}
