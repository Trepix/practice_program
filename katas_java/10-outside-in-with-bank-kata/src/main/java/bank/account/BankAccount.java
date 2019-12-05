package bank.account;

import bank.account.statement.Statement;
import bank.account.statement.StatementPrinter;
import bank.system.Calendar;
import bank.system.Display;

public class BankAccount {

    private final Calendar calendar;
    private final Statement statement = new Statement();
    private final StatementPrinter statementPrinter;

    public BankAccount(Calendar calendar, Display display) {
        this.calendar = calendar;
        this.statementPrinter = new StatementPrinter(display);
    }

    public void deposit(int amount) {
        statement.add(BankingTransaction.deposit(calendar.date(), amount));
    }

    public void withdraw(int amount) {
        statement.add(BankingTransaction.withdrawal(calendar.date(), amount));
    }

    public void printStatement() {
        statementPrinter.print(statement);
    }
}
