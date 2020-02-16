package alert_service.unit.detector;

import alert_service.DateHelper;
import alert_service.UnusualExpenses;
import alert_service.detection.Payments;
import alert_service.notify.UserId;
import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDate;

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
        assertThat(noPayments.findUnusualExpenses(now), is(noUnusualExpenses));
    }

    @Test
    @Ignore
    public void when_there_are_no_payments_for_current_month_should_return_unusual_expenses() {
        Payments noPayments = new Payments(userId, emptyList());

        UnusualExpenses noUnusualExpenses = new UnusualExpenses(userId, emptyList());
        assertThat(noPayments.findUnusualExpenses(null), is(noUnusualExpenses));
    }
}
