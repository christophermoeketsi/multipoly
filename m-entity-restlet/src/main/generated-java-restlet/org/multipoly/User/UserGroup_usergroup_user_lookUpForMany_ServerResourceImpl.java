package org.multipoly.User;

import org.multipoly.User.User.UserRuntimePropertyEnum;
import org.multipoly.User.UserGroup.UserGroupRuntimePropertyEnum;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.umlg.runtime.adaptor.UMLG;
import org.umlg.runtime.collection.ocl.BooleanExpressionEvaluator;
import org.umlg.runtime.domain.json.ToJsonUtil;
import org.umlg.runtime.restlet.util.UmlgURLDecoder;

public class UserGroup_usergroup_user_lookUpForMany_ServerResourceImpl extends ServerResource {
	private Object usergroupId;

	/**
	 * default constructor for UserGroup_usergroup_user_lookUpForMany_ServerResourceImpl
	 */
	public UserGroup_usergroup_user_lookUpForMany_ServerResourceImpl()  {
		setNegotiated(false);
	}

	@Override
	public Representation get() throws ResourceException {
		try {
			this.usergroupId = UmlgURLDecoder.decode((String)getRequestAttributes().get("usergroupId"));
			UserGroup resource = UMLG.get().getEntity(this.usergroupId);
			StringBuilder json = new StringBuilder();
			json.append("[");
			json.append("{\"data\": [");
			json.append(ToJsonUtil.toJsonWithoutCompositeParent(resource.lookupFor_usergroup_user().select(new BooleanExpressionEvaluator<User>() {
						@Override
						public Boolean evaluate(User e) {
							return e.getClass() == User.class;
						}
					})));
			json.append("],");
			json.append(" \"meta\" : {");
			json.append("\"qualifiedName\": \"RootElement::org::multipoly::User::UserGroup::user\"");
			json.append(", \"to\": ");
			if ( resource.getClass() == UserGroup.class ) {
				json.append(UserRuntimePropertyEnum.asJson());
				json.append(", \"from\": ");
				json.append(UserGroupRuntimePropertyEnum.asJson());
			}
			json.append("}");
			json.append("}]");
			return new JsonRepresentation(json.toString());
		} finally {
			UMLG.get().rollback();
		}
	}


}