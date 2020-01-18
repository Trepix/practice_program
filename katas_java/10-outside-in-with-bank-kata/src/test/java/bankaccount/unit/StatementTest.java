package bankaccount.unit;

import bankaccount.domain.bankingtransactions.BankingTransaction;
import bankaccount.domain.statement.Statement;
import bankaccount.domain.statement.StatementLine;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static bankaccount.DateHelper.*;
import static bankaccount.StatementBuilder.*;
import static bankaccount.domain.bankingtransactions.BankingTransaction.deposit;
import static bankaccount.domain.bankingtransactions.BankingTransaction.withdrawal;
import static org.hamcrest.Matchers.*;

public class StatementTest {

    @Test
    public void should_return_empty_iterator() {
        Statement statement = new Statement();

        assertThat(statement.iterator(), emptyIterable());
    }

    @Test
    public void should_return_iterator_with_one_statement_line() {
        BankingTransaction deposit = deposit(date("17/04/1961"), 1000);
        Statement statement = with(deposit).build();

        assertThat(statement.iterator(), contains(
                new StatementLine(deposit, 1000)
        ));
    }

    @Test
    public void should_return_iterator_with_multiple_statement_lines() {
        BankingTransaction deposit = deposit(date("17/04/1961"), 400);
        BankingTransaction withdrawal = withdrawal(date("19/04/1961"), 600);

        Statement statement = startingWithOldest(deposit).andAfter(withdrawal).build();

        assertThat(statement.iterator(), contains(
                new StatementLine(withdrawal, -200),
                new StatementLine(deposit, 400)
        ));
    }

    private <T> void assertThat(Iterator<T> iterator, Matcher<Iterable<? extends T>> listMatcher) {
        List<T> listFromIterator = StreamSupport
                .stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false)
                .collect(Collectors.toList());
        String reasonToFail = "Iterator " + listFromIterator + " not contains the elements of " + listMatcher.toString();
        Assert.assertThat(reasonToFail, listFromIterator, listMatcher);
    }


}
