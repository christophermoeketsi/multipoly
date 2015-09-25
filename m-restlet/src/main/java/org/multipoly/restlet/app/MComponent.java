package org.multipoly.restlet.module;


/**
 * Date: 2013/04/17
 * Time: 11:16 AM
 */
public class MComponent extends Component {

    private final CmRestletApplication cmRestletApplication;

    public MComponent() {
        setName("RESTful Cm Server component");
        setDescription("Cm rest component");
        setOwner("Restlet SAS");
        setAuthor("The 80% Team");
        getClients().add(Protocol.FILE);
        getClients().add(Protocol.CLAP);
        getClients().add(Protocol.RIAP);
        this.cmRestletApplication = new CmRestletApplication();
        getDefaultHost().attachDefault(cmRestletApplication);
    }

    public CmMemoryRealm getCmMemoryRealm() {
        return this.cmRestletApplication.getRealm();
    }

}
