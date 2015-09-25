package org.umlg.model;

import java.util.HashMap;
import java.util.Map;

import org.multipoly.Board.Block;
import org.multipoly.Board.Board;
import org.multipoly.Board.Edward;
import org.multipoly.ROLE;
import org.multipoly.User;
import org.multipoly.UserGroup;

public class QualifiedNameClassMap {
	static public QualifiedNameClassMap INSTANCE = new QualifiedNameClassMap();
	private Map<String,Class<?>> qualifiedNameClassMap = new HashMap<String, Class<?>>();

	/**
	 * constructor for QualifiedNameClassMap
	 */
	private QualifiedNameClassMap()  {
		addAllEntries();
	}

	public Class<?> get(String qualifiedName) {
		return this.qualifiedNameClassMap.get(qualifiedName);
	}
	
	private void addAllEntries() {
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::Notification::Notification::Name", String.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::Notification::Notification::Content", String.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::Notification::Notification::Message", String.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::Board::Board::Name", String.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::Board::Board::block", Block.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::Board::Board::edward", Edward.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::Board::Block::Name", String.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::Board::Block::Colour", String.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::Board::Block::board", Board.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::Board::Block::Position", String.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::Board::Edward::Name", String.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::Board::Edward::board", Board.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::UserGroup::Name", String.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::UserGroup::user", User.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::User::Name", String.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::User::Username", String.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::User::Password", String.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::User::LastLoggedIn", Integer.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::User::LastLoggerOut", Integer.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::User::role", ROLE.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::User::usergroup", UserGroup.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::User::Rogue", Boolean.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::ROLE::user", User.class);
	}


}