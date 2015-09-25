package org.umlg.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.multipoly.Board.Block;
import org.multipoly.Board.Board;
import org.multipoly.Board.Edward;
import org.multipoly.Board.meta.BlockMeta;
import org.multipoly.Board.meta.BoardMeta;
import org.multipoly.Board.meta.EdwardMeta;
import org.multipoly.Notification.Notification;
import org.multipoly.Notification.meta.NotificationMeta;
import org.multipoly.User;
import org.multipoly.UserGroup;
import org.multipoly.meta.UserGroupMeta;
import org.multipoly.meta.UserMeta;
import org.umlg.runtime.adaptor.UMLG;
import org.umlg.runtime.adaptor.UmlgLabelConverterFactory;
import org.umlg.runtime.collection.UmlgRuntimeProperty;
import org.umlg.runtime.collection.UmlgSet;
import org.umlg.runtime.collection.memory.UmlgMemorySequence;
import org.umlg.runtime.domain.DataTypeEnum;
import org.umlg.runtime.domain.UmlgApplicationNode;
import org.umlg.runtime.domain.UmlgMetaNode;
import org.umlg.runtime.domain.restlet.FieldType;
import org.umlg.runtime.validation.UmlgValidation;

/** This class represents the model RootElement.
It is a singleton and allows on access to all the root classes in the model.
 */
public class RootElement implements UmlgApplicationNode {
	static public RootElement INSTANCE = new RootElement();

	/**
	 * default constructor for RootElement
	 */
	private RootElement()  {
	}

	public UmlgSet<? extends Block> getBlock() {
		return org.multipoly.Board.Block.allInstances();
	}
	
	public BlockMeta getBlockMeta() {
		return org.multipoly.Board.meta.BlockMeta.getInstance();
	}
	
	public UmlgSet<? extends Board> getBoard() {
		return org.multipoly.Board.Board.allInstances();
	}
	
	public BoardMeta getBoardMeta() {
		return org.multipoly.Board.meta.BoardMeta.getInstance();
	}
	
	public UmlgSet<? extends Edward> getEdward() {
		return org.multipoly.Board.Edward.allInstances();
	}
	
	public EdwardMeta getEdwardMeta() {
		return org.multipoly.Board.meta.EdwardMeta.getInstance();
	}
	
	@Override
	public Object getId() {
		return getRootVertex().id();
	}
	
	@Override
	public UmlgMetaNode getMetaClassForQualifiedName(String qualifiedName) {
		switch ( qualifiedName ) {
			case "RootElement::org::multipoly::Notification::Notification":
				return getNotificationMeta();
		
			case "RootElement::org::multipoly::Board::Board":
				return getBoardMeta();
		
			case "RootElement::org::multipoly::Board::Block":
				return getBlockMeta();
		
			case "RootElement::org::multipoly::Board::Edward":
				return getEdwardMeta();
		
			case "RootElement::org::multipoly::UserGroup":
				return getUserGroupMeta();
		
			case "RootElement::org::multipoly::User":
				return getUserMeta();
		
			default:
				throw new IllegalArgumentException("Unknown qualified name: " + qualifiedName + "!");
		}
	}
	
	public UmlgSet<? extends Notification> getNotification() {
		return org.multipoly.Notification.Notification.allInstances();
	}
	
	public NotificationMeta getNotificationMeta() {
		return org.multipoly.Notification.meta.NotificationMeta.getInstance();
	}
	
	public UmlgSet<? extends User> getUser() {
		return org.multipoly.User.allInstances();
	}
	
	public UmlgSet<? extends UserGroup> getUserGroup() {
		return org.multipoly.UserGroup.allInstances();
	}
	
	public UserGroupMeta getUserGroupMeta() {
		return org.multipoly.meta.UserGroupMeta.getInstance();
	}
	
	public UserMeta getUserMeta() {
		return org.multipoly.meta.UserMeta.getInstance();
	}
	
	public String toJson() {
		return "{\"id\": " + getId() + "}";
	}
	
	private Vertex getRootVertex() {
		return UMLG.get().getRoot();
	}

