package org.multipoly.meta;

import java.util.Iterator;

import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.umlg.runtime.adaptor.UMLG;
import org.umlg.runtime.adaptor.UmlgLabelConverterFactory;
import org.umlg.runtime.domain.BaseMetaNode;
import org.umlg.runtime.domain.UmlgMetaNode;

public class UserMeta extends BaseMetaNode implements UmlgMetaNode {


	/**
	 * constructor for UserMeta
	 * 
	 * @param vertex 
	 */
	public UserMeta(Vertex vertex)  {
		this.vertex= vertex;
	}
	
	/**
	 * default constructor for UserMeta
	 */
	public UserMeta()  {
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
		return UmlgLabelConverterFactory.getUmlgLabelConverter().convert("rootUserMeta");
	}
	
	synchronized static public UserMeta getInstance() {
		UserMeta result;
		Iterator<Edge> iter = UMLG.get().getRoot().edges(Direction.OUT, UmlgLabelConverterFactory.getUmlgLabelConverter().convert("rootUserMeta"));
		if ( iter.hasNext() ) {
			result =  new UserMeta(iter.next().inVertex());
		} else {
			iter = UMLG.get().getRoot().edges(Direction.OUT, UmlgLabelConverterFactory.getUmlgLabelConverter().convert("rootUserMeta"));
			if ( !iter.hasNext() ) {
				result = new UserMeta();
			} else {
				result = new UserMeta(iter.next().inVertex());
			}
		}
		return result;
	}


}