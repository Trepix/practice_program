package bankaccount.domain;

import bankaccount.domain.statement.Statement;
import bankaccount.domain.statement.StatementPrinter;

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
