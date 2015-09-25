package org.multipoly.User;

import org.restlet.representation.Representation;
import org.restlet.resource.Post;

public interface Users_LookupServerResource {
	@Post(	"json")
	public Representation post(Representation entity);


}