package bankaccount.unit;

import bankaccount.domain.Display;
import bankaccount.domain.bankingtransactions.BankingTransaction;
import bankaccount.domain.statement.BritishStatementPrinter;
import bankaccount.domain.statement.Statement;
import bankaccount.domain.statement.StatementLine;
import io.cucumber.java.bs.I;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static bankaccount.DateHelper.date;
import static bankaccount.domain.bankingtransactions.BankingTransaction.deposit;
import static bankaccount.domain.bankingtransactions.BankingTransaction.withdrawal;
import static java.util.Arrays.asList;
import static java.util.Collections.*;
import static org.mockito.Mockito.*;

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
        Statement statement = mock(Statement.class);
        when(statement.iterator()).thenReturn(singletonList(new StatementLine(deposit, 1000)).iterator());

        britishStatementPrinter.print(statement);

        InOrder statementOrder = Mockito.inOrder(display);
        statementOrder.verify(display).show("date || credit || debit || balance");
        statementOrder.verify(display).show("12/04/1961 || 1000.00 || || 1000.00");
    }

    @Test
    public void should_print_statement_with_one_withdrawal() {
        BankingTransaction withdrawal = withdrawal(date("12/04/1961"), 500);
        Statement statement = mock(Statement.class);
        when(statement.iterator()).thenReturn(singletonList(new StatementLine(withdrawal, -500)).iterator());

        britishStatementPrinter.print(statement);

        InOrder statementOrder = Mockito.inOrder(display);
        statementOrder.verify(display).show("date || credit || debit || balance");
        statementOrder.verify(display).show("12/04/1961 || || 500.00 || -500.00");
    }

    @Test
    @Ignore("working on Statement to change mocks for real implementation")
    public void should_print_statement_with_multiple_banking_transactions() {
        BankingTransaction withdrawal = withdrawal(date("12/04/1961"), 1000);
        BankingTransaction deposit = deposit(date("16/04/1945"), 500);
        Statement statement = mock(Statement.class);

        britishStatementPrinter.print(statement);

        InOrder statementOrder = Mockito.inOrder(display);
        statementOrder.verify(display).show("date || credit || debit || balance");
        statementOrder.verify(display).show("12/04/1961 || || 500.00 || -500.00");
    }

}
