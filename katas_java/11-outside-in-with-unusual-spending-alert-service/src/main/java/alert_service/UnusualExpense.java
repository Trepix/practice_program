package alert_service;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class UnusualExpense implements Comparable<UnusualExpense> {
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

    @Override
    public int compareTo(UnusualExpense that) {
        if (!this.amount.equals(that.amount)) {
            return that.amount.compareTo(this.amount);
        }
        return this.category.compareTo(that.category);
    }
}
