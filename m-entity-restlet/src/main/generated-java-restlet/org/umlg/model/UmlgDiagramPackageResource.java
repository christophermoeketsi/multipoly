package org.umlg.model;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class UmlgDiagramPackageResource extends ServerResource {


	/**
	 * default constructor for UmlgDiagramPackageResource
	 */
	public UmlgDiagramPackageResource()  {
		setNegotiated(false);
	}

	@Override
	public Representation get() throws ResourceException {
		return new JsonRepresentation("[{\"label\":\"RootElement\",\"children\":[{\"label\":\"org\",\"children\":[{\"label\":\"multipoly\",\"children\":[{\"label\":\"User\"},{\"label\":\"Notification\"},{\"label\":\"Board\"}]}]}]}]");
	}


}