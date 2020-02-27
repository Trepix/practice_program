package alert_service.unit.notify;

import alert_service.UnusualExpense;
import alert_service.UnusualExpenses;
import alert_service.notify.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class NotifierTest {

    private static final UserId userId = UserId.of("abcdef");

    private NotificationSender notificationSender;
    private UserRepository userRepository;
    private Notifier notifier;

    @Before
    public void setup() {
        notificationSender = Mockito.mock(NotificationSender.class);
        userRepository = Mockito.mock(UserRepository.class);
        notifier = new Notifier(notificationSender, userRepository);
    }

    @Test
    public void when_unusual_expenses_are_empty_should_not_notify() {
        UnusualExpenses unusualExpenses = new UnusualExpenses(userId, emptyList());

        notifier.notify(unusualExpenses);

        verify(notificationSender, never()).send(any());
    }


    @Test
    public void when_there_are_an_usual_expense_should_send_a_notification() {
        when(userRepository.getBy(userId)).thenReturn(new User(userId, "user@mail.com"));
        UnusualExpenses unusualExpenses = new UnusualExpenses(userId,
                singletonList(new UnusualExpense("rent", 1000)));

        notifier.notify(unusualExpenses);

        verify(notificationSender).send(
                Notification.to("user@mail.com")
                .withSubject("Unusual spending of $1000 detected!")
                .withBody("Hello card user!\n",
                        "\n",
                        "We have detected unusually high spending on your card in these categories:\n",
                        "\n",
                        "* You spent $1000 on rent\n",
                        "\n",
                        "Love,\n",
                        "\n",
                        "The Credit Card Company\n").build());
    }
}
