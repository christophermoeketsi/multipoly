package org.multipoly.User;

import org.multipoly.Board.Board;
import org.multipoly.Board.Board.BoardRuntimePropertyEnum;
import org.multipoly.User.User.UserRuntimePropertyEnum;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.umlg.runtime.adaptor.UMLG;
import org.umlg.runtime.collection.ocl.BooleanExpressionEvaluator;
import org.umlg.runtime.domain.json.ToJsonUtil;
import org.umlg.runtime.restlet.util.UmlgURLDecoder;

public class User_user_board_lookUpForMany_ServerResourceImpl extends ServerResource {
	private Object userId;

	/**
	 * default constructor for User_user_board_lookUpForMany_ServerResourceImpl
	 */
	public User_user_board_lookUpForMany_ServerResourceImpl()  {
		setNegotiated(false);
	}

	@Override
	public Representation get() throws ResourceException {
		try {
			this.userId = UmlgURLDecoder.decode((String)getRequestAttributes().get("userId"));
			User resource = UMLG.get().getEntity(this.userId);
			StringBuilder json = new StringBuilder();
			json.append("[");
			json.append("{\"data\": [");
			json.append(ToJsonUtil.toJsonWithoutCompositeParent(resource.lookupFor_user_board().select(new BooleanExpressionEvaluator<Board>() {
						@Override
						public Boolean evaluate(Board e) {
							return e.getClass() == Board.class;
						}
					})));
			json.append("],");
			json.append(" \"meta\" : {");
			json.append("\"qualifiedName\": \"RootElement::org::multipoly::User::User::board\"");
			json.append(", \"to\": ");
			if ( resource.getClass() == User.class ) {
				json.append(BoardRuntimePropertyEnum.asJson());
				json.append(", \"from\": ");
				json.append(UserRuntimePropertyEnum.asJson());
			}
			json.append("}");
			json.append("}]");
			return new JsonRepresentation(json.toString());
		} finally {
			UMLG.get().rollback();
		}
	}


}