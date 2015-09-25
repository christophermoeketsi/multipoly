package org.multipoly.restlet.app;

import com.rorotika.cm.restlet.modules.fancybrowser.ElementBrowserFancyTreeServerResource;
import com.rorotika.cm.restlet.modules.fancybrowser.NetworkSoftwareVersionFancyTreeServerResource;
import com.rorotika.cm.restlet.modules.fancybrowser.VirtualGroupBrowserFancyTreeServerResource;
import com.rorotika.cm.restlet.modules.fancybrowser.VirtualGroupFancyTreeServerResource;
import org.multipoly.restlet.modules.login.LoginFormServerResource;
import com.rorotika.cm.restlet.modules.networknode.NetworkNodeFancyTreeServerResource;
import com.rorotika.cm.restlet.modules.reports.ReportAllServerResource;
import com.rorotika.cm.restlet.modules.reports.ReportToExcelServerResource;
import com.rorotika.cm.restlet.modules.reports.ReportsServerResource;
import com.rorotika.cm.restlet.modules.tools.DashboardServerResource;
import com.rorotika.cm.restlet.modules.validation.ValidateInputServerResource;
import com.rorotika.cm.restlet.sync.ReloadEtlMetaDataInf;
import com.rorotika.cm.user.ROLE;
import org.reflections.Reflections;
import org.restlet.Restlet;
import org.restlet.engine.application.Encoder;
import org.restlet.resource.Directory;
import org.restlet.resource.ServerResource;
import org.restlet.routing.Filter;
import org.restlet.routing.Router;
import org.restlet.routing.Template;
import org.restlet.security.Role;
import org.restlet.service.EncoderService;
import org.umlg.runtime.adaptor.UMLG;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Set;

import static org.reflections.ReflectionUtils.getAllMethods;
import static org.reflections.ReflectionUtils.withAnnotation;

/**
 * Date: 2014/04/24
 * Time: 12:55 PM
 */
public class MRestletApplication extends BaseCmApplication {

    //    public final static String NETCM = "netcm";
    public static MRestletApplication INSTANCE = null;

    public MRestletApplication() {
        setName("CMRestletApplication Server application");
        setDescription("CMRestletApplication");
        setAuthor("The 80% Team");
        //Add in the roles
        for (ROLE role : ROLE.values()) {
            getRoles().add(new Role(this, role.name()));
        }
        setStatusService(new CmStatusService());
        INSTANCE = this;
    }

    @Override
    public Restlet createInboundRoot() {
        this.setupRealm();
        Router router = new Router(getContext());

        //work out the directory from which to server the files.
        //bin is for the distribution
        File current = new File(".");
        if (current.getAbsolutePath().contains("/bin")) {
            current = new File("./../");
        }
        Directory js = new Directory(getContext(), "file:///" + current.getAbsolutePath() + "/cm-web/src/main/resources/js");

        js.setListingAllowed(true);
        router.attach("/js/", js);

        Directory lib = new Directory(getContext(), "file:///" + current.getAbsolutePath() + "/cm-web/src/main/resources/lib");
        lib.setListingAllowed(true);
        router.attach("/lib/", lib);

        Directory css = new Directory(getContext(), "file:///" + current.getAbsolutePath() + "/cm-web/src/main/resources/css");
        css.setListingAllowed(true);
        router.attach("/css/", css);

        Directory img = new Directory(getContext(), "file:///" + current.getAbsolutePath() + "/cm-web/src/main/resources/img");
        img.setListingAllowed(true);
        router.attach("/img/", img);

        Directory audio = new Directory(getContext(), "file:///" + current.getAbsolutePath() + "/cm-web/src/main/resources/audio");
        audio.setListingAllowed(true);
        router.attach("/audio/", audio);

        Directory ftlModules = new Directory(getContext(), "file:///" + current.getAbsolutePath() + "/cm-web/src/main/resources/modules");
        ftlModules.setListingAllowed(true);
        router.attach("/modules/", ftlModules);

        Directory ftlLayout = new Directory(getContext(), "file:///" + current.getAbsolutePath() + "/cm-web/src/main/resources/layout");
        ftlLayout.setListingAllowed(true);
        router.attach("/layout/", ftlLayout);

        attachToRouter(router, LoginFormServerResource.class, "/login");

        CmCookieAuthenticator authenticator = CmCookieAuthenticator.get(getContext(), "CMRealm", /*must be 16 bytes*/"My Server KeyXXX".getBytes());
        authenticator.setRedirectQueryName("redirectUri");
        authenticator.setNext(router);

        attachAllResources(router);

        Reflections reflections = new Reflections("com.rorotika.cm.restlet");
        Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(Resource.class);

        for (Class aClass : typesAnnotatedWith) {
            Resource annotation = (Resource) aClass.getAnnotation(Resource.class);

            Set<Method> authMethods = getAllMethods(aClass, withAnnotation(Auth.class));

            FineGrainedAuth resourceAuth = new FineGrainedAuth(authMethods);
            resourceAuth.setNext(aClass);
            router.attach(annotation.value(), resourceAuth, Template.MODE_EQUALS);
        }


        Filter freemarkerFilter = new FreemarkerFilter(getContext());
        freemarkerFilter.setNext(authenticator);

        CmRestletFilter umlgRestletFilter = new CmRestletFilter(getContext());
        umlgRestletFilter.setNext(freemarkerFilter);

        Filter encoder = new Encoder(getContext(), false, true, new EncoderService(true));
        encoder.setNext(umlgRestletFilter);
        UMLG.get().rollback();
        return encoder;
    }

    private void attachAllResources(Router router) {
        attachToRouter(router, LoginFormServerResource.class, LoginFormServerResource.PATH);
        attachToRouter(router, GroupNetworkNsvServerResource.class, GroupNetworkNsvServerResource.PATH);
           }

    private void attachToRouter(Router router, Class<? extends ServerResource> clazz, String path) {
        Set<Method> authMethods = getAllMethods(clazz, withAnnotation(Auth.class));

        FineGrainedAuth resourceAuth = new FineGrainedAuth(authMethods);
        resourceAuth.setNext(clazz);
        router.attach(path, resourceAuth, Template.MODE_EQUALS);
    }

}
