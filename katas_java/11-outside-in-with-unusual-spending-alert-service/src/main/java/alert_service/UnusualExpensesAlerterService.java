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
