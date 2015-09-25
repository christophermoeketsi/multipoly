package org.multipoly.restlet.sync;

import org.restlet.representation.Representation;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;

/**
 * Created by Ceasar on 2014/11/06.
 */
public interface ReloadEtlMetaDataInf {

    public static final String PATH = "/data/sync/etlMetaData";

    @Put
    public Representation put(Representation entity) throws ResourceException;
}
