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

public class Block_block_board_lookUpForOne_ServerResourceImpl extends ServerResource {
	private Object blockId;

	/**
	 * default constructor for Block_block_board_lookUpForOne_ServerResourceImpl
	 */
	public Block_block_board_lookUpForOne_ServerResourceImpl()  {
		setNegotiated(false);
	}

	@Override
	public Representation get() throws ResourceException {
		try {
			this.blockId = UmlgURLDecoder.decode((String)getRequestAttributes().get("blockId"));
			Block resource = UMLG.get().getEntity(this.blockId);
			StringBuilder json = new StringBuilder();
			json.append("{\"data\": [");
			json.append(UmlgRestletToJsonUtil.toJson(resource.lookupFor_block_board()));
			json.append("],");
			json.append(" \"meta\" : {");
			json.append("\"qualifiedName\": \"RootElement::org::multipoly::Board::Block::board\"");
			json.append(", \"to\": ");
			json.append(BoardRuntimePropertyEnum.asJson());
			json.append(", \"from\": ");
			json.append(BlockRuntimePropertyEnum.asJson());
			json.append("}}");
			return new JsonRepresentation(json.toString());
		} finally {
			UMLG.get().rollback();
		}
	}


}