	static public enum RootElementRuntimePropertyEnum implements UmlgRuntimeProperty {
		RootElement(/* qualifiedName */ "RootElement",/* persistentName */ "RootElement",/* inverseName */ "inverseOfRootElement",/* inverseQualifiedName */ "inverseOfRootElement",/* isAssociationClassOne */ false,/* isMemberEndOfAssociationClass */ false,/* associationClassPropertyNameField */ "null",/* inverseAssociationClassPropertyNameField */ "null",/* isAssociationClassProperty */ false,/* isOnePrimitivePropertyOfAssociationClass */ false,/* isOnePrimitive */ false,/* isReadOnly */ false,/* dataTypeEnum */ null,/* validations */ Collections.<UmlgValidation>emptyList(),/* isManyPrimitive */ false,/* oneEnumeration */ false,/* manyEnumeration */ false,/* isControllingSide */ true,/* isComposite */ false,/* isInverseComposite */ true,/* label */ UmlgLabelConverterFactory.getUmlgLabelConverter().convert("rootRootElement"),/* isOneToOne */ true,/* isOneToMany */ false,/* isManyToOne */ false,/* isManyToMany */ false,/* upper */ -1,/* lower */ 0,/* inverseUpper */ 1,/* isQualified */ false,/* isInverseQualified */ false,/* isOrdered */ false,/* isInverseOrdered */ false,/* isUnique */ false,/* isInverseUnique */ false,/* isDerived */ false,/* isNavigable */ false,/* propertyType */ Object.class,/* json */ "{\"name\": \"RootElement\", \"associationClassOne\": false, \"memberEndOfAssociationClass\": false, \"associationClassPropertyName\": null, \"inverseAssociationClassPropertyName\": null, \"associationClassProperty\": false, \"onePrimitivePropertyOfAssociationClass\": false, \"onePrimitive\": false, \"readOnly\": false, \"dataTypeEnum\": null, \"validations\": null, \"qualifiedName\": \"RootElement\", \"persistentName\": \"RootElement\", \"inverseName\": \"inverseOfRootElement\", \"inverseQualifiedName\": \"inverseOfRootElement\", \"manyPrimitive\": false, \"oneEnumeration\": false, \"manyEnumeration\": false, \"controllingSide\": true, \"composite\": false, \"inverseComposite\": true, \"oneToOne\": true, \"oneToMany\": false, \"manyToOne\": false, \"manyToMany\": false, \"upper\": -1, \"lower\": 0, \"inverseUpper\": 1, \"label\": \"rootRootElement\", \"qualified\": false, \"inverseQualified\": false, \"ordered\": false, \"inverseOrdered\": false, \"unique\": false, \"inverseUnique\": false, \"derived\": false, \"navigable\": false, \"tumlMetaDataUri\": \"\", \"tumlUri\": \"/RootElement\", \"tumlOverloadedPostUri\": \"/RootElement\", \"fieldType\": \"" + FieldType.Date + "\"}",/* isChangeListenerAttribute */ false,/* tumlMetaDataUri */ "",/* tumlUri */ "/RootElement",/* tumlOverloadedPostUri */ "/RootElement",/* fieldType */ FieldType.Date),
		notification(/* qualifiedName */ "RootElement::org::multipoly::Notification::Notification",/* persistentName */ "Notification",/* inverseName */ "inverseOf::Notification",/* inverseQualifiedName */ "inverseOf::RootElement::org::multipoly::Notification::Notification",/* isAssociationClassOne */ false,/* isMemberEndOfAssociationClass */ false,/* associationClassPropertyNameField */ "null",/* inverseAssociationClassPropertyNameField */ "null",/* isAssociationClassProperty */ false,/* isOnePrimitivePropertyOfAssociationClass */ false,/* isOnePrimitive */ false,/* isReadOnly */ false,/* dataTypeEnum */ null,/* validations */ Collections.<UmlgValidation>emptyList(),/* isManyPrimitive */ false,/* oneEnumeration */ false,/* manyEnumeration */ false,/* isControllingSide */ false,/* isComposite */ true,/* isInverseComposite */ false,/* label */ UmlgLabelConverterFactory.getUmlgLabelConverter().convert("rootNotification"),/* isOneToOne */ false,/* isOneToMany */ true,/* isManyToOne */ false,/* isManyToMany */ false,/* upper */ -1,/* lower */ 0,/* inverseUpper */ 1,/* isQualified */ false,/* isInverseQualified */ false,/* isOrdered */ false,/* isInverseOrdered */ false,/* isUnique */ true,/* isInverseUnique */ true,/* isDerived */ false,/* isNavigable */ true,/* propertyType */ Notification.class,/* json */ "{\"name\": \"notification\", \"associationClassOne\": false, \"memberEndOfAssociationClass\": false, \"associationClassPropertyName\": null, \"inverseAssociationClassPropertyName\": null, \"associationClassProperty\": false, \"onePrimitivePropertyOfAssociationClass\": false, \"onePrimitive\": false, \"readOnly\": false, \"dataTypeEnum\": null, \"validations\": null, \"qualifiedName\": \"RootElement::org::multipoly::Notification::Notification\", \"persistentName\": \"Notification\", \"inverseName\": \"inverseOf::Notification\", \"inverseQualifiedName\": \"inverseOf::RootElement::org::multipoly::Notification::Notification\", \"manyPrimitive\": false, \"oneEnumeration\": false, \"manyEnumeration\": false, \"controllingSide\": false, \"composite\": true, \"inverseComposite\": false, \"oneToOne\": false, \"oneToMany\": true, \"manyToOne\": false, \"manyToMany\": false, \"upper\": -1, \"lower\": 0, \"inverseUpper\": 1, \"label\": \"rootNotification\", \"qualified\": false, \"inverseQualified\": false, \"ordered\": false, \"inverseOrdered\": false, \"unique\": true, \"inverseUnique\": true, \"derived\": false, \"navigable\": true, \"tumlMetaDataUri\": \"/RootElement/notificationMetaData\", \"tumlUri\": \"/RootElement/notifications\", \"tumlOverloadedPostUri\": \"/RootElement/overloadedpost/notifications\", \"fieldType\": \"" + FieldType.Date + "\"}",/* isChangeListenerAttribute */ false,/* tumlMetaDataUri */ "/RootElement/notificationMetaData",/* tumlUri */ "/RootElement/notifications",/* tumlOverloadedPostUri */ "/RootElement/overloadedpost/notifications",/* fieldType */ FieldType.Date),
		board(/* qualifiedName */ "RootElement::org::multipoly::Board::Board",/* persistentName */ "Board",/* inverseName */ "inverseOf::Board",/* inverseQualifiedName */ "inverseOf::RootElement::org::multipoly::Board::Board",/* isAssociationClassOne */ false,/* isMemberEndOfAssociationClass */ false,/* associationClassPropertyNameField */ "null",/* inverseAssociationClassPropertyNameField */ "null",/* isAssociationClassProperty */ false,/* isOnePrimitivePropertyOfAssociationClass */ false,/* isOnePrimitive */ false,/* isReadOnly */ false,/* dataTypeEnum */ null,/* validations */ Collections.<UmlgValidation>emptyList(),/* isManyPrimitive */ false,/* oneEnumeration */ false,/* manyEnumeration */ false,/* isControllingSide */ false,/* isComposite */ true,/* isInverseComposite */ false,/* label */ UmlgLabelConverterFactory.getUmlgLabelConverter().convert("rootBoard"),/* isOneToOne */ false,/* isOneToMany */ true,/* isManyToOne */ false,/* isManyToMany */ false,/* upper */ -1,/* lower */ 0,/* inverseUpper */ 1,/* isQualified */ false,/* isInverseQualified */ false,/* isOrdered */ false,/* isInverseOrdered */ false,/* isUnique */ true,/* isInverseUnique */ true,/* isDerived */ false,/* isNavigable */ true,/* propertyType */ Board.class,/* json */ "{\"name\": \"board\", \"associationClassOne\": false, \"memberEndOfAssociationClass\": false, \"associationClassPropertyName\": null, \"inverseAssociationClassPropertyName\": null, \"associationClassProperty\": false, \"onePrimitivePropertyOfAssociationClass\": false, \"onePrimitive\": false, \"readOnly\": false, \"dataTypeEnum\": null, \"validations\": null, \"qualifiedName\": \"RootElement::org::multipoly::Board::Board\", \"persistentName\": \"Board\", \"inverseName\": \"inverseOf::Board\", \"inverseQualifiedName\": \"inverseOf::RootElement::org::multipoly::Board::Board\", \"manyPrimitive\": false, \"oneEnumeration\": false, \"manyEnumeration\": false, \"controllingSide\": false, \"composite\": true, \"inverseComposite\": false, \"oneToOne\": false, \"oneToMany\": true, \"manyToOne\": false, \"manyToMany\": false, \"upper\": -1, \"lower\": 0, \"inverseUpper\": 1, \"label\": \"rootBoard\", \"qualified\": false, \"inverseQualified\": false, \"ordered\": false, \"inverseOrdered\": false, \"unique\": true, \"inverseUnique\": true, \"derived\": false, \"navigable\": true, \"tumlMetaDataUri\": \"/RootElement/boardMetaData\", \"tumlUri\": \"/RootElement/boards\", \"tumlOverloadedPostUri\": \"/RootElement/overloadedpost/boards\", \"fieldType\": \"" + FieldType.Date + "\"}",/* isChangeListenerAttribute */ false,/* tumlMetaDataUri */ "/RootElement/boardMetaData",/* tumlUri */ "/RootElement/boards",/* tumlOverloadedPostUri */ "/RootElement/overloadedpost/boards",/* fieldType */ FieldType.Date),
		block(/* qualifiedName */ "RootElement::org::multipoly::Board::Block",/* persistentName */ "Block",/* inverseName */ "inverseOf::Block",/* inverseQualifiedName */ "inverseOf::RootElement::org::multipoly::Board::Block",/* isAssociationClassOne */ false,/* isMemberEndOfAssociationClass */ false,/* associationClassPropertyNameField */ "null",/* inverseAssociationClassPropertyNameField */ "null",/* isAssociationClassProperty */ false,/* isOnePrimitivePropertyOfAssociationClass */ false,/* isOnePrimitive */ false,/* isReadOnly */ false,/* dataTypeEnum */ null,/* validations */ Collections.<UmlgValidation>emptyList(),/* isManyPrimitive */ false,/* oneEnumeration */ false,/* manyEnumeration */ false,/* isControllingSide */ false,/* isComposite */ true,/* isInverseComposite */ false,/* label */ UmlgLabelConverterFactory.getUmlgLabelConverter().convert("rootBlock"),/* isOneToOne */ false,/* isOneToMany */ true,/* isManyToOne */ false,/* isManyToMany */ false,/* upper */ -1,/* lower */ 0,/* inverseUpper */ 1,/* isQualified */ false,/* isInverseQualified */ false,/* isOrdered */ false,/* isInverseOrdered */ false,/* isUnique */ true,/* isInverseUnique */ true,/* isDerived */ false,/* isNavigable */ true,/* propertyType */ Block.class,/* json */ "{\"name\": \"block\", \"associationClassOne\": false, \"memberEndOfAssociationClass\": false, \"associationClassPropertyName\": null, \"inverseAssociationClassPropertyName\": null, \"associationClassProperty\": false, \"onePrimitivePropertyOfAssociationClass\": false, \"onePrimitive\": false, \"readOnly\": false, \"dataTypeEnum\": null, \"validations\": null, \"qualifiedName\": \"RootElement::org::multipoly::Board::Block\", \"persistentName\": \"Block\", \"inverseName\": \"inverseOf::Block\", \"inverseQualifiedName\": \"inverseOf::RootElement::org::multipoly::Board::Block\", \"manyPrimitive\": false, \"oneEnumeration\": false, \"manyEnumeration\": false, \"controllingSide\": false, \"composite\": true, \"inverseComposite\": false, \"oneToOne\": false, \"oneToMany\": true, \"manyToOne\": false, \"manyToMany\": false, \"upper\": -1, \"lower\": 0, \"inverseUpper\": 1, \"label\": \"rootBlock\", \"qualified\": false, \"inverseQualified\": false, \"ordered\": false, \"inverseOrdered\": false, \"unique\": true, \"inverseUnique\": true, \"derived\": false, \"navigable\": true, \"tumlMetaDataUri\": \"/RootElement/blockMetaData\", \"tumlUri\": \"/RootElement/blocks\", \"tumlOverloadedPostUri\": \"/RootElement/overloadedpost/blocks\", \"fieldType\": \"" + FieldType.Date + "\"}",/* isChangeListenerAttribute */ false,/* tumlMetaDataUri */ "/RootElement/blockMetaData",/* tumlUri */ "/RootElement/blocks",/* tumlOverloadedPostUri */ "/RootElement/overloadedpost/blocks",/* fieldType */ FieldType.Date),
		edward(/* qualifiedName */ "RootElement::org::multipoly::Board::Edward",/* persistentName */ "Edward",/* inverseName */ "inverseOf::Edward",/* inverseQualifiedName */ "inverseOf::RootElement::org::multipoly::Board::Edward",/* isAssociationClassOne */ false,/* isMemberEndOfAssociationClass */ false,/* associationClassPropertyNameField */ "null",/* inverseAssociationClassPropertyNameField */ "null",/* isAssociationClassProperty */ false,/* isOnePrimitivePropertyOfAssociationClass */ false,/* isOnePrimitive */ false,/* isReadOnly */ false,/* dataTypeEnum */ null,/* validations */ Collections.<UmlgValidation>emptyList(),/* isManyPrimitive */ false,/* oneEnumeration */ false,/* manyEnumeration */ false,/* isControllingSide */ false,/* isComposite */ true,/* isInverseComposite */ false,/* label */ UmlgLabelConverterFactory.getUmlgLabelConverter().convert("rootEdward"),/* isOneToOne */ false,/* isOneToMany */ true,/* isManyToOne */ false,/* isManyToMany */ false,/* upper */ -1,/* lower */ 0,/* inverseUpper */ 1,/* isQualified */ false,/* isInverseQualified */ false,/* isOrdered */ false,/* isInverseOrdered */ false,/* isUnique */ true,/* isInverseUnique */ true,/* isDerived */ false,/* isNavigable */ true,/* propertyType */ Edward.class,/* json */ "{\"name\": \"edward\", \"associationClassOne\": false, \"memberEndOfAssociationClass\": false, \"associationClassPropertyName\": null, \"inverseAssociationClassPropertyName\": null, \"associationClassProperty\": false, \"onePrimitivePropertyOfAssociationClass\": false, \"onePrimitive\": false, \"readOnly\": false, \"dataTypeEnum\": null, \"validations\": null, \"qualifiedName\": \"RootElement::org::multipoly::Board::Edward\", \"persistentName\": \"Edward\", \"inverseName\": \"inverseOf::Edward\", \"inverseQualifiedName\": \"inverseOf::RootElement::org::multipoly::Board::Edward\", \"manyPrimitive\": false, \"oneEnumeration\": false, \"manyEnumeration\": false, \"controllingSide\": false, \"composite\": true, \"inverseComposite\": false, \"oneToOne\": false, \"oneToMany\": true, \"manyToOne\": false, \"manyToMany\": false, \"upper\": -1, \"lower\": 0, \"inverseUpper\": 1, \"label\": \"rootEdward\", \"qualified\": false, \"inverseQualified\": false, \"ordered\": false, \"inverseOrdered\": false, \"unique\": true, \"inverseUnique\": true, \"derived\": false, \"navigable\": true, \"tumlMetaDataUri\": \"/RootElement/edwardMetaData\", \"tumlUri\": \"/RootElement/edwards\", \"tumlOverloadedPostUri\": \"/RootElement/overloadedpost/edwards\", \"fieldType\": \"" + FieldType.Date + "\"}",/* isChangeListenerAttribute */ false,/* tumlMetaDataUri */ "/RootElement/edwardMetaData",/* tumlUri */ "/RootElement/edwards",/* tumlOverloadedPostUri */ "/RootElement/overloadedpost/edwards",/* fieldType */ FieldType.Date),
		userGroup(/* qualifiedName */ "RootElement::org::multipoly::UserGroup",/* persistentName */ "UserGroup",/* inverseName */ "inverseOf::UserGroup",/* inverseQualifiedName */ "inverseOf::RootElement::org::multipoly::UserGroup",/* isAssociationClassOne */ false,/* isMemberEndOfAssociationClass */ false,/* associationClassPropertyNameField */ "null",/* inverseAssociationClassPropertyNameField */ "null",/* isAssociationClassProperty */ false,/* isOnePrimitivePropertyOfAssociationClass */ false,/* isOnePrimitive */ false,/* isReadOnly */ false,/* dataTypeEnum */ null,/* validations */ Collections.<UmlgValidation>emptyList(),/* isManyPrimitive */ false,/* oneEnumeration */ false,/* manyEnumeration */ false,/* isControllingSide */ false,/* isComposite */ true,/* isInverseComposite */ false,/* label */ UmlgLabelConverterFactory.getUmlgLabelConverter().convert("rootUserGroup"),/* isOneToOne */ false,/* isOneToMany */ true,/* isManyToOne */ false,/* isManyToMany */ false,/* upper */ -1,/* lower */ 0,/* inverseUpper */ 1,/* isQualified */ false,/* isInverseQualified */ false,/* isOrdered */ false,/* isInverseOrdered */ false,/* isUnique */ true,/* isInverseUnique */ true,/* isDerived */ false,/* isNavigable */ true,/* propertyType */ UserGroup.class,/* json */ "{\"name\": \"userGroup\", \"associationClassOne\": false, \"memberEndOfAssociationClass\": false, \"associationClassPropertyName\": null, \"inverseAssociationClassPropertyName\": null, \"associationClassProperty\": false, \"onePrimitivePropertyOfAssociationClass\": false, \"onePrimitive\": false, \"readOnly\": false, \"dataTypeEnum\": null, \"validations\": null, \"qualifiedName\": \"RootElement::org::multipoly::UserGroup\", \"persistentName\": \"UserGroup\", \"inverseName\": \"inverseOf::UserGroup\", \"inverseQualifiedName\": \"inverseOf::RootElement::org::multipoly::UserGroup\", \"manyPrimitive\": false, \"oneEnumeration\": false, \"manyEnumeration\": false, \"controllingSide\": false, \"composite\": true, \"inverseComposite\": false, \"oneToOne\": false, \"oneToMany\": true, \"manyToOne\": false, \"manyToMany\": false, \"upper\": -1, \"lower\": 0, \"inverseUpper\": 1, \"label\": \"rootUserGroup\", \"qualified\": false, \"inverseQualified\": false, \"ordered\": false, \"inverseOrdered\": false, \"unique\": true, \"inverseUnique\": true, \"derived\": false, \"navigable\": true, \"tumlMetaDataUri\": \"/RootElement/usergroupMetaData\", \"tumlUri\": \"/RootElement/usergroups\", \"tumlOverloadedPostUri\": \"/RootElement/overloadedpost/usergroups\", \"fieldType\": \"" + FieldType.Date + "\"}",/* isChangeListenerAttribute */ false,/* tumlMetaDataUri */ "/RootElement/usergroupMetaData",/* tumlUri */ "/RootElement/usergroups",/* tumlOverloadedPostUri */ "/RootElement/overloadedpost/usergroups",/* fieldType */ FieldType.Date),
		user(/* qualifiedName */ "RootElement::org::multipoly::User",/* persistentName */ "User",/* inverseName */ "inverseOf::User",/* inverseQualifiedName */ "inverseOf::RootElement::org::multipoly::User",/* isAssociationClassOne */ false,/* isMemberEndOfAssociationClass */ false,/* associationClassPropertyNameField */ "null",/* inverseAssociationClassPropertyNameField */ "null",/* isAssociationClassProperty */ false,/* isOnePrimitivePropertyOfAssociationClass */ false,/* isOnePrimitive */ false,/* isReadOnly */ false,/* dataTypeEnum */ null,/* validations */ Collections.<UmlgValidation>emptyList(),/* isManyPrimitive */ false,/* oneEnumeration */ false,/* manyEnumeration */ false,/* isControllingSide */ false,/* isComposite */ true,/* isInverseComposite */ false,/* label */ UmlgLabelConverterFactory.getUmlgLabelConverter().convert("rootUser"),/* isOneToOne */ false,/* isOneToMany */ true,/* isManyToOne */ false,/* isManyToMany */ false,/* upper */ -1,/* lower */ 0,/* inverseUpper */ 1,/* isQualified */ false,/* isInverseQualified */ false,/* isOrdered */ false,/* isInverseOrdered */ false,/* isUnique */ true,/* isInverseUnique */ true,/* isDerived */ false,/* isNavigable */ true,/* propertyType */ User.class,/* json */ "{\"name\": \"user\", \"associationClassOne\": false, \"memberEndOfAssociationClass\": false, \"associationClassPropertyName\": null, \"inverseAssociationClassPropertyName\": null, \"associationClassProperty\": false, \"onePrimitivePropertyOfAssociationClass\": false, \"onePrimitive\": false, \"readOnly\": false, \"dataTypeEnum\": null, \"validations\": null, \"qualifiedName\": \"RootElement::org::multipoly::User\", \"persistentName\": \"User\", \"inverseName\": \"inverseOf::User\", \"inverseQualifiedName\": \"inverseOf::RootElement::org::multipoly::User\", \"manyPrimitive\": false, \"oneEnumeration\": false, \"manyEnumeration\": false, \"controllingSide\": false, \"composite\": true, \"inverseComposite\": false, \"oneToOne\": false, \"oneToMany\": true, \"manyToOne\": false, \"manyToMany\": false, \"upper\": -1, \"lower\": 0, \"inverseUpper\": 1, \"label\": \"rootUser\", \"qualified\": false, \"inverseQualified\": false, \"ordered\": false, \"inverseOrdered\": false, \"unique\": true, \"inverseUnique\": true, \"derived\": false, \"navigable\": true, \"tumlMetaDataUri\": \"/RootElement/userMetaData\", \"tumlUri\": \"/RootElement/users\", \"tumlOverloadedPostUri\": \"/RootElement/overloadedpost/users\", \"fieldType\": \"" + FieldType.Date + "\"}",/* isChangeListenerAttribute */ false,/* tumlMetaDataUri */ "/RootElement/userMetaData",/* tumlUri */ "/RootElement/users",/* tumlOverloadedPostUri */ "/RootElement/overloadedpost/users",/* fieldType */ FieldType.Date),
		id(/* qualifiedName */ "not_applicable",/* persistentName */ "not_applicable",/* inverseName */ "inverseO::not_applicable",/* inverseQualifiedName */ "inverseO::not_applicable",/* isAssociationClassOne */ false,/* isMemberEndOfAssociationClass */ false,/* associationClassPropertyNameField */ "null",/* inverseAssociationClassPropertyNameField */ "null",/* isAssociationClassProperty */ false,/* isOnePrimitivePropertyOfAssociationClass */ false,/* isOnePrimitive */ false,/* isReadOnly */ true,/* dataTypeEnum */ null,/* validations */ Collections.<UmlgValidation>emptyList(),/* isManyPrimitive */ false,/* oneEnumeration */ false,/* manyEnumeration */ false,/* isControllingSide */ false,/* isComposite */ false,/* isInverseComposite */ false,/* label */ UmlgLabelConverterFactory.getUmlgLabelConverter().convert(""),/* isOneToOne */ false,/* isOneToMany */ false,/* isManyToOne */ false,/* isManyToMany */ false,/* upper */ 1,/* lower */ 1,/* inverseUpper */ 1,/* isQualified */ false,/* isInverseQualified */ false,/* isOrdered */ false,/* isInverseOrdered */ false,/* isUnique */ false,/* isInverseUnique */ true,/* isDerived */ false,/* isNavigable */ true,/* propertyType */ Object.class,/* json */ "{\"name\": \"id\", \"associationClassOne\": false, \"memberEndOfAssociationClass\": false, \"associationClassPropertyName\": null, \"inverseAssociationClassPropertyName\": null, \"associationClassProperty\": false, \"onePrimitivePropertyOfAssociationClass\": false, \"onePrimitive\": false, \"readOnly\": true, \"dataTypeEnum\": null, \"validations\": null, \"qualifiedName\": \"not_applicable\", \"persistentName\": \"not_applicable\", \"inverseName\": \"inverseO::not_applicable\", \"inverseQualifiedName\": \"inverseO::not_applicable\", \"manyPrimitive\": false, \"oneEnumeration\": false, \"manyEnumeration\": false, \"controllingSide\": false, \"composite\": false, \"inverseComposite\": false, \"oneToOne\": false, \"oneToMany\": false, \"manyToOne\": false, \"manyToMany\": false, \"upper\": 1, \"lower\": 1, \"inverseUpper\": 1, \"label\": \"\", \"qualified\": false, \"inverseQualified\": false, \"ordered\": false, \"inverseOrdered\": false, \"unique\": false, \"inverseUnique\": true, \"derived\": false, \"navigable\": true, \"tumlMetaDataUri\": \"\", \"tumlUri\": \"/RootElement\", \"tumlOverloadedPostUri\": \"/RootElement\", \"fieldType\": \"" + FieldType.Date + "\"}",/* isChangeListenerAttribute */ false,/* tumlMetaDataUri */ "",/* tumlUri */ "/RootElement",/* tumlOverloadedPostUri */ "/RootElement",/* fieldType */ FieldType.Date);
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
		private String tumlMetaDataUri;
		private String tumlUri;
		private String tumlOverloadedPostUri;
		private FieldType fieldType;
		/**
		 * constructor for RootElementRuntimePropertyEnum
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
		 * @param tumlMetaDataUri 
		 * @param tumlUri 
		 * @param tumlOverloadedPostUri 
		 * @param fieldType 
		 */
		private RootElementRuntimePropertyEnum(String _qualifiedName, String _persistentName, String _inverseName, String _inverseQualifiedName, boolean _associationClassOne, boolean _memberEndOfAssociationClass, String _associationClassPropertyName, String _inverseAssociationClassPropertyName, boolean _associationClassProperty, boolean _onePrimitivePropertyOfAssociationClass, boolean _onePrimitive, Boolean _readOnly, DataTypeEnum dataTypeEnum, List<UmlgValidation> validations, boolean _manyPrimitive, boolean _oneEnumeration, boolean _manyEnumeration, boolean _controllingSide, boolean _composite, boolean _inverseComposite, String _label, boolean _oneToOne, boolean _oneToMany, boolean _manyToOne, boolean _manyToMany, int _upper, int _lower, int _inverseUpper, boolean _qualified, boolean _inverseQualified, boolean _ordered, boolean _inverseOrdered, boolean _unique, boolean _inverseUnique, boolean _derived, boolean _navigability, Class _propertyType, String _json, boolean _changeListener, String tumlMetaDataUri, String tumlUri, String tumlOverloadedPostUri, FieldType fieldType)  {
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
			this.tumlMetaDataUri = tumlMetaDataUri;
			this.tumlUri = tumlUri;
			this.tumlOverloadedPostUri = tumlOverloadedPostUri;
			this.fieldType = fieldType;
		}
	
