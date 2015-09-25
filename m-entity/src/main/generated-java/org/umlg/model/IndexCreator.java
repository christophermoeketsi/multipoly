package org.umlg.model;

import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.umlg.runtime.adaptor.UMLG;
import org.umlg.runtime.adaptor.UmlgGraph;
import org.umlg.runtime.adaptor.UmlgIndexManager;
import org.umlg.runtime.adaptor.UmlgParameter;

/** This class is responsible to create all keyed indexes.
 * It is invoked via reflection the first time a graph is created.
 */
public class IndexCreator implements UmlgIndexManager {



	public void createIndexes() {
		UMLG.get().createKeyIndex(UmlgGraph.ROOT_VERTEX, Vertex.class, new UmlgParameter<String,Class<?>>("unusedIndexValueType", String.class), new UmlgParameter<String,Boolean>("unusedUniqueorNot", true), new UmlgParameter<String,String>("unusedLabel", "ROOT_VERTEX"));
	}


}