package org.multipoly.meta;

import java.util.Iterator;

import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.umlg.runtime.adaptor.UMLG;
import org.umlg.runtime.adaptor.UmlgLabelConverterFactory;
import org.umlg.runtime.domain.BaseMetaNode;
import org.umlg.runtime.domain.UmlgMetaNode;

public class UserGroupMeta extends BaseMetaNode implements UmlgMetaNode {


	/**
	 * constructor for UserGroupMeta
	 * 
	 * @param vertex 
	 */
	public UserGroupMeta(Vertex vertex)  {
		this.vertex= vertex;
	}
	
	/**
	 * default constructor for UserGroupMeta
	 */
	public UserGroupMeta()  {
		this.vertex = UMLG.get().addVertex(this.getClass().getName());
		this.vertex.property("className", getClass().getName());
		defaultCreate();
		UMLG.get().getRoot().addEdge(getEdgeToRootLabel(), this.vertex);
	}

	@Override
	public void defaultCreate() {
		getUid();
	}
	
	public String getEdgeToRootLabel() {
		return UmlgLabelConverterFactory.getUmlgLabelConverter().convert("rootUserGroupMeta");
	}
	
	synchronized static public UserGroupMeta getInstance() {
		UserGroupMeta result;
		Iterator<Edge> iter = UMLG.get().getRoot().edges(Direction.OUT, UmlgLabelConverterFactory.getUmlgLabelConverter().convert("rootUserGroupMeta"));
		if ( iter.hasNext() ) {
			result =  new UserGroupMeta(iter.next().inVertex());
		} else {
			iter = UMLG.get().getRoot().edges(Direction.OUT, UmlgLabelConverterFactory.getUmlgLabelConverter().convert("rootUserGroupMeta"));
			if ( !iter.hasNext() ) {
				result = new UserGroupMeta();
			} else {
				result = new UserGroupMeta(iter.next().inVertex());
			}
		}
		return result;
	}


}