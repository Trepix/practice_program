package alert_service.unit;

import alert_service.UnusualExpense;
import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertThat;

public class UnusualExpenseTest {


    @Test
    public void should_be_ordered_by_amount_when_they_are_different() {
        UnusualExpense first = new UnusualExpense("zzz", 2000);
        UnusualExpense second = new UnusualExpense("aaa", 1000);

        List<UnusualExpense> list = asList(second, first);
        Collections.sort(list);

        assertThat(list, Is.is(asList(first, second)));
    }

    @Test
    public void when_amount_are_equal_should_order_by_category() {
        UnusualExpense first = new UnusualExpense("aaa", 2000);
        UnusualExpense second = new UnusualExpense("zzz", 2000);

        List<UnusualExpense> list = asList(second, first);
        Collections.sort(list);

        assertThat(list, Is.is(asList(first, second)));
    }
}
