package alert_service.detection;

import alert_service.notify.UserId;

import java.util.List;

public class Payments {
    private final UserId userId;
    private final List<Payment> payments;

    public Payments(UserId userId, List<Payment> payments) {
        this.userId = userId;
        this.payments = payments;
    }
}
