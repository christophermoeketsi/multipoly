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

public class Asset_asset_board_lookUpForMany_ServerResourceImpl extends ServerResource {
	private Object assetId;

	/**
	 * default constructor for Asset_asset_board_lookUpForMany_ServerResourceImpl
	 */
	public Asset_asset_board_lookUpForMany_ServerResourceImpl()  {
		setNegotiated(false);
	}

	@Override
	public Representation get() throws ResourceException {
		try {
			this.assetId = UmlgURLDecoder.decode((String)getRequestAttributes().get("assetId"));
			Asset resource = UMLG.get().getEntity(this.assetId);
			StringBuilder json = new StringBuilder();
			json.append("[");
			json.append("{\"data\": [");
			json.append(ToJsonUtil.toJsonWithoutCompositeParent(resource.lookupFor_asset_board().select(new BooleanExpressionEvaluator<Board>() {
						@Override
						public Boolean evaluate(Board e) {
							return e.getClass() == Board.class;
						}
					})));
			json.append("],");
			json.append(" \"meta\" : {");
			json.append("\"qualifiedName\": \"RootElement::org::multipoly::Board::Asset::board\"");
			json.append(", \"to\": ");
			if ( resource.getClass() == Asset.class ) {
				json.append(BoardRuntimePropertyEnum.asJson());
				json.append(", \"from\": ");
				json.append(AssetRuntimePropertyEnum.asJson());
			}
			json.append("}");
			json.append("}]");
			return new JsonRepresentation(json.toString());
		} finally {
			UMLG.get().rollback();
		}
	}


}