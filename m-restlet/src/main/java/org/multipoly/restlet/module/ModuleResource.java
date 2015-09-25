package org.multipoly.restlet.module;

import freemarker.template.utility.StringUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.umlg.runtime.adaptor.UMLG;
import org.umlg.runtime.collection.memory.UmlgMemorySet;

import org.multipoly.restlet.app.CmComponent;


import java.util.Arrays;
import java.util.Set;

public class ModuleResource extends BaseServerResource {


    public final static String PATH = "/module/{id}";

    public ModuleResource() {
        setNegotiated(false);
    }

    public static JSONObject getModuleAsJSON (ROLE item) {
        JSONObject result = new JSONObject();

        try {
            result.put("id", item.name()); // All modules are unique
            result.put("name", StringUtil.capitalize(item.name()));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public static JSONArray getModulesAsJSON (Set<ROLE> items) {
        JSONArray result = new JSONArray();

        for (ROLE item : items)
            result.put(getModuleAsJSON(item));

        return result;
    }

    @Override
    protected Representation get() throws ResourceException {
        String id = (String) getRequestAttributes().get("id");

        if (id.equals("all") || id.equals("")){
            return new JsonRepresentation(getModulesAsJSON(new UmlgMemorySet<>(Arrays.asList(ROLE.values()))));
        } else {
            return new JsonRepresentation(getModuleAsJSON(UMLG.get().getEntity(id)));
        }
    }
}
