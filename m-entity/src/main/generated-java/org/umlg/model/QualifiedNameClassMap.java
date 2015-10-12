package org.umlg.model;

import java.util.HashMap;
import java.util.Map;

import org.multipoly.Board.Asset;
import org.multipoly.Board.AssetType;
import org.multipoly.Board.Block;
import org.multipoly.Board.Board;
import org.multipoly.Notification.NOTIFICATIONTYPE;
import org.multipoly.Notification.Notification;
import org.multipoly.User.ROLE;
import org.multipoly.User.User;
import org.multipoly.User.UserGroup;

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
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::User::UserGroup::Name", String.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::User::UserGroup::user", User.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::User::User::Name", String.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::User::User::Username", String.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::User::User::Password", String.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::User::User::LastLoggedIn", Integer.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::User::User::LastLoggedOut", Integer.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::User::User::Rogue", Boolean.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::User::User::usergroup", UserGroup.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::User::User::board", Board.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::User::User::Surname", String.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::User::User::Email", String.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::User::User::role", ROLE.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::User::ROLE::user", User.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::Notification::Notification::Name", String.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::Notification::Notification::Content", String.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::Notification::Notification::Message", String.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::Notification::Notification::notificationtype", NOTIFICATIONTYPE.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::Notification::NOTIFICATIONTYPE::notification", Notification.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::Board::Board::Name", String.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::Board::Board::block", Block.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::Board::Board::asset", Asset.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::Board::Board::user", User.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::Board::Block::Name", String.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::Board::Block::Colour", String.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::Board::Block::board", Board.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::Board::Block::Position", String.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::Board::Block::asset", Asset.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::Board::Asset::Name", String.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::Board::Asset::Value", Integer.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::Board::Asset::Available", Boolean.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::Board::Asset::assettype", AssetType.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::Board::Asset::block", Block.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::Board::Asset::board", Board.class);
		this.qualifiedNameClassMap.put("RootElement::org::multipoly::Board::AssetType::asset", Asset.class);
	}


}