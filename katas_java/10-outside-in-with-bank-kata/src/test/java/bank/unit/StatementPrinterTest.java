package bank.unit;

import bank.BankingTransaction;
import bank.DateUtils;
import bank.Display;
import bank.statement.Statement;
import bank.statement.StatementPrinter;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

public class StatementPrinterTest {

    @Test
    public void when_statement_is_empty_only_should_print_the_headers() {
        Statement statement = new Statement();
        Display display = Mockito.spy(Display.class);
        StatementPrinter printer = new StatementPrinter(display);

        printer.print(statement);

        Mockito.verify(display).show("date || credit || debit || balance");
    }

    @Test
    public void statement_with_only_one_deposit() {
        Display display = Mockito.spy(Display.class);
        StatementPrinter printer = new StatementPrinter(display);
        Statement statement = new Statement();
        statement.add(BankingTransaction.deposit(DateUtils.parse("10/01/2012"), 1000));

        printer.print(statement);

        InOrder statementOrder = Mockito.inOrder(display);
        statementOrder.verify(display).show("date || credit || debit || balance");
        statementOrder.verify(display).show("10/01/2012 || 1000.00 || || 1000.00");
    }

    @Test
    public void statement_with_two_deposits() {
        Display display = Mockito.spy(Display.class);
        StatementPrinter printer = new StatementPrinter(display);
        Statement statement = new Statement();
        statement.add(BankingTransaction.deposit(DateUtils.parse("10/01/2012"), 1000));
        statement.add(BankingTransaction.deposit(DateUtils.parse("11/01/2012"), 2000));

        printer.print(statement);

        InOrder statementOrder = Mockito.inOrder(display);
        statementOrder.verify(display).show("date || credit || debit || balance");
        statementOrder.verify(display).show("11/01/2012 || 2000.00 || || 3000.00");
        statementOrder.verify(display).show("10/01/2012 || 1000.00 || || 1000.00");
    }
}
