package bankaccount.unit;

import bankaccount.DateHelper;
import bankaccount.StatementBuilder;
import bankaccount.domain.bankingtransactions.BankingTransaction;
import bankaccount.domain.statement.Statement;
import bankaccount.domain.statement.StatementLine;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static bankaccount.StatementBuilder.*;
import static bankaccount.domain.bankingtransactions.BankingTransaction.deposit;

public class StatementTest {

    @Test
    public void should_return_empty_iterator() {
        Statement statement = new Statement();

        assertThat(statement.iterator(), Matchers.emptyIterable());
    }

    @Test
    public void should_return_statement_with_one_deposit() {
        BankingTransaction deposit = deposit(DateHelper.date("17/04/1961"), 1000);
        Statement statement = with(deposit).build();

        assertThat(statement.iterator(), Matchers.contains(
                new StatementLine(deposit, 1000)
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
