package alert_service;

import alert_service.notify.Notification;

public interface NotificationSender {
    void send(Notification notification);
}
