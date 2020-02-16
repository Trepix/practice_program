package alert_service.detection;

import alert_service.notify.UserId;

public interface PaymentsRepository {
    Payments getBy(UserId userId, DateRange between);
}
