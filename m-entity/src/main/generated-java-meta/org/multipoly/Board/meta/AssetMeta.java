package org.multipoly.Board.meta;

import java.util.Iterator;

import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.umlg.runtime.adaptor.UMLG;
import org.umlg.runtime.adaptor.UmlgLabelConverterFactory;
import org.umlg.runtime.domain.BaseMetaNode;
import org.umlg.runtime.domain.UmlgMetaNode;

public class AssetMeta extends BaseMetaNode implements UmlgMetaNode {


	/**
	 * constructor for AssetMeta
	 * 
	 * @param vertex 
	 */
	public AssetMeta(Vertex vertex)  {
		this.vertex= vertex;
	}
	
	/**
	 * default constructor for AssetMeta
	 */
	public AssetMeta()  {
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
		return UmlgLabelConverterFactory.getUmlgLabelConverter().convert("rootAssetMeta");
	}
	
	synchronized static public AssetMeta getInstance() {
		AssetMeta result;
		Iterator<Edge> iter = UMLG.get().getRoot().edges(Direction.OUT, UmlgLabelConverterFactory.getUmlgLabelConverter().convert("rootAssetMeta"));
		if ( iter.hasNext() ) {
			result =  new AssetMeta(iter.next().inVertex());
		} else {
			iter = UMLG.get().getRoot().edges(Direction.OUT, UmlgLabelConverterFactory.getUmlgLabelConverter().convert("rootAssetMeta"));
			if ( !iter.hasNext() ) {
				result = new AssetMeta();
			} else {
				result = new AssetMeta(iter.next().inVertex());
			}
		}
		return result;
	}


}