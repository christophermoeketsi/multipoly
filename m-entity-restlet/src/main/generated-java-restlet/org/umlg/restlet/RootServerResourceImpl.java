package org.umlg.restlet;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.umlg.model.RootElement.RootElementRuntimePropertyEnum;

public class RootServerResourceImpl extends ServerResource {


	/**
	 * default constructor for RootServerResourceImpl
	 */
	public RootServerResourceImpl()  {
		setNegotiated(false);
	}

	@Override
	public Representation get() throws ResourceException {
		StringBuilder json = new StringBuilder();
		json.append("[{\"data\": [{\"name\": \"APP\"}]");
		json.append(", \"meta\": ");
		json.append("{\"qualifiedName\": \"RootElement\"");
		json.append("}}]");
		return new JsonRepresentation(json.toString());
	}
	
	@Override
	public Representation options() throws ResourceException {
		StringBuilder json = new StringBuilder();
		json.append("[{\"meta\": ");
		json.append("{\"qualifiedName\": \"RootElement\"");
		json.append(", \"to\": ");
		json.append(RootElementRuntimePropertyEnum.asJson());
		json.append("}}]");
		return new JsonRepresentation(json.toString());
	}


}