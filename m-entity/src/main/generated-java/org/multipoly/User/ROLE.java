package org.multipoly.User;

import org.apache.tinkerpop.gremlin.process.traversal.P;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.umlg.runtime.adaptor.UMLG;
import org.umlg.runtime.adaptor.UmlgLabelConverterFactory;
import org.umlg.runtime.collection.UmlgRuntimeProperty;
import org.umlg.runtime.collection.persistent.UmlgSetClosableIterableImpl;
import org.umlg.runtime.domain.DataTypeEnum;
import org.umlg.runtime.domain.UmlgEnum;
import org.umlg.runtime.validation.UmlgValidation;

import java.util.Collections;
import java.util.List;

public enum ROLE implements UmlgEnum {
	PALYER,
	ADMIN;

	/**
	 * constructor for ROLE
	 */
	private ROLE()  {
	}

	static public ROLE fromJson(String json) {
		if ( json == null || json.equals("null") ) {
			return null;
		} else {
			return ROLE.valueOf(json);
		}
	}
	
	@Override
	public String getQualifiedName() {
		return "RootElement::org::multipoly::User::ROLE";
	}
	
	/**
	 * This is not a proper UML property, it is always a Set
	 */
	public UmlgSetClosableIterableImpl<User> getUser() {
		GraphTraversalSource graph = UMLG.get().getUnderlyingGraph().traversal();
		GraphTraversal<Vertex, Vertex> traversal = graph.V().has("User", "role", P.within(name()));
		return new UmlgSetClosableIterableImpl<>(traversal, ROLERuntimePropertyEnum.user);
	}
	
	public String toJson() {
		return name();
	}

	static public enum ROLERuntimePropertyEnum implements UmlgRuntimeProperty {
		user(/* qualifiedName */ "RootElement::org::multipoly::User::ROLE::user",/* persistentName */ "user",/* inverseName */ "role",/* inverseQualifiedName */ "RootElement::org::multipoly::User::User::role",/* isAssociationClassOne */ false,/* isMemberEndOfAssociationClass */ false,/* associationClassPropertyNameField */ "null",/* inverseAssociationClassPropertyNameField */ "null",/* isAssociationClassProperty */ false,/* isOnePrimitivePropertyOfAssociationClass */ false,/* isOnePrimitive */ false,/* isReadOnly */ false,/* dataTypeEnum */ null,/* validations */ Collections.<UmlgValidation>emptyList(),/* isManyPrimitive */ false,/* oneEnumeration */ false,/* manyEnumeration */ false,/* isControllingSide */ true,/* isComposite */ false,/* isInverseComposite */ false,/* label */ UmlgLabelConverterFactory.getUmlgLabelConverter().convert("user_role"),/* isOneToOne */ false,/* isOneToMany */ true,/* isManyToOne */ false,/* isManyToMany */ false,/* upper */ -1,/* lower */ 0,/* inverseUpper */ 1,/* isQualified */ false,/* isInverseQualified */ false,/* isOrdered */ false,/* isInverseOrdered */ false,/* isUnique */ true,/* isInverseUnique */ true,/* isDerived */ false,/* isNavigable */ true,/* propertyType */ User.class,/* json */ "{\"name\": \"user\", \"associationClassOne\": false, \"memberEndOfAssociationClass\": false, \"associationClassPropertyName\": null, \"inverseAssociationClassPropertyName\": null, \"associationClassProperty\": false, \"onePrimitivePropertyOfAssociationClass\": false, \"onePrimitive\": false, \"readOnly\": false, \"dataTypeEnum\": null, \"validations\": null, \"qualifiedName\": \"RootElement::org::multipoly::User::ROLE::user\", \"persistentName\": \"user\", \"inverseName\": \"role\", \"inverseQualifiedName\": \"RootElement::org::multipoly::User::User::role\", \"manyPrimitive\": false, \"oneEnumeration\": false, \"manyEnumeration\": false, \"controllingSide\": true, \"composite\": false, \"inverseComposite\": false, \"oneToOne\": false, \"oneToMany\": true, \"manyToOne\": false, \"manyToMany\": false, \"upper\": -1, \"lower\": 0, \"inverseUpper\": 1, \"label\": \"user_role\", \"qualified\": false, \"inverseQualified\": false, \"ordered\": false, \"inverseOrdered\": false, \"unique\": true, \"inverseUnique\": true, \"derived\": false, \"navigable\": true}",/* isChangeListenerAttribute */ false),
		RootElement(/* qualifiedName */ "RootElement",/* persistentName */ "RootElement",/* inverseName */ "inverseOfRootElement",/* inverseQualifiedName */ "inverseOfRootElement",/* isAssociationClassOne */ false,/* isMemberEndOfAssociationClass */ false,/* associationClassPropertyNameField */ "null",/* inverseAssociationClassPropertyNameField */ "null",/* isAssociationClassProperty */ false,/* isOnePrimitivePropertyOfAssociationClass */ false,/* isOnePrimitive */ false,/* isReadOnly */ false,/* dataTypeEnum */ null,/* validations */ Collections.<UmlgValidation>emptyList(),/* isManyPrimitive */ false,/* oneEnumeration */ false,/* manyEnumeration */ false,/* isControllingSide */ true,/* isComposite */ false,/* isInverseComposite */ true,/* label */ UmlgLabelConverterFactory.getUmlgLabelConverter().convert("rootROLE"),/* isOneToOne */ true,/* isOneToMany */ false,/* isManyToOne */ false,/* isManyToMany */ false,/* upper */ -1,/* lower */ 0,/* inverseUpper */ 1,/* isQualified */ false,/* isInverseQualified */ false,/* isOrdered */ false,/* isInverseOrdered */ false,/* isUnique */ false,/* isInverseUnique */ false,/* isDerived */ false,/* isNavigable */ false,/* propertyType */ Object.class,/* json */ "{\"name\": \"RootElement\", \"associationClassOne\": false, \"memberEndOfAssociationClass\": false, \"associationClassPropertyName\": null, \"inverseAssociationClassPropertyName\": null, \"associationClassProperty\": false, \"onePrimitivePropertyOfAssociationClass\": false, \"onePrimitive\": false, \"readOnly\": false, \"dataTypeEnum\": null, \"validations\": null, \"qualifiedName\": \"RootElement\", \"persistentName\": \"RootElement\", \"inverseName\": \"inverseOfRootElement\", \"inverseQualifiedName\": \"inverseOfRootElement\", \"manyPrimitive\": false, \"oneEnumeration\": false, \"manyEnumeration\": false, \"controllingSide\": true, \"composite\": false, \"inverseComposite\": true, \"oneToOne\": true, \"oneToMany\": false, \"manyToOne\": false, \"manyToMany\": false, \"upper\": -1, \"lower\": 0, \"inverseUpper\": 1, \"label\": \"rootROLE\", \"qualified\": false, \"inverseQualified\": false, \"ordered\": false, \"inverseOrdered\": false, \"unique\": false, \"inverseUnique\": false, \"derived\": false, \"navigable\": false}",/* isChangeListenerAttribute */ false);
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
		/**
		 * constructor for ROLERuntimePropertyEnum
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
		 */
		private ROLERuntimePropertyEnum(String _qualifiedName, String _persistentName, String _inverseName, String _inverseQualifiedName, boolean _associationClassOne, boolean _memberEndOfAssociationClass, String _associationClassPropertyName, String _inverseAssociationClassPropertyName, boolean _associationClassProperty, boolean _onePrimitivePropertyOfAssociationClass, boolean _onePrimitive, Boolean _readOnly, DataTypeEnum dataTypeEnum, List<UmlgValidation> validations, boolean _manyPrimitive, boolean _oneEnumeration, boolean _manyEnumeration, boolean _controllingSide, boolean _composite, boolean _inverseComposite, String _label, boolean _oneToOne, boolean _oneToMany, boolean _manyToOne, boolean _manyToMany, int _upper, int _lower, int _inverseUpper, boolean _qualified, boolean _inverseQualified, boolean _ordered, boolean _inverseOrdered, boolean _unique, boolean _inverseUnique, boolean _derived, boolean _navigability, Class _propertyType, String _json, boolean _changeListener)  {
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
		}
	
