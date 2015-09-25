package org.umlg.model;

import org.multipoly.Board.meta.AssetMeta;
import org.multipoly.Board.meta.BlockMeta;
import org.multipoly.Board.meta.BoardMeta;
import org.multipoly.Notification.meta.NotificationMeta;
import org.multipoly.User.meta.UserGroupMeta;
import org.multipoly.User.meta.UserMeta;
import org.umlg.runtime.adaptor.UmlgMetaNodeManager;

/** This class is responsible to create the meta singleton upfront.
 * It is invoked via reflection the first time a graph is created.
 */
public class MetaNodeCreator implements UmlgMetaNodeManager {



	public int count() {
		return 6;
	}
	
	public void createAllMetaNodes() {
		UserGroupMeta.getInstance();
		UserMeta.getInstance();
		NotificationMeta.getInstance();
		BoardMeta.getInstance();
		BlockMeta.getInstance();
		AssetMeta.getInstance();
	}


}