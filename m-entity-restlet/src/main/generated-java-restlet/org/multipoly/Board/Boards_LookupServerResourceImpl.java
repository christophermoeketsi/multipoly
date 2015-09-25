package org.multipoly.Board;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.umlg.runtime.adaptor.UMLG;
import org.umlg.runtime.adaptor.UmlgExceptionUtilFactory;
import org.umlg.runtime.adaptor.UmlgTmpIdManager;
import org.umlg.runtime.restlet.util.UmlgURLDecoder;
import org.umlg.runtime.util.ObjectMapperFactory;

public class Boards_LookupServerResourceImpl extends ServerResource implements Boards_LookupServerResource {


	/**
	 * default constructor for Boards_LookupServerResourceImpl
	 */
	public Boards_LookupServerResourceImpl()  {
		setNegotiated(false);
	}

	@SuppressWarnings(	"unchecked")
	@Override
	public Representation post(Representation entity) throws ResourceException {
		try {
			ObjectMapper mapper = ObjectMapperFactory.INSTANCE.getObjectMapper();
			String entityText = entity.getText();
			Map<String,Object> overloaded = mapper.readValue(entityText, Map.class);
			Object o = overloaded.get("insert");
			if ( o != null ) {
				if ( o instanceof ArrayList ) {
					List<Map<String,Object>> array = (ArrayList<Map<String, Object>>)o;
					for ( Map<String,Object> map : array ) {
						add(map);
					}
				} else {
					Map<String,Object> map = (Map<String, Object>) o;
					add(map);
				}
			}
			o = overloaded.get("update");
			if ( o != null ) {
				if ( o instanceof ArrayList ) {
					List<Map<String,Object>> array = (ArrayList<Map<String, Object>>)o;
					for ( Map<String,Object> map : array ) {
						put(map);
					}
				} else {
					Map<String,Object> map = (Map<String, Object>) o;
					put(map);
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
			String lookupUri = UmlgURLDecoder.decode(getReference().getQueryAsForm(false).getFirstValue("lookupUri"));
			lookupUri = "riap://host" + lookupUri;
			int fakeIdIndex = lookupUri.indexOf("fake");
			if ( fakeIdIndex != -1 ) {
				int indexOfForwardSlash = lookupUri.indexOf("/", fakeIdIndex);
				String fakeId = lookupUri.substring(fakeIdIndex, indexOfForwardSlash);
				Object id = UmlgTmpIdManager.INSTANCE.get(fakeId);
				lookupUri = lookupUri.replace(fakeId, UmlgURLDecoder.encode(id.toString()));
			}
			ClientResource cr = new ClientResource(lookupUri);
			Representation result = cr.get();
			return result;
		} catch (Exception e) {
			UMLG.get().rollback();
			throw UmlgExceptionUtilFactory.getTumlExceptionUtil().handle(e);
		} finally {
			UmlgTmpIdManager.INSTANCE.remove();
			UMLG.get().rollback();
		}
	}
	
	/**
	 * This method adds a single new instance. If and id already exist it passes the existing id back as a tmpId
	 * 
	 * @param propertyMap 
	 */
	private void add(Map<String,Object> propertyMap) {
		Board childResource = new Board(true);
		childResource.fromJson(propertyMap);
	}
	
	private void delete(Map<String,Object> propertyMap) {
		Object id = propertyMap.get("id");
		Board childResource = UMLG.get().getEntity(id);
		childResource.delete();
	}
	
	private void put(Map<String,Object> propertyMap) {
		Object id = propertyMap.get("id");
		Board childResource = UMLG.get().getEntity(id);
		childResource.fromJson(propertyMap);
	}


}