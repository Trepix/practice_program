package alert_service;

import alert_service.notify.User;
import alert_service.notify.UserId;

public interface UserRepository {
    User getBy(UserId userId);
}
