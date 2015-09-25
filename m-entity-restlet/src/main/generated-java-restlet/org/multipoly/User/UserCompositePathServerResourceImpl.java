package org.multipoly.User;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.umlg.runtime.adaptor.UMLG;
import org.umlg.runtime.domain.restlet.UmlgRestletNode;
import org.umlg.runtime.restlet.util.RestletToJsonUtil;
import org.umlg.runtime.restlet.util.UmlgURLDecoder;

public class UserCompositePathServerResourceImpl extends ServerResource {
	private Object userId;

	/**
	 * default constructor for UserCompositePathServerResourceImpl
	 */
	public UserCompositePathServerResourceImpl()  {
		setNegotiated(false);
	}

	@Override
	public Representation get() throws ResourceException {
		try {
			this.userId= UmlgURLDecoder.decode((String)getRequestAttributes().get("userId"));;
			User c = UMLG.get().getEntity(this.userId);
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