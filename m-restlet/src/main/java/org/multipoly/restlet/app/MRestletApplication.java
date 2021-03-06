package org.multipoly.restlet.app;

import org.multipoly.User.ROLE;
import org.multipoly.restlet.BaseMApplication;
import org.multipoly.restlet.modules.LandingPageServerResource;
import org.multipoly.restlet.modules.dashboard.DashboardServerResource;
import org.multipoly.restlet.modules.dashboard.DashboardViewServerResource;
import org.multipoly.restlet.modules.game.GameServerResource;
import org.multipoly.restlet.modules.login.LoginFormServerResource;
import org.multipoly.restlet.modules.login.LogonServerResource;
import org.multipoly.restlet.modules.register.RegisterFormServerResource;
import org.restlet.Restlet;
import org.restlet.engine.application.Encoder;
import org.restlet.resource.Directory;
import org.restlet.routing.Filter;
import org.restlet.routing.Router;
import org.restlet.security.Role;
import org.restlet.service.EncoderService;
import org.umlg.runtime.adaptor.UMLG;

import java.io.File;

/**
 * Date: 2014/04/24
 * Time: 12:55 PM
 */
public class MRestletApplication extends BaseMApplication {

    private Router router;
    public static MRestletApplication INSTANCE = null;

    public MRestletApplication() {
        setName("MRestletApplication Server application");
        setDescription("MRestletApplication");
        setAuthor("The Multipoly Guru");
        //Add in the roles
        for (ROLE role : ROLE.values()) {
            getRoles().add(new Role(this, role.name()));
        }
        setStatusService(new MStatusService());
        INSTANCE = this;
        router = new Router();
    }



    public Router getRouter (){
        return router;
    }

    @Override
    public Restlet createInboundRoot() {
        this.setupRealm();
        //work out the directory from which to server the files.
        //bin is for the distribution
        File current = new File(".");
        if (current.getAbsolutePath().contains("/bin")) {
            current = new File("./../");
        }
        Directory js = new Directory(getContext(), "file:///" + current.getAbsolutePath() + "/m-web/src/main/resources/js");
        js.setListingAllowed(true);
        router.attach("/js/", js);

        Directory lib = new Directory(getContext(), "file:///" + current.getAbsolutePath() + "/m-web/src/main/resources/lib");
        lib.setListingAllowed(true);
        router.attach("/lib/", lib);

        Directory img = new Directory(getContext(), "file:///" + current.getAbsolutePath() + "/m-web/src/main/resources/img");
        img.setListingAllowed(true);
        router.attach("/img/", img);

        Directory css = new Directory(getContext(), "file:///" + current.getAbsolutePath() + "/m-web/src/main/resources/css");
        css.setListingAllowed(true);
        router.attach("/css/", css);

        Directory pages = new Directory(getContext(), "file:///" + current.getAbsolutePath() + "/m-web/src/main/resources/pages");
        css.setListingAllowed(true);
        router.attach("/pages/", pages);

        Directory audio = new Directory(getContext(), "file:///" + current.getAbsolutePath() + "/m-web/src/main/resources/audio");
        audio.setListingAllowed(true);
        router.attach("/audio/", audio);

        Directory ftlModules = new Directory(getContext(), "file:///" + current.getAbsolutePath() + "/m-web/src/main/resources/modules");
        ftlModules.setListingAllowed(true);
        router.attach("/modules/", ftlModules);

        Directory ftlLayout = new Directory(getContext(), "file:///" + current.getAbsolutePath() + "/m-web/src/main/resources/layout");
        ftlLayout.setListingAllowed(true);
        router.attach("/layout/", ftlLayout);

        Directory directives = new Directory(getContext(), "file:///" + current.getAbsolutePath() + "/m-web/src/main/resources/directives");
        ftlLayout.setListingAllowed(true);
        router.attach("/directives/", directives);

        attachAllSafeResources(router);

        MCookieAuthenticator authenticator = MCookieAuthenticator.get(getContext(), "MRealm", /*must be 16 bytes*/"My Server KeyXXX".getBytes());
        authenticator.setRedirectQueryName("redirectUri");
        authenticator.setNext(router);


        //These are all routes that need authentication
        attachAuthenticatedResources(router);

        Filter freemarkerFilter = new FreemarkerFilter(getContext());
        freemarkerFilter.setNext(authenticator);

        MRestletFilter umlgRestletFilter = new MRestletFilter(getContext());
        umlgRestletFilter.setNext(freemarkerFilter);

        Filter encoder = new Encoder(getContext(), false, true, new EncoderService(true));
        encoder.setNext(umlgRestletFilter);
        UMLG.get().rollback();
        return encoder;
    }

    private void attachAllSafeResources(Router router) {
        router.attach(LoginFormServerResource.PATH, LoginFormServerResource.class);
        router.attach(RegisterFormServerResource.PATH, RegisterFormServerResource.class);
        router.attach(LogonServerResource.PATH, LogonServerResource.class);
    }

    private void attachAuthenticatedResources(Router router) {
        router.attach(DashboardServerResource.PATH, DashboardServerResource.class);
        router.attach(LandingPageServerResource.PATH,LandingPageServerResource.class);
        /*This is for the ng views*/
        router.attach(DashboardViewServerResource.PATH, DashboardViewServerResource.class);
        router.attach(GameServerResource.PATH, GameServerResource.class);
    }
}
