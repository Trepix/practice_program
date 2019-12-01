package bank.unit;

import bank.BankingTransaction;
import bank.statement.Statement;
import bank.statement.StatementRow;
import io.cucumber.java.bs.I;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static bank.DateUtils.parse;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class StatementTest {

    @Test
    public void when_no_banking_transactions_are_added_statement_should_be_empty() {
        Statement statement = new Statement();
        assertThat(statement.rows(), is(new ArrayList<>()));
    }

    @Test
    public void a_deposit_is_added() {
        Statement statement = new Statement();
        BankingTransaction transaction = BankingTransaction.deposit(parse("10/01/2012"), 1000);

        statement.add(transaction);

        List<StatementRow> expectedRow = singletonList(StatementRow.deposit(transaction, 1000));
        assertThat(expectedRow, is(statement.rows()));
    }

    @Test
    public void two_deposits_are_added() {
        Statement statement = new Statement();
        BankingTransaction oldestTransaction = BankingTransaction.deposit(parse("10/01/2012"), 1000);
        BankingTransaction moreRecentTransaction = BankingTransaction.deposit(parse("11/01/2012"), 1000);

        statement.add(oldestTransaction);
        statement.add(moreRecentTransaction);

        List<StatementRow> expectedRow = asList(
                StatementRow.deposit(moreRecentTransaction, 2000),
                StatementRow.deposit(oldestTransaction, 1000)
        );
        assertThat(expectedRow, is(statement.rows()));
    }

    @Test
    public void one_withdrawal_is_added() {
        Statement statement = new Statement();
        BankingTransaction transaction = BankingTransaction.withdrawal(parse("10/01/2012"), 1000);

        statement.add(transaction);

        List<StatementRow> expectedRow = singletonList(StatementRow.withdrawal(transaction, -1000));
        assertThat(expectedRow, is(statement.rows()));
    }

    @Test
    @Ignore("naming must be improved")
    public void when_two_deposits_are_added_the_order_of_its_addition_to_statement_does_not_matter_because_are_sorted_by_date() {
        Statement statement_1_2 = new Statement();
        Statement statement_2_1 = new Statement();
        BankingTransaction transaction_1 = BankingTransaction.deposit(parse("10/01/2012"), 1000);
        BankingTransaction transaction_2 = BankingTransaction.deposit(parse("11/01/2012"), 1000);

        statement_1_2.add(transaction_1);
        statement_1_2.add(transaction_2);

        statement_2_1.add(transaction_2);
        statement_2_1.add(transaction_1);

        assertThat(statement_1_2.rows(), is(statement_2_1.rows()));
    }

}
