package alert_service.unit.detector;

import alert_service.UnusualExpense;
import alert_service.UnusualExpenses;
import alert_service.detection.Payment;
import alert_service.detection.Payments;
import alert_service.notify.UserId;
import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDate;

import static alert_service.DateHelper.date;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class PaymentsTest {

    private static final UserId userId = UserId.of("56789");
    private static final LocalDate now = date("22/04/1870");

    @Test
    public void when_there_are_no_payments_should_return_no_unusual_expenses() {
        Payments noPayments = new Payments(userId, emptyList());

        UnusualExpenses noUnusualExpenses = new UnusualExpenses(userId, emptyList());
        assertThat(noPayments.findUnusual(now), is(noUnusualExpenses));
    }

    @Test
    public void when_there_are_no_payments_for_current_month_should_return_no_unusual_expenses() {
        Payments noPayments = new Payments(userId, asList(
                new Payment(100, "rent", date("02/03/1870")),
                new Payment(200, "rent", date("05/03/1870"))
        ));

        UnusualExpenses noUnusualExpenses = new UnusualExpenses(userId, emptyList());
        assertThat(noPayments.findUnusual(now), is(noUnusualExpenses));
    }

    @Test
    public void should_return_current_month_payment_as_unusual_expense() {
        Payments payments = new Payments(userId, asList(
                new Payment(1, "rent", date("02/03/1870")),
                new Payment(2000, "rent", date("05/04/1870"))
        ));

        UnusualExpenses unusualExpenses = new UnusualExpenses(userId,
                singletonList(new UnusualExpense("rent", 2000)));
        assertThat(payments.findUnusual(now), is(unusualExpenses));
    }

    @Test
    public void should_return_the_sum_of_current_month_payment_as_unusual_expense() {
        Payments payments = new Payments(userId, asList(
                new Payment(1, "rent", date("02/03/1870")),
                new Payment(200, "rent", date("03/04/1870")),
                new Payment(400, "rent", date("05/04/1870"))
        ));

        UnusualExpenses unusualExpenses = new UnusualExpenses(userId,
                singletonList(new UnusualExpense("rent", 600)));
        assertThat(payments.findUnusual(now), is(unusualExpenses));
    }

    @Test
    public void should_return_the_sum_of_unusual_expenses_for_each_category() {
        Payments payments = new Payments(userId, asList(
                new Payment(1, "rent", date("02/03/1870")),
                new Payment(1, "restaurant", date("02/03/1870")),
                new Payment(300, "restaurant", date("01/04/1870")),
                new Payment(200, "rent", date("03/04/1870")),
                new Payment(400, "rent", date("05/04/1870"))
        ));

        UnusualExpenses unusualExpenses = new UnusualExpenses(userId,
                asList(new UnusualExpense("rent", 600),
                        new UnusualExpense("restaurant", 300)));
        assertThat(payments.findUnusual(now), is(unusualExpenses));
    }

    @Test
    public void should_not_detect_as_unusual_expense_if_not_outstrip_by_more_than_50_percent() {
        Payments payments = new Payments(userId, asList(
                new Payment(100, "rent", date("02/03/1870")),
                new Payment(150, "rent", date("03/04/1870"))
        ));

        UnusualExpenses unusualExpenses = new UnusualExpenses(userId, emptyList());
        assertThat(payments.findUnusual(now), is(unusualExpenses));
    }
}
