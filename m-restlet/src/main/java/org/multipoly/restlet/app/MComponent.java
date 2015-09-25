package org.multipoly.restlet.module;


import org.multipoly.restlet.app.MRestletApplication;
import org.multipoly.security.MMemoryRealm;
import org.restlet.Component;
import org.restlet.data.Protocol;

/**
 * Date: 2013/04/17
 * Time: 11:16 AM
 */
public class MComponent extends Component {

    private final MRestletApplication mRestletApplication;

    public MComponent() {
        setName("RESTful Cm Server component");
        setDescription("Cm rest component");
        setOwner("Restlet SAS");
        setAuthor("The 80% Team");
        getClients().add(Protocol.FILE);
        getClients().add(Protocol.CLAP);
        getClients().add(Protocol.RIAP);
        this.mRestletApplication = new MRestletApplication();
        getDefaultHost().attachDefault(mRestletApplication);
    }

    public MMemoryRealm getCmMemoryRealm() {
        return this.mRestletApplication.getRealm();
    }

}
