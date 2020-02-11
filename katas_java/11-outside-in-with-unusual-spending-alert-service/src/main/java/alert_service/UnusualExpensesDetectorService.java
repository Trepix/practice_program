package alert_service;

public class UnusualExpensesDetectorService {
    public UnusualExpensesDetectorService(PaymentsRepository paymentsRepository,
                                          UserRepository userRepository,
                                          NotificationSender notificationSender,
                                          Calendar calendar) { }

    public void execute(UserId userId) {

    }
}
