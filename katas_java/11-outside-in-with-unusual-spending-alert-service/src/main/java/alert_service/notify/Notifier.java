package alert_service.notify;

import alert_service.UnusualExpenses;

public class Notifier {

    private NotificationSender notificationSender;
    private UserRepository userRepository;

    public Notifier(NotificationSender notificationSender, UserRepository userRepository) {

        this.notificationSender = notificationSender;
        this.userRepository = userRepository;
    }

    public void notify(UnusualExpenses unusualExpenses) {
        if (unusualExpenses.isEmpty()) return ;
        User user = userRepository.getBy(unusualExpenses.userId());
        Notification notification = Notification.to(user.email())
                .withSubject("Unusual spending of $1000 detected!")
                .withBody(
                "Hello card user!\n",
                        "\n",
                        "We have detected unusually high spending on your card in these categories:\n",
                        "\n",
                        "* You spent $1000 on rent\n",
                        "\n",
                        "Love,\n",
                        "\n",
                        "The Credit Card Company\n").build();
        notificationSender.send(notification);
    }

}
