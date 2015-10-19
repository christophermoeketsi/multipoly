package org.multipoly.security;

import org.multipoly.User.ROLE;
import org.multipoly.User.User;
import org.multipoly.User.UserGroup;
import org.multipoly.admin.MUserThreadVar;
import org.multipoly.restlet.BaseMApplication;
import org.restlet.Application;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.ClientInfo;
import org.restlet.engine.security.RoleMapping;
import org.restlet.security.Enroler;
import org.restlet.security.Realm;
import org.restlet.security.Role;
import org.restlet.security.Verifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.umlg.runtime.adaptor.UMLG;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Date: 2014/11/15
 * Time: 12:34 PM
 */
public class MMemoryRealm extends Realm {

    private static Logger logger = LoggerFactory.getLogger(MMemoryRealm.class);

    /**
     * Enroler based on the default security model.
     */
    private class DefaultEnroler implements Enroler {

        public void enrole(ClientInfo clientInfo) {
            User user = findUser(clientInfo.getUser().getIdentifier());

            if (user != null) {
                // Find all the inherited groups of this user
                UserGroup userGroup = findGroups(user);
                // Add roles specific to this user
                Set<Role> userRoles = findRoles(user);

                for (Role role : userRoles) {
                    clientInfo.getRoles().add(role);
                }
            }
        }
    }

    /**
     * Verifier based on the default security model. It looks up users in the
     * mapped organizations.
     */
    private class DefaultVerifier implements Verifier {


        @Override
        public int verify(Request request, Response response) {
            int result;

            if (request.getChallengeResponse() == null) {
                result = RESULT_MISSING;
            } else {
                String identifier = getIdentifier(request, response);
                char[] secret = getSecret(request, response);
                result = verify(identifier, secret);

                if (result == RESULT_VALID) {
                    User user = MMemoryRealm.this.findUser(identifier);
                    request.getClientInfo().setUser(fromUser(user));
                    List<Role> rolesAsList = new ArrayList<>();
                    rolesAsList.addAll(findRoles(user));
                    request.getClientInfo().setRoles(rolesAsList);
                    MUserThreadVar.INSTANCE.set(UMLG.get().getEntity(user.getId()));
                } else {
                    MUserThreadVar.INSTANCE.remove();
                }
            }

            return result;

        }

        protected String getIdentifier(Request request, Response response) {
            return request.getChallengeResponse().getIdentifier();
        }

        protected char[] getSecret(Request request, Response response) {
            return request.getChallengeResponse().getSecret();
        }

        public int verify(String identifier, char[] secret) {
            User user = MMemoryRealm.this.findUser(identifier);
            if (user != null) {
                return SecurityUtil.verify(user, secret) ? RESULT_VALID : RESULT_INVALID;
            } else {
                return RESULT_INVALID;
            }
        }
    }

    /**
     * The modifiable list of role mappings.
     */
    private final List<RoleMapping> roleMappings;

    /**
     * The modifiable list of root groups.
     */
    private final List<UserGroup> rootGroups;

    /**
     * The modifiable list of users.
     */
    private final List<User> users;

    private BaseMApplication application;


    /**
     * Constructor.
     *
     * @param application
     */
    public MMemoryRealm(BaseMApplication application) {
        setVerifier(new DefaultVerifier());
        setEnroler(new DefaultEnroler());
        this.rootGroups = new CopyOnWriteArrayList<>();
        this.roleMappings = new CopyOnWriteArrayList<>();
        this.users = new CopyOnWriteArrayList<>();
        this.application = application;
        addUsersAndRolesToRealm();
    }


    public void addUser(User user) {
        getUsers().add(user);
        for (ROLE role : ROLE.values()) {
            unmap(user, getRole(role.name()));
        }
        map(user, getRole(user.getRole().getQualifiedName()));
    }

    public void removeUser(User user) {
        for (ROLE role : ROLE.values()) { // remove all roles
            unmap(user, getRole(role.name()));
        }
        getUsers().remove(user);
    }

    public void resetUser(User user) {
        removeUser(user);
        addUser(user);
    }

    private void addModule(UserGroup userGroup, ROLE role1) {
        Role role = new Role(this.application, role1.name());
        if (!this.application.getRoles().contains(role)) {
            this.application.getRoles().add(role);
        }
        for (User user : userGroup.getUser()) {
            map(user, role);
        }
    }

    private void removeModule(UserGroup userGroup, ROLE role1) {
        Role role = new Role(this.application, role1.name());
        if (this.application.getRoles().contains(role)) {
            this.application.getRoles().remove(role);
        }
        for (User user : userGroup.getUser()) {
            unmap(user, role);
        }
    }

    public void addUsersAndRolesToRealm() {
        Set<? extends User> users = User.allInstances();
        for (User user : users) {
            //Preload the uid, else the equals method calls it and starts a transaction
            user.getUid();
            user.getUsername();
            user.getPassword();
            user.getName();
            user.getSurname();
            user.getEmail();
            getUsers().add(user);
            map(user, getRole(user.getRole().name()));
        }
    }

