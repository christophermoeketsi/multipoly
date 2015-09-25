package org.multipoly;

/**
 * Date: 2015/03/21
 * Time: 9:18 PM
 */
public class MExceptions {

    public static class NoDataFound extends CmException {
        public NoDataFound(String message) {
            super(message);
        }
    }

    public static abstract class CmException extends RuntimeException {
        public CmException(String message) {
            super(message);
        }
    }

}
