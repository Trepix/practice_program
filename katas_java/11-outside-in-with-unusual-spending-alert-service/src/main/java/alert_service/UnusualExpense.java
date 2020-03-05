package alert_service;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class UnusualExpense {
    private final String category;
    private final Integer amount;

    public UnusualExpense(String category, Integer amount) {
        this.category = category;
        this.amount = amount;
    }

    public int amount(){
        return this.amount;
    }

    public String category() {
        return this.category;
    }
}
