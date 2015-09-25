package org.umlg;

import org.multipoly.Board.AssetCompositePathServerResourceImpl;
import org.multipoly.Board.AssetLookupServerResourceImpl;
import org.multipoly.Board.AssetServerResourceImpl;
import org.multipoly.Board.AssetType_assettype_asset_LookupServerResourceImpl;
import org.multipoly.Board.AssetType_assettype_asset_ServerResourceImpl;
import org.multipoly.Board.Asset_asset_block_LookupServerResourceImpl;
import org.multipoly.Board.Asset_asset_block_ServerResourceImpl;
import org.multipoly.Board.Asset_asset_block_lookUpForMany_ServerResourceImpl;
import org.multipoly.Board.Asset_asset_board_LookupServerResourceImpl;
import org.multipoly.Board.Asset_asset_board_ServerResourceImpl;
import org.multipoly.Board.Asset_asset_board_lookUpForMany_ServerResourceImpl;
import org.multipoly.Board.Assets_LookupServerResourceImpl;
import org.multipoly.Board.Assets_ServerResourceImpl;
import org.multipoly.Board.BlockCompositePathServerResourceImpl;
import org.multipoly.Board.BlockLookupServerResourceImpl;
import org.multipoly.Board.BlockServerResourceImpl;
import org.multipoly.Board.Block_block_asset_LookupServerResourceImpl;
import org.multipoly.Board.Block_block_asset_ServerResourceImpl;
import org.multipoly.Board.Block_block_asset_lookUpForMany_ServerResourceImpl;
import org.multipoly.Board.Block_block_board_LookupServerResourceImpl;
import org.multipoly.Board.Block_block_board_ServerResourceImpl;
import org.multipoly.Board.Block_block_board_lookUpForOne_ServerResourceImpl;
import org.multipoly.Board.Blocks_LookupServerResourceImpl;
import org.multipoly.Board.Blocks_ServerResourceImpl;
import org.multipoly.Board.BoardCompositePathServerResourceImpl;
import org.multipoly.Board.BoardLookupServerResourceImpl;
import org.multipoly.Board.BoardServerResourceImpl;
import org.multipoly.Board.Board_board_asset_LookupServerResourceImpl;
import org.multipoly.Board.Board_board_asset_ServerResourceImpl;
import org.multipoly.Board.Board_board_asset_lookUpForMany_ServerResourceImpl;
import org.multipoly.Board.Board_board_block_LookupServerResourceImpl;
import org.multipoly.Board.Board_board_block_ServerResourceImpl;
import org.multipoly.Board.Board_board_block_lookUpForOne_ServerResourceImpl;
import org.multipoly.Board.Boards_LookupServerResourceImpl;
import org.multipoly.Board.Boards_ServerResourceImpl;
import org.multipoly.Notification.NotificationCompositePathServerResourceImpl;
import org.multipoly.Notification.NotificationLookupServerResourceImpl;
import org.multipoly.Notification.NotificationServerResourceImpl;
import org.multipoly.Notification.Notifications_LookupServerResourceImpl;
import org.multipoly.Notification.Notifications_ServerResourceImpl;
import org.multipoly.User.UserCompositePathServerResourceImpl;
import org.multipoly.User.UserGroupCompositePathServerResourceImpl;
import org.multipoly.User.UserGroupLookupServerResourceImpl;
import org.multipoly.User.UserGroupServerResourceImpl;
import org.multipoly.User.UserGroup_usergroup_user_LookupServerResourceImpl;
import org.multipoly.User.UserGroup_usergroup_user_ServerResourceImpl;
import org.multipoly.User.UserGroup_usergroup_user_lookUpForMany_ServerResourceImpl;
import org.multipoly.User.UserGroups_LookupServerResourceImpl;
import org.multipoly.User.UserGroups_ServerResourceImpl;
import org.multipoly.User.UserLookupServerResourceImpl;
import org.multipoly.User.UserServerResourceImpl;
import org.multipoly.User.UserType_usertype_user_LookupServerResourceImpl;
import org.multipoly.User.UserType_usertype_user_ServerResourceImpl;
import org.multipoly.User.User_user_usergroup_LookupServerResourceImpl;
import org.multipoly.User.User_user_usergroup_ServerResourceImpl;
import org.multipoly.User.User_user_usergroup_lookUpForOne_ServerResourceImpl;
import org.multipoly.User.Users_LookupServerResourceImpl;
import org.multipoly.User.Users_ServerResourceImpl;
import org.restlet.resource.ServerResource;
import org.restlet.routing.Router;
import org.umlg.model.EnumerationLookup_ServerResourceImpl;
import org.umlg.model.UmlgDiagramPackageResource;
import org.umlg.restlet.RootServerResourceImpl;
import org.umlg.runtime.restlet.OclCodeInsightServerResource;
import org.umlg.runtime.restlet.QueryExecuteServerResourceImpl;
import org.umlg.runtime.restlet.UmlgDiagramResource;
import org.umlg.runtime.restlet.UmlgMetaQueryServerResourceImpl;