		static public String asJson() {
			int count = 1;
			StringBuilder sb = new StringBuilder();;
			sb.append("{\"name\": \"RootElement\", ");
			sb.append("\"uri\": \"/RootElement\", ");
			sb.append("\"properties\": [");
			sb.append(RootElementRuntimePropertyEnum.RootElement.toJson());
			sb.append(",");
			sb.append(RootElementRuntimePropertyEnum.notification.toJson());
			sb.append(",");
			sb.append(RootElementRuntimePropertyEnum.board.toJson());
			sb.append(",");
			sb.append(RootElementRuntimePropertyEnum.block.toJson());
			sb.append(",");
			sb.append(RootElementRuntimePropertyEnum.edward.toJson());
			sb.append(",");
			sb.append(RootElementRuntimePropertyEnum.userGroup.toJson());
			sb.append(",");
			sb.append(RootElementRuntimePropertyEnum.user.toJson());
			sb.append("]}");
			return sb.toString();
		}
		
		static public RootElementRuntimePropertyEnum fromInverseQualifiedName(String inverseQualifiedName) {
			if ( id.getInverseQualifiedName().equals(inverseQualifiedName) ) {
				return id;
			}
			if ( user.getInverseQualifiedName().equals(inverseQualifiedName) ) {
				return user;
			}
			if ( userGroup.getInverseQualifiedName().equals(inverseQualifiedName) ) {
				return userGroup;
			}
			if ( edward.getInverseQualifiedName().equals(inverseQualifiedName) ) {
				return edward;
			}
			if ( block.getInverseQualifiedName().equals(inverseQualifiedName) ) {
				return block;
			}
			if ( board.getInverseQualifiedName().equals(inverseQualifiedName) ) {
				return board;
			}
			if ( notification.getInverseQualifiedName().equals(inverseQualifiedName) ) {
				return notification;
			}
			if ( RootElement.getInverseQualifiedName().equals(inverseQualifiedName) ) {
				return RootElement;
			}
			return null;
		}
		
