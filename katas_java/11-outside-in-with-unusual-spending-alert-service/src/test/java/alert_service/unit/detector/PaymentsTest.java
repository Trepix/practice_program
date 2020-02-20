package alert_service.unit.detector;

import alert_service.UnusualExpenses;
import alert_service.detection.Payment;
import alert_service.detection.Payments;
import alert_service.notify.UserId;
import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static alert_service.DateHelper.date;
import static java.util.Collections.emptyList;
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
    public void when_there_are_no_payments_for_current_month_should_return_unusual_expenses() {
        Payments noPayments = new Payments(userId, Arrays.asList(
                new Payment(100, "rent", date("02/03/1870")),
                new Payment(200, "rent", date("05/03/1870"))
        ));

        UnusualExpenses noUnusualExpenses = new UnusualExpenses(userId, emptyList());
        assertThat(noPayments.findUnusual(now), is(noUnusualExpenses));
    }
}
