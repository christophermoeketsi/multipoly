package org.multipoly.Notification;

import org.multipoly.Notification.Notification.NotificationRuntimePropertyEnum;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.EmptyRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.umlg.runtime.adaptor.UMLG;
import org.umlg.runtime.adaptor.UmlgExceptionUtilFactory;
import org.umlg.runtime.restlet.util.UmlgURLDecoder;

public class NotificationServerResourceImpl extends ServerResource {
	private Object notificationId;

	/**
	 * default constructor for NotificationServerResourceImpl
	 */
	public NotificationServerResourceImpl()  {
		setNegotiated(false);
	}

	@Override
	public Representation delete() throws ResourceException {
		this.notificationId= UmlgURLDecoder.decode((String)getRequestAttributes().get("notificationId"));
		Notification c = UMLG.get().getEntity(this.notificationId);
		try {
			c.delete();
			UMLG.get().commit();
		} catch (Exception e) {
			UMLG.get().rollback();
			throw UmlgExceptionUtilFactory.getTumlExceptionUtil().handle(e);
		}
		return new EmptyRepresentation();
	}
	
	@Override
	public Representation get() throws ResourceException {
		try {
			StringBuilder json = new StringBuilder();
			this.notificationId= UmlgURLDecoder.decode((String)getRequestAttributes().get("notificationId"));
			Notification c = UMLG.get().getEntity(this.notificationId);
			json.append("[{\"data\": ");
			json.append(c.toJsonWithoutCompositeParent());
			json.append(", \"meta\" : {");
			json.append("\"qualifiedName\": \"RootElement::org::multipoly::Notification::Notification\"");
			json.append("}}]");
			return new JsonRepresentation(json.toString());
		} finally {
			UMLG.get().rollback();
		}
	}
	
	@Override
	public Representation options() throws ResourceException {
		try {
			StringBuilder json = new StringBuilder();
			json.append("[");
			json.append("{\"data\": null,");
			json.append(" \"meta\" : {");
			json.append("\"qualifiedName\": \"RootElement::org::multipoly::Notification::Notification\"");
			json.append(", \"to\": ");
			json.append(NotificationRuntimePropertyEnum.asJson());
			json.append("} ");
			json.append("}]");
			return new JsonRepresentation(json.toString());
		} finally {
			UMLG.get().rollback();
		}
	}
	
	@Override
	public Representation put(Representation entity) throws ResourceException {
		this.notificationId= UmlgURLDecoder.decode((String)getRequestAttributes().get("notificationId"));
		Notification c = UMLG.get().getEntity(this.notificationId);
		try {
			String entityText = entity.getText();
			c.fromJson(entityText);
			UMLG.get().commit();
			StringBuilder json = new StringBuilder();
			json.append("[{\"data\": ");
			json.append(c.toJsonWithoutCompositeParent());
			json.append(", \"meta\" : {");
			json.append("\"qualifiedName\": \"RootElement::org::multipoly::Notification::Notification\"");
			json.append(", \"to\": ");
			json.append(NotificationRuntimePropertyEnum.asJson());
			json.append("}}]");
			return new JsonRepresentation(json.toString());
		} catch (Exception e) {
			UMLG.get().rollback();
			if ( e instanceof RuntimeException ) {
				throw (RuntimeException)e;
			}
			throw new RuntimeException(e);
		} finally {
			UMLG.get().rollback();
		}
	}


}