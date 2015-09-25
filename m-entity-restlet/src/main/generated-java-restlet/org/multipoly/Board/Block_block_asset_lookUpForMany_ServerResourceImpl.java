package org.multipoly.Board;

import org.multipoly.Board.Asset.AssetRuntimePropertyEnum;
import org.multipoly.Board.Block.BlockRuntimePropertyEnum;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.umlg.runtime.adaptor.UMLG;
import org.umlg.runtime.collection.ocl.BooleanExpressionEvaluator;
import org.umlg.runtime.domain.json.ToJsonUtil;
import org.umlg.runtime.restlet.util.UmlgURLDecoder;

public class Block_block_asset_lookUpForMany_ServerResourceImpl extends ServerResource {
	private Object blockId;

	/**
	 * default constructor for Block_block_asset_lookUpForMany_ServerResourceImpl
	 */
	public Block_block_asset_lookUpForMany_ServerResourceImpl()  {
		setNegotiated(false);
	}

	@Override
	public Representation get() throws ResourceException {
		try {
			this.blockId = UmlgURLDecoder.decode((String)getRequestAttributes().get("blockId"));
			Block resource = UMLG.get().getEntity(this.blockId);
			StringBuilder json = new StringBuilder();
			json.append("[");
			json.append("{\"data\": [");
			json.append(ToJsonUtil.toJsonWithoutCompositeParent(resource.lookupFor_block_asset().select(new BooleanExpressionEvaluator<Asset>() {
						@Override
						public Boolean evaluate(Asset e) {
							return e.getClass() == Asset.class;
						}
					})));
			json.append("],");
			json.append(" \"meta\" : {");
			json.append("\"qualifiedName\": \"RootElement::org::multipoly::Board::Block::asset\"");
			json.append(", \"to\": ");
			if ( resource.getClass() == Block.class ) {
				json.append(AssetRuntimePropertyEnum.asJson());
				json.append(", \"from\": ");
				json.append(BlockRuntimePropertyEnum.asJson());
			}
			json.append("}");
			json.append("}]");
			return new JsonRepresentation(json.toString());
		} finally {
			UMLG.get().rollback();
		}
	}


}