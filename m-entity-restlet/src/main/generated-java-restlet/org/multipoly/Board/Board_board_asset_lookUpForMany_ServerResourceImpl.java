package org.multipoly.Board;

import org.multipoly.Board.Asset.AssetRuntimePropertyEnum;
import org.multipoly.Board.Board.BoardRuntimePropertyEnum;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.umlg.runtime.adaptor.UMLG;
import org.umlg.runtime.collection.ocl.BooleanExpressionEvaluator;
import org.umlg.runtime.domain.json.ToJsonUtil;
import org.umlg.runtime.restlet.util.UmlgURLDecoder;

public class Board_board_asset_lookUpForMany_ServerResourceImpl extends ServerResource {
	private Object boardId;

	/**
	 * default constructor for Board_board_asset_lookUpForMany_ServerResourceImpl
	 */
	public Board_board_asset_lookUpForMany_ServerResourceImpl()  {
		setNegotiated(false);
	}

	@Override
	public Representation get() throws ResourceException {
		try {
			this.boardId = UmlgURLDecoder.decode((String)getRequestAttributes().get("boardId"));
			Board resource = UMLG.get().getEntity(this.boardId);
			StringBuilder json = new StringBuilder();
			json.append("[");
			json.append("{\"data\": [");
			json.append(ToJsonUtil.toJsonWithoutCompositeParent(resource.lookupFor_board_asset().select(new BooleanExpressionEvaluator<Asset>() {
						@Override
						public Boolean evaluate(Asset e) {
							return e.getClass() == Asset.class;
						}
					})));
			json.append("],");
			json.append(" \"meta\" : {");
			json.append("\"qualifiedName\": \"RootElement::org::multipoly::Board::Board::asset\"");
			json.append(", \"to\": ");
			if ( resource.getClass() == Board.class ) {
				json.append(AssetRuntimePropertyEnum.asJson());
				json.append(", \"from\": ");
				json.append(BoardRuntimePropertyEnum.asJson());
			}
			json.append("}");
			json.append("}]");
			return new JsonRepresentation(json.toString());
		} finally {
			UMLG.get().rollback();
		}
	}


}