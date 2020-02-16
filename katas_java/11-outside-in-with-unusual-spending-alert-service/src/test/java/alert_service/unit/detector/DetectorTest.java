package alert_service.unit.detector;

import alert_service.DateHelper;
import alert_service.UnusualExpenses;
import alert_service.detection.*;
import alert_service.notify.UserId;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import java.time.LocalDate;

import static alert_service.DateHelper.date;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class DetectorTest {

    private final UserId userId = UserId.of("1234");
    private final Payments noPayments = new Payments(userId, emptyList());

    private PaymentsRepository paymentsRepository;
    private Calendar calendar;
    private Detector detector;

    @Before
    public void setUp() {
       this.paymentsRepository = mock(PaymentsRepository.class);
       this.calendar = mock(Calendar.class);
       this.detector = new Detector(paymentsRepository, calendar);
    }

    @Test
    public void when_there_are_no_payments_should_return_empty_unusual_expenses() {
        LocalDate date = date("28/04/1945");
        when(calendar.today()).thenReturn(date);
        when(paymentsRepository.getBy(eq(userId), any())).thenReturn(noPayments);

        UnusualExpenses unusualExpenses = detector.detect(userId);

        UnusualExpenses emptyUnusualExpenses = new UnusualExpenses(userId, emptyList());
        assertThat(unusualExpenses, is(emptyUnusualExpenses));
    }

    @Test
    public void when_retrieve_payments_should_ask_for_the_current_and_past_month_payments() {
        LocalDate now = date("28/04/1945");
        when(calendar.today()).thenReturn(now);
        when(paymentsRepository.getBy(eq(userId), any())).thenReturn(noPayments);

        detector.detect(userId);

        DateRange dateRange = new DateRange(date("01/03/1945"), now);
        verify(paymentsRepository).getBy(userId, dateRange);
    }
}
