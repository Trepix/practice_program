package alert_service;

import alert_service.notify.UserId;
import lombok.ToString;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@ToString
public class UnusualExpenses implements Iterable<UnusualExpense> {
    private final UserId userId;
    private final List<UnusualExpense> unusualExpenses;

    public UnusualExpenses(UserId userId, List<UnusualExpense> unusualExpenses) {
        this.userId = userId;
        this.unusualExpenses = unusualExpenses;
    }

    public boolean isEmpty() {
        return unusualExpenses.isEmpty();
    }

    public UserId userId() {
        return this.userId;
    }

    public int total() {
        return unusualExpenses.stream().mapToInt(UnusualExpense::amount).sum();
    }

    @Override
    public Iterator<UnusualExpense> iterator() {
        Collections.sort(unusualExpenses);
        return unusualExpenses.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnusualExpenses that = (UnusualExpenses) o;
        return Objects.equals(userId, that.userId) &&
                ((unusualExpenses == that.unusualExpenses) ||
                        (unusualExpenses != null && unusualExpenses.containsAll(that.unusualExpenses) && that.unusualExpenses.containsAll(unusualExpenses)));
    }

    /*
    TODO: never rewrite equals without do the same with hashcode
    If two objects are equal according to the equals(Object) method,
    then calling the hashcode() method on each of the two objects must produce the same integer result.
    */
    @Override
    public int hashCode() {
        return Objects.hash(userId, unusualExpenses);
    }
}
