package org.multipoly.Board;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.multipoly.Board.Asset.AssetRuntimePropertyEnum;
import org.multipoly.Board.Board.BoardRuntimePropertyEnum;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.umlg.runtime.adaptor.UMLG;
import org.umlg.runtime.adaptor.UmlgExceptionUtilFactory;
import org.umlg.runtime.adaptor.UmlgSchemaFactory;
import org.umlg.runtime.collection.ocl.BooleanExpressionEvaluator;
import org.umlg.runtime.domain.json.ToJsonUtil;
import org.umlg.runtime.restlet.util.UmlgNodeJsonHolder;
import org.umlg.runtime.restlet.util.UmlgURLDecoder;
import org.umlg.runtime.util.ObjectMapperFactory;

public class Board_board_asset_ServerResourceImpl extends ServerResource {
	private Object boardId;

	/**
	 * default constructor for Board_board_asset_ServerResourceImpl
	 */
	public Board_board_asset_ServerResourceImpl()  {
		setNegotiated(false);
	}

	@Override
	public Representation get() throws ResourceException {
		try {
			this.boardId = UmlgURLDecoder.decode((String)getRequestAttributes().get("boardId"));
			Board parentResource = UMLG.get().getEntity(boardId);
			StringBuilder json = new StringBuilder();
			json.append("[");
			json.append("{\"data\": [");
			json.append(ToJsonUtil.toJsonWithoutCompositeParent(parentResource.getAsset().select(new BooleanExpressionEvaluator<Asset>() {
						@Override
						public Boolean evaluate(Asset e) {
							return e.getClass() == Asset.class;
						}
					})));
			json.append("],");
			json.append(" \"meta\" : {");
			json.append("\"qualifiedName\": \"RootElement::org::multipoly::Board::Board::asset\"");
			json.append(",\"qualifiedNameFrom\": \"" + parentResource.getQualifiedName() + "\"");
			json.append(",\"qualifiedNameTo\": \"RootElement::org::multipoly::Board::Asset\"");
			json.append("}");
			json.append("}]");
			return new JsonRepresentation(json.toString());
		} finally {
			UMLG.get().rollback();
		}
	}
	
	@Override
	public Representation options() throws ResourceException {
		try {
			this.boardId = UmlgURLDecoder.decode((String)getRequestAttributes().get("boardId"));
			Board parentResource = UMLG.get().getEntity(boardId);
			StringBuilder json = new StringBuilder();
			json.append("[");
			json.append("{");
			json.append(" \"meta\" : {");
			json.append("\"qualifiedName\": \"RootElement::org::multipoly::Board::Board::asset\"");
			json.append(", \"to\": ");
			json.append(AssetRuntimePropertyEnum.asJson());
			json.append(", \"from\": ");
			json.append(BoardRuntimePropertyEnum.asJson());
			json.append("}}");
			json.append("]");
			return new JsonRepresentation(json.toString());
		} finally {
			UMLG.get().rollback();
		}
	}
	
