package alert_service.detection;

import alert_service.UnusualExpenses;
import alert_service.notify.UserId;

import java.util.Collections;

import static java.util.Collections.*;

public class Detector {
    public Detector(PaymentsRepository paymentsRepository) {

    }

    public UnusualExpenses detect(UserId userId) {
        return new UnusualExpenses(userId, emptyList());
    }
}
