package org.multipoly.User;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.multipoly.User.User.UserRuntimePropertyEnum;
import org.multipoly.User.meta.UserGroupMeta;
import org.umlg.runtime.adaptor.TransactionThreadEntityVar;
import org.umlg.runtime.adaptor.UMLG;
import org.umlg.runtime.adaptor.UmlgLabelConverterFactory;
import org.umlg.runtime.adaptor.UmlgTmpIdManager;
import org.umlg.runtime.collection.Filter;
import org.umlg.runtime.collection.Qualifier;
import org.umlg.runtime.collection.UmlgRuntimeProperty;
import org.umlg.runtime.collection.UmlgSet;
import org.umlg.runtime.collection.memory.UmlgMemorySet;
import org.umlg.runtime.collection.persistent.UmlgSetImpl;
import org.umlg.runtime.domain.BaseUmlgCompositionNode;
import org.umlg.runtime.domain.CompositionNode;
import org.umlg.runtime.domain.DataTypeEnum;
import org.umlg.runtime.domain.UmlgMetaNode;
import org.umlg.runtime.domain.UmlgNode;
import org.umlg.runtime.domain.UmlgRootNode;
import org.umlg.runtime.domain.restlet.FieldType;
import org.umlg.runtime.domain.restlet.UmlgRestletNode;
import org.umlg.runtime.util.ObjectMapperFactory;
import org.umlg.runtime.validation.UmlgConstraintViolation;
import org.umlg.runtime.validation.UmlgConstraintViolationException;
import org.umlg.runtime.validation.UmlgValidation;

public class UserGroup extends BaseUmlgCompositionNode implements UmlgRestletNode, UmlgRootNode, CompositionNode {
	static final public long serialVersionUID = 1L;
	private UmlgSet<String> Name;
	private UmlgSet<User> user;
	private String tmpId;	// tmpId is only used the umlg restlet gui. It is never persisted. Its value is generated by the gui.

	/**
	 * constructor for UserGroup
	 * 
	 * @param id 
	 */
	public UserGroup(Object id)  {
		super(id);
	}
	
	/**
	 * constructor for UserGroup
	 * 
	 * @param vertex 
	 */
	public UserGroup(Vertex vertex)  {
		super(vertex);
	}
	
	/**
	 * default constructor for UserGroup
	 */
	public UserGroup()  {
		this(true);
	}
	
	/**
	 * constructor for UserGroup
	 * 
	 * @param persistent 
	 */
	public UserGroup(Boolean persistent)  {
		super(persistent);
		Edge edge = UMLG.get().getRoot().addEdge(getEdgeToRootLabel(), this.vertex);
		edge.property("inClass", this.getClass().getName());
	}

	public void addToName(String Name) {
		if ( !this.Name.isEmpty() ) {
			throw new RuntimeException("Property is a one and already has value, first clear it before adding!");
		}
		if ( Name != null ) {
			List<UmlgConstraintViolation> violations = validateName(Name);
			if ( violations.isEmpty() ) {
				this.Name.add(Name);
			} else {
				throw new UmlgConstraintViolationException(violations);
			}
		}
	}
	
	public void addToNameIgnoreInverse(String Name) {
		if ( !this.Name.isEmpty() ) {
			throw new RuntimeException("Property is a one and already has value, first clear it before adding!");
		}
		if ( Name != null ) {
			List<UmlgConstraintViolation> violations = validateName(Name);
			if ( violations.isEmpty() ) {
				this.Name.add(Name);
			} else {
				throw new UmlgConstraintViolationException(violations);
			}
		}
	}
	
	public void addToUser(UmlgSet<User> user) {
		if ( !user.isEmpty() ) {
			this.user.addAll(user);
		}
	}
	
	public void addToUser(User user) {
		if ( user != null ) {
			user.clearUsergroup();
			user.initialiseProperty(UserRuntimePropertyEnum.usergroup, false);
			removeFromUser(user);
		}
		if ( user != null ) {
			this.user.add(user);
		}
	}
	
	public void addToUserIgnoreInverse(User user) {
		if ( user != null ) {
			user.clearUsergroup();
			user.initialiseProperty(UserRuntimePropertyEnum.usergroup, false);
			removeFromUser(user);
		}
		if ( user != null ) {
			this.user.addIgnoreInverse(user);
		}
	}
	
	static public UmlgSet<? extends UserGroup> allInstances(Filter filter) {
		UmlgSet<UserGroup> result = new UmlgMemorySet<UserGroup>();
		result.addAll(UMLG.get().allInstances(UserGroup.class.getName(), filter));
		return result;
	}
	
	static public UmlgSet<? extends UserGroup> allInstances() {
		UmlgSet<UserGroup> result = new UmlgMemorySet<UserGroup>();
		result.addAll(UMLG.get().allInstances(UserGroup.class.getName()));
		return result;
	}
	
	@Override
	public List<UmlgConstraintViolation> checkClassConstraints() {
		List<UmlgConstraintViolation> result = new ArrayList<UmlgConstraintViolation>();
		return result;
	}
	
	public void clearName() {
		this.Name.clear();
	}
	
	public void clearUser() {
		this.user.clear();
	}
	
	@Override
	public void delete() {
		this.user.clear();
		TransactionThreadEntityVar.remove(this);
		this.vertex.remove();
	}
	
