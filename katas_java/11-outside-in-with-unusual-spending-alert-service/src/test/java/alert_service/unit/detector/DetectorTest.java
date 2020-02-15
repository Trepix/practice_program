package alert_service.unit.detector;

import alert_service.UnusualExpenses;
import alert_service.detection.Detector;
import alert_service.detection.Payments;
import alert_service.detection.PaymentsRepository;
import alert_service.notify.UserId;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DetectorTest {

    private final UserId userId = UserId.of("1234");
    private PaymentsRepository paymentsRepository;
    private Detector detector;

    @Before
    public void setUp() {
       this.paymentsRepository = mock(PaymentsRepository.class);
       this.detector = new Detector(paymentsRepository);
    }

    @Test
    public void when_there_are_no_payments_should_return_empty_unusual_expenses() {
        UnusualExpenses emptyUnusualExpenses = new UnusualExpenses(userId, emptyList());
        Payments noPayments = new Payments(userId, emptyList());
        when(paymentsRepository.getByUserId(userId)).thenReturn(noPayments);

        UnusualExpenses unusualExpenses = detector.detect(userId);

        assertThat(unusualExpenses, is(emptyUnusualExpenses));
    }
}
