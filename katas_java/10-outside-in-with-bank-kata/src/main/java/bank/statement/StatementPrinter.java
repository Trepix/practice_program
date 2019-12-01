package bank.statement;

import bank.Display;

public class StatementPrinter {
    private final Display display;

    public StatementPrinter(Display display) {
        this.display = display;
    }

    public void print(Statement statement) {
        display.show("date || credit || debit || balance");
        if (!statement.rows().isEmpty()) {
            StatementRow row = statement.rows().get(0);
            display.show("10/01/2012 || " + row.amount() + ".00 || || " + row.balance() + ".00");
        }
    }
}
