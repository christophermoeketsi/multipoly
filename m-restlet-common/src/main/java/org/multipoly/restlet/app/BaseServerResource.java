package org.multipoly.restlet.app;

import org.multipoly.restlet.BaseMApplication;
import org.multipoly.security.MMemoryRealm;
import org.restlet.engine.header.Header;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.restlet.security.Role;
import org.restlet.util.Series;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Date: 2013/08/16
 * Time: 9:14 AM
 */
public abstract class BaseServerResource extends ServerResource {
    protected Map<String, Object> getDataModelWithUser() {
        String cmPrefix = extractCmPrefix();
        Map<String, Object> dataModel = new HashMap<String, Object>();
        dataModel.put("username", getClientInfo().getUser());
        dataModel.put("cmPrefix", cmPrefix);
        List<String> roles = new ArrayList<String>(getClientInfo().getRoles().size());
        for (Role role : getClientInfo().getRoles()) {
            roles.add(role.getName());
        }
        dataModel.put("roles", roles);
        return dataModel;
    }

    protected String extractCmPrefix() {
        String url = getReference().getBaseRef().getRemainingPart();
        int indexStart = url.indexOf("/cm/");
        int indexEnd = url.indexOf("/", indexStart + "/cm/".length() + 1);
        return url.substring(indexStart + "/cm/".length(), indexEnd);
    }

    @Override
    protected Representation options() throws ResourceException {
        setHeaders();
        return new StringRepresentation("");
    }

    public void setHeaders(){
        Series<Header> responseHeaders = (Series<Header>) getResponse().getAttributes().get("org.restlet.http.headers");
        if (responseHeaders == null) {
            responseHeaders = new Series(Header.class);
            getResponse().getAttributes().put("org.restlet.http.headers", responseHeaders);
        }
        String hostIdentifier = getRequest().getHostRef().getHostIdentifier();
        responseHeaders.add(new Header("Access-Control-Allow-Origin", hostIdentifier.replace("8081","8080")));
        responseHeaders.add((new Header("Access-Control-Allow-Credentials", "true")));
        responseHeaders.add(new Header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept"));
        responseHeaders.add(new Header("Access-Control-Allow-Methods", "PUT, GET, POST, OPTIONS"));
    }

    protected MMemoryRealm getRealm() {
        return ((BaseMApplication)this.getApplication()).getRealm();
    }

}
