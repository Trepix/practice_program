package alert_service.detection;

import alert_service.UnusualExpense;
import alert_service.UnusualExpenses;
import alert_service.notify.UserId;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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
        List<CategorySpending> currentMonthSpending = sumCategoryExpensesOfCurrentMonth(today);
        List<CategorySpending> monthBeforeSpending = sumCategoryExpensesOfMonthBefore(today);
        List<UnusualExpense> unusualExpensesDetected = detectUnusual(currentMonthSpending, monthBeforeSpending);
        return new UnusualExpenses(userId, unusualExpensesDetected);
    }

    private List<UnusualExpense> detectUnusual(List<CategorySpending> currentMonth, List<CategorySpending> monthBefore) {
        List<UnusualExpense> unusualExpensesDetected = new LinkedList<>();
        for (CategorySpending categoryExpenseMonthBefore : monthBefore) {
            Optional<CategorySpending> currentMonthCategoryExpense = currentMonth.stream().filter(x -> x.category.equals(categoryExpenseMonthBefore.category)).findFirst();
            currentMonthCategoryExpense.filter(x -> categoryExpenseMonthBefore.amount * 1.5 < x.amount).map(x -> new UnusualExpense(x.category, x.amount)).ifPresent(unusualExpensesDetected::add);
        }
        return unusualExpensesDetected;
    }

    private List<CategorySpending> sumCategoryExpensesOfCurrentMonth(LocalDate today) {
        return sumCategoryExpenses(filterByMonth(today.getMonth()));
    }

    private List<CategorySpending> sumCategoryExpensesOfMonthBefore(LocalDate today) {
        return sumCategoryExpenses(filterByMonth(today.getMonth().minus(1)));
    }

    private List<Payment> filterByMonth(Month month) {
        return payments.stream()
                .filter(payment -> payment.wasMakeIn(month))
                .collect(toList());
    }

    private List<CategorySpending> sumCategoryExpenses(List<Payment> payments) {
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
