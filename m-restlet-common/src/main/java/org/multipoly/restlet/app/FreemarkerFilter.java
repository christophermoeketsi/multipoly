package org.multipoly.restlet.app;

import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.routing.Filter;

/**
 * Date: 2014/05/07
 * Time: 11:06 AM
 */
public class FreemarkerFilter extends Filter {

    public FreemarkerFilter(Context context) {
        super(context);
    }

    @Override
    protected int beforeHandle(Request request, Response response) {
        if (MFreemarker.getConfiguration() == null) {
            new MFreemarker();
        }
        return CONTINUE;
    }
}
