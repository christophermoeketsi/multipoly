package org.multipoly.User;

import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.umlg.runtime.adaptor.UMLG;
import org.umlg.runtime.adaptor.UmlgTmpIdManager;
import org.umlg.runtime.restlet.util.UmlgURLDecoder;

public class UserLookupServerResourceImpl extends ServerResource {
	private Object userId;

	/**
	 * default constructor for UserLookupServerResourceImpl
	 */
	public UserLookupServerResourceImpl()  {
		setNegotiated(false);
	}

	@Override
	public Representation put(Representation entity) throws ResourceException {
		this.userId= UmlgURLDecoder.decode((String)getRequestAttributes().get("userId"));
		User c = UMLG.get().getEntity(this.userId);
		try {
			String entityText = entity.getText();
			c.fromJson(entityText);
			String lookupUri = UmlgURLDecoder.decode(getReference().getQueryAsForm(false).getFirstValue("lookupUri"));
			lookupUri = "riap://host" + lookupUri;
			int fakeIdIndex = lookupUri.indexOf("fake");
			if ( fakeIdIndex != -1 ) {
				int indexOfForwardSlash = lookupUri.indexOf("/", fakeIdIndex);
				String fakeId = lookupUri.substring(fakeIdIndex, indexOfForwardSlash);
				Object id = UmlgTmpIdManager.INSTANCE.get(fakeId);
				lookupUri = lookupUri.replace(fakeId, UmlgURLDecoder.encode(id.toString()));
			}
			ClientResource cr = new ClientResource(lookupUri);
			Representation result = cr.get();
			return result;
		} catch (Exception e) {
			if ( e instanceof RuntimeException ) {
				throw (RuntimeException)e;
			}
			throw new RuntimeException(e);
		} finally {
			UmlgTmpIdManager.INSTANCE.remove();
			UMLG.get().rollback();
		}
	}


}