package org.multipoly.Board;

import org.multipoly.Board.Board.BoardRuntimePropertyEnum;
import org.multipoly.Board.Edward.EdwardRuntimePropertyEnum;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.umlg.runtime.adaptor.UMLG;
import org.umlg.runtime.restlet.UmlgRestletToJsonUtil;
import org.umlg.runtime.restlet.util.UmlgURLDecoder;

public class Edward_edward_board_lookUpForOne_ServerResourceImpl extends ServerResource {
	private Object edwardId;

	/**
	 * default constructor for Edward_edward_board_lookUpForOne_ServerResourceImpl
	 */
	public Edward_edward_board_lookUpForOne_ServerResourceImpl()  {
		setNegotiated(false);
	}

	@Override
	public Representation get() throws ResourceException {
		try {
			this.edwardId = UmlgURLDecoder.decode((String)getRequestAttributes().get("edwardId"));
			Edward resource = UMLG.get().getEntity(this.edwardId);
			StringBuilder json = new StringBuilder();
			json.append("{\"data\": [");
			json.append(UmlgRestletToJsonUtil.toJson(resource.lookupFor_edward_board()));
			json.append("],");
			json.append(" \"meta\" : {");
			json.append("\"qualifiedName\": \"RootElement::org::multipoly::Board::Edward::board\"");
			json.append(", \"to\": ");
			json.append(BoardRuntimePropertyEnum.asJson());
			json.append(", \"from\": ");
			json.append(EdwardRuntimePropertyEnum.asJson());
			json.append("}}");
			return new JsonRepresentation(json.toString());
		} finally {
			UMLG.get().rollback();
		}
	}


}