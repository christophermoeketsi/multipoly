package org.multipoly.Board.meta;

import java.util.Iterator;

import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.umlg.runtime.adaptor.UMLG;
import org.umlg.runtime.adaptor.UmlgLabelConverterFactory;
import org.umlg.runtime.domain.BaseMetaNode;
import org.umlg.runtime.domain.UmlgMetaNode;

public class BoardMeta extends BaseMetaNode implements UmlgMetaNode {


	/**
	 * constructor for BoardMeta
	 * 
	 * @param vertex 
	 */
	public BoardMeta(Vertex vertex)  {
		this.vertex= vertex;
	}
	
	/**
	 * default constructor for BoardMeta
	 */
	public BoardMeta()  {
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
		return UmlgLabelConverterFactory.getUmlgLabelConverter().convert("rootBoardMeta");
	}
	
	synchronized static public BoardMeta getInstance() {
		BoardMeta result;
		Iterator<Edge> iter = UMLG.get().getRoot().edges(Direction.OUT, UmlgLabelConverterFactory.getUmlgLabelConverter().convert("rootBoardMeta"));
		if ( iter.hasNext() ) {
			result =  new BoardMeta(iter.next().inVertex());
		} else {
			iter = UMLG.get().getRoot().edges(Direction.OUT, UmlgLabelConverterFactory.getUmlgLabelConverter().convert("rootBoardMeta"));
			if ( !iter.hasNext() ) {
				result = new BoardMeta();
			} else {
				result = new BoardMeta(iter.next().inVertex());
			}
		}
		return result;
	}


}