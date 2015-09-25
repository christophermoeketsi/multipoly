package org.multipoly.restlet.app;

import com.rorotika.cm.admin.CmUserThreadVar;
import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.routing.Filter;
import org.umlg.runtime.adaptor.UMLG;

/**
 * Date: 2014/10/08
 * Time: 10:33 AM
 */
public class CmRestletFilter extends Filter {

    public CmRestletFilter(Context context) {
        super(context);
    }

    @Override
    protected int beforeHandle(Request request, Response response) {
        return CONTINUE;
    }

    @Override
    protected void afterHandle(Request request, Response response) {
        CmUserThreadVar.INSTANCE.remove();
        UMLG.get().rollback();
    }
}
