package bankaccount.domain.statement;

import bankaccount.domain.Display;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BritishStatementPrinter implements StatementPrinter {
    private final Display display;

    public BritishStatementPrinter(Display display) {
        this.display = display;
    }

    @Override
    public void print(Statement statement) {
        display.show("date || credit || debit || balance");

        for (StatementLine statementLine : statement) {
            display.show(format(statementLine.date()) + " || " + statementLine.amount() + ".00 || || " + statementLine.balance() + ".00");
        }

    }

    private String format(LocalDate date) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dateFormat.format(date);
    }
}