	@SuppressWarnings(	"unchecked")
	@Override
	public Representation post(Representation entity) throws ResourceException {
		this.boardId = UmlgURLDecoder.decode((String)getRequestAttributes().get("boardId"));
		Board parentResource = UMLG.get().getEntity(boardId);
		try {
			ObjectMapper mapper = ObjectMapperFactory.INSTANCE.getObjectMapper();
			String entityText = entity.getText();
			Map<Class<? extends Asset>,List<UmlgNodeJsonHolder>> resultMap = new HashMap<Class<? extends Asset>, List<UmlgNodeJsonHolder>>();
			Map<String,Object> overloaded = mapper.readValue(entityText, Map.class);
			Object o = overloaded.get("insert");
			if ( o != null ) {
				if ( o instanceof ArrayList ) {
					List<Map<String,Object>> array = (ArrayList<Map<String, Object>>)o;
					for ( Map<String,Object> overloadedJsonMap : array ) {
						add(resultMap, parentResource, overloadedJsonMap);
					}
				} else {
					Map<String,Object> overloadedJsonMap = (Map<String, Object>) o;
					add(resultMap, parentResource, overloadedJsonMap);
				}
			}
			o = overloaded.get("delete");
			if ( o != null ) {
				if ( o instanceof ArrayList ) {
					ArrayList<Map<String,Object>> array = (ArrayList<Map<String, Object>>)o;
					for ( Map<String,Object> map : array ) {
						delete(parentResource, map);
					}
				} else {
					Map<String,Object> map = (Map<String, Object>) o;
					delete(parentResource, map);
				}
			}
			o = overloaded.get("update");
			if ( o != null ) {
				if ( o instanceof ArrayList ) {
					ArrayList<Map<String,Object>> array = (ArrayList<Map<String, Object>>)o;
					for ( Map<String,Object> overloadedJsonMap : array ) {
						put(resultMap, overloadedJsonMap);
					}
				} else {
					Map<String,Object> overloadedJsonMap = (Map<String, Object>) o;
					put(resultMap, overloadedJsonMap);
				}
			}
			if ( !(getQueryValue("rollback") != null && Boolean.valueOf(getQueryValue("rollback"))) ) {
				UMLG.get().commit();
			}
			StringBuilder result = new StringBuilder("[");
			int count = 1;
			for ( Class<? extends Asset> baseClass : resultMap.keySet() ) {
				List<UmlgNodeJsonHolder> objectList = resultMap.get(baseClass);
				int objectListCount = 1;
				result.append("{\"data\": [");
				for ( UmlgNodeJsonHolder object : objectList ) {
					result.append(object.toJson());
					if ( objectListCount++ < objectList.size() ) {
						result.append(",");
					}
				}
				result.append("],");
				result.append(" \"meta\" : {");
				result.append("\"qualifiedName\": \"RootElement::org::multipoly::Board::Board::asset\"");
				result.append(", \"to\": ");
				if ( baseClass.equals(Asset.class) ) {
					result.append(AssetRuntimePropertyEnum.asJson());
					result.append(", \"from\": ");
					result.append(BoardRuntimePropertyEnum.asJson());
					result.append("}");
					result.append("");
				} else {
					throw new IllegalStateException("Unknown type " + baseClass.getName());
				}
				if ( count++ == resultMap.size() ) {
					result.append("}");
				} else {
					result.append("},");
				}
			}
			result.append("]");
			return new JsonRepresentation(result.toString());
		} catch (Exception e) {
			UMLG.get().rollback();
			throw UmlgExceptionUtilFactory.getTumlExceptionUtil().handle(e);
		} finally {
			UMLG.get().rollback();
		}
	}
	
	private void add(Map<Class<? extends Asset>,List<UmlgNodeJsonHolder>> resultMap, Board parentResource, Map<String,Object> propertyMap) {
		String qualifiedName = (String)propertyMap.get("qualifiedName");
		Class<Asset> baseTumlClass = UmlgSchemaFactory.getUmlgSchemaMap().get(qualifiedName);
		List<UmlgNodeJsonHolder> objectList;
		if ( !resultMap.containsKey(baseTumlClass) ) {
			objectList = new ArrayList<UmlgNodeJsonHolder>();
			resultMap.put(baseTumlClass, objectList);
		} else {
			objectList = resultMap.get(baseTumlClass);
		}
		try {
			Object id = propertyMap.get("id");
			propertyMap.remove("board");
			Asset childResource = UMLG.get().getEntity(id);
			parentResource.addToAsset(childResource);
			objectList.add(new UmlgNodeJsonHolder(childResource));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private void delete(Board parentResource, Map<String,Object> propertyMap) {
		Object id = propertyMap.get("id");
		Asset childResource = UMLG.get().getEntity(id);
		parentResource.removeFromAsset(childResource);
	}
	
	private void put(Map<Class<? extends Asset>,List<UmlgNodeJsonHolder>> resultMap, Map<String,Object> propertyMap) {
		Object id = propertyMap.get("id");
		Asset childResource = UMLG.get().getEntity(id);
		childResource.fromJson(propertyMap);
		Class<? extends Asset> baseTumlClass = childResource.getClass();
		List<UmlgNodeJsonHolder> objectList;
		if ( !resultMap.containsKey(baseTumlClass) ) {
			objectList = new ArrayList<UmlgNodeJsonHolder>();
			resultMap.put(baseTumlClass, objectList);
		} else {
			objectList = resultMap.get(baseTumlClass);
		}
		objectList.add(new UmlgNodeJsonHolder(childResource));
	}


}