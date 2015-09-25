package org.umlg.rootelement;

import org.umlg.runtime.restlet.UmlgRestletComponent;

public class RootElementComponent extends UmlgRestletComponent {



	static public void main(String[] args) throws Exception {
		RootElementComponent app = new RootElementComponent();
		app.start();
	}
	
	@Override
	protected void attachApplications() {
		getDefaultHost().attach("/RootElement", new RootElementApplication());
	}


}