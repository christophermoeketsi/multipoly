package org.umlg.model;

import java.util.Arrays;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.umlg.runtime.domain.UmlgEnum;
import org.umlg.runtime.domain.json.ToJsonUtil;

public class EnumerationLookup_ServerResourceImpl extends ServerResource implements EnumerationLookup_ServerResource {


	/**
	 * default constructor for EnumerationLookup_ServerResourceImpl
	 */
	public EnumerationLookup_ServerResourceImpl()  {
		setNegotiated(false);
	}

	@Override
	public Representation get() throws ResourceException {
		String enumQualifiedName = getQuery().getFirst("enumQualifiedName").getValue();;
		Class<?> enumClass = QualifiedNameClassMap.INSTANCE.get(enumQualifiedName);
		UmlgEnum[] enumConstants = (UmlgEnum[])enumClass.getEnumConstants();
		StringBuilder json = new StringBuilder();
		json.append("{\"data\": [");
		json.append(ToJsonUtil.enumsToJson(Arrays.asList(enumConstants)));
		json.append("]}");
		return new JsonRepresentation(json.toString());
	}


}