package org.multipoly.Notification;

import org.restlet.representation.Representation;
import org.restlet.resource.Post;

public interface Notifications_LookupServerResource {
	@Post(	"json")
	public Representation post(Representation entity);


}