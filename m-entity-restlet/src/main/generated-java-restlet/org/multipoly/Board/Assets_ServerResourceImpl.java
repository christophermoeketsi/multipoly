package org.multipoly.Board;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.multipoly.Board.Asset.AssetRuntimePropertyEnum;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.umlg.model.RootElement;
import org.umlg.model.RootElement.RootElementRuntimePropertyEnum;
import org.umlg.runtime.adaptor.UMLG;
import org.umlg.runtime.adaptor.UmlgExceptionUtilFactory;
import org.umlg.runtime.adaptor.UmlgTmpIdManager;
import org.umlg.runtime.collection.UmlgSet;
import org.umlg.runtime.domain.UmlgNode;
import org.umlg.runtime.domain.json.ToJsonUtil;
import org.umlg.runtime.util.ObjectMapperFactory;

public class Assets_ServerResourceImpl extends ServerResource {


	/**
	 * default constructor for Assets_ServerResourceImpl
	 */
	public Assets_ServerResourceImpl()  {
		setNegotiated(false);
	}

	@Override
	public Representation get() throws ResourceException {
		try {
			StringBuilder json = new StringBuilder();
			UmlgSet<Asset> resource = (UmlgSet<Asset>)Asset.allInstances();
			json.append("[");
			json.append("{\"data\": [");
			json.append(ToJsonUtil.toJsonWithoutCompositeParent(resource));
			json.append("], \"meta\": {");
			json.append("\"qualifiedName\": \"RootElement::org::multipoly::Board::Asset\"");
			json.append(",\"qualifiedNameTo\": \"RootElement::org::multipoly::Board::Asset\"");
			json.append("}");
			json.append("}]");
			return new JsonRepresentation(json.toString());
		} finally {
			UMLG.get().rollback();
		}
	}
	
	public Representation options() throws ResourceException {
		try {
			StringBuilder json = new StringBuilder();
			json.append("[");
			json.append("{\"meta\" : {");
			json.append("\"qualifiedName\": \"RootElement::org::multipoly::Board::Asset\"");
			json.append(", \"to\": ");
			json.append(AssetRuntimePropertyEnum.asJson());
			json.append(", \"from\": ");
			json.append(RootElementRuntimePropertyEnum.asJson());
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
		try {
			ObjectMapper mapper = ObjectMapperFactory.INSTANCE.getObjectMapper();
			String entityText = entity.getText();
			Map<String,Object> overloaded = mapper.readValue(entityText, Map.class);
			List<UmlgNode> objectList = new ArrayList<UmlgNode>();
			int count = 0;
			Object o = overloaded.get("insert");
			if ( o != null ) {
				if ( o instanceof ArrayList ) {
					List<Map<String,Object>> array = (ArrayList<Map<String, Object>>)o;
					for ( Map<String,Object> map : array ) {
						objectList.add(add(map));
					}
				} else {
					Map<String,Object> map = (Map<String, Object>) o;
					objectList.add(add(map));
				}
			}
			o = overloaded.get("update");
			if ( o != null ) {
				if ( o instanceof ArrayList ) {
					List<Map<String,Object>> array = (ArrayList<Map<String, Object>>)o;
					for ( Map<String,Object> map : array ) {
						objectList.add(put(map));
					}
				} else {
					Map<String,Object> map = (Map<String, Object>) o;
					objectList.add(put(map));
				}
			}
			o = overloaded.get("delete");
			if ( o != null ) {
				if ( o instanceof ArrayList ) {
					List<Map<String,Object>> array = (ArrayList<Map<String, Object>>)o;
					for ( Map<String,Object> map : array ) {
						delete(map);
					}
				} else {
					Map<String,Object> map = (Map<String, Object>) o;
					delete(map);
				}
			}
			if ( !(getQueryValue("rollback") != null && Boolean.valueOf(getQueryValue("rollback"))) ) {
				UMLG.get().commit();
			}
			StringBuilder json = new StringBuilder();
			json.append("[");
			json.append("{\"data\": [");
			for ( UmlgNode umlgNode : objectList ) {
				json.append(umlgNode.toJsonWithoutCompositeParent(true));
				if ( ++count < objectList.size() ) {
					json.append(",");
				}
			}
			json.append("], \"meta\": {");
			json.append("\"qualifiedName\": \"RootElement::org::multipoly::Board::Asset\"");
			json.append(", \"to\": ");
			json.append(AssetRuntimePropertyEnum.asJson());
			json.append(", \"from\": ");
			json.append(RootElementRuntimePropertyEnum.asJson());
			json.append("}}]");
			return new JsonRepresentation(json.toString());
		} catch (Exception e) {
			UMLG.get().rollback();
			throw UmlgExceptionUtilFactory.getTumlExceptionUtil().handle(e);
		} finally {
			UMLG.get().rollback();
			UmlgTmpIdManager.INSTANCE.remove();
		}
	}
	
	/**
	 * This method adds a single new instance. If and id already exist it passes the existing id back as a tmpId
	 * 
	 * @param propertyMap 
	 */
	private UmlgNode add(Map<String,Object> propertyMap) {
		Asset childResource = new Asset(true);
		childResource.fromJson(propertyMap);
		return childResource;
	}
	
	private void delete(Map<String,Object> propertyMap) {
		Object id = propertyMap.get("id");
		Asset childResource = UMLG.get().getEntity(id);
		childResource.delete();
	}
	
	private UmlgNode put(Map<String,Object> propertyMap) {
		Object id = propertyMap.get("id");
		Asset childResource = UMLG.get().getEntity(id);
		childResource.fromJson(propertyMap);
		return childResource;
	}


}