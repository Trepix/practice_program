package alert_service.unit.detector;

import alert_service.DateHelper;
import alert_service.UnusualExpenses;
import alert_service.detection.*;
import alert_service.notify.UserId;
import io.cucumber.java.ca.Cal;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Date;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DetectorTest {

    private final UserId userId = UserId.of("1234");
    private final LocalDate date = DateHelper.date("28/04/1945");
    private PaymentsRepository paymentsRepository;
    private Calendar calendar;
    private Detector detector;

    @Before
    public void setUp() {
       this.paymentsRepository = mock(PaymentsRepository.class);
       this.calendar = mock(Calendar.class);
       this.detector = new Detector(paymentsRepository);
    }

    @Test
    public void when_there_are_no_payments_should_return_empty_unusual_expenses() {
        Payments noPayments = new Payments(userId, emptyList());
        when(calendar.today()).thenReturn(date);
        when(paymentsRepository.getByUserId(userId)).thenReturn(noPayments);

        UnusualExpenses unusualExpenses = detector.detect(userId);

        UnusualExpenses emptyUnusualExpenses = new UnusualExpenses(userId, emptyList());
        assertThat(unusualExpenses, is(emptyUnusualExpenses));
    }

    @Test
    public void when_there_are_payments_but_not_belong_to_last_two_months_should_return_empty_unusual_expenses() {
        Payments tooOldPayments = new Payments(userId, asList(
                new Payment(1000, "rent", "01/03/1940"),
                new Payment(500, "rent", "02/03/1940"),
                new Payment(500, "rent", "02/04/1940")
        ));
        when(calendar.today()).thenReturn(date);
        when(paymentsRepository.getByUserId(userId)).thenReturn(tooOldPayments);

        UnusualExpenses unusualExpenses = detector.detect(userId);

        UnusualExpenses emptyUnusualExpenses = new UnusualExpenses(userId, emptyList());
        assertThat(unusualExpenses, is(emptyUnusualExpenses));
    }
}
