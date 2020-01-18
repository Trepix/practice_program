package bankaccount.unit;

import bankaccount.domain.Display;
import bankaccount.domain.bankingtransactions.BankingTransaction;
import bankaccount.domain.statement.BritishStatementPrinter;
import bankaccount.domain.statement.Statement;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static bankaccount.DateHelper.date;
import static bankaccount.StatementBuilder.startingWithOldest;
import static bankaccount.StatementBuilder.with;
import static bankaccount.domain.bankingtransactions.BankingTransaction.deposit;
import static bankaccount.domain.bankingtransactions.BankingTransaction.withdrawal;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BritishDateStatementPrinterTest {

    private Display display;
    private BritishStatementPrinter britishStatementPrinter;

    @Before
    public void setUp() {
        display = mock(Display.class);
        britishStatementPrinter = new BritishStatementPrinter(display);
    }

    @Test
    public void when_statement_is_empty_should_print_headers() {
        Statement statement = new Statement();

        britishStatementPrinter.print(statement);

        verify(display).show("date || credit || debit || balance");
    }

    @Test
    public void should_print_statement_with_one_deposit() {
        BankingTransaction deposit = deposit(date("12/04/1961"), 1000);
        Statement statement = with(deposit).build();

        britishStatementPrinter.print(statement);

        InOrder statementOrder = Mockito.inOrder(display);
        statementOrder.verify(display).show("date || credit || debit || balance");
        statementOrder.verify(display).show("12/04/1961 || 1000.00 || || 1000.00");
    }

    @Test
    public void should_print_statement_with_one_withdrawal() {
        BankingTransaction withdrawal = withdrawal(date("12/04/1961"), 500);
        Statement statement = with(withdrawal).build();

        britishStatementPrinter.print(statement);

        InOrder statementOrder = Mockito.inOrder(display);
        statementOrder.verify(display).show("date || credit || debit || balance");
        statementOrder.verify(display).show("12/04/1961 || || 500.00 || -500.00");
    }

    @Test
    public void should_print_statement_with_multiple_banking_transactions() {
        BankingTransaction withdrawal = withdrawal(date("12/04/1961"), 500);
        BankingTransaction deposit = deposit(date("16/04/1945"), 1200);
        Statement statement = startingWithOldest(withdrawal).andAfter(deposit).build();

        britishStatementPrinter.print(statement);

        InOrder statementOrder = Mockito.inOrder(display);
        statementOrder.verify(display).show("date || credit || debit || balance");
        statementOrder.verify(display).show("16/04/1945 || 1200.00 || || 700.00");
        statementOrder.verify(display).show("12/04/1961 || || 500.00 || -500.00");
    }

}
