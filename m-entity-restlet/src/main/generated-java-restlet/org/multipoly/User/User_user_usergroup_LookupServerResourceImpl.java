package org.multipoly.User;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Map;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.umlg.runtime.adaptor.UMLG;
import org.umlg.runtime.adaptor.UmlgExceptionUtilFactory;
import org.umlg.runtime.adaptor.UmlgSchemaFactory;
import org.umlg.runtime.adaptor.UmlgTmpIdManager;
import org.umlg.runtime.restlet.util.UmlgURLDecoder;
import org.umlg.runtime.util.ObjectMapperFactory;

public class User_user_usergroup_LookupServerResourceImpl extends ServerResource {
	private Object userId;

	/**
	 * default constructor for User_user_usergroup_LookupServerResourceImpl
	 */
	public User_user_usergroup_LookupServerResourceImpl()  {
		setNegotiated(false);
	}

	@SuppressWarnings(	"unchecked")
	@Override
	public Representation post(Representation entity) throws ResourceException {
		this.userId = UmlgURLDecoder.decode((String)getRequestAttributes().get("userId"));
		User parentResource = UMLG.get().getEntity(userId);
		try {
			ObjectMapper mapper = ObjectMapperFactory.INSTANCE.getObjectMapper();
			String entityText = entity.getText();
			Map<String,Object> overloaded = mapper.readValue(entityText, Map.class);
			Object o = overloaded.get("insert");
			if ( o != null ) {
				if ( o instanceof ArrayList ) {
					ArrayList<Map<String,Object>> array = (ArrayList<Map<String, Object>>)o;
					for ( Map<String,Object> map : array ) {
						add(parentResource, map);
					}
				} else {
					Map<String,Object> map = (Map<String, Object>) o;
					add(parentResource, map);
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
					for ( Map<String,Object> map : array ) {
						put(map);
					}
				} else {
					Map<String,Object> map = (Map<String, Object>) o;
					put(map);
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
			throw UmlgExceptionUtilFactory.getTumlExceptionUtil().handle(e);
		} finally {
			UmlgTmpIdManager.INSTANCE.remove();
			UMLG.get().rollback();
		}
	}
	
	private void add(User parentResource, Map<String,Object> propertyMap) {
		String qualifiedName = (String)propertyMap.get("qualifiedName");
		Class<UserGroup> baseTumlClass = UmlgSchemaFactory.getUmlgSchemaMap().get(qualifiedName);
		try {
			Object id = propertyMap.get("id");
			UserGroup childResource = UMLG.get().getEntity(id);
			parentResource.addToUsergroup(childResource);
			childResource.fromJson(propertyMap);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private void delete(User parentResource, Map<String,Object> propertyMap) {
		Object id = propertyMap.get("id");
		UserGroup childResource = UMLG.get().getEntity(id);
		parentResource.removeFromUsergroup(childResource);
	}
	
	private void put(Map<String,Object> propertyMap) {
		Object id = propertyMap.get("id");
		UserGroup childResource = UMLG.get().getEntity(id);
		childResource.fromJson(propertyMap);
		Class<? extends UserGroup> baseTumlClass = childResource.getClass();
	}


}