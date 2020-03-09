package alert_service.notify;

import alert_service.NotificationSender;
import alert_service.UnusualExpense;
import alert_service.UnusualExpenses;
import alert_service.UserRepository;

public class Notifier {

    private NotificationSender notificationSender;
    private UserRepository userRepository;

    public Notifier(NotificationSender notificationSender, UserRepository userRepository) {
        this.notificationSender = notificationSender;
        this.userRepository = userRepository;
    }

    public void notify(UnusualExpenses unusualExpenses) {
        if (unusualExpenses.isEmpty()) return;
        User user = userRepository.getBy(unusualExpenses.userId());
        Notification notification = Notification.to(user.email())
                .withSubject(subject(unusualExpenses.total()))
                .withBody(body(unusualExpenses)).build();
        notificationSender.send(notification);
    }

    private String subject(Integer total) {
        return "Unusual spending of $" + total + " detected!";
    }

    private String[] body(UnusualExpenses unusualExpenses) {
        return new String[]{"Hello card user!\n",
                "\n",
                "We have detected unusually high spending on your card in these categories:\n",
                "\n",
                generateCategoriesSpending(unusualExpenses),
                "\n",
                "Love,\n",
                "\n",
                "The Credit Card Company"};
    }

    private String generateCategoriesSpending(UnusualExpenses unusualExpenses) {
        String lineTemplate = "* You spent $%d on %s\n";
        StringBuilder result = new StringBuilder();
        for (UnusualExpense unusualExpense : unusualExpenses) {
            result.append(String.format(lineTemplate, unusualExpense.amount(), unusualExpense.category()));
        }
        return result.toString();
    }

}
