package org.multipoly;

import org.multipoly.Notification.Notification;
import org.multipoly.User.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * Date: 12/10/12
 * Time: 3:33 PM
 */
public class MMassanger implements Callable<MMassanger> {

    private static NotifyInf notifyInf;
    private static MExecutorService EXECUTOR_SERVICE_GET = MExecutorService.createCompletionService("notification-subscription", 1);
    private Notification notification;
    private User userForm;
    private User userTo;

    private Logger logger = LoggerFactory.getLogger(getClass());

    private MMassanger(Notification notification, User userForm, User userTo) {
        this.notification = notification;
        this.userForm = userForm;
        this.userTo = userTo;
    }

    public static Future<MMassanger> notify(Notification notification, User userFrom, User userTo) {
        if (notifyInf == null) {
            try {
                Class MessageWebsocketSessionManager = Class.forName("com.rorotika.cm.websocket.NotificationWebsocketSessionManager");
                Field f = MessageWebsocketSessionManager.getDeclaredField("INSTANCE");
                notifyInf = (NotifyInf) f.get(null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        //this logic is to cater for tests that restart the notification service after every test but some notifications remain to be submitted
        if (EXECUTOR_SERVICE_GET.isTerminated() && Thread.currentThread().getContextClassLoader().getResource("empty.test.marker") == null) {
            throw new IllegalStateException("BUG: the service should never be down!");
        }
        if (!EXECUTOR_SERVICE_GET.isTerminated()) {
            return EXECUTOR_SERVICE_GET.submit(new MMassanger(notification,userFrom,userTo));
        } else {
            return null;
        }
    }

    private void internalNotify(User userForm, User userTo) {
        logger.debug("notification {}", notification.getName(), this.notification.getName());
        Date currentNotificationDate = new Date();
        Date now = new Date();
        if (now.after(currentNotificationDate) || now.equals(currentNotificationDate)) {
            switch (notification.getNotificationtype()) {
                case PERSONAL:
                    if (notifyInf != null) {
                        try {
                            NotificationMessage notificationMessage = new NotificationMessage(new Date(), this.notification,userForm,userTo);
                            notifyInf.sendMessage(notificationMessage.toJson(),userForm.getUsername());
                        } catch (Exception e) {
                            logger.error("Exception sending out notification", e);
                        }

                    }
                    break;
                case ALL:
                    if (notifyInf != null) {
                        try {
                            NotificationMessage notificationMessage = new NotificationMessage(new Date(), this.notification,userTo,userForm);
                            notifyInf.sendMessage(notificationMessage.toJson(),userForm.getUsername());
                        } catch (Exception e) {
                            logger.error("Exception sending out notification", e);
                        }

                    }
                    break;
                case BOARD:
                    if (notifyInf != null) {
                        try {
                            NotificationMessage notificationMessage = new NotificationMessage(new Date(), this.notification, userForm, userForm.getBoard());
                            notifyInf.sendMessage(notificationMessage.toJson(),userForm.getUsername());
                        } catch (Exception e) {
                            logger.error("Exception sending out notification", e);
                        }

                    }
                default:
                    throw new RuntimeException("Unhandled Notification Channel " + notification.getNotificationtype().name());
            }

        }
    }



    private Date calculateNewNotificationDate(Date nextNotificationDate, Integer minutesToAdd) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.MINUTE, minutesToAdd);
        return instance.getTime();
    }

    @Override
    public MMassanger call() throws Exception {
        try {
            internalNotify( this.userForm,this.userTo);
        } catch (RuntimeException e) {
            logger.error("MMassanger failure", e);
        } finally {
        }
        return this;
    }

    public static void restart() {
        shutdown();
        start();
    }

    public static void shutdown() {
        EXECUTOR_SERVICE_GET.shutdownNow();
    }

    public static void start() {
        EXECUTOR_SERVICE_GET = MExecutorService.createCompletionService("notification-subscription", 1);
    }

}
