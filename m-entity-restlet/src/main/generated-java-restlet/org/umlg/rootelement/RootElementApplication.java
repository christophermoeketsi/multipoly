package org.umlg.rootelement;

import org.restlet.routing.Router;
import org.umlg.runtime.restlet.UmlgRestletApplication;

public class RootElementApplication extends UmlgRestletApplication {


	/**
	 * default constructor for RootElementApplication
	 */
	public RootElementApplication()  {
	}

	@Override
	protected void attachAll(Router router) {
		org.umlg.RestletRouterEnum.attachAll(router);;
	}
	
	@Override
	protected String getModelFileName() {
		return "model.uml";
	}


}