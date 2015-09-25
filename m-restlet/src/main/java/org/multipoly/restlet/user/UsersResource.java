package org.multipoly.restlet.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rorotika.cm.admin.initialize.CmInitialize;
import com.rorotika.cm.admin.CmUserThreadVar;
import com.rorotika.cm.restlet.app.BaseServerResource;
import com.rorotika.cm.user.User;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.umlg.runtime.collection.UmlgSet;
import org.umlg.runtime.util.ObjectMapperFactory;

/**
 * Date: 2014/09/02
 * Time: 11:46 AM
 */
public class UsersResource extends BaseServerResource {

    public final static String PATH = "/data/users";

    public UsersResource() {
        setNegotiated(false);
    }

    @Override
    protected Representation get() throws ResourceException {
        ObjectMapper objectMapper = ObjectMapperFactory.INSTANCE.getObjectMapper();
        ObjectNode result = new ObjectNode(objectMapper.getNodeFactory());
        ArrayNode userNodes = new ArrayNode(objectMapper.getNodeFactory());
        User currentUser = CmUserThreadVar.INSTANCE.get();
        //TODO the CmMemoryRealm is out of sync, needs more notification listeners
        currentUser.reload();
        //Get the networks the user has access to
        //i.e get the user's Networks the user has access to via the UserGroups
        //then for each network the user has access to get the User via the Networks UserGroups
        UmlgSet<User> users= currentUser.getUsersInMyNetworks();
        users.forEach(u -> {
            ObjectNode userNode = new ObjectNode(objectMapper.getNodeFactory());
            userNode.put("id", u.getId().toString());
            userNode.put("username", u.getUsername());
            userNodes.add(userNode);
        });
        //Include the system user/{users}
        User systemUser = User.user_findByUsername(CmInitialize.SYSTEM);
        ObjectNode systemUserNode = new ObjectNode(objectMapper.getNodeFactory());
        systemUserNode.put("id", systemUser.getId().toString());
        systemUserNode.put("username", systemUser.getUsername());
        userNodes.add(systemUserNode);

        result.put("status", "success");
        result.put("users", userNodes);
        return new JsonRepresentation(result.toString());
    }
}