		static public RootElementRuntimePropertyEnum fromLabel(String _label) {
			if ( id.getLabel().equals(_label) ) {
				return id;
			}
			if ( user.getLabel().equals(_label) ) {
				return user;
			}
			if ( userGroup.getLabel().equals(_label) ) {
				return userGroup;
			}
			if ( edward.getLabel().equals(_label) ) {
				return edward;
			}
			if ( block.getLabel().equals(_label) ) {
				return block;
			}
			if ( board.getLabel().equals(_label) ) {
				return board;
			}
			if ( notification.getLabel().equals(_label) ) {
				return notification;
			}
			if ( RootElement.getLabel().equals(_label) ) {
				return RootElement;
			}
			return null;
		}
		
		static public RootElementRuntimePropertyEnum fromQualifiedName(String qualifiedName) {
			if ( id.getQualifiedName().equals(qualifiedName) ) {
				return id;
			}
			if ( user.getQualifiedName().equals(qualifiedName) ) {
				return user;
			}
			if ( userGroup.getQualifiedName().equals(qualifiedName) ) {
				return userGroup;
			}
			if ( edward.getQualifiedName().equals(qualifiedName) ) {
				return edward;
			}
			if ( block.getQualifiedName().equals(qualifiedName) ) {
				return block;
			}
			if ( board.getQualifiedName().equals(qualifiedName) ) {
				return board;
			}
			if ( notification.getQualifiedName().equals(qualifiedName) ) {
				return notification;
			}
			if ( RootElement.getQualifiedName().equals(qualifiedName) ) {
				return RootElement;
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