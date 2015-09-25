package org.multipoly.restlet;

import org.multipoly.security.MMemoryRealm;
import org.restlet.Application;

/**
 * Date: 2014/11/15
 * Time: 2:07 PM
 */
public abstract class BaseMApplication extends Application{

    protected MMemoryRealm realm;
    public void setupRealm() {
        //Add all users and roles to the realm.
        this.realm = new MMemoryRealm(this);
        this.realm.setName("CMRealm");
        getContext().setDefaultEnroler(this.realm.getEnroler());
        getContext().setDefaultVerifier(this.realm.getVerifier());
    }

    public MMemoryRealm getRealm() {
        return realm;
    }
}