    public Role getRole(String name) {
        for (Role role : this.application.getRoles()) {
            if (role.getName().equals(name)) {
                return role;
            }
        }

        return null;
    }


    private org.restlet.security.User fromUser(User user) {
        org.restlet.security.User restletUser = new org.restlet.security.User(
                user.getUsername(),
                "restlet user password not used",
                user.getName(),
                user.getSurname(),
                user.getEmail());
        return restletUser;
    }


    /**
     * Finds the set of groups where a given user is a member.
     *
     * @param user The member user.
     * @return The set of groups.
     */
    public UserGroup findGroups(User user) {
        return user.getUsergroup();
    }

    /**
     * Finds the roles mapped to given user groups.
     *
     * @param userGroups The user groups.
     * @return The roles found.
     */
    public Set<Role> findRoles(Set<UserGroup> userGroups) {
        Set<Role> result = new HashSet<>();
        Object source;

        for (RoleMapping mapping : getRoleMappings()) {
            source = mapping.getSource();

            if ((userGroups != null) && userGroups.contains(source)) {
                result.add(mapping.getTarget());
            }
        }

        return result;
    }

    /**
     * Finds the roles mapped to a given user.
     *
     * @param user The user.
     * @return The roles found.
     */
    public Set<Role> findRoles(User user) {
        Set<Role> result = new HashSet<>();
        Object source;

        for (RoleMapping mapping : getRoleMappings()) {
            source = mapping.getSource();

            if ((user != null) && user.equals(source)) {
                result.add(mapping.getTarget());
            }
        }

        return result;
    }

    /**
     * Finds a user in the organization based on its identifier.
     *
     * @param userIdentifier The identifier to match.
     * @return The matched user or null.
     */
    public User findUser(String userIdentifier) {
        User result = null;
        User user;

        for (int i = 0; (result == null) && (i < getUsers().size()); i++) {
            user = getUsers().get(i);

            if (user.getUsername().equals(userIdentifier)) {
                result = user;
            }
        }

        return result;
    }

    /**
     * Returns the modifiable list of role mappings.
     *
     * @return The modifiable list of role mappings.
     */
    private List<RoleMapping> getRoleMappings() {
        return roleMappings;
    }

    /**
     * Returns the modifiable list of root groups.
     *
     * @return The modifiable list of root groups.
     */
    public List<UserGroup> getRootGroups() {
        return rootGroups;
    }

    /**
     * Returns the modifiable list of users.
     *
     * @return The modifiable list of users.
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Maps a group defined in a component to a role defined in the application.
     *
     * @param group The source group.
     * @param role  The target role.
     */
    public void map(UserGroup group, Role role) {
        getRoleMappings().add(new RoleMapping(group, role));
    }

    /**
     * Maps a user defined in a component to a role defined in the application.
     *
     * @param user        The source user.
     * @param application The parent application. Can't be null.
     * @param roleName    The target role name.
     * @throws IllegalArgumentException If application is null.
     */
    public void map(User user, Application application, String roleName) {
        map(user, Role.get(application, roleName, null));
    }

    /**
     * Maps a user defined in a component to a role defined in the application.
     *
     * @param user The source user.
     * @param role The target role.
     */
    public void map(User user, Role role) {
        getRoleMappings().add(new RoleMapping(user, role));
    }

    /**
     * Unmaps a group defined in a component from a role defined in the
     * application.
     *
     * @param group       The source group.
     * @param application The parent application. Can't be null.
     * @param roleName    The target role name.
     * @throws IllegalArgumentException If application is null.
     */
    public void unmap(UserGroup group, Application application, String roleName) {
        unmap(group, Role.get(application, roleName, null));
    }

    /**
     * Unmaps a group defined in a component from a role defined in the
     * application.
     *
     * @param userGroup The source group.
     * @param role      The target role.
     */
    public void unmap(UserGroup userGroup, Role role) {
        unmap((Object) userGroup, role);
    }

    /**
     * Unmaps an element (user, group or organization) defined in a component
     * from a role defined in the application.
     *
     * @param source The source group.
     * @param role   The target role.
     */
    private void unmap(Object source, Role role) {
        for (int i = 0; i < getRoleMappings().size(); i++) {
            RoleMapping mapping = getRoleMappings().get(i);

            if (mapping.getSource().equals(source)
                    && mapping.getTarget().equals(role)) {
                getRoleMappings().remove(i);
            }
        }
    }

    /**
     * Unmaps a user defined in a component from a role defined in the
     * application.
     *
     * @param user        The source user.
     * @param application The parent application. Can't be null.
     * @param roleName    The target role name.
     * @throws IllegalArgumentException If application is null.
     */
    public void unmap(User user, Application application, String roleName) {
        unmap(user, Role.get(application, roleName, null));
    }

    /**
     * Unmaps a user defined in a component from a role defined in the
     * application.
     *
     * @param user The source user.
     * @param role The target role.
     */
    public void unmap(User user, Role role) {
        unmap((Object) user, role);
    }

}