		static public String asJson() {
			int count = 1;
			StringBuilder sb = new StringBuilder();;
			sb.append("{\"name\": \"ROLE\", ");
			sb.append("\"qualifiedName\": \"RootElement::org::multipoly::User::ROLE\", ");
			sb.append("\"uri\": \"TODO\", ");
			sb.append("\"properties\": [");
			for ( ROLERuntimePropertyEnum l : ROLERuntimePropertyEnum.values() ) {
				sb.append(l.toJson());
				if ( count < ROLERuntimePropertyEnum.values().length ) {
					count++;
					sb.append(",");
				}
			}
			sb.append("]}");
			return sb.toString();
		}
		
		static public ROLERuntimePropertyEnum fromInverseQualifiedName(String inverseQualifiedName) {
			if ( RootElement.getInverseQualifiedName().equals(inverseQualifiedName) ) {
				return RootElement;
			}
			if ( user.getInverseQualifiedName().equals(inverseQualifiedName) ) {
				return user;
			}
			return null;
		}
		
		static public ROLERuntimePropertyEnum fromLabel(String _label) {
			if ( RootElement.getLabel().equals(_label) ) {
				return RootElement;
			}
			if ( user.getLabel().equals(_label) ) {
				return user;
			}
			return null;
		}
		
		static public ROLERuntimePropertyEnum fromQualifiedName(String qualifiedName) {
			if ( RootElement.getQualifiedName().equals(qualifiedName) ) {
				return RootElement;
			}
			if ( user.getQualifiedName().equals(qualifiedName) ) {
				return user;
			}
			return null;
		}
		
		public String getAssociationClassPropertyName() {
			return this._associationClassPropertyName;
		}
		
		public DataTypeEnum getDataTypeEnum() {
			return this.dataTypeEnum;
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
		
		public int getUpper() {
			return this._upper;
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