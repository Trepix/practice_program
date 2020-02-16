package alert_service.detection;

import alert_service.UnusualExpenses;
import alert_service.notify.UserId;

import java.time.LocalDate;

public class Detector {
    private final PaymentsRepository paymentsRepository;
    private final Calendar calendar;

    public Detector(PaymentsRepository paymentsRepository, Calendar calendar) {

        this.paymentsRepository = paymentsRepository;
        this.calendar = calendar;
    }

    public UnusualExpenses detect(UserId userId) {
        LocalDate end = calendar.today();
        LocalDate start = end.minusMonths(1).withDayOfMonth(1);
        Payments payments = paymentsRepository.getBy(userId, new DateRange(start, end));
        return payments.findUnusualExpenses();
    }
}
