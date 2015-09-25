package org.umlg.runtime.adaptor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.multipoly.Board.Block;
import org.multipoly.Board.Board;
import org.multipoly.Board.Edward;
import org.multipoly.Notification.Notification;
import org.multipoly.User;
import org.multipoly.UserGroup;
import org.umlg.runtime.domain.UmlgNode;

public class UmlgSchemaCreatorImpl implements UmlgSchemaCreator {
	static public UmlgSchemaCreatorImpl INSTANCE = new UmlgSchemaCreatorImpl();
	private Set<List<String>> qualifiedNameVertexSchemaSet = new HashSet<List<String>>();
	private Set<String> qualifiedNameEdgeSchemaSet = new HashSet<String>();

	/**
	 * constructor for UmlgSchemaCreatorImpl
	 */
	private UmlgSchemaCreatorImpl()  {
		addAllVertexEntries();
		addAllEdgeEntries();
	}

	@Override
	public void createEdgeSchemas(EdgeSchemaCreator edgeSchemaCreator) {
		for ( String label : qualifiedNameEdgeSchemaSet ) {
			edgeSchemaCreator.create(label);
		}
	}
	
	@Override
	public void createVertexSchemas(VertexSchemaCreator vertexSchemaCreator) {
		for ( List<String> hierarchy : qualifiedNameVertexSchemaSet ) {
			vertexSchemaCreator.create(hierarchy);
		}
	}
	
	static public UmlgSchemaCreator getInstance() {
		return INSTANCE;
	}
	
	private void addAllEdgeEntries() {
		this.qualifiedNameEdgeSchemaSet.add("rootNotificationMeta");
		this.qualifiedNameEdgeSchemaSet.add("root_Notification");
		this.qualifiedNameEdgeSchemaSet.add("deletedVertexEdgeToRoot");
		this.qualifiedNameEdgeSchemaSet.add("allinstances");
		this.qualifiedNameEdgeSchemaSet.add("board_block");
		this.qualifiedNameEdgeSchemaSet.add("eddie_board");
		this.qualifiedNameEdgeSchemaSet.add("rootBoardMeta");
		this.qualifiedNameEdgeSchemaSet.add("root_Board");
		this.qualifiedNameEdgeSchemaSet.add("deletedVertexEdgeToRoot");
		this.qualifiedNameEdgeSchemaSet.add("allinstances");
		this.qualifiedNameEdgeSchemaSet.add("board_block");
		this.qualifiedNameEdgeSchemaSet.add("rootBlockMeta");
		this.qualifiedNameEdgeSchemaSet.add("root_Block");
		this.qualifiedNameEdgeSchemaSet.add("deletedVertexEdgeToRoot");
		this.qualifiedNameEdgeSchemaSet.add("allinstances");
		this.qualifiedNameEdgeSchemaSet.add("eddie_board");
		this.qualifiedNameEdgeSchemaSet.add("rootEdwardMeta");
		this.qualifiedNameEdgeSchemaSet.add("root_Edward");
		this.qualifiedNameEdgeSchemaSet.add("deletedVertexEdgeToRoot");
		this.qualifiedNameEdgeSchemaSet.add("allinstances");
		this.qualifiedNameEdgeSchemaSet.add("userGroup_role");
		this.qualifiedNameEdgeSchemaSet.add("rootUserGroupMeta");
		this.qualifiedNameEdgeSchemaSet.add("root_UserGroup");
		this.qualifiedNameEdgeSchemaSet.add("deletedVertexEdgeToRoot");
		this.qualifiedNameEdgeSchemaSet.add("allinstances");
		this.qualifiedNameEdgeSchemaSet.add("userGroup_role");
		this.qualifiedNameEdgeSchemaSet.add("user_role");
		this.qualifiedNameEdgeSchemaSet.add("rootUserMeta");
		this.qualifiedNameEdgeSchemaSet.add("root_User");
		this.qualifiedNameEdgeSchemaSet.add("deletedVertexEdgeToRoot");
		this.qualifiedNameEdgeSchemaSet.add("allinstances");
	}
	
	private void addAllVertexEntries() {
		this.qualifiedNameVertexSchemaSet.add(Arrays.asList("org::umlg::runtime::domain::BaseUmlgCompositionNode", "org::multipoly::Notification::Notification"));
		this.qualifiedNameVertexSchemaSet.add(Arrays.asList("org.multipoly.Notification.meta.NotificationMeta"));
		this.qualifiedNameVertexSchemaSet.add(Arrays.asList("rootVertex"));
		this.qualifiedNameVertexSchemaSet.add(Arrays.asList("deletionVertex"));
		this.qualifiedNameVertexSchemaSet.add(Arrays.asList("org::umlg::runtime::domain::BaseUmlgCompositionNode", "org::multipoly::Board::Board"));
		this.qualifiedNameVertexSchemaSet.add(Arrays.asList("org.multipoly.Board.meta.BoardMeta"));
		this.qualifiedNameVertexSchemaSet.add(Arrays.asList("rootVertex"));
		this.qualifiedNameVertexSchemaSet.add(Arrays.asList("deletionVertex"));
		this.qualifiedNameVertexSchemaSet.add(Arrays.asList("org::umlg::runtime::domain::BaseUmlgCompositionNode", "org::multipoly::Board::Block"));
		this.qualifiedNameVertexSchemaSet.add(Arrays.asList("org.multipoly.Board.meta.BlockMeta"));
		this.qualifiedNameVertexSchemaSet.add(Arrays.asList("rootVertex"));
		this.qualifiedNameVertexSchemaSet.add(Arrays.asList("deletionVertex"));
		this.qualifiedNameVertexSchemaSet.add(Arrays.asList("org::umlg::runtime::domain::BaseUmlgCompositionNode", "org::multipoly::Board::Edward"));
		this.qualifiedNameVertexSchemaSet.add(Arrays.asList("org.multipoly.Board.meta.EdwardMeta"));
		this.qualifiedNameVertexSchemaSet.add(Arrays.asList("rootVertex"));
		this.qualifiedNameVertexSchemaSet.add(Arrays.asList("deletionVertex"));
		this.qualifiedNameVertexSchemaSet.add(Arrays.asList("org::umlg::runtime::domain::BaseUmlgCompositionNode", "org::multipoly::UserGroup"));
		this.qualifiedNameVertexSchemaSet.add(Arrays.asList("org.multipoly.meta.UserGroupMeta"));
		this.qualifiedNameVertexSchemaSet.add(Arrays.asList("rootVertex"));
		this.qualifiedNameVertexSchemaSet.add(Arrays.asList("deletionVertex"));
		this.qualifiedNameVertexSchemaSet.add(Arrays.asList("org::umlg::runtime::domain::BaseUmlgCompositionNode", "org::multipoly::User"));
		this.qualifiedNameVertexSchemaSet.add(Arrays.asList("org.multipoly.meta.UserMeta"));
		this.qualifiedNameVertexSchemaSet.add(Arrays.asList("rootVertex"));
		this.qualifiedNameVertexSchemaSet.add(Arrays.asList("deletionVertex"));
	}


}