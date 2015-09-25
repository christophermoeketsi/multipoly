package org.multipoly.User;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.umlg.runtime.adaptor.UMLG;
import org.umlg.runtime.domain.restlet.UmlgRestletNode;
import org.umlg.runtime.restlet.util.RestletToJsonUtil;
import org.umlg.runtime.restlet.util.UmlgURLDecoder;

public class UserGroupCompositePathServerResourceImpl extends ServerResource {
	private Object usergroupId;

	/**
	 * default constructor for UserGroupCompositePathServerResourceImpl
	 */
	public UserGroupCompositePathServerResourceImpl()  {
		setNegotiated(false);
	}

	@Override
	public Representation get() throws ResourceException {
		try {
			this.usergroupId= UmlgURLDecoder.decode((String)getRequestAttributes().get("usergroupId"));;
			UserGroup c = UMLG.get().getEntity(this.usergroupId);
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