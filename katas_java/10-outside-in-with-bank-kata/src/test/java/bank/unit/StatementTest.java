package bank.unit;

import bank.account.BankingTransaction;
import bank.account.statement.Statement;
import bank.account.statement.StatementLine;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static bank.DateHelper.date;
import static org.hamcrest.Matchers.contains;

public class StatementTest {

    @Test
    public void when_no_banking_transactions_are_added_statement_should_be_empty() {
        Statement statement = new Statement();

        assertThat(statement.iterator(), Matchers.emptyIterable());
    }

    @Test
    public void two_deposits_are_added() {
        Statement statement = new Statement();
        BankingTransaction oldestTransaction = BankingTransaction.deposit(date("01/05/1886"), 1000);
        BankingTransaction moreRecentTransaction = BankingTransaction.deposit(date("03/05/1886"), 1000);

        statement.add(oldestTransaction);
        statement.add(moreRecentTransaction);

        assertThat(statement.iterator(), contains(
                new StatementLine(moreRecentTransaction, 2000),
                new StatementLine(oldestTransaction, 1000)));
    }

    @Test
    public void one_withdrawal_is_added() {
        Statement statement = new Statement();
        BankingTransaction transaction = BankingTransaction.withdrawal(date("01/05/1886"), 1000);

        statement.add(transaction);

        assertThat(statement.iterator(), contains(new StatementLine(transaction, -1000)));
    }

    @Test
    public void deposits_and_withdrawals_are_added_to_statement() {
        Statement statement = new Statement();
        BankingTransaction firstDeposit = BankingTransaction.deposit(date("01/05/1886"), 1000);
        BankingTransaction withdrawal = BankingTransaction.withdrawal(date("03/05/1886"), 500);
        BankingTransaction secondDeposit = BankingTransaction.deposit(date("12/01/2012"), 2000);

        statement.add(firstDeposit);
        statement.add(withdrawal);
        statement.add(secondDeposit);

        assertThat(statement.iterator(), contains(
                new StatementLine(secondDeposit, 2500),
                new StatementLine(withdrawal, 500),
                new StatementLine(firstDeposit, 1000)));
    }

    @Test
    @Ignore("naming must be improved")
    public void when_two_deposits_are_added_the_order_of_its_addition_to_statement_does_not_matter_because_are_sorted_by_date() {
        Statement statement_1_2 = new Statement();
        Statement statement_2_1 = new Statement();
        BankingTransaction transaction_1 = BankingTransaction.deposit(date("01/05/1886"), 1000);
        BankingTransaction transaction_2 = BankingTransaction.deposit(date("03/05/1886"), 1000);

        statement_1_2.add(transaction_1);
        statement_1_2.add(transaction_2);

        statement_2_1.add(transaction_2);
        statement_2_1.add(transaction_1);

        //assertThat(statement_1_2.iterator(), is(statement_2_1.iterator()));
    }

    private <T> void assertThat(Iterator<T> iterator, Matcher<Iterable<? extends T>> listMatcher) {
        List<T> listFromIterator = StreamSupport
                .stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false)
                .collect(Collectors.toList());
        Assert.assertThat("", listFromIterator, listMatcher);
    }
}
