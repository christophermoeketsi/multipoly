package org.multipoly;

import org.multipoly.User.UserRuntimePropertyEnum;
import org.multipoly.UserGroup.UserGroupRuntimePropertyEnum;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.umlg.runtime.adaptor.UMLG;
import org.umlg.runtime.restlet.UmlgRestletToJsonUtil;
import org.umlg.runtime.restlet.util.UmlgURLDecoder;

public class User_user_usergroup_lookUpForOne_ServerResourceImpl extends ServerResource {
	private Object userId;

	/**
	 * default constructor for User_user_usergroup_lookUpForOne_ServerResourceImpl
	 */
	public User_user_usergroup_lookUpForOne_ServerResourceImpl()  {
		setNegotiated(false);
	}

	@Override
	public Representation get() throws ResourceException {
		try {
			this.userId = UmlgURLDecoder.decode((String)getRequestAttributes().get("userId"));
			User resource = UMLG.get().getEntity(this.userId);
			StringBuilder json = new StringBuilder();
			json.append("{\"data\": [");
			json.append(UmlgRestletToJsonUtil.toJson(resource.lookupFor_user_usergroup()));
			json.append("],");
			json.append(" \"meta\" : {");
			json.append("\"qualifiedName\": \"RootElement::org::multipoly::User::usergroup\"");
			json.append(", \"to\": ");
			json.append(UserGroupRuntimePropertyEnum.asJson());
			json.append(", \"from\": ");
			json.append(UserRuntimePropertyEnum.asJson());
			json.append("}}");
			return new JsonRepresentation(json.toString());
		} finally {
			UMLG.get().rollback();
		}
	}


}