public enum RestletRouterEnum {
	USERGROUP(/*  */ "/usergroups/{usergroupId}",/*  */ UserGroupServerResourceImpl.class),
	USERGROUP_META(/*  */ "/userGroupMetaData",/*  */ UserGroupServerResourceImpl.class),
	USER(/*  */ "/users/{userId}",/*  */ UserServerResourceImpl.class),
	USER_META(/*  */ "/userMetaData",/*  */ UserServerResourceImpl.class),
	NOTIFICATION(/*  */ "/notifications/{notificationId}",/*  */ NotificationServerResourceImpl.class),
	NOTIFICATION_META(/*  */ "/notificationMetaData",/*  */ NotificationServerResourceImpl.class),
	BOARD(/*  */ "/boards/{boardId}",/*  */ BoardServerResourceImpl.class),
	BOARD_META(/*  */ "/boardMetaData",/*  */ BoardServerResourceImpl.class),
	BLOCK(/*  */ "/blocks/{blockId}",/*  */ BlockServerResourceImpl.class),
	BLOCK_META(/*  */ "/blockMetaData",/*  */ BlockServerResourceImpl.class),
	ASSET(/*  */ "/assets/{assetId}",/*  */ AssetServerResourceImpl.class),
	ASSET_META(/*  */ "/assetMetaData",/*  */ AssetServerResourceImpl.class),
	USERGROUP_forwardToLookup(/*  */ "/usergroups/{usergroupId}/forwardToLookup",/*  */ UserGroupLookupServerResourceImpl.class),
	USER_forwardToLookup(/*  */ "/users/{userId}/forwardToLookup",/*  */ UserLookupServerResourceImpl.class),
	NOTIFICATION_forwardToLookup(/*  */ "/notifications/{notificationId}/forwardToLookup",/*  */ NotificationLookupServerResourceImpl.class),
	BOARD_forwardToLookup(/*  */ "/boards/{boardId}/forwardToLookup",/*  */ BoardLookupServerResourceImpl.class),
	BLOCK_forwardToLookup(/*  */ "/blocks/{blockId}/forwardToLookup",/*  */ BlockLookupServerResourceImpl.class),
	ASSET_forwardToLookup(/*  */ "/assets/{assetId}/forwardToLookup",/*  */ AssetLookupServerResourceImpl.class),
	UserGroup_user(/*  */ "/usergroups/{usergroupId}/user",/*  */ UserGroup_usergroup_user_ServerResourceImpl.class),
	User_usergroup(/*  */ "/users/{userId}/usergroup",/*  */ User_user_usergroup_ServerResourceImpl.class),
	UserType_user(/*  */ "/usertypes/{usertypeId}/user",/*  */ UserType_usertype_user_ServerResourceImpl.class),
	Board_block(/*  */ "/boards/{boardId}/block",/*  */ Board_board_block_ServerResourceImpl.class),
	Board_asset(/*  */ "/boards/{boardId}/asset",/*  */ Board_board_asset_ServerResourceImpl.class),
	Block_board(/*  */ "/blocks/{blockId}/board",/*  */ Block_block_board_ServerResourceImpl.class),
	Block_asset(/*  */ "/blocks/{blockId}/asset",/*  */ Block_block_asset_ServerResourceImpl.class),
	Asset_block(/*  */ "/assets/{assetId}/block",/*  */ Asset_asset_block_ServerResourceImpl.class),
	Asset_board(/*  */ "/assets/{assetId}/board",/*  */ Asset_asset_board_ServerResourceImpl.class),
	AssetType_asset(/*  */ "/assettypes/{assettypeId}/asset",/*  */ AssetType_assettype_asset_ServerResourceImpl.class),
	UserGroup_user_forwardToLookup(/*  */ "/usergroups/{usergroupId}/user_forwardToLookup",/*  */ UserGroup_usergroup_user_LookupServerResourceImpl.class),
	User_usergroup_forwardToLookup(/*  */ "/users/{userId}/usergroup_forwardToLookup",/*  */ User_user_usergroup_LookupServerResourceImpl.class),
	UserType_user_forwardToLookup(/*  */ "/usertypes/{usertypeId}/user_forwardToLookup",/*  */ UserType_usertype_user_LookupServerResourceImpl.class),
	Board_block_forwardToLookup(/*  */ "/boards/{boardId}/block_forwardToLookup",/*  */ Board_board_block_LookupServerResourceImpl.class),
	Board_asset_forwardToLookup(/*  */ "/boards/{boardId}/asset_forwardToLookup",/*  */ Board_board_asset_LookupServerResourceImpl.class),
	Block_board_forwardToLookup(/*  */ "/blocks/{blockId}/board_forwardToLookup",/*  */ Block_block_board_LookupServerResourceImpl.class),
	Block_asset_forwardToLookup(/*  */ "/blocks/{blockId}/asset_forwardToLookup",/*  */ Block_block_asset_LookupServerResourceImpl.class),
	Asset_block_forwardToLookup(/*  */ "/assets/{assetId}/block_forwardToLookup",/*  */ Asset_asset_block_LookupServerResourceImpl.class),
	Asset_board_forwardToLookup(/*  */ "/assets/{assetId}/board_forwardToLookup",/*  */ Asset_asset_board_LookupServerResourceImpl.class),
	AssetType_asset_forwardToLookup(/*  */ "/assettypes/{assettypeId}/asset_forwardToLookup",/*  */ AssetType_assettype_asset_LookupServerResourceImpl.class),
	USERGROUPS(/*  */ "/usergroups",/*  */ UserGroups_ServerResourceImpl.class),
	USERS(/*  */ "/users",/*  */ Users_ServerResourceImpl.class),
	NOTIFICATIONS(/*  */ "/notifications",/*  */ Notifications_ServerResourceImpl.class),
	BOARDS(/*  */ "/boards",/*  */ Boards_ServerResourceImpl.class),
	BLOCKS(/*  */ "/blocks",/*  */ Blocks_ServerResourceImpl.class),
	ASSETS(/*  */ "/assets",/*  */ Assets_ServerResourceImpl.class),
	USERGROUPS_forwardToLookup(/*  */ "/usergroups_forwardToLookup",/*  */ UserGroups_LookupServerResourceImpl.class),
	USERS_forwardToLookup(/*  */ "/users_forwardToLookup",/*  */ Users_LookupServerResourceImpl.class),
	NOTIFICATIONS_forwardToLookup(/*  */ "/notifications_forwardToLookup",/*  */ Notifications_LookupServerResourceImpl.class),
	BOARDS_forwardToLookup(/*  */ "/boards_forwardToLookup",/*  */ Boards_LookupServerResourceImpl.class),
	BLOCKS_forwardToLookup(/*  */ "/blocks_forwardToLookup",/*  */ Blocks_LookupServerResourceImpl.class),
	ASSETS_forwardToLookup(/*  */ "/assets_forwardToLookup",/*  */ Assets_LookupServerResourceImpl.class),
	ROOT(/*  */ "/umlgRoot",/*  */ RootServerResourceImpl.class),
	USER_lookupFor_user_usergroup(/*  */ "/users/{userId}/lookupFor_user_usergroup",/*  */ User_user_usergroup_lookUpForOne_ServerResourceImpl.class),
	BOARD_lookupFor_board_block(/*  */ "/boards/{boardId}/lookupFor_board_block",/*  */ Board_board_block_lookUpForOne_ServerResourceImpl.class),
	BLOCK_lookupFor_block_board(/*  */ "/blocks/{blockId}/lookupFor_block_board",/*  */ Block_block_board_lookUpForOne_ServerResourceImpl.class),
	USERGROUP_lookupFor_usergroup_user(/*  */ "/usergroups/{usergroupId}/lookupFor_usergroup_user",/*  */ UserGroup_usergroup_user_lookUpForMany_ServerResourceImpl.class),
	BOARD_lookupFor_board_asset(/*  */ "/boards/{boardId}/lookupFor_board_asset",/*  */ Board_board_asset_lookUpForMany_ServerResourceImpl.class),
	BLOCK_lookupFor_block_asset(/*  */ "/blocks/{blockId}/lookupFor_block_asset",/*  */ Block_block_asset_lookUpForMany_ServerResourceImpl.class),
	ASSET_lookupFor_asset_block(/*  */ "/assets/{assetId}/lookupFor_asset_block",/*  */ Asset_asset_block_lookUpForMany_ServerResourceImpl.class),
	ASSET_lookupFor_asset_board(/*  */ "/assets/{assetId}/lookupFor_asset_board",/*  */ Asset_asset_board_lookUpForMany_ServerResourceImpl.class),
	USERGROUP_compositePath(/*  */ "/usergroups/{usergroupId}/compositePathToRoot",/*  */ UserGroupCompositePathServerResourceImpl.class),
	USER_compositePath(/*  */ "/users/{userId}/compositePathToRoot",/*  */ UserCompositePathServerResourceImpl.class),
	NOTIFICATION_compositePath(/*  */ "/notifications/{notificationId}/compositePathToRoot",/*  */ NotificationCompositePathServerResourceImpl.class),
	BOARD_compositePath(/*  */ "/boards/{boardId}/compositePathToRoot",/*  */ BoardCompositePathServerResourceImpl.class),
	BLOCK_compositePath(/*  */ "/blocks/{blockId}/compositePathToRoot",/*  */ BlockCompositePathServerResourceImpl.class),
	ASSET_compositePath(/*  */ "/assets/{assetId}/compositePathToRoot",/*  */ AssetCompositePathServerResourceImpl.class),
	ENUM_LOOKUP(/*  */ "/tumlEnumLookup",/*  */ EnumerationLookup_ServerResourceImpl.class),
	QUERY_EXECUTE(/*  */ "/{contextId}/oclExecuteQuery",/*  */ QueryExecuteServerResourceImpl.class),
	QUERY_EXECUTE_STATIC(/*  */ "/oclExecuteQuery",/*  */ QueryExecuteServerResourceImpl.class),
	OCL_CODE_INSIGHT(/*  */ "/oclCodeInsight",/*  */ OclCodeInsightServerResource.class),
	CLASS_QUERY(/*  */ "/classquery",/*  */ UmlgMetaQueryServerResourceImpl.class),
	DIAGRAM_PACKAGE(/*  */ "/diagramPackages",/*  */ UmlgDiagramPackageResource.class),
	DIAGRAM(/*  */ "/diagram",/*  */ UmlgDiagramResource.class);
	private String uri;
	private Class<? extends ServerResource> serverResource;
	/**
	 * constructor for RestletRouterEnum
	 * 
	 * @param uri 
	 * @param serverResource 
	 */
	private RestletRouterEnum(String uri, Class<? extends ServerResource> serverResource)  {
		this.uri = uri;
		this.serverResource = serverResource;
	}

