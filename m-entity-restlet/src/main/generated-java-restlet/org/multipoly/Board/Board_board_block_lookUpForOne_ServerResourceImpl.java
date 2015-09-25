package org.multipoly.Board;

import org.multipoly.Board.Block.BlockRuntimePropertyEnum;
import org.multipoly.Board.Board.BoardRuntimePropertyEnum;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.umlg.runtime.adaptor.UMLG;
import org.umlg.runtime.restlet.UmlgRestletToJsonUtil;
import org.umlg.runtime.restlet.util.UmlgURLDecoder;

public class Board_board_block_lookUpForOne_ServerResourceImpl extends ServerResource {
	private Object boardId;

	/**
	 * default constructor for Board_board_block_lookUpForOne_ServerResourceImpl
	 */
	public Board_board_block_lookUpForOne_ServerResourceImpl()  {
		setNegotiated(false);
	}

	@Override
	public Representation get() throws ResourceException {
		try {
			this.boardId = UmlgURLDecoder.decode((String)getRequestAttributes().get("boardId"));
			Board resource = UMLG.get().getEntity(this.boardId);
			StringBuilder json = new StringBuilder();
			json.append("{\"data\": [");
			json.append(UmlgRestletToJsonUtil.toJson(resource.lookupFor_board_block()));
			json.append("],");
			json.append(" \"meta\" : {");
			json.append("\"qualifiedName\": \"RootElement::org::multipoly::Board::Board::block\"");
			json.append(", \"to\": ");
			json.append(BlockRuntimePropertyEnum.asJson());
			json.append(", \"from\": ");
			json.append(BoardRuntimePropertyEnum.asJson());
			json.append("}}");
			return new JsonRepresentation(json.toString());
		} finally {
			UMLG.get().rollback();
		}
	}


}