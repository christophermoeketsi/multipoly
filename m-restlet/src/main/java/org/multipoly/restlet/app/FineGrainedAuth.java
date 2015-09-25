package org.multipoly.restlet.app;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.multipoly.User.ROLE;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.routing.Filter;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class FineGrainedAuth extends Filter {

    private Map<String, ROLE[]> authMapping = new HashMap<>();

    public FineGrainedAuth(Set<Method> authMethods) {
        for (Method authMethod : authMethods) {
            String httpMethod = "";

            if (authMethod.getAnnotation(Post.class) != null) {
                httpMethod = "post";
            } else if (authMethod.getAnnotation(Get.class) != null) {
                httpMethod = "get";
            } else if (authMethod.getAnnotation(Put.class) != null) {
                httpMethod = "put";
            } else if (authMethod.getAnnotation(Delete.class) != null) {
                httpMethod = "delete";
            }

            if (!httpMethod.equals("")) {
                authMapping.put(httpMethod, authMethod.getAnnotation(Auth.class).value());
            }
        }

    }

    @Override
    protected int beforeHandle(Request request, Response response) {
        List<String> roles = request.getClientInfo().getRoles().stream().map(r -> r.getName().toLowerCase()).collect(Collectors.toList());
        ROLE[] requiredModules = authMapping.get(request.getMethod().getName().toLowerCase());

        if (requiredModules != null && requiredModules.length > 0) {
            for (ROLE role : requiredModules) {
                if (roles.contains(role.toString().toLowerCase())) {
                    return CONTINUE;
                }
            }

            try {
                List<ROLE> roles1 = Arrays.asList(requiredModules);
                String requiredModulesResponse = roles1.stream().map(m -> StringUtils.capitalize(m.toString().toLowerCase())).collect(Collectors.joining(", "));

                response.setEntity(new JsonRepresentation(
                        new JSONObject()
                            .put("message", "Not authorized to access resource, the following role(s) are required: " + requiredModulesResponse)));

            } catch (JSONException e) {
                throw new RuntimeException("Could not create json representation for authentication response.", e);
            }

            response.setStatus(Status.CLIENT_ERROR_UNAUTHORIZED);
            return STOP;
        } else { // if there is no required modules specified then let the user pass by default
            return CONTINUE;
        }
    }
}
