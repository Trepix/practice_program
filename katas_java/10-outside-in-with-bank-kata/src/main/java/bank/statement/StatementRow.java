package bank.statement;

import bank.BankingTransaction;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@EqualsAndHashCode
public class StatementRow {
    private final BankingTransaction transaction;
    private final int balance;

    public StatementRow(BankingTransaction transaction, int balance) {
        this.transaction = transaction;
        this.balance = balance;
    }

    int amount() {
        return transaction.amount();
    }

    int balance() {
        return balance;
    }

    LocalDate date() {
        return transaction.date();
    }
}
