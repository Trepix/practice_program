package alert_service;

import alert_service.detection.Calendar;
import alert_service.detection.Detector;
import alert_service.detection.PaymentsRepository;
import alert_service.notify.NotificationSender;
import alert_service.notify.Notifier;
import alert_service.notify.UserId;
import alert_service.notify.UserRepository;

public class UnusualExpensesAlerterService {

    private Detector detector;
    private Notifier notifier;

    public UnusualExpensesAlerterService(Detector detector, Notifier notifier) {
        this.detector = detector;
        this.notifier = notifier;
    }

    public UnusualExpensesAlerterService(PaymentsRepository paymentsRepository,
                                         UserRepository userRepository,
                                         NotificationSender notificationSender,
                                         Calendar calendar) {
    }

    public void execute(UserId userId) {
        UnusualExpenses unusualExpenses = detector.detect(userId);
        if (unusualExpenses.isEmpty()) return;

        notifier.notify(unusualExpenses);
    }
}
