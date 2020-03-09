package alert_service;

import alert_service.detection.Detector;
import alert_service.notify.Notifier;
import alert_service.notify.UserId;

public class UnusualExpensesAlerterService {

    private Detector detector;
    private Notifier notifier;

    public UnusualExpensesAlerterService(PaymentsRepository paymentsRepository,
                                         UserRepository userRepository,
                                         NotificationSender notificationSender,
                                         Calendar calendar) {
        this.detector = new Detector(paymentsRepository, calendar);
        this.notifier = new Notifier(notificationSender, userRepository);
    }

    public void execute(UserId userId) {
        UnusualExpenses unusualExpenses = detector.detect(userId);
        notifier.notify(unusualExpenses);
    }
}