	public void attach(Router router) {
		router.attach(uri, serverResource);
	}
	
	static public void attachAll(Router router) {
		RestletRouterEnum.USERGROUP.attach(router);
		RestletRouterEnum.USERGROUP_META.attach(router);
		RestletRouterEnum.USER.attach(router);
		RestletRouterEnum.USER_META.attach(router);
		RestletRouterEnum.NOTIFICATION.attach(router);
		RestletRouterEnum.NOTIFICATION_META.attach(router);
		RestletRouterEnum.BOARD.attach(router);
		RestletRouterEnum.BOARD_META.attach(router);
		RestletRouterEnum.BLOCK.attach(router);
		RestletRouterEnum.BLOCK_META.attach(router);
		RestletRouterEnum.ASSET.attach(router);
		RestletRouterEnum.ASSET_META.attach(router);
		RestletRouterEnum.USERGROUP_forwardToLookup.attach(router);
		RestletRouterEnum.USER_forwardToLookup.attach(router);
		RestletRouterEnum.NOTIFICATION_forwardToLookup.attach(router);
		RestletRouterEnum.BOARD_forwardToLookup.attach(router);
		RestletRouterEnum.BLOCK_forwardToLookup.attach(router);
		RestletRouterEnum.ASSET_forwardToLookup.attach(router);
		RestletRouterEnum.UserGroup_user.attach(router);
		RestletRouterEnum.User_usergroup.attach(router);
		RestletRouterEnum.UserType_user.attach(router);
		RestletRouterEnum.Board_block.attach(router);
		RestletRouterEnum.Board_asset.attach(router);
		RestletRouterEnum.Block_board.attach(router);
		RestletRouterEnum.Block_asset.attach(router);
		RestletRouterEnum.Asset_block.attach(router);
		RestletRouterEnum.Asset_board.attach(router);
		RestletRouterEnum.AssetType_asset.attach(router);
		RestletRouterEnum.UserGroup_user_forwardToLookup.attach(router);
		RestletRouterEnum.User_usergroup_forwardToLookup.attach(router);
		RestletRouterEnum.UserType_user_forwardToLookup.attach(router);
		RestletRouterEnum.Board_block_forwardToLookup.attach(router);
		RestletRouterEnum.Board_asset_forwardToLookup.attach(router);
		RestletRouterEnum.Block_board_forwardToLookup.attach(router);
		RestletRouterEnum.Block_asset_forwardToLookup.attach(router);
		RestletRouterEnum.Asset_block_forwardToLookup.attach(router);
		RestletRouterEnum.Asset_board_forwardToLookup.attach(router);
		RestletRouterEnum.AssetType_asset_forwardToLookup.attach(router);
		RestletRouterEnum.USERGROUPS.attach(router);
		RestletRouterEnum.USERS.attach(router);
		RestletRouterEnum.NOTIFICATIONS.attach(router);
		RestletRouterEnum.BOARDS.attach(router);
		RestletRouterEnum.BLOCKS.attach(router);
		RestletRouterEnum.ASSETS.attach(router);
		RestletRouterEnum.USERGROUPS_forwardToLookup.attach(router);
		RestletRouterEnum.USERS_forwardToLookup.attach(router);
		RestletRouterEnum.NOTIFICATIONS_forwardToLookup.attach(router);
		RestletRouterEnum.BOARDS_forwardToLookup.attach(router);
		RestletRouterEnum.BLOCKS_forwardToLookup.attach(router);
		RestletRouterEnum.ASSETS_forwardToLookup.attach(router);
		RestletRouterEnum.ROOT.attach(router);
		RestletRouterEnum.USER_lookupFor_user_usergroup.attach(router);
		RestletRouterEnum.BOARD_lookupFor_board_block.attach(router);
		RestletRouterEnum.BLOCK_lookupFor_block_board.attach(router);
		RestletRouterEnum.USERGROUP_lookupFor_usergroup_user.attach(router);
		RestletRouterEnum.BOARD_lookupFor_board_asset.attach(router);
		RestletRouterEnum.BLOCK_lookupFor_block_asset.attach(router);
		RestletRouterEnum.ASSET_lookupFor_asset_block.attach(router);
		RestletRouterEnum.ASSET_lookupFor_asset_board.attach(router);
		RestletRouterEnum.USERGROUP_compositePath.attach(router);
		RestletRouterEnum.USER_compositePath.attach(router);
		RestletRouterEnum.NOTIFICATION_compositePath.attach(router);
		RestletRouterEnum.BOARD_compositePath.attach(router);
		RestletRouterEnum.BLOCK_compositePath.attach(router);
		RestletRouterEnum.ASSET_compositePath.attach(router);
		RestletRouterEnum.ENUM_LOOKUP.attach(router);
		RestletRouterEnum.QUERY_EXECUTE.attach(router);
		RestletRouterEnum.QUERY_EXECUTE_STATIC.attach(router);
		RestletRouterEnum.OCL_CODE_INSIGHT.attach(router);
		RestletRouterEnum.CLASS_QUERY.attach(router);
		RestletRouterEnum.DIAGRAM_PACKAGE.attach(router);
		RestletRouterEnum.DIAGRAM.attach(router);
	}
	
	public Class<? extends ServerResource> getServerResource() {
		return this.serverResource;
	}
	
	public String getUri() {
		return this.uri;
	}


}