package alert_service.detection;

import alert_service.UnusualExpense;
import alert_service.UnusualExpenses;
import alert_service.notify.UserId;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class Payments {
    private final UserId userId;
    private final List<Payment> payments;

    public Payments(UserId userId, List<Payment> payments) {
        this.userId = userId;
        this.payments = payments;
    }

    public UnusualExpenses findUnusualCategorySpending(LocalDate today) {
        List<CategorySpending> currentMonthSpending = sumCategoryExpensesOfCurrentMonth(today);
        List<CategorySpending> monthBeforeSpending = sumCategoryExpensesOfMonthBefore(today);
        List<UnusualExpense> unusualExpensesDetected = detectUnusual(currentMonthSpending, monthBeforeSpending);
        return new UnusualExpenses(userId, unusualExpensesDetected);
    }

    private List<UnusualExpense> detectUnusual(List<CategorySpending> currentMonth, List<CategorySpending> monthBefore) {
        return Stream.concat(
                currentMonth.stream().map(BimonthlyCategorySpending::ofCurrentMonth),
                monthBefore.stream().map(BimonthlyCategorySpending::ofMonthBefore)
        )
                .collect(groupingBy(BimonthlyCategorySpending::category))
                .values().stream()
                .map(list -> list.stream().reduce(BimonthlyCategorySpending::merge))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(BimonthlyCategorySpending::currentMonthSpendingIsUnusual)
                .map(BimonthlyCategorySpending::getUnusualExpense)
                .collect(toList());
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
    private static class BimonthlyCategorySpending {
        private static final double UNUSUAL_SPENDING_RATIO = 1.5;
        private final CategorySpending currentMonth;
        private final CategorySpending monthBefore;

        private static BimonthlyCategorySpending ofCurrentMonth(CategorySpending categorySpending) {
            return new BimonthlyCategorySpending(categorySpending, null);
        }

        private static BimonthlyCategorySpending ofMonthBefore(CategorySpending categorySpending) {
            return new BimonthlyCategorySpending(null, categorySpending);
        }

        private static BimonthlyCategorySpending merge(BimonthlyCategorySpending first, BimonthlyCategorySpending second) {
            CategorySpending current = Optional.ofNullable(first.currentMonth).orElse(second.currentMonth);
            CategorySpending monthBefore = Optional.ofNullable(first.monthBefore).orElse(second.monthBefore);
            return new BimonthlyCategorySpending(current, monthBefore);
        }

        private String category() {
            if (currentMonth != null) return currentMonth.category;
            else return monthBefore.category;
        }

        private boolean currentMonthSpendingIsUnusual() {
            if (monthBefore == null) return false;
            if (currentMonth == null) return false;
            return currentMonth.amount > monthBefore.amount * UNUSUAL_SPENDING_RATIO;
        }

        private UnusualExpense getUnusualExpense() {
            return new UnusualExpense(currentMonth.category, currentMonth.amount);
        }
    }

    @RequiredArgsConstructor
    private static class CategorySpending {
        private final String category;
        private final int amount;
    }

}