	@Override
	public void fromJson(Map<String,Object> propertyMap) {
		fromJsonDataTypeAndComposite(propertyMap);
		fromJsonNonCompositeOne(propertyMap);
		fromJsonNonCompositeRequiredMany(propertyMap);
	}
	
	@Override
	public void fromJson(String json) {
		ObjectMapper mapper = ObjectMapperFactory.INSTANCE.getObjectMapper();
		try {
			@SuppressWarnings(	"unchecked")
			 Map<String,Object> propertyMap = mapper.readValue(json, Map.class);
			fromJson(propertyMap);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void fromJsonDataTypeAndComposite(Map<String,Object> propertyMap) {
		if ( propertyMap.containsKey("Name") ) {
			if ( propertyMap.get("Name") != null ) {
				String Name = (String)propertyMap.get("Name");
				setName(Name);
			} else {
				setName(null);
			}
		}
		if ( propertyMap.containsKey("tmpId") ) {
			if ( propertyMap.get("tmpId") != null ) {
				this.tmpId = (String)propertyMap.get("tmpId");
				UmlgTmpIdManager.INSTANCE.put(this.tmpId, getId());
			} else {
				this.tmpId = null;
			}
		}
	}
	
	@Override
	public void fromJsonNonCompositeOne(Map<String,Object> propertyMap) {
	}
	
	@Override
	public void fromJsonNonCompositeRequiredMany(Map<String,Object> propertyMap) {
	}
	
	public String getEdgeToRootLabel() {
		return UmlgLabelConverterFactory.getUmlgLabelConverter().convert("root_UserGroup");
	}
	
	@Override
	public String getMetaDataAsJson() {
		return UserGroup.UserGroupRuntimePropertyEnum.asJson();
	}
	
	public UmlgMetaNode getMetaNode() {
		return UserGroupMeta.getInstance();
	}
	
	public String getName() {
		UmlgSet<String> tmp = this.Name;
		if ( !tmp.isEmpty() ) {
			return tmp.iterator().next();
		} else {
			return null;
		}
	}
	
	@Override
	public UmlgNode getOwningObject() {
		return null;
	}
	
	@Override
	public String getQualifiedName() {
		return "RootElement::org::multipoly::User::UserGroup";
	}
	
	/**
	 * getQualifiers is called from the collection in order to update the index used to implement the qualifier
	 * 
	 * @param tumlRuntimeProperty 
	 * @param node 
	 * @param inverse 
	 */
	@Override
	public List<Qualifier> getQualifiers(UmlgRuntimeProperty tumlRuntimeProperty, UmlgNode node, boolean inverse) {
		List<Qualifier> result = Collections.emptyList();
		UserGroupRuntimePropertyEnum runtimeProperty;
		if ( !inverse ) {
			runtimeProperty = UserGroupRuntimePropertyEnum.fromQualifiedName(tumlRuntimeProperty.getQualifiedName());
		} else {
			runtimeProperty = UserGroupRuntimePropertyEnum.fromQualifiedName(tumlRuntimeProperty.getInverseQualifiedName());
		}
		if ( runtimeProperty != null && result.isEmpty() ) {
			switch ( runtimeProperty ) {
				default:
					result = Collections.emptyList();
			}
		
		}
		return result;
	}
	
	/**
	 * getSize is called from the BaseCollection.addInternal in order to save the sice of the inverse collection to update the edge's sequence order
	 * 
	 * @param inverse 
	 * @param tumlRuntimeProperty 
	 */
	@Override
	public int getSize(boolean inverse, UmlgRuntimeProperty tumlRuntimeProperty) {
		int result = 0;
		UserGroupRuntimePropertyEnum runtimeProperty;
		if ( !inverse ) {
			runtimeProperty = UserGroupRuntimePropertyEnum.fromQualifiedName(tumlRuntimeProperty.getQualifiedName());
		} else {
			runtimeProperty = UserGroupRuntimePropertyEnum.fromQualifiedName(tumlRuntimeProperty.getInverseQualifiedName());
		}
		if ( runtimeProperty != null && result == 0 ) {
			switch ( runtimeProperty ) {
				case Name:
					result = Name.size();
					break;
			
				case user:
					result = user.size();
					break;
			
				default:
					result = 0;
			}
		
		}
		return result;
	}
	
	@Override
	public String getUid() {
		String uid;
		if ( !this.vertex.property("uid").isPresent() ) {
			uid=UUID.randomUUID().toString();
			this.vertex.property("uid", uid);
		} else {
			uid=this.vertex.value("uid");
		}
		return uid;
	}
	
	@Override
	public String getUmlName() {
		return "UserGroup";
	}
	
	@Override
	public String getUri() {
		return ("\"" + UserGroupRuntimePropertyEnum.getUriToObject() + "\"");
	}
	
	public UmlgSet<User> getUser() {
		return this.user;
	}
	
	@Override
	public boolean hasOnlyOneCompositeParent() {
		int result = 0;
		return result == 1;
	}
	
	public void initVariables() {
	}
	
	@Override
	public void initialiseProperties() {
		this.Name =  new UmlgSetImpl<String>(this, UserGroupRuntimePropertyEnum.Name);
		this.user =  new UmlgSetImpl<User>(this, UserGroupRuntimePropertyEnum.user);
	}
	
	@Override
	public void initialiseProperty(UmlgRuntimeProperty tumlRuntimeProperty, boolean inverse) {
		UserGroupRuntimePropertyEnum runtimeProperty;
		if ( !inverse ) {
			runtimeProperty = (UserGroupRuntimePropertyEnum.fromQualifiedName(tumlRuntimeProperty.getQualifiedName()));
		} else {
			runtimeProperty = (UserGroupRuntimePropertyEnum.fromQualifiedName(tumlRuntimeProperty.getInverseQualifiedName()));
		}
		if ( runtimeProperty != null ) {
			switch ( runtimeProperty ) {
				case Name:
					this.Name =  new UmlgSetImpl<String>(this, UserGroupRuntimePropertyEnum.Name);
					break;
			
				case user:
					this.user =  new UmlgSetImpl<User>(this, UserGroupRuntimePropertyEnum.user);
					break;
			
			}
		
		}
	}
	
	@Override
	public UmlgRuntimeProperty inverseAdder(UmlgRuntimeProperty tumlRuntimeProperty, boolean inverse, UmlgNode umlgNode) {
		UserGroupRuntimePropertyEnum runtimeProperty;
		if ( !inverse ) {
			runtimeProperty = (UserGroupRuntimePropertyEnum.fromQualifiedName(tumlRuntimeProperty.getQualifiedName()));
		} else {
			runtimeProperty = (UserGroupRuntimePropertyEnum.fromQualifiedName(tumlRuntimeProperty.getInverseQualifiedName()));
		}
		if ( runtimeProperty != null ) {
			switch ( runtimeProperty ) {
				case user:
					this.user.inverseAdder((User)umlgNode);
					break;
			
			}
			
			return runtimeProperty;
		} else {
			return null;
		}
	}
	
	@Override
	public boolean isTinkerRoot() {
		return true;
	}
	
	public UmlgSet<User> lookupFor_usergroup_user() {
		UmlgSet<User> result = new UmlgMemorySet<User>();
		Filter<User> filter = new Filter<User>() {
		    @Override
		    public boolean filter(User entity){
		        return entity.getUsergroup() == null;
		    }
		};
		result.addAll(org.multipoly.User.User.allInstances(filter));
		return result;
	}
	
	public void removeFromName(String Name) {
		if ( Name != null ) {
			this.Name.remove(Name);
		}
	}
	
	public void removeFromName(UmlgSet<String> Name) {
		if ( !Name.isEmpty() ) {
			this.Name.removeAll(Name);
		}
	}
	
	public void removeFromUser(UmlgSet<User> user) {
		if ( !user.isEmpty() ) {
			this.user.removeAll(user);
		}
	}
	
	public void removeFromUser(User user) {
		if ( user != null ) {
			this.user.remove(user);
		}
	}
	
	public void setName(String Name) {
		clearName();
		addToName(Name);
	}
	
	public void setUser(UmlgSet<User> user) {
		clearUser();
		if ( user != null ) {
			addToUser(user);
		}
	}
	
	/**
	 * deep indicates that components also be serialized.
	 * 
	 * @param deep 
	 */
	@Override
	public String toJson(Boolean deep) {
		StringBuilder sb = new StringBuilder();
		if ( this.tmpId != null ) {
			sb.append("\"tmpId\": \"" + this.tmpId + "\", ");
		}
		sb.append("\"id\": \"" + getId() + "\", ");
		sb.append("\"metaNodeId\": \"" + getMetaNode().getId() + "\", ");
		sb.append("\"Name\": " + (getName() != null ? "\"" + StringEscapeUtils.escapeJson(getName()) + "\"" : null ));
		sb.append(", ");
		sb.append("\"qualifiedName\": \"" + getQualifiedName() + "\"");
		sb.append(", ");
		sb.append("\"uri\": " + getUri());
		sb.insert(0, "{");
		sb.append("}");
		return sb.toString();
	}
	
	@Override
	public String toJson() {
		return toJson(false);
	}
	
	/**
	 * deep indicates that components also be serialized.
	 * 
	 * @param deep 
	 */
	@Override
	public String toJsonWithoutCompositeParent(Boolean deep) {
		StringBuilder sb = new StringBuilder();
		if ( this.tmpId != null ) {
			sb.append("\"tmpId\": \"" + this.tmpId + "\", ");
		}
		sb.append("\"id\": \"" + getId() + "\", ");
		sb.append("\"metaNodeId\": \"" + getMetaNode().getId() + "\", ");
		sb.append("\"Name\": " + (getName() != null ? "\"" + StringEscapeUtils.escapeJson(getName()) + "\"" : null ));
		sb.append(", ");
		sb.append("\"qualifiedName\": \"" + getQualifiedName() + "\"");
		sb.append(", ");
		sb.append("\"uri\": " + getUri());
		sb.insert(0, "{");
		sb.append("}");
		return sb.toString();
	}
	
	@Override
	public String toJsonWithoutCompositeParent() {
		return toJsonWithoutCompositeParent(false);
	}
	
	@Override
	public List<UmlgConstraintViolation> validateMultiplicities() {
		List<UmlgConstraintViolation> result = new ArrayList<UmlgConstraintViolation>();
		if ( getName() == null ) {
			result.add(new UmlgConstraintViolation("multiplicity", "RootElement::org::multipoly::User::UserGroup::Name", "lower multiplicity is 1"));
		}
		return result;
	}
	
	public List<UmlgConstraintViolation> validateName(String Name) {
		List<UmlgConstraintViolation> result = new ArrayList<UmlgConstraintViolation>();
		return result;
	}

	static public enum UserGroupRuntimePropertyEnum implements UmlgRuntimeProperty {
		Name(/* qualifiedName */ "RootElement::org::multipoly::User::UserGroup::Name",/* persistentName */ "Name",/* inverseName */ "inverseOf::Name",/* inverseQualifiedName */ "inverseOf::RootElement::org::multipoly::User::UserGroup::Name",/* isAssociationClassOne */ false,/* isMemberEndOfAssociationClass */ false,/* associationClassPropertyNameField */ "null",/* inverseAssociationClassPropertyNameField */ "null",/* isAssociationClassProperty */ false,/* isOnePrimitivePropertyOfAssociationClass */ false,/* isOnePrimitive */ true,/* isReadOnly */ false,/* dataTypeEnum */ null,/* validations */ Collections.<UmlgValidation>emptyList(),/* isManyPrimitive */ false,/* oneEnumeration */ false,/* manyEnumeration */ false,/* isControllingSide */ true,/* isComposite */ false,/* isInverseComposite */ false,/* label */ UmlgLabelConverterFactory.getUmlgLabelConverter().convert("Name"),/* isOneToOne */ false,/* isOneToMany */ false,/* isManyToOne */ true,/* isManyToMany */ false,/* upper */ 1,/* lower */ 1,/* inverseUpper */ 1,/* isQualified */ false,/* isInverseQualified */ false,/* isOrdered */ false,/* isInverseOrdered */ false,/* isUnique */ true,/* isInverseUnique */ false,/* isDerived */ false,/* isNavigable */ true,/* propertyType */ String.class,/* json */ "{\"name\": \"Name\", \"associationClassOne\": false, \"memberEndOfAssociationClass\": false, \"associationClassPropertyName\": null, \"inverseAssociationClassPropertyName\": null, \"associationClassProperty\": false, \"onePrimitivePropertyOfAssociationClass\": false, \"onePrimitive\": true, \"readOnly\": false, \"dataTypeEnum\": null, \"validations\": null, \"qualifiedName\": \"RootElement::org::multipoly::User::UserGroup::Name\", \"persistentName\": \"Name\", \"inverseName\": \"inverseOf::Name\", \"inverseQualifiedName\": \"inverseOf::RootElement::org::multipoly::User::UserGroup::Name\", \"manyPrimitive\": false, \"oneEnumeration\": false, \"manyEnumeration\": false, \"controllingSide\": true, \"composite\": false, \"inverseComposite\": false, \"oneToOne\": false, \"oneToMany\": false, \"manyToOne\": true, \"manyToMany\": false, \"upper\": 1, \"lower\": 1, \"inverseUpper\": 1, \"label\": \"Name\", \"qualified\": false, \"inverseQualified\": false, \"ordered\": false, \"inverseOrdered\": false, \"unique\": true, \"inverseUnique\": false, \"derived\": false, \"navigable\": true, \"tumlUri\": \"/RootElement/usergroups/{usergroupId}/Name\", \"tumlOverloadedPostUri\": \"/RootElement/overloadedpost/usergroups/{usergroupId}/Name\", \"tumlMetaDataUri\": \"/RootElement/stringMetaData\", \"fieldType\": \"" + FieldType.String + "\", \"tumlLookupUri\": \"\", \"tumlCompositeParentLookupUri\": \"\"}",/* isChangeListenerAttribute */ false,/* tumlUri */ "/RootElement/usergroups/{usergroupId}/Name",/* tumlOverloadedPostUri */ "/RootElement/overloadedpost/usergroups/{usergroupId}/Name",/* tumlMetaDataUri */ "/RootElement/stringMetaData",/* fieldType */ FieldType.String,/* tumlLookupUri */ "",/* tumlCompositeParentLookupUri */ ""),
		user(/* qualifiedName */ "RootElement::org::multipoly::User::UserGroup::user",/* persistentName */ "user",/* inverseName */ "usergroup",/* inverseQualifiedName */ "RootElement::org::multipoly::User::User::usergroup",/* isAssociationClassOne */ false,/* isMemberEndOfAssociationClass */ false,/* associationClassPropertyNameField */ "null",/* inverseAssociationClassPropertyNameField */ "null",/* isAssociationClassProperty */ false,/* isOnePrimitivePropertyOfAssociationClass */ false,/* isOnePrimitive */ false,/* isReadOnly */ false,/* dataTypeEnum */ null,/* validations */ Collections.<UmlgValidation>emptyList(),/* isManyPrimitive */ false,/* oneEnumeration */ false,/* manyEnumeration */ false,/* isControllingSide */ true,/* isComposite */ false,/* isInverseComposite */ false,/* label */ UmlgLabelConverterFactory.getUmlgLabelConverter().convert("userGroup_user"),/* isOneToOne */ false,/* isOneToMany */ true,/* isManyToOne */ false,/* isManyToMany */ false,/* upper */ -1,/* lower */ 0,/* inverseUpper */ 1,/* isQualified */ false,/* isInverseQualified */ false,/* isOrdered */ false,/* isInverseOrdered */ false,/* isUnique */ true,/* isInverseUnique */ true,/* isDerived */ false,/* isNavigable */ true,/* propertyType */ User.class,/* json */ "{\"name\": \"user\", \"associationClassOne\": false, \"memberEndOfAssociationClass\": false, \"associationClassPropertyName\": null, \"inverseAssociationClassPropertyName\": null, \"associationClassProperty\": false, \"onePrimitivePropertyOfAssociationClass\": false, \"onePrimitive\": false, \"readOnly\": false, \"dataTypeEnum\": null, \"validations\": null, \"qualifiedName\": \"RootElement::org::multipoly::User::UserGroup::user\", \"persistentName\": \"user\", \"inverseName\": \"usergroup\", \"inverseQualifiedName\": \"RootElement::org::multipoly::User::User::usergroup\", \"manyPrimitive\": false, \"oneEnumeration\": false, \"manyEnumeration\": false, \"controllingSide\": true, \"composite\": false, \"inverseComposite\": false, \"oneToOne\": false, \"oneToMany\": true, \"manyToOne\": false, \"manyToMany\": false, \"upper\": -1, \"lower\": 0, \"inverseUpper\": 1, \"label\": \"userGroup_user\", \"qualified\": false, \"inverseQualified\": false, \"ordered\": false, \"inverseOrdered\": false, \"unique\": true, \"inverseUnique\": true, \"derived\": false, \"navigable\": true, \"tumlUri\": \"/RootElement/usergroups/{usergroupId}/user\", \"tumlOverloadedPostUri\": \"/RootElement/overloadedpost/usergroups/{usergroupId}/user\", \"tumlMetaDataUri\": \"/RootElement/userMetaData\", \"fieldType\": \"" + FieldType.Date + "\", \"tumlLookupUri\": \"/RootElement/usergroups/{usergroupId}/lookupFor_usergroup_user\", \"tumlCompositeParentLookupUri\": \"\"}",/* isChangeListenerAttribute */ false,/* tumlUri */ "/RootElement/usergroups/{usergroupId}/user",/* tumlOverloadedPostUri */ "/RootElement/overloadedpost/usergroups/{usergroupId}/user",/* tumlMetaDataUri */ "/RootElement/userMetaData",/* fieldType */ FieldType.Date,/* tumlLookupUri */ "/RootElement/usergroups/{usergroupId}/lookupFor_usergroup_user",/* tumlCompositeParentLookupUri */ ""),
		RootElement(/* qualifiedName */ "RootElement",/* persistentName */ "RootElement",/* inverseName */ "inverseOfRootElement",/* inverseQualifiedName */ "inverseOfRootElement",/* isAssociationClassOne */ false,/* isMemberEndOfAssociationClass */ false,/* associationClassPropertyNameField */ "null",/* inverseAssociationClassPropertyNameField */ "null",/* isAssociationClassProperty */ false,/* isOnePrimitivePropertyOfAssociationClass */ false,/* isOnePrimitive */ false,/* isReadOnly */ false,/* dataTypeEnum */ null,/* validations */ Collections.<UmlgValidation>emptyList(),/* isManyPrimitive */ false,/* oneEnumeration */ false,/* manyEnumeration */ false,/* isControllingSide */ true,/* isComposite */ false,/* isInverseComposite */ true,/* label */ UmlgLabelConverterFactory.getUmlgLabelConverter().convert("rootUserGroup"),/* isOneToOne */ true,/* isOneToMany */ false,/* isManyToOne */ false,/* isManyToMany */ false,/* upper */ -1,/* lower */ 0,/* inverseUpper */ 1,/* isQualified */ false,/* isInverseQualified */ false,/* isOrdered */ false,/* isInverseOrdered */ false,/* isUnique */ false,/* isInverseUnique */ false,/* isDerived */ false,/* isNavigable */ false,/* propertyType */ Object.class,/* json */ "{\"name\": \"RootElement\", \"associationClassOne\": false, \"memberEndOfAssociationClass\": false, \"associationClassPropertyName\": null, \"inverseAssociationClassPropertyName\": null, \"associationClassProperty\": false, \"onePrimitivePropertyOfAssociationClass\": false, \"onePrimitive\": false, \"readOnly\": false, \"dataTypeEnum\": null, \"validations\": null, \"qualifiedName\": \"RootElement\", \"persistentName\": \"RootElement\", \"inverseName\": \"inverseOfRootElement\", \"inverseQualifiedName\": \"inverseOfRootElement\", \"manyPrimitive\": false, \"oneEnumeration\": false, \"manyEnumeration\": false, \"controllingSide\": true, \"composite\": false, \"inverseComposite\": true, \"oneToOne\": true, \"oneToMany\": false, \"manyToOne\": false, \"manyToMany\": false, \"upper\": -1, \"lower\": 0, \"inverseUpper\": 1, \"label\": \"rootUserGroup\", \"qualified\": false, \"inverseQualified\": false, \"ordered\": false, \"inverseOrdered\": false, \"unique\": false, \"inverseUnique\": false, \"derived\": false, \"navigable\": false, \"tumlUri\": \"/RootElement\", \"tumlOverloadedPostUri\": \"/RootElement\", \"tumlMetaDataUri\": \"\", \"fieldType\": \"" + FieldType.String + "\", \"tumlLookupUri\": \"\", \"tumlCompositeParentLookupUri\": \"\"}",/* isChangeListenerAttribute */ false,/* tumlUri */ "/RootElement",/* tumlOverloadedPostUri */ "/RootElement",/* tumlMetaDataUri */ "",/* fieldType */ FieldType.String,/* tumlLookupUri */ "",/* tumlCompositeParentLookupUri */ ""),
		id(/* qualifiedName */ "not_applicable",/* persistentName */ "not_applicable",/* inverseName */ "inverseOf::not_applicable",/* inverseQualifiedName */ "inverseOf::not_applicable",/* isAssociationClassOne */ false,/* isMemberEndOfAssociationClass */ false,/* associationClassPropertyNameField */ "null",/* inverseAssociationClassPropertyNameField */ "null",/* isAssociationClassProperty */ false,/* isOnePrimitivePropertyOfAssociationClass */ false,/* isOnePrimitive */ true,/* isReadOnly */ true,/* dataTypeEnum */ null,/* validations */ Collections.<UmlgValidation>emptyList(),/* isManyPrimitive */ false,/* oneEnumeration */ false,/* manyEnumeration */ false,/* isControllingSide */ false,/* isComposite */ false,/* isInverseComposite */ false,/* label */ UmlgLabelConverterFactory.getUmlgLabelConverter().convert(""),/* isOneToOne */ true,/* isOneToMany */ false,/* isManyToOne */ true,/* isManyToMany */ false,/* upper */ 1,/* lower */ 1,/* inverseUpper */ 1,/* isQualified */ false,/* isInverseQualified */ false,/* isOrdered */ false,/* isInverseOrdered */ false,/* isUnique */ false,/* isInverseUnique */ true,/* isDerived */ false,/* isNavigable */ true,/* propertyType */ Object.class,/* json */ "{\"name\": \"id\", \"associationClassOne\": false, \"memberEndOfAssociationClass\": false, \"associationClassPropertyName\": null, \"inverseAssociationClassPropertyName\": null, \"associationClassProperty\": false, \"onePrimitivePropertyOfAssociationClass\": false, \"onePrimitive\": true, \"readOnly\": true, \"dataTypeEnum\": null, \"validations\": null, \"qualifiedName\": \"not_applicable\", \"persistentName\": \"not_applicable\", \"inverseName\": \"inverseOf::not_applicable\", \"inverseQualifiedName\": \"inverseOf::not_applicable\", \"manyPrimitive\": false, \"oneEnumeration\": false, \"manyEnumeration\": false, \"controllingSide\": false, \"composite\": false, \"inverseComposite\": false, \"oneToOne\": true, \"oneToMany\": false, \"manyToOne\": true, \"manyToMany\": false, \"upper\": 1, \"lower\": 1, \"inverseUpper\": 1, \"label\": \"\", \"qualified\": false, \"inverseQualified\": false, \"ordered\": false, \"inverseOrdered\": false, \"unique\": false, \"inverseUnique\": true, \"derived\": false, \"navigable\": true, \"tumlUri\": \"\", \"tumlOverloadedPostUri\": \"\", \"tumlMetaDataUri\": \"\", \"fieldType\": \"" + FieldType.Integer + "\", \"tumlLookupUri\": \"\", \"tumlCompositeParentLookupUri\": \"\"}",/* isChangeListenerAttribute */ false,/* tumlUri */ "",/* tumlOverloadedPostUri */ "",/* tumlMetaDataUri */ "",/* fieldType */ FieldType.Integer,/* tumlLookupUri */ "",/* tumlCompositeParentLookupUri */ "");
		private String _qualifiedName;
		private String _persistentName;
		private String _inverseName;
		private String _inverseQualifiedName;
		private boolean _associationClassOne;
		private boolean _memberEndOfAssociationClass;
		private String _associationClassPropertyName;
		private String _inverseAssociationClassPropertyName;
		private boolean _associationClassProperty;
		private boolean _onePrimitivePropertyOfAssociationClass;
		private boolean _onePrimitive;
		private Boolean _readOnly;
		private DataTypeEnum dataTypeEnum;
		private List<UmlgValidation> validations;
		private boolean _manyPrimitive;
		private boolean _oneEnumeration;
		private boolean _manyEnumeration;
		private boolean _controllingSide;
		private boolean _composite;
		private boolean _inverseComposite;
		private String _label;
		private boolean _oneToOne;
		private boolean _oneToMany;
		private boolean _manyToOne;
		private boolean _manyToMany;
		private int _upper;
		private int _lower;
		private int _inverseUpper;
		private boolean _qualified;
		private boolean _inverseQualified;
		private boolean _ordered;
		private boolean _inverseOrdered;
		private boolean _unique;
		private boolean _inverseUnique;
		private boolean _derived;
		private boolean _navigability;
		private Class _propertyType;
		private String _json;
		private boolean _changeListener;
		private String tumlUri;
		private String tumlOverloadedPostUri;
		private String tumlMetaDataUri;
		private FieldType fieldType;
		private String tumlLookupUri;
		private String tumlCompositeParentLookupUri;
		/**
		 * constructor for UserGroupRuntimePropertyEnum
		 * 
		 * @param _qualifiedName 
		 * @param _persistentName 
		 * @param _inverseName 
		 * @param _inverseQualifiedName 
		 * @param _associationClassOne 
		 * @param _memberEndOfAssociationClass 
		 * @param _associationClassPropertyName 
		 * @param _inverseAssociationClassPropertyName 
		 * @param _associationClassProperty 
		 * @param _onePrimitivePropertyOfAssociationClass 
		 * @param _onePrimitive 
		 * @param _readOnly 
		 * @param dataTypeEnum 
		 * @param validations 
		 * @param _manyPrimitive 
		 * @param _oneEnumeration 
		 * @param _manyEnumeration 
		 * @param _controllingSide 
		 * @param _composite 
		 * @param _inverseComposite 
		 * @param _label 
		 * @param _oneToOne 
		 * @param _oneToMany 
		 * @param _manyToOne 
		 * @param _manyToMany 
		 * @param _upper 
		 * @param _lower 
		 * @param _inverseUpper 
		 * @param _qualified 
		 * @param _inverseQualified 
		 * @param _ordered 
		 * @param _inverseOrdered 
		 * @param _unique 
		 * @param _inverseUnique 
		 * @param _derived 
		 * @param _navigability 
		 * @param _propertyType 
		 * @param _json 
		 * @param _changeListener 
		 * @param tumlUri 
		 * @param tumlOverloadedPostUri 
		 * @param tumlMetaDataUri 
		 * @param fieldType 
		 * @param tumlLookupUri 
		 * @param tumlCompositeParentLookupUri 
		 */
		private UserGroupRuntimePropertyEnum(String _qualifiedName, String _persistentName, String _inverseName, String _inverseQualifiedName, boolean _associationClassOne, boolean _memberEndOfAssociationClass, String _associationClassPropertyName, String _inverseAssociationClassPropertyName, boolean _associationClassProperty, boolean _onePrimitivePropertyOfAssociationClass, boolean _onePrimitive, Boolean _readOnly, DataTypeEnum dataTypeEnum, List<UmlgValidation> validations, boolean _manyPrimitive, boolean _oneEnumeration, boolean _manyEnumeration, boolean _controllingSide, boolean _composite, boolean _inverseComposite, String _label, boolean _oneToOne, boolean _oneToMany, boolean _manyToOne, boolean _manyToMany, int _upper, int _lower, int _inverseUpper, boolean _qualified, boolean _inverseQualified, boolean _ordered, boolean _inverseOrdered, boolean _unique, boolean _inverseUnique, boolean _derived, boolean _navigability, Class _propertyType, String _json, boolean _changeListener, String tumlUri, String tumlOverloadedPostUri, String tumlMetaDataUri, FieldType fieldType, String tumlLookupUri, String tumlCompositeParentLookupUri)  {
			this._qualifiedName = _qualifiedName;
			this._persistentName = _persistentName;
			this._inverseName = _inverseName;
			this._inverseQualifiedName = _inverseQualifiedName;
			this._associationClassOne = _associationClassOne;
			this._memberEndOfAssociationClass = _memberEndOfAssociationClass;
			this._associationClassPropertyName = _associationClassPropertyName;
			this._inverseAssociationClassPropertyName = _inverseAssociationClassPropertyName;
			this._associationClassProperty = _associationClassProperty;
			this._onePrimitivePropertyOfAssociationClass = _onePrimitivePropertyOfAssociationClass;
			this._onePrimitive = _onePrimitive;
			this._readOnly = _readOnly;
			this.dataTypeEnum = dataTypeEnum;
			this.validations = validations;
			this._manyPrimitive = _manyPrimitive;
			this._oneEnumeration = _oneEnumeration;
			this._manyEnumeration = _manyEnumeration;
			this._controllingSide = _controllingSide;
			this._composite = _composite;
			this._inverseComposite = _inverseComposite;
			this._label = _label;
			this._oneToOne = _oneToOne;
			this._oneToMany = _oneToMany;
			this._manyToOne = _manyToOne;
			this._manyToMany = _manyToMany;
			this._upper = _upper;
			this._lower = _lower;
			this._inverseUpper = _inverseUpper;
			this._qualified = _qualified;
			this._inverseQualified = _inverseQualified;
			this._ordered = _ordered;
			this._inverseOrdered = _inverseOrdered;
			this._unique = _unique;
			this._inverseUnique = _inverseUnique;
			this._derived = _derived;
			this._navigability = _navigability;
			this._propertyType = _propertyType;
			this._json = _json;
			this._changeListener = _changeListener;
			this.tumlUri = tumlUri;
			this.tumlOverloadedPostUri = tumlOverloadedPostUri;
			this.tumlMetaDataUri = tumlMetaDataUri;
			this.fieldType = fieldType;
			this.tumlLookupUri = tumlLookupUri;
			this.tumlCompositeParentLookupUri = tumlCompositeParentLookupUri;
		}
	
		static public String asJson() {
			int count = 1;
			StringBuilder sb = new StringBuilder();;
			sb.append("{\"name\": \"UserGroup\", ");
			sb.append("\"qualifiedName\": \"RootElement::org::multipoly::User::UserGroup\", ");
			sb.append("\"uri\": \"" + getUriToObject() + "\", ");
			sb.append("\"properties\": [");
			for ( UserGroupRuntimePropertyEnum l : UserGroupRuntimePropertyEnum.values() ) {
				sb.append(l.toJson());
				if ( count < UserGroupRuntimePropertyEnum.values().length ) {
					count++;
					sb.append(",");
				}
			}
			sb.append("]}");
			return sb.toString();
		}
		
		static public UserGroupRuntimePropertyEnum fromInverseQualifiedName(String inverseQualifiedName) {
			if ( id.getInverseQualifiedName().equals(inverseQualifiedName) ) {
				return id;
			}
			if ( RootElement.getInverseQualifiedName().equals(inverseQualifiedName) ) {
				return RootElement;
			}
			if ( user.getInverseQualifiedName().equals(inverseQualifiedName) ) {
				return user;
			}
			if ( Name.getInverseQualifiedName().equals(inverseQualifiedName) ) {
				return Name;
			}
			return null;
		}
		
		static public UserGroupRuntimePropertyEnum fromLabel(String _label) {
			if ( id.getLabel().equals(_label) ) {
				return id;
			}
			if ( RootElement.getLabel().equals(_label) ) {
				return RootElement;
			}
			if ( user.getLabel().equals(_label) ) {
				return user;
			}
			if ( Name.getLabel().equals(_label) ) {
				return Name;
			}
			return null;
		}
		
		static public UserGroupRuntimePropertyEnum fromQualifiedName(String qualifiedName) {
			if ( id.getQualifiedName().equals(qualifiedName) ) {
				return id;
			}
			if ( RootElement.getQualifiedName().equals(qualifiedName) ) {
				return RootElement;
			}
			if ( user.getQualifiedName().equals(qualifiedName) ) {
				return user;
			}
			if ( Name.getQualifiedName().equals(qualifiedName) ) {
				return Name;
			}
			return null;
		}
		
		public String getAssociationClassPropertyName() {
			return this._associationClassPropertyName;
		}
		
		public DataTypeEnum getDataTypeEnum() {
			return this.dataTypeEnum;
		}
		
		public FieldType getFieldType() {
			return this.fieldType;
		}
		
		public String getInverseAssociationClassPropertyName() {
			return this._inverseAssociationClassPropertyName;
		}
		
		public String getInverseName() {
			return this._inverseName;
		}
		
		public String getInverseQualifiedName() {
			return this._inverseQualifiedName;
		}
		
		public int getInverseUpper() {
			return this._inverseUpper;
		}
		
		public String getJson() {
			return this._json;
		}
		
		public String getLabel() {
			return this._label;
		}
		
		public String getLookupFor_usergroup_user() {
			return this.tumlLookupUri;
		}
		
		public int getLower() {
			return this._lower;
		}
		
		public String getPersistentName() {
			return this._persistentName;
		}
		
		public Class getPropertyType() {
			return this._propertyType;
		}
		
		public String getQualifiedName() {
			return this._qualifiedName;
		}
		
		public Boolean getReadOnly() {
			return this._readOnly;
		}
		
		public String getTumlCompositeParentLookupUri() {
			return this.tumlCompositeParentLookupUri;
		}
		
		public String getTumlMetaDataUri() {
			return this.tumlMetaDataUri;
		}
		
		public String getTumlOverloadedPostUri() {
			return this.tumlOverloadedPostUri;
		}
		
		public String getTumlUri() {
			return this.tumlUri;
		}
		
		public int getUpper() {
			return this._upper;
		}
		
		static public String getUriToObject() {
			return "/RootElement/usergroups/{usergroupId}";
		}
		
		public List<UmlgValidation> getValidations() {
			return this.validations;
		}
		
		public boolean isAssociationClassOne() {
			return this._associationClassOne;
		}
		
		public boolean isAssociationClassProperty() {
			return this._associationClassProperty;
		}
		
		public boolean isChangeListener() {
			return this._changeListener;
		}
		
		public boolean isComposite() {
			return this._composite;
		}
		
		public boolean isControllingSide() {
			return this._controllingSide;
		}
		
		public boolean isDerived() {
			return this._derived;
		}
		
		public boolean isInverseComposite() {
			return this._inverseComposite;
		}
		
		public boolean isInverseOrdered() {
			return this._inverseOrdered;
		}
		
		public boolean isInverseQualified() {
			return this._inverseQualified;
		}
		
		public boolean isInverseUnique() {
			return this._inverseUnique;
		}
		
		public boolean isManyEnumeration() {
			return this._manyEnumeration;
		}
		
		public boolean isManyPrimitive() {
			return this._manyPrimitive;
		}
		
		public boolean isManyToMany() {
			return this._manyToMany;
		}
		
		public boolean isManyToOne() {
			return this._manyToOne;
		}
		
		public boolean isMemberEndOfAssociationClass() {
			return this._memberEndOfAssociationClass;
		}
		
		public boolean isNavigability() {
			return this._navigability;
		}
		
		public boolean isOneEnumeration() {
			return this._oneEnumeration;
		}
		
		public boolean isOnePrimitive() {
			return this._onePrimitive;
		}
		
		public boolean isOnePrimitivePropertyOfAssociationClass() {
			return this._onePrimitivePropertyOfAssociationClass;
		}
		
		public boolean isOneToMany() {
			return this._oneToMany;
		}
		
		public boolean isOneToOne() {
			return this._oneToOne;
		}
		
		public boolean isOrdered() {
			return this._ordered;
		}
		
		public boolean isQualified() {
			return this._qualified;
		}
		
		public boolean isUnique() {
			return this._unique;
		}
		
		@Override
		public boolean isValid(int elementCount) {
			if ( isQualified() ) {
				return elementCount >= getLower();
			} else {
				return (getUpper() == -1 || elementCount <= getUpper()) && elementCount >= getLower();
			}
		}
		
		@Override
		public String toJson() {
			return getJson();
		}
	
	
	}
}