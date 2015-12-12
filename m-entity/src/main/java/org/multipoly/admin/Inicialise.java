package org.multipoly.admin;

import org.multipoly.Board.Block;
import org.multipoly.Board.Board;
import org.multipoly.User.ROLE;
import org.multipoly.User.User;
import org.multipoly.User.UserGroup;
import org.umlg.runtime.adaptor.UMLG;
import org.umlg.runtime.collection.UmlgSet;
import org.umlg.runtime.collection.memory.UmlgMemorySet;

/**
 * Created by chris on 2015/10/02.
 */
public class Inicialise {


    public static void  createBaseData() {
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
            adminGroup = new UserGroup();
            adminGroup.setName("Players");
        }

        if (User.allInstances().size() == 0) {
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

            //This is to create the initial board things

            Board board = new Board();
            board.setName("Default Board");
            UmlgMemorySet<User> users = new UmlgMemorySet<>();
            users.add(admin);

            Block  block  = new Block();
            block.setColour("Brown");
            block.setPosition("S:1");
            block.setName("Soweto");
            board.setUser(users);
            board.addToBlock(block);
            adminGroup.addToUser(admin);

        }
        UMLG.get().commit();
    }


    public static void main() {
        /*createBaseData();*/
        createPlayersUserGroup();
    }


    private UmlgSet<Block> createGameBlocks(){
        UmlgMemorySet<Block> blocks  = new UmlgMemorySet<>();
        return blocks;
    }

    static private void createPlayersUserGroup  () {
        UserGroup adminGroup = new UserGroup();
        adminGroup.setName("Players");
        UMLG.get().commit();
    }

}
