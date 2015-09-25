package org.multipoly.restlet.user;

import com.rorotika.cm.admin.CmUserThreadVar;
import com.rorotika.cm.network.policy.Policy;
import com.rorotika.cm.restlet.app.BaseServerResource;
import com.rorotika.cm.restlet.modules.policy.AllPoliciesResource;
import com.rorotika.cm.user.User;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.umlg.runtime.adaptor.UMLG;
import org.umlg.sqlg.structure.RecordId;
import org.umlg.sqlg.structure.SchemaTable;
import java.util.List;

/**
 * Date: 2014/09/02
 * Time: 11:46 AM
 */
public class FavouritePolicyResource extends BaseServerResource {

    public final static String PATH = "/favourites/policy/{id}";

    public FavouritePolicyResource() { setNegotiated(false); }

    @Override
    protected Representation delete() throws ResourceException {
        User user = CmUserThreadVar.INSTANCE.get();

        String policyId = (String) getRequestAttributes().get("id");
        Policy policy = UMLG.get().getEntity(RecordId.from(SchemaTable.of("public", "Policy"), Long.valueOf(policyId)));

        policy.removeFromPolicyLabel(user.getFavouritePolicyLabel());
        UMLG.get().commit();

        return new JsonRepresentation("{ \"success\": true }");
    }

    @Override
    protected Representation post(Representation entity) throws ResourceException {
        User user = CmUserThreadVar.INSTANCE.get();

        String policyId = (String) getRequestAttributes().get("id");
        Policy policy = UMLG.get().getEntity(RecordId.from(SchemaTable.of("public","Policy"), Long.valueOf(policyId)));

        policy.addToPolicyLabel(user.getFavouritePolicyLabel());
        UMLG.get().commit();

        return new JsonRepresentation("{ \"success\": true }");
    }

    @Override
    protected Representation get() throws ResourceException {
        User user = CmUserThreadVar.INSTANCE.get();

        List<Policy> policies = user.getFavouritePolicies().asSequence();

        return AllPoliciesResource.getPoliciesJSONRepresentation(1, policies);
    }
}