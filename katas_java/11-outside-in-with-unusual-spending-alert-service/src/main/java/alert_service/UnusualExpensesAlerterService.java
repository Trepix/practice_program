package alert_service;

public class UnusualExpensesAlerterService {

    private UnusualExpensesDetector unusualExpensesDetector;
    private UnusualExpensesNotifier notifier;

    public UnusualExpensesAlerterService(UnusualExpensesDetector unusualExpensesDetector, UnusualExpensesNotifier notifier) {
        this.unusualExpensesDetector = unusualExpensesDetector;
        this.notifier = notifier;
    }

    public UnusualExpensesAlerterService(PaymentsRepository paymentsRepository,
                                         UserRepository userRepository,
                                         NotificationSender notificationSender,
                                         Calendar calendar) {
    }

    public void execute(UserId userId) {
        UnusualExpenses unusualExpenses = unusualExpensesDetector.execute(userId);
        if (unusualExpenses.isEmpty()) return;

        notifier.execute(unusualExpenses);
    }
}
