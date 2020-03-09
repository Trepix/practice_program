package alert_service;

import alert_service.detection.DateRange;
import alert_service.detection.Payments;
import alert_service.notify.UserId;

public interface PaymentsRepository {
    Payments getBy(UserId userId, DateRange between);
}
