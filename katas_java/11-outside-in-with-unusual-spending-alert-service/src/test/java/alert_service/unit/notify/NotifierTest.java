package alert_service.unit.notify;

import alert_service.UnusualExpenses;
import alert_service.notify.NotificationSender;
import alert_service.notify.Notifier;
import alert_service.notify.UserId;
import org.junit.Test;
import org.mockito.Mockito;

import static java.util.Collections.emptyList;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class NotifierTest {

    private static final UserId userId = UserId.of("abcdef");

    @Test
    public void when_unusual_expenses_are_empty_should_not_notify() {
        UnusualExpenses unusualExpenses = new UnusualExpenses(userId, emptyList());

        NotificationSender notificationSender = Mockito.mock(NotificationSender.class);
        Notifier notifier = new Notifier(notificationSender);

        notifier.notify(unusualExpenses);

        verify(notificationSender, never()).send(any());
    }
}
