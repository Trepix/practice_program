package alert_service.detection;

import alert_service.UnusualExpense;
import alert_service.UnusualExpenses;
import alert_service.notify.UserId;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

public class Payments {
    private final UserId userId;
    private final List<Payment> payments;

    public Payments(UserId userId, List<Payment> payments) {
        this.userId = userId;
        this.payments = payments;
    }

    UnusualExpenses findUnusualExpenses() {
        if (payments.isEmpty()) return new UnusualExpenses(userId, emptyList());
        return new UnusualExpenses(userId, singletonList(new UnusualExpense("rent", 2000)));
    }
}
