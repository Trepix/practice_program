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
            display.show(format(statementLine));
        }
    }

    private String format(StatementLine statementLine) {
        if (statementLine.isCredit())
            return format(statementLine.date()) + " || " + format(statementLine.amount()) + " || || " + format(statementLine.balance());
        else
            return format(statementLine.date()) + " || || " + format(statementLine.amount()) + " || " + format(statementLine.balance());
    }

    private String format(LocalDate date) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dateFormat.format(date);
    }

    private String format(int amount) {
        return amount + ".00";
    }
}
