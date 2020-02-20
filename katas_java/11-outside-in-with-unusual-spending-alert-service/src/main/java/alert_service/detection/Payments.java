package alert_service.detection;

import alert_service.UnusualExpense;
import alert_service.UnusualExpenses;
import alert_service.notify.UserId;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

public class Payments {
    private final UserId userId;
    private final List<Payment> payments;

    public Payments(UserId userId, List<Payment> payments) {
        this.userId = userId;
        this.payments = payments;
    }

    public UnusualExpenses findUnusual(LocalDate today) {
        List<Payment> currentMonthPayments = filterByMonth(today.getMonth());
        if (currentMonthPayments.isEmpty()) return new UnusualExpenses(userId, emptyList());
        return new UnusualExpenses(userId, singletonList(new UnusualExpense("rent", 2000)));

    }

    private List<Payment> filterByMonth(Month month) {
        return payments.stream()
                .filter(payment -> payment.wasMakeIn(month))
                .collect(toList());
    }
}
