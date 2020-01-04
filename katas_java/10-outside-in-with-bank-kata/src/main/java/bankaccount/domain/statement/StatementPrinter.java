package bankaccount.domain.statement;

import bankaccount.domain.Display;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StatementPrinter {
    private final Display display;

    public StatementPrinter(Display display) {
        this.display = display;
    }

    public void print(Statement statement) {
        display.show("date || credit || debit || balance");
        for (StatementLine statementLine : statement) {
            display.show(format(statementLine));
        }
    }

    private String format(StatementLine statementLine) {
        if (statementLine.isAnIncome())
            return format(statementLine.date()) + " || " + format(statementLine.deposit()) + " || || " + format(statementLine.balance());
        else
            return format(statementLine.date()) + " || || " + format(statementLine.withdrawal()) + " || " + format(statementLine.balance());
    }

    private String format(LocalDate date) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dateFormat.format(date);
    }

    private String format(int amount) {
        return amount + ".00";
    }
}
