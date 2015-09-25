package org.multipoly.restlet.app;

import org.apache.commons.lang.StringUtils;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.security.Role;
import org.restlet.security.RoleAuthorizer;

import java.util.stream.Collectors;
import java.util.stream.Stream;

class CmRoleAuthorizer extends RoleAuthorizer {
    @Override
    protected int beforeHandle(Request request, Response response) {
        StringBuilder builder = new StringBuilder();
        String requiredRoles = getAuthorizedRoles().stream().map(role -> StringUtils.capitalize(StringUtils.lowerCase(role.getName()))).collect(Collectors.joining(""));

        builder.append("{");
        builder.append("\"message\": \"" + requiredRoles + " role(s) required to complete the request.\"");
        builder.append("}");

        response.setEntity(new JsonRepresentation(builder.toString()));

        return super.beforeHandle(request, response);
    }
}
