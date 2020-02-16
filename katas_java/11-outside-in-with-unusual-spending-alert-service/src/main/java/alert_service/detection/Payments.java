package alert_service.detection;

import alert_service.UnusualExpenses;
import alert_service.notify.UserId;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.emptyList;

public class Payments {
    private final UserId userId;
    private final List<Payment> payments;

    public Payments(UserId userId, List<Payment> payments) {
        this.userId = userId;
        this.payments = payments;
    }

    UnusualExpenses findUnusualExpenses() {
        return new UnusualExpenses(userId, emptyList());
    }
}
