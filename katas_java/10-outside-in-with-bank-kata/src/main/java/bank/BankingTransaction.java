package bank;

import java.time.LocalDate;

public class BankingTransaction {

    private final int amount;
    private final LocalDate date;

    private BankingTransaction(LocalDate date, int amount) {
        this.amount = amount;
        this.date = date;
    }

    public static BankingTransaction deposit(LocalDate date, int amount) {
        return new BankingTransaction(date, amount);
    }

    public static BankingTransaction withdrawal(LocalDate date, int amount) {
        return new BankingTransaction(date, -amount);
    }

    public int amount() {
        return this.amount;
    }

    public LocalDate date() {
        return this.date;
    }
}
