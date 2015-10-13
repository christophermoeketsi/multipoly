package org.multipoly.admin;

import org.multipoly.User.ROLE;
import org.multipoly.User.User;
import org.multipoly.User.UserGroup;
import org.umlg.runtime.adaptor.UMLG;
import org.umlg.runtime.collection.UmlgSet;

/**
 * Created by chris on 2015/10/02.
 */
public class Inicialise {


    public static void createBaseData() {
        captureUserGroupsAndUsers();
    }

    private static void captureUserGroupsAndUsers() {
        // check if the admin user exists.
        UmlgSet<? extends UserGroup> userGroups = UserGroup.allInstances(new org.umlg.runtime.collection.Filter<UserGroup>() {
            @Override
            public boolean filter(UserGroup userGroup) {
                return userGroup.getName().equals("Admin");
            }
        });
        UserGroup adminGroup = null;
        if (userGroups.isEmpty()) {
            adminGroup = new UserGroup();
            adminGroup.setName("Admin");
        }

        if (User.allInstances().select(u -> u.getName() == "admin").size() == 0) {
            //admin
            User admin = new User();
            admin.setName("admin");
            admin.setSurname("admin");
            admin.setUsername("admin");
            admin.setEmail("admin@multipoly.com");
            admin.setPassword("admin");
            admin.setLastLoggedIn(1);
            admin.setLastLoggedOut(1);
            admin.addToRole(ROLE.ADMIN);
            adminGroup.addToUser(admin);

        }

        UMLG.get().commit();
    }


    public static void main() {
        createBaseData();
    }

}
