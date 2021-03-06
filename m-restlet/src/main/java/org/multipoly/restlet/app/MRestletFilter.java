package org.multipoly.restlet.app;

import org.multipoly.admin.MUserThreadVar;
import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.routing.Filter;
import org.umlg.runtime.adaptor.UMLG;

/**
 * Date: 2014/10/08
 * Time: 10:33 AM
 */
public class MRestletFilter extends Filter {

    public MRestletFilter(Context context) {
        super(context);
    }

    @Override
    protected int beforeHandle(Request request, Response response) {
        return CONTINUE;
    }

    @Override
    protected void afterHandle(Request request, Response response) {
        MUserThreadVar.INSTANCE.remove();
        UMLG.get().rollback();
    }
}
