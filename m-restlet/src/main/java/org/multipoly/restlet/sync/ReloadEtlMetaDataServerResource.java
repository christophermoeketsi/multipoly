package org.multipoly.restlet.sync;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rorotika.cm.etl.meta.EtlDirectoryStructure;
import com.rorotika.cm.etl.meta.EtlMetaData;
import com.rorotika.cm.network.NetworkSoftwareVersion;
import com.rorotika.cm.network.SOFTWARE_VERSION;
import com.rorotika.cm.restlet.app.BaseServerResource;
import com.rorotika.cm.workspace.WorkspaceEnum;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.umlg.runtime.adaptor.UMLG;
import org.umlg.runtime.util.ObjectMapperFactory;

import java.io.IOException;
import java.util.Map;

/**
 * This resource is called after the etlserver parser has run.
 * It reloads the EtlMetaData for some software version
 * Date: 2014/11/05
 * Time: 7:38 AM
 */
public class ReloadEtlMetaDataServerResource extends BaseServerResource {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public ReloadEtlMetaDataServerResource() {
        setNegotiated(false);
    }

    @Override
    public Representation post(Representation entity) throws ResourceException {
        try {
            ObjectMapper mapper = ObjectMapperFactory.INSTANCE.getObjectMapper();
            String entityText = entity.getText();
            Map<String,String> dataMap = mapper.readValue(entityText, Map.class);
            SOFTWARE_VERSION softwareVersion = SOFTWARE_VERSION.valueOf(dataMap.get("softwareVersion"));
            String networkSoftwareVersionId = dataMap.get("networkSoftwareVersion");
            NetworkSoftwareVersion nsv = UMLG.get().getEntity(networkSoftwareVersionId);
            WorkspaceEnum workspaceEnum = WorkspaceEnum.valueOf(dataMap.get("workspaceEnum"));
            EtlMetaData.parse(softwareVersion, EtlDirectoryStructure.of(nsv));
            logger.debug(String.format("Reloaded EtlMetaData for %s and %s and %s", softwareVersion.name(), nsv.getName(), workspaceEnum.name()));
            return new JsonRepresentation("success");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
