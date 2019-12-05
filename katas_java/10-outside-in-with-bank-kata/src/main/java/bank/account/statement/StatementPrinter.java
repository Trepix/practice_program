package bank.account.statement;

import bank.system.Display;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StatementPrinter {
    private final Display display;

    public StatementPrinter(Display display) {
        this.display = display;
    }

    public void print(Statement statement) {
        display.show("date || credit || debit || balance");
        for (StatementRow row : statement.rows()) {
            display.show(generateLine(row));
        }
    }

    private String generateLine(StatementRow row) {
        if (row.isAnIncome())
            return format(row.date()) + " || " + format(row.deposit()) + " || || " + format(row.balance());
        else
            return format(row.date()) + " || || " + format(row.withdrawal()) + " || " + format(row.balance());
    }

    private String format(LocalDate date) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dateFormat.format(date);
    }

    private String format(int amount) {
        return amount + ".00";
    }
}
