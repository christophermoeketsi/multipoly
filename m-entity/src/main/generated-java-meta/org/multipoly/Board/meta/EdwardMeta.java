package org.multipoly.Board.meta;

import java.util.Iterator;

import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.umlg.runtime.adaptor.UMLG;
import org.umlg.runtime.adaptor.UmlgLabelConverterFactory;
import org.umlg.runtime.domain.BaseMetaNode;
import org.umlg.runtime.domain.UmlgMetaNode;

public class EdwardMeta extends BaseMetaNode implements UmlgMetaNode {


	/**
	 * constructor for EdwardMeta
	 * 
	 * @param vertex 
	 */
	public EdwardMeta(Vertex vertex)  {
		this.vertex= vertex;
	}
	
	/**
	 * default constructor for EdwardMeta
	 */
	public EdwardMeta()  {
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
		return UmlgLabelConverterFactory.getUmlgLabelConverter().convert("rootEdwardMeta");
	}
	
	synchronized static public EdwardMeta getInstance() {
		EdwardMeta result;
		Iterator<Edge> iter = UMLG.get().getRoot().edges(Direction.OUT, UmlgLabelConverterFactory.getUmlgLabelConverter().convert("rootEdwardMeta"));
		if ( iter.hasNext() ) {
			result =  new EdwardMeta(iter.next().inVertex());
		} else {
			iter = UMLG.get().getRoot().edges(Direction.OUT, UmlgLabelConverterFactory.getUmlgLabelConverter().convert("rootEdwardMeta"));
			if ( !iter.hasNext() ) {
				result = new EdwardMeta();
			} else {
				result = new EdwardMeta(iter.next().inVertex());
			}
		}
		return result;
	}


}