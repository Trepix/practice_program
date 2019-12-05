package bank.account.statement;

import bank.account.BankingTransaction;
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

    int deposit() {
        return transaction.amount();
    }

    public int withdrawal() {
        return Math.abs(transaction.amount());
    }

    int balance() {
        return balance;
    }

    LocalDate date() {
        return transaction.date();
    }

    boolean isAnIncome() {
        return transaction.amount() > 0;
    }
}
