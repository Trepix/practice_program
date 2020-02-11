package alert_service;

import java.util.Date;

public interface PaymentsRepository {
    Payments getByUserId(UserId userId);
}
