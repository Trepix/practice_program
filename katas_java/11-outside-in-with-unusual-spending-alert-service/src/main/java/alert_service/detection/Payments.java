package alert_service.detection;

import alert_service.UnusualExpense;
import alert_service.UnusualExpenses;
import alert_service.notify.UserId;
import lombok.RequiredArgsConstructor;

import javax.swing.text.html.CSS;
import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class Payments {
    private final UserId userId;
    private final List<Payment> payments;

    public Payments(UserId userId, List<Payment> payments) {
        this.userId = userId;
        this.payments = payments;
    }

    public UnusualExpenses findUnusual(LocalDate today) {
        List<Payment> currentMonthPayments = currentMonthPayments(today);
        if (currentMonthPayments.isEmpty()) return new UnusualExpenses(userId, emptyList());
        List<CategorySpending> currentMonthCategorySpending = sumUnusualExpenses(currentMonthPayments);
        return new UnusualExpenses(userId, currentMonthCategorySpending.stream().map(x -> new UnusualExpense(x.category, x.amount)).collect(toList()));
    }

    private List<Payment> currentMonthPayments(LocalDate today) {
        return filterByMonth(today.getMonth());
    }

    private List<Payment> filterByMonth(Month month) {
        return payments.stream()
                .filter(payment -> payment.wasMakeIn(month))
                .collect(toList());
    }

    private List<CategorySpending> sumUnusualExpenses(List<Payment> payments) {
        return payments.stream()
                .collect(groupingBy(Payment::category))
                .entrySet().stream()
                .map(entry -> {
                            List<Payment> categoryPayments = entry.getValue();
                            int amount = categoryPayments.stream().mapToInt(Payment::amount).sum();
                            String category = entry.getKey();
                            return new CategorySpending(category, amount);
                        }
                ).collect(toList());
    }

    @RequiredArgsConstructor
    private static class CategorySpending {
        private final String category;
        private final int amount;
    }

}
