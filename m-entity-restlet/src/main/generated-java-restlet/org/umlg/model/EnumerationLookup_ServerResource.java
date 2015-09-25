package org.umlg.model;

import org.restlet.representation.Representation;
import org.restlet.resource.Get;

public interface EnumerationLookup_ServerResource {
	@Get(	"json")
	public Representation get();


}