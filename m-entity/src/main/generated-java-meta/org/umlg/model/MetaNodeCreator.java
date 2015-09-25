package org.umlg.model;

import org.multipoly.Board.meta.BlockMeta;
import org.multipoly.Board.meta.BoardMeta;
import org.multipoly.Board.meta.EdwardMeta;
import org.multipoly.Notification.meta.NotificationMeta;
import org.multipoly.meta.UserGroupMeta;
import org.multipoly.meta.UserMeta;
import org.umlg.runtime.adaptor.UmlgMetaNodeManager;

/** This class is responsible to create the meta singleton upfront.
 * It is invoked via reflection the first time a graph is created.
 */
public class MetaNodeCreator implements UmlgMetaNodeManager {



	public int count() {
		return 6;
	}
	
	public void createAllMetaNodes() {
		NotificationMeta.getInstance();
		BoardMeta.getInstance();
		BlockMeta.getInstance();
		EdwardMeta.getInstance();
		UserGroupMeta.getInstance();
		UserMeta.getInstance();
	}


}