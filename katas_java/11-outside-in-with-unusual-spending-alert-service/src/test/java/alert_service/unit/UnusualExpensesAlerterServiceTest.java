package alert_service.unit;

import alert_service.UnusualExpense;
import alert_service.UnusualExpenses;
import alert_service.UnusualExpensesAlerterService;
import alert_service.detection.Detector;
import alert_service.notify.Notifier;
import alert_service.notify.UserId;
import org.junit.Before;
import org.junit.Test;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.mockito.Mockito.*;

public class UnusualExpensesAlerterServiceTest {

    private final UserId userId = UserId.of("1234");

    private Detector detector;
    private Notifier notifier;
    private UnusualExpensesAlerterService unusualExpensesAlerterService;

    @Before
    public void setUp() {
        this.detector = mock(Detector.class);
        this.notifier = mock(Notifier.class);
        this.unusualExpensesAlerterService = new UnusualExpensesAlerterService(detector, notifier);
    }

    @Test
    public void when_there_are_unusual_expenses_should_send_a_notification() {
        UnusualExpenses expenses = new UnusualExpenses(userId, singletonList(new UnusualExpense("rent", 100)));
        when(detector.detect(userId)).thenReturn(expenses);

        unusualExpensesAlerterService.execute(userId);

        verify(notifier).notify(expenses);
    }

    @Test
    public void when_there_are_not_unusual_expenses_should_not_send_a_notification() {
        UnusualExpenses emptyUnusualExpenses = new UnusualExpenses(userId, emptyList());
        when(detector.detect(userId)).thenReturn(emptyUnusualExpenses);

        unusualExpensesAlerterService.execute(userId);

        verify(notifier, never()).notify(any());
    }
}
