package org.multipoly.Board;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.umlg.runtime.adaptor.UMLG;
import org.umlg.runtime.domain.restlet.UmlgRestletNode;
import org.umlg.runtime.restlet.util.RestletToJsonUtil;
import org.umlg.runtime.restlet.util.UmlgURLDecoder;

public class EdwardCompositePathServerResourceImpl extends ServerResource {
	private Object edwardId;

	/**
	 * default constructor for EdwardCompositePathServerResourceImpl
	 */
	public EdwardCompositePathServerResourceImpl()  {
		setNegotiated(false);
	}

	@Override
	public Representation get() throws ResourceException {
		try {
			this.edwardId= UmlgURLDecoder.decode((String)getRequestAttributes().get("edwardId"));;
			Edward c = UMLG.get().getEntity(this.edwardId);
			StringBuilder json = new StringBuilder();
			json.append("{\"data\": [");
			json.append(RestletToJsonUtil.pathToCompositionRootAsJson(c.<UmlgRestletNode>getPathToCompositionalRoot(), "Root", "/RootElement"));
			json.append("]}");
			return new JsonRepresentation(json.toString());
		} finally {
			UMLG.get().rollback();
		}
	}


}