package bank.statement;

import bank.Display;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StatementPrinter {
    private final Display display;

    public StatementPrinter(Display display) {
        this.display = display;
    }

    public void print(Statement statement) {
        display.show("date || credit || debit || balance");
        if (!statement.rows().isEmpty()) {
            StatementRow row = statement.rows().get(0);
            display.show(format(row.date()) + " || " + format(row.deposit()) + " || || " + format(row.balance()));
        }
    }

    private String format(LocalDate date) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dateFormat.format(date);
    }

    private String format(int amount) {
        return amount + ".00";
    }
}
