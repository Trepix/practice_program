package alert_service.detection;

import alert_service.notify.UserId;

public interface PaymentsRepository {
    Payments getByUserId(UserId userId);
}
