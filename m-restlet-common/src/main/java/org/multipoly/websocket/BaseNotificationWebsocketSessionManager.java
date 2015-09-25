package org.multipoly.websocket;

import org.eclipse.jetty.websocket.api.Session;
import org.multipoly.NotifyInf;
import org.multipoly.User.User;
import org.multipoly.security.MMemoryRealm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.umlg.runtime.util.Pair;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Date: 2014/11/18
 * Time: 9:20 PM
 */
public abstract class BaseNotificationWebsocketSessionManager implements NotifyInf {

    protected final Map<User, List<Pair<String, Session>>> sessionMap = new HashMap<>();
    protected Logger logger = LoggerFactory.getLogger(getClass());
    protected MMemoryRealm mMemoryRealm;

    public BaseNotificationWebsocketSessionManager(MMemoryRealm mMemoryRealm) {
        this.mMemoryRealm = mMemoryRealm;
    }

    public void addSession(Session session, String remoteAddress) {
        User user = WebsocketAuthentication.authenticate(session, getRealm());
        if (user != null) {
            List<Pair<String, Session>> userSessions = this.sessionMap.get(user);
            if (userSessions == null) {
                userSessions = new ArrayList<>();
                this.sessionMap.put(user, userSessions);
            }
            userSessions.add(Pair.of(remoteAddress, session));
            sendMessage(session, user.getUsername());
            logger.info(String.format("addSession, number of websocket hosts are %d", new Integer[]{this.sessionMap.size()}));
        } else {
            logger.warn(String.format("addSession, credentials failed"));
        }
    }

    public void removeSession(Session session) {
        User user = WebsocketAuthentication.authenticate(session, getRealm());
        if (user != null) {
            List<Pair<String, Session>> userSessions = this.sessionMap.get(user);
            List<Pair<String, Session>> toRemove = new ArrayList<>();
            for (Pair<String, Session> userSession : userSessions) {
                if (userSession.getFirst().equals(session.getRemoteAddress().toString())) {
                    toRemove.add(userSession);
                }
            }
            userSessions.removeAll(toRemove);
            if (userSessions.isEmpty()) {
                this.sessionMap.remove(user);
            }
        } else {
            Map<User, List<Pair<String, Session>>> toRemove = new HashMap<>();
            //if up, go through all session and remove any session with the same remote address
            for (Map.Entry<User, List<Pair<String, Session>>> entry : this.sessionMap.entrySet()) {
                List<Pair<String, Session>> userSessions = entry.getValue();
                for (Pair<String, Session> userSession : userSessions) {
                    if (userSession.getFirst().equals(session.getRemoteAddress().toString())) {
                        toRemove.put(entry.getKey(), entry.getValue());
                    }
                }
            }
            for (User userToRemove : toRemove.keySet()) {
                this.sessionMap.remove(userToRemove);
                this.getUserMessageMap().remove(userToRemove);
            }
        }
        logger.info(String.format("removeSession, number of websocket hosts are %d", new Integer[]{this.sessionMap.size()}));
    }

    protected abstract Map<User, AutoDiscardingDeque<String>> getUserMessageMap();

    protected MMemoryRealm getRealm() {
        return this.mMemoryRealm;
    }

    protected abstract int getNumberOfMessageToSave();

    private void sendMessage(Session session, String username) {
        User user = getRealm().findUser(username);
        if (user != null) {
            if (this.getUserMessageMap().containsKey(user)) {
                AutoDiscardingDeque<String> messages;
                if (!this.getUserMessageMap().containsKey(user)) {
                    messages = new AutoDiscardingDeque<>(getNumberOfMessageToSave());
                    this.getUserMessageMap().put(user, messages);
                    sendPing(session, username);
                } else {
                    messages = this.getUserMessageMap().get(user);
                    Iterator<String> iterator = messages.descendingIterator();
                    while (iterator.hasNext()) {
                        String message = iterator.next();
                        try {
                            session.getRemote().sendString(message);
                        } catch (IOException e) {
                            removeSession(session);
                            throw new RuntimeException(e);
                        }
                    }
                }
            } else {
                sendPing(session, username);
            }
        }
    }

    @Override
    public void sendMessage(String message, String username) {
        User user = getRealm().findUser(username);
        if (user != null) {
            List<Pair<String, Session>> remoteAddressSessionPairs = this.sessionMap.get(user);
            if (remoteAddressSessionPairs != null) {
                AutoDiscardingDeque<String> messages;
                if (!this.getUserMessageMap().containsKey(user)) {
                    messages = new AutoDiscardingDeque<>(100);
                    this.getUserMessageMap().put(user, messages);
                } else {
                    messages = this.getUserMessageMap().get(user);
                }
                if(!messages.contains("{\"x\":\"halo\"}")){
                    messages.offerFirst(message);
                }
                for (Pair<String, Session> remoteAddressSessionPair : remoteAddressSessionPairs) {
                    Session session = remoteAddressSessionPair.getSecond();
                    try {
                        //logger.debug(String.format("sendMessage, message %s username %s \nremoteAddress %s", message, username, remoteAddressSessionPair.getFirst()));
                        session.getRemote().sendString(message);
                    } catch (IOException e) {
                        removeSession(session);
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public void sendPing(Session session, String username) {
        User user = getRealm().findUser(username);
        if (user != null) {
            try {
                logger.debug(String.format("sendPing, username %s", username));
                String data = "You There?";
                ByteBuffer payload = ByteBuffer.wrap(data.getBytes());
                session.getRemote().sendPing(payload);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static class AutoDiscardingDeque<E> extends LinkedBlockingDeque<E> {

        public AutoDiscardingDeque(int capacity) {
            super(capacity);
        }

        @Override
        public synchronized boolean offerFirst(E e) {
            if (remainingCapacity() == 0) {
                removeLast();
            }
            super.offerFirst(e);
            return true;
        }

    }
}
