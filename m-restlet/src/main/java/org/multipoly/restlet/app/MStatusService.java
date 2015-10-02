package org.multipoly.restlet.app;

import org.apache.commons.lang3.StringEscapeUtils;
import org.multipoly.MExceptions;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Resource;
import org.restlet.service.StatusService;

/**
 * Date: 2015/05/29
 * Time: 8:01 AM
 */
public class MStatusService extends StatusService {

    @Override
    public Status getStatus(Throwable throwable, Resource resource) {
        Status ret;
        Throwable cause = throwable.getCause();
        if (throwable instanceof MExceptions.MException) {
            ret = new Status(Status.SERVER_ERROR_INTERNAL, throwable, throwable.getMessage());
        } else if (cause instanceof MExceptions.MException) {
            ret = new Status(Status.SERVER_ERROR_INTERNAL, cause, StringEscapeUtils.escapeJson(cause.getMessage()));
        } else {
            if (cause != null) {
                ret = new Status(Status.SERVER_ERROR_INTERNAL, cause, StringEscapeUtils.escapeJson(cause.getMessage()));
            } else {
                ret = new Status(Status.SERVER_ERROR_INTERNAL, throwable, StringEscapeUtils.escapeJson(throwable.getMessage()));
            }
        }
        return ret;
    }

    @Override
    public Representation getRepresentation(Status status, Request request,
                                            Response response) {
        StringBuilder error = new StringBuilder();
        error.append("{");
        error.append("\"message\": \"").append(status.getDescription()).append("\"");
        error.append("}");
        return new JsonRepresentation(error.toString());
    }

}
