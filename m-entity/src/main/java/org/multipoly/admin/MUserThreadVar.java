package org.multipoly.admin;

import org.multipoly.User.User;

import java.util.Objects;

/**
 * Date: 2014/12/02
 * Time: 1:58 PM
 */
public class MUserThreadVar {

    public static MUserThreadVar INSTANCE = new MUserThreadVar();

    private static ThreadLocal<User> userTransactionVar = new ThreadLocal<User>() {
    };

    private MUserThreadVar() {
    }

    public void set(User user) {
        Objects.requireNonNull(user, "User may not be null when setting the MUserThreadVar!");
        userTransactionVar.set(user);
    }

    public User get() {
        return userTransactionVar.get();
    }

    public void remove() {
        userTransactionVar.remove();
    }

}
