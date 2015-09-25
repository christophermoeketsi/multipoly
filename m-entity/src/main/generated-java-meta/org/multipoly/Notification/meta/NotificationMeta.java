package org.multipoly.Notification.meta;

import java.util.Iterator;

import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.umlg.runtime.adaptor.UMLG;
import org.umlg.runtime.adaptor.UmlgLabelConverterFactory;
import org.umlg.runtime.domain.BaseMetaNode;
import org.umlg.runtime.domain.UmlgMetaNode;

public class NotificationMeta extends BaseMetaNode implements UmlgMetaNode {


	/**
	 * constructor for NotificationMeta
	 * 
	 * @param vertex 
	 */
	public NotificationMeta(Vertex vertex)  {
		this.vertex= vertex;
	}
	
	/**
	 * default constructor for NotificationMeta
	 */
	public NotificationMeta()  {
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
		return UmlgLabelConverterFactory.getUmlgLabelConverter().convert("rootNotificationMeta");
	}
	
	synchronized static public NotificationMeta getInstance() {
		NotificationMeta result;
		Iterator<Edge> iter = UMLG.get().getRoot().edges(Direction.OUT, UmlgLabelConverterFactory.getUmlgLabelConverter().convert("rootNotificationMeta"));
		if ( iter.hasNext() ) {
			result =  new NotificationMeta(iter.next().inVertex());
		} else {
			iter = UMLG.get().getRoot().edges(Direction.OUT, UmlgLabelConverterFactory.getUmlgLabelConverter().convert("rootNotificationMeta"));
			if ( !iter.hasNext() ) {
				result = new NotificationMeta();
			} else {
				result = new NotificationMeta(iter.next().inVertex());
			}
		}
		return result;
	}


}