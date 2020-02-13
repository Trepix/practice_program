package alert_service.unit;

import alert_service.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.mockito.Mockito.*;

public class UnusualExpensesAlerterServiceTest {

    private final UserId userId = UserId.of("1234");

    private UnusualExpensesDetector detector;
    private UnusualExpensesNotifier notifier;
    private UnusualExpensesAlerterService unusualExpensesAlerterService;

    @Before
    public void setUp() {
        this.detector = mock(UnusualExpensesDetector.class);
        this.notifier = mock(UnusualExpensesNotifier.class);
        this.unusualExpensesAlerterService = new UnusualExpensesAlerterService(detector, notifier);
    }

    @Test
    public void when_there_are_unusual_expenses_should_send_a_notification() {
        UnusualExpenses expenses = new UnusualExpenses(userId, singletonList(new UnusualExpense("rent", 100)));
        when(detector.execute(userId)).thenReturn(expenses);

        unusualExpensesAlerterService.execute(userId);

        verify(notifier).execute(expenses);
    }

    @Test
    public void when_there_are_not_unusual_expenses_should_not_send_a_notification() {
        UnusualExpenses emptyUnusualExpenses = new UnusualExpenses(userId, emptyList());
        when(detector.execute(userId)).thenReturn(emptyUnusualExpenses);

        unusualExpensesAlerterService.execute(userId);

        verify(notifier, never()).execute(any());
    }
}
