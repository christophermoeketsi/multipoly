package org.multipoly;

import org.restlet.representation.Representation;
import org.restlet.resource.Post;

public interface UserGroups_LookupServerResource {
	@Post(	"json")
	public Representation post(Representation entity);


}