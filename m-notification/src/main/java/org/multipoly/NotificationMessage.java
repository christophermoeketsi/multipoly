package org.multipoly;

import org.multipoly.Board.Board;
import org.multipoly.Notification.Notification;
import org.multipoly.User.User;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date: 2014/11/16
 * Time: 9:52 PM
 */
public class NotificationMessage {

    private Date date;
    private Notification notification;
    private User toUser;
    private User fromUser;
    private Board board;

    public NotificationMessage(Date date, Notification notification, User userFrom,User userTo) {
        this.date = date;
        this.notification = notification;
        this.fromUser = userFrom;
        this.toUser = userTo;
    }

    public NotificationMessage(Date date, Notification notification, User userFrom,Board board) {
        this.date = date;
        this.notification = notification;
        this.fromUser = userFrom;
        this.board = board;
    }

    public String toJson() {
        StringBuilder json = new StringBuilder();
        json.append("{\"type\":\"").append(notification.getNotificationtype().name()).append("\",\"date\":\"");
        json.append(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date));
        json.append("\",\"from\":\"");
        json.append(this.fromUser.getName());
        json.append("\",\"to\":\"");
        json.append(this.toUser == null ? this.board.getName():this.toUser.getUsername());
        json.append("\",\"message\":\"");
        if (this.notification.getMessage()!= null) {
            json.append(this.notification.getMessage());
            json.append(" ");
        }
        json.append("\"}");
        return json.toString();
    }
}
