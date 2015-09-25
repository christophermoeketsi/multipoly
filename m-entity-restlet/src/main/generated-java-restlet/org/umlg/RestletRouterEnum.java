package org.umlg;

import org.multipoly.Board.BlockCompositePathServerResourceImpl;
import org.multipoly.Board.BlockLookupServerResourceImpl;
import org.multipoly.Board.BlockServerResourceImpl;
import org.multipoly.Board.Block_block_board_LookupServerResourceImpl;
import org.multipoly.Board.Block_block_board_ServerResourceImpl;
import org.multipoly.Board.Block_block_board_lookUpForOne_ServerResourceImpl;
import org.multipoly.Board.Blocks_LookupServerResourceImpl;
import org.multipoly.Board.Blocks_ServerResourceImpl;
import org.multipoly.Board.BoardCompositePathServerResourceImpl;
import org.multipoly.Board.BoardLookupServerResourceImpl;
import org.multipoly.Board.BoardServerResourceImpl;
import org.multipoly.Board.Board_board_block_LookupServerResourceImpl;
import org.multipoly.Board.Board_board_block_ServerResourceImpl;
import org.multipoly.Board.Board_board_block_lookUpForOne_ServerResourceImpl;
import org.multipoly.Board.Board_board_edward_LookupServerResourceImpl;
import org.multipoly.Board.Board_board_edward_ServerResourceImpl;
import org.multipoly.Board.Board_board_edward_lookUpForOne_ServerResourceImpl;
import org.multipoly.Board.Boards_LookupServerResourceImpl;
import org.multipoly.Board.Boards_ServerResourceImpl;
import org.multipoly.Board.EdwardCompositePathServerResourceImpl;
import org.multipoly.Board.EdwardLookupServerResourceImpl;
import org.multipoly.Board.EdwardServerResourceImpl;
import org.multipoly.Board.Edward_edward_board_LookupServerResourceImpl;
import org.multipoly.Board.Edward_edward_board_ServerResourceImpl;
import org.multipoly.Board.Edward_edward_board_lookUpForOne_ServerResourceImpl;
import org.multipoly.Board.Edwards_LookupServerResourceImpl;
import org.multipoly.Board.Edwards_ServerResourceImpl;
import org.multipoly.Notification.NotificationCompositePathServerResourceImpl;
import org.multipoly.Notification.NotificationLookupServerResourceImpl;
import org.multipoly.Notification.NotificationServerResourceImpl;
import org.multipoly.Notification.Notifications_LookupServerResourceImpl;
import org.multipoly.Notification.Notifications_ServerResourceImpl;
import org.multipoly.ROLE_role_user_LookupServerResourceImpl;
import org.multipoly.ROLE_role_user_ServerResourceImpl;
import org.multipoly.UserCompositePathServerResourceImpl;
import org.multipoly.UserGroupCompositePathServerResourceImpl;
import org.multipoly.UserGroupLookupServerResourceImpl;
import org.multipoly.UserGroupServerResourceImpl;
import org.multipoly.UserGroup_usergroup_user_LookupServerResourceImpl;
import org.multipoly.UserGroup_usergroup_user_ServerResourceImpl;
import org.multipoly.UserGroup_usergroup_user_lookUpForMany_ServerResourceImpl;
import org.multipoly.UserGroups_LookupServerResourceImpl;
import org.multipoly.UserGroups_ServerResourceImpl;
import org.multipoly.UserLookupServerResourceImpl;
import org.multipoly.UserServerResourceImpl;
import org.multipoly.User_user_usergroup_LookupServerResourceImpl;
import org.multipoly.User_user_usergroup_ServerResourceImpl;
import org.multipoly.User_user_usergroup_lookUpForOne_ServerResourceImpl;
import org.multipoly.Users_LookupServerResourceImpl;
import org.multipoly.Users_ServerResourceImpl;
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
	NOTIFICATION(/*  */ "/notifications/{notificationId}",/*  */ NotificationServerResourceImpl.class),
	NOTIFICATION_META(/*  */ "/notificationMetaData",/*  */ NotificationServerResourceImpl.class),
	BOARD(/*  */ "/boards/{boardId}",/*  */ BoardServerResourceImpl.class),
	BOARD_META(/*  */ "/boardMetaData",/*  */ BoardServerResourceImpl.class),
	BLOCK(/*  */ "/blocks/{blockId}",/*  */ BlockServerResourceImpl.class),
	BLOCK_META(/*  */ "/blockMetaData",/*  */ BlockServerResourceImpl.class),
	EDWARD(/*  */ "/edwards/{edwardId}",/*  */ EdwardServerResourceImpl.class),
	EDWARD_META(/*  */ "/edwardMetaData",/*  */ EdwardServerResourceImpl.class),
	USERGROUP(/*  */ "/usergroups/{usergroupId}",/*  */ UserGroupServerResourceImpl.class),
	USERGROUP_META(/*  */ "/userGroupMetaData",/*  */ UserGroupServerResourceImpl.class),
	USER(/*  */ "/users/{userId}",/*  */ UserServerResourceImpl.class),
	USER_META(/*  */ "/userMetaData",/*  */ UserServerResourceImpl.class),
	NOTIFICATION_forwardToLookup(/*  */ "/notifications/{notificationId}/forwardToLookup",/*  */ NotificationLookupServerResourceImpl.class),
	BOARD_forwardToLookup(/*  */ "/boards/{boardId}/forwardToLookup",/*  */ BoardLookupServerResourceImpl.class),
	BLOCK_forwardToLookup(/*  */ "/blocks/{blockId}/forwardToLookup",/*  */ BlockLookupServerResourceImpl.class),
	EDWARD_forwardToLookup(/*  */ "/edwards/{edwardId}/forwardToLookup",/*  */ EdwardLookupServerResourceImpl.class),
	USERGROUP_forwardToLookup(/*  */ "/usergroups/{usergroupId}/forwardToLookup",/*  */ UserGroupLookupServerResourceImpl.class),
	USER_forwardToLookup(/*  */ "/users/{userId}/forwardToLookup",/*  */ UserLookupServerResourceImpl.class),
	Board_block(/*  */ "/boards/{boardId}/block",/*  */ Board_board_block_ServerResourceImpl.class),
	Board_edward(/*  */ "/boards/{boardId}/edward",/*  */ Board_board_edward_ServerResourceImpl.class),
	Block_board(/*  */ "/blocks/{blockId}/board",/*  */ Block_block_board_ServerResourceImpl.class),
	Edward_board(/*  */ "/edwards/{edwardId}/board",/*  */ Edward_edward_board_ServerResourceImpl.class),
	UserGroup_user(/*  */ "/usergroups/{usergroupId}/user",/*  */ UserGroup_usergroup_user_ServerResourceImpl.class),
	User_usergroup(/*  */ "/users/{userId}/usergroup",/*  */ User_user_usergroup_ServerResourceImpl.class),
	ROLE_user(/*  */ "/roles/{roleId}/user",/*  */ ROLE_role_user_ServerResourceImpl.class),
	Board_block_forwardToLookup(/*  */ "/boards/{boardId}/block_forwardToLookup",/*  */ Board_board_block_LookupServerResourceImpl.class),
	Board_edward_forwardToLookup(/*  */ "/boards/{boardId}/edward_forwardToLookup",/*  */ Board_board_edward_LookupServerResourceImpl.class),
	Block_board_forwardToLookup(/*  */ "/blocks/{blockId}/board_forwardToLookup",/*  */ Block_block_board_LookupServerResourceImpl.class),
	Edward_board_forwardToLookup(/*  */ "/edwards/{edwardId}/board_forwardToLookup",/*  */ Edward_edward_board_LookupServerResourceImpl.class),
	UserGroup_user_forwardToLookup(/*  */ "/usergroups/{usergroupId}/user_forwardToLookup",/*  */ UserGroup_usergroup_user_LookupServerResourceImpl.class),
	User_usergroup_forwardToLookup(/*  */ "/users/{userId}/usergroup_forwardToLookup",/*  */ User_user_usergroup_LookupServerResourceImpl.class),
	ROLE_user_forwardToLookup(/*  */ "/roles/{roleId}/user_forwardToLookup",/*  */ ROLE_role_user_LookupServerResourceImpl.class),
	NOTIFICATIONS(/*  */ "/notifications",/*  */ Notifications_ServerResourceImpl.class),
	BOARDS(/*  */ "/boards",/*  */ Boards_ServerResourceImpl.class),
	BLOCKS(/*  */ "/blocks",/*  */ Blocks_ServerResourceImpl.class),
	EDWARDS(/*  */ "/edwards",/*  */ Edwards_ServerResourceImpl.class),
	USERGROUPS(/*  */ "/usergroups",/*  */ UserGroups_ServerResourceImpl.class),
	USERS(/*  */ "/users",/*  */ Users_ServerResourceImpl.class),
	NOTIFICATIONS_forwardToLookup(/*  */ "/notifications_forwardToLookup",/*  */ Notifications_LookupServerResourceImpl.class),
	BOARDS_forwardToLookup(/*  */ "/boards_forwardToLookup",/*  */ Boards_LookupServerResourceImpl.class),
	BLOCKS_forwardToLookup(/*  */ "/blocks_forwardToLookup",/*  */ Blocks_LookupServerResourceImpl.class),
	EDWARDS_forwardToLookup(/*  */ "/edwards_forwardToLookup",/*  */ Edwards_LookupServerResourceImpl.class),
	USERGROUPS_forwardToLookup(/*  */ "/usergroups_forwardToLookup",/*  */ UserGroups_LookupServerResourceImpl.class),
	USERS_forwardToLookup(/*  */ "/users_forwardToLookup",/*  */ Users_LookupServerResourceImpl.class),
	ROOT(/*  */ "/umlgRoot",/*  */ RootServerResourceImpl.class),
	BOARD_lookupFor_board_block(/*  */ "/boards/{boardId}/lookupFor_board_block",/*  */ Board_board_block_lookUpForOne_ServerResourceImpl.class),
	BOARD_lookupFor_board_edward(/*  */ "/boards/{boardId}/lookupFor_board_edward",/*  */ Board_board_edward_lookUpForOne_ServerResourceImpl.class),
	BLOCK_lookupFor_block_board(/*  */ "/blocks/{blockId}/lookupFor_block_board",/*  */ Block_block_board_lookUpForOne_ServerResourceImpl.class),
	EDWARD_lookupFor_edward_board(/*  */ "/edwards/{edwardId}/lookupFor_edward_board",/*  */ Edward_edward_board_lookUpForOne_ServerResourceImpl.class),
	USER_lookupFor_user_usergroup(/*  */ "/users/{userId}/lookupFor_user_usergroup",/*  */ User_user_usergroup_lookUpForOne_ServerResourceImpl.class),
	USERGROUP_lookupFor_usergroup_user(/*  */ "/usergroups/{usergroupId}/lookupFor_usergroup_user",/*  */ UserGroup_usergroup_user_lookUpForMany_ServerResourceImpl.class),
	NOTIFICATION_compositePath(/*  */ "/notifications/{notificationId}/compositePathToRoot",/*  */ NotificationCompositePathServerResourceImpl.class),
	BOARD_compositePath(/*  */ "/boards/{boardId}/compositePathToRoot",/*  */ BoardCompositePathServerResourceImpl.class),
	BLOCK_compositePath(/*  */ "/blocks/{blockId}/compositePathToRoot",/*  */ BlockCompositePathServerResourceImpl.class),
	EDWARD_compositePath(/*  */ "/edwards/{edwardId}/compositePathToRoot",/*  */ EdwardCompositePathServerResourceImpl.class),
	USERGROUP_compositePath(/*  */ "/usergroups/{usergroupId}/compositePathToRoot",/*  */ UserGroupCompositePathServerResourceImpl.class),
	USER_compositePath(/*  */ "/users/{userId}/compositePathToRoot",/*  */ UserCompositePathServerResourceImpl.class),
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
		RestletRouterEnum.NOTIFICATION.attach(router);
		RestletRouterEnum.NOTIFICATION_META.attach(router);
		RestletRouterEnum.BOARD.attach(router);
		RestletRouterEnum.BOARD_META.attach(router);
		RestletRouterEnum.BLOCK.attach(router);
		RestletRouterEnum.BLOCK_META.attach(router);
		RestletRouterEnum.EDWARD.attach(router);
		RestletRouterEnum.EDWARD_META.attach(router);
		RestletRouterEnum.USERGROUP.attach(router);
		RestletRouterEnum.USERGROUP_META.attach(router);
		RestletRouterEnum.USER.attach(router);
		RestletRouterEnum.USER_META.attach(router);
		RestletRouterEnum.NOTIFICATION_forwardToLookup.attach(router);
		RestletRouterEnum.BOARD_forwardToLookup.attach(router);
		RestletRouterEnum.BLOCK_forwardToLookup.attach(router);
		RestletRouterEnum.EDWARD_forwardToLookup.attach(router);
		RestletRouterEnum.USERGROUP_forwardToLookup.attach(router);
		RestletRouterEnum.USER_forwardToLookup.attach(router);
		RestletRouterEnum.Board_block.attach(router);
		RestletRouterEnum.Board_edward.attach(router);
		RestletRouterEnum.Block_board.attach(router);
		RestletRouterEnum.Edward_board.attach(router);
		RestletRouterEnum.UserGroup_user.attach(router);
		RestletRouterEnum.User_usergroup.attach(router);
		RestletRouterEnum.ROLE_user.attach(router);
		RestletRouterEnum.Board_block_forwardToLookup.attach(router);
		RestletRouterEnum.Board_edward_forwardToLookup.attach(router);
		RestletRouterEnum.Block_board_forwardToLookup.attach(router);
		RestletRouterEnum.Edward_board_forwardToLookup.attach(router);
		RestletRouterEnum.UserGroup_user_forwardToLookup.attach(router);
		RestletRouterEnum.User_usergroup_forwardToLookup.attach(router);
		RestletRouterEnum.ROLE_user_forwardToLookup.attach(router);
		RestletRouterEnum.NOTIFICATIONS.attach(router);
		RestletRouterEnum.BOARDS.attach(router);
		RestletRouterEnum.BLOCKS.attach(router);
		RestletRouterEnum.EDWARDS.attach(router);
		RestletRouterEnum.USERGROUPS.attach(router);
		RestletRouterEnum.USERS.attach(router);
		RestletRouterEnum.NOTIFICATIONS_forwardToLookup.attach(router);
		RestletRouterEnum.BOARDS_forwardToLookup.attach(router);
		RestletRouterEnum.BLOCKS_forwardToLookup.attach(router);
		RestletRouterEnum.EDWARDS_forwardToLookup.attach(router);
		RestletRouterEnum.USERGROUPS_forwardToLookup.attach(router);
		RestletRouterEnum.USERS_forwardToLookup.attach(router);
		RestletRouterEnum.ROOT.attach(router);
		RestletRouterEnum.BOARD_lookupFor_board_block.attach(router);
		RestletRouterEnum.BOARD_lookupFor_board_edward.attach(router);
		RestletRouterEnum.BLOCK_lookupFor_block_board.attach(router);
		RestletRouterEnum.EDWARD_lookupFor_edward_board.attach(router);
		RestletRouterEnum.USER_lookupFor_user_usergroup.attach(router);
		RestletRouterEnum.USERGROUP_lookupFor_usergroup_user.attach(router);
		RestletRouterEnum.NOTIFICATION_compositePath.attach(router);
		RestletRouterEnum.BOARD_compositePath.attach(router);
		RestletRouterEnum.BLOCK_compositePath.attach(router);
		RestletRouterEnum.EDWARD_compositePath.attach(router);
		RestletRouterEnum.USERGROUP_compositePath.attach(router);
		RestletRouterEnum.USER_compositePath.attach(router);
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