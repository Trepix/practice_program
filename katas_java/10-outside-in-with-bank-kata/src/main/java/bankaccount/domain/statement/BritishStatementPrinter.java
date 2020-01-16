package bankaccount.domain.statement;

import bankaccount.domain.Display;

public class BritishStatementPrinter implements StatementPrinter {
    private final Display display;

    public BritishStatementPrinter(Display display) {
        this.display = display;
    }

    @Override
    public void print(Statement statement) {
        display.show("date || credit || debit || balance");
    }
}
