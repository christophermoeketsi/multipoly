package org.multipoly;

/**
 * Date: 2015/03/21
 * Time: 9:18 PM
 */
public class MExceptions {

    public static class NoDataFound extends MException {
        public NoDataFound(String message) {
            super(message);
        }
    }

    public static abstract class MException extends RuntimeException {
        public MException(String message) {
            super(message);
        }
    }

}
