package org.multipoly.websocket;

import org.multipoly.NotifyInf;
import org.multipoly.User.User;
import org.multipoly.security.MMemoryRealm;
import org.umlg.runtime.adaptor.UMLG;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Date: 2014/04/05
 * Time: 8:20 PM
 */
public class NotificationWebsocketSessionManager extends BaseNotificationWebsocketSessionManager implements NotifyInf {

    private final ScheduledExecutorService scheduledExecutorService;
    //A cache of the users last 100 messages
    private Map<User, AutoDiscardingDeque<String>> userMessageMap = new HashMap<>();
    public static NotificationWebsocketSessionManager INSTANCE = null;
    private int numberOfMessagesToKeep;

    public NotificationWebsocketSessionManager(MMemoryRealm mMemoryRealm, int numberOfMessagesToKeep) {
        super(mMemoryRealm);
        this.numberOfMessagesToKeep = numberOfMessagesToKeep;
        scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(
                () -> {
                    try {
                        for (User user : getRealm().getUsers()) {
                            sendMessage("{\"x\":\"halo\"}", user.getUsername());
                        }
                        UMLG.get().rollback();
                    } catch (Exception e) {
//                        logger.error("Failed to send notification websocket ping", e);
                        logger.error("Failed to send notification websocket ping: " + e.getMessage());
                    }
                },
                1,
                30,
                TimeUnit.SECONDS
        );
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                NotificationWebsocketSessionManager.this.scheduledExecutorService.shutdownNow();
            }
        });
        INSTANCE = this;
    }

    @Override
    protected Map<User, AutoDiscardingDeque<String>> getUserMessageMap() {
        return userMessageMap;
    }

    @Override
    protected int getNumberOfMessageToSave() {
        return this.numberOfMessagesToKeep;
    }


}
