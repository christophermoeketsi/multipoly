package org.umlg.runtime.adaptor;

import java.util.HashMap;
import java.util.Map;

import org.multipoly.Board.Asset;
import org.multipoly.Board.Block;
import org.multipoly.Board.Board;
import org.multipoly.Notification.Notification;
import org.multipoly.User.User;
import org.multipoly.User.UserGroup;
import org.umlg.runtime.domain.UmlgNode;

public class UmlgSchemaMapImpl implements UmlgSchemaMap {
	static public UmlgSchemaMapImpl INSTANCE = new UmlgSchemaMapImpl();
	private Map<String,Class<? extends UmlgNode>> qualifiedNameClassMap = new HashMap<String, Class<? extends UmlgNode>>();

	/**
	 * constructor for UmlgSchemaMapImpl
	 */
	private UmlgSchemaMapImpl()  {
		addAllEntries();
	}

	public <T extends UmlgNode> Class<T> get(String qualifiedName) {
		return (Class<T>)this.qualifiedNameClassMap.get(qualifiedName);
	}
	
	static public UmlgSchemaMap getInstance() {
		return INSTANCE;
	}
	
	private void addAllEntries() {
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::User::UserGroup", UserGroup.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::User::User", User.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::Notification::Notification", Notification.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::Board::Board", Board.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::Board::Block", Block.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::Board::Asset", Asset.class);
	}


}