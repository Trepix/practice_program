package alert_service.unit.detector;

import alert_service.DateHelper;
import alert_service.UnusualExpense;
import alert_service.UnusualExpenses;
import alert_service.detection.*;
import alert_service.notify.UserId;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static alert_service.DateHelper.date;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class DetectorTest {

    private final LocalDate now = date("28/04/1945");
    private final UserId userId = UserId.of("1234");
    private final Payments noPayments = new Payments(userId, emptyList());

    private PaymentsRepository paymentsRepository;
    private Detector detector;

    @Before
    public void setUp() {
        this.paymentsRepository = mock(PaymentsRepository.class);
        Calendar calendar = mock(Calendar.class);
        when(calendar.today()).thenReturn(now);

        this.detector = new Detector(paymentsRepository, calendar);
    }

    @Test
    public void when_there_are_no_payments_should_return_empty_unusual_expenses() {
        when(paymentsRepository.getBy(eq(userId), any())).thenReturn(noPayments);

        UnusualExpenses unusualExpenses = detector.detect(userId);

        UnusualExpenses emptyUnusualExpenses = new UnusualExpenses(userId, emptyList());
        assertThat(unusualExpenses, is(emptyUnusualExpenses));
    }

    @Test
    public void when_retrieve_payments_should_ask_for_the_current_and_past_month_payments() {
        when(paymentsRepository.getBy(eq(userId), any())).thenReturn(noPayments);

        detector.detect(userId);

        DateRange dateRange = new DateRange(date("01/03/1945"), now);
        verify(paymentsRepository).getBy(userId, dateRange);
    }

    @Test
    public void when_payments_of_month_before_of_the_same_category_outstrip_by_50_percent_should_be_detected_as_unusual_expenses() {
        Payments payments = new Payments(userId, asList(
                new Payment(1000, "rent", DateHelper.date("02/03/1945")),
                new Payment(2000, "rent", DateHelper.date("02/04/1945"))
        ));
        when(paymentsRepository.getBy(eq(userId), any())).thenReturn(payments);

        UnusualExpenses unusualExpenses = detector.detect(userId);

        UnusualExpenses detected = new UnusualExpenses(userId, singletonList(new UnusualExpense("rent", 2000)));
        assertThat(unusualExpenses, is(detected));
    }
}
