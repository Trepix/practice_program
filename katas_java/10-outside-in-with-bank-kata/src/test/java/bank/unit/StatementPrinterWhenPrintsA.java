package bank.unit;

import bank.account.BankingTransaction;
import bank.account.statement.Statement;
import bank.account.statement.StatementPrinter;
import bank.account.statement.StatementLine;
import bank.system.Display;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static bank.DateHelper.*;

public class StatementPrinterWhenPrintsA {

    private Display display;
    private StatementPrinter printer;

    @Before
    public void setup() {
        display = Mockito.spy(Display.class);
        printer = new StatementPrinter(display);
    }

    @Test
    public void an_empty_statement_should_print_only_the_headers() {
        Statement statement = new Statement();

        printer.print(statement);

        Mockito.verify(display).show("date || credit || debit || balance");
    }

    @Test
    public void statement_with_only_one_deposit() {
        Statement statement = new Statement();
        statement.add(BankingTransaction.deposit(date("06/11/1917"), 1000));

        printer.print(statement);

        InOrder statementOrder = Mockito.inOrder(display);
        statementOrder.verify(display).show("date || credit || debit || balance");
        statementOrder.verify(display).show("06/11/1917 || 1000.00 || || 1000.00");
    }

    @Test
    public void statement_with_two_deposits() {
        Statement statement = new Statement();
        statement.add(BankingTransaction.deposit(date("06/11/1917"), 1000));
        statement.add(BankingTransaction.deposit(date("08/11/1917"), 2000));

        printer.print(statement);

        InOrder statementOrder = Mockito.inOrder(display);
        statementOrder.verify(display).show("date || credit || debit || balance");
        statementOrder.verify(display).show("08/11/1917 || 2000.00 || || 3000.00");
        statementOrder.verify(display).show("06/11/1917 || 1000.00 || || 1000.00");
    }

    @Test
    public void statement_with_one_withdrawal() {
        Statement statement = new Statement();
        statement.add(BankingTransaction.withdrawal(date("06/11/1917"), 1000));

        printer.print(statement);

        InOrder statementOrder = Mockito.inOrder(display);
        statementOrder.verify(display).show("date || credit || debit || balance");
        statementOrder.verify(display).show("06/11/1917 || || 1000.00 || -1000.00");
    }

    @Test
    public void statement_with_deposits_and_withdrawals() {
        Statement statement = new Statement();
        statement.add(BankingTransaction.deposit(date("06/11/1917"), 1000));
        statement.add(BankingTransaction.withdrawal(date("08/11/1917"), 1500));

        printer.print(statement);

        InOrder statementOrder = Mockito.inOrder(display);
        statementOrder.verify(display).show("date || credit || debit || balance");
        statementOrder.verify(display).show("08/11/1917 || || 1500.00 || -500.00");
        statementOrder.verify(display).show("06/11/1917 || 1000.00 || || 1000.00");
    }

    @Test
    @Ignore
    public void when_statement_line_is_a_deposit_and_withdrawal_is_called() {
        BankingTransaction transaction = BankingTransaction.deposit(date("06/11/1917"), 1000);
        StatementLine statementLine = new StatementLine(transaction, 0);

        statementLine.withdrawal();
    }
}
