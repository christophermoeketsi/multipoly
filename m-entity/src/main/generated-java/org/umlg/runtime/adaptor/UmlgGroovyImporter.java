package org.umlg.runtime.adaptor;

import java.util.HashSet;
import java.util.Set;

public class UmlgGroovyImporter {
	static public Set<String> imports = new HashSet<String>();
	static public Set<String> importStatic = new HashSet<String>();

	static {
		imports.add("org.multipoly.Notification.*");
		importStatic.add("org.multipoly.Notification.Notification.NotificationRuntimePropertyEnum");
		imports.add("org.umlg.runtime.adaptor.*");
		imports.add("org.multipoly.Board.*");
		importStatic.add("org.multipoly.Board.Board.BoardRuntimePropertyEnum");
		imports.add("org.umlg.runtime.adaptor.*");
		imports.add("org.multipoly.Board.*");
		importStatic.add("org.multipoly.Board.Block.BlockRuntimePropertyEnum");
		imports.add("org.umlg.runtime.adaptor.*");
		imports.add("org.multipoly.Board.*");
		importStatic.add("org.multipoly.Board.Edward.EdwardRuntimePropertyEnum");
		imports.add("org.umlg.runtime.adaptor.*");
		imports.add("org.multipoly.*");
		importStatic.add("org.multipoly.UserGroup.UserGroupRuntimePropertyEnum");
		imports.add("org.umlg.runtime.adaptor.*");
		imports.add("org.multipoly.*");
		importStatic.add("org.multipoly.User.UserRuntimePropertyEnum");
		imports.add("org.umlg.runtime.adaptor.*");
	}





}