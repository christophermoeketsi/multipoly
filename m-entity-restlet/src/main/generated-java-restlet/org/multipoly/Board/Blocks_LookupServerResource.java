package org.multipoly.Board;

import org.restlet.representation.Representation;
import org.restlet.resource.Post;

public interface Blocks_LookupServerResource {
	@Post(	"json")
	public Representation post(Representation entity);


}