package alert_service;

import alert_service.notify.UserId;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@ToString
@EqualsAndHashCode
public class UnusualExpenses {
    private final UserId userId;
    private List<UnusualExpense> unusualExpenses;

    public UnusualExpenses(UserId userId, List<UnusualExpense> unusualExpenses) {
        this.userId = userId;
        this.unusualExpenses = unusualExpenses;
    }

    public boolean isEmpty() {
        return unusualExpenses.isEmpty();
    }
}
