package bankaccount.domain.statement;

import bankaccount.domain.BankingTransaction;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@EqualsAndHashCode
public class StatementLine {
    private final BankingTransaction transaction;
    private final int balance;

    public StatementLine(BankingTransaction transaction, int balance) {
        this.transaction = transaction;
        this.balance = balance;
    }

    int credit() {
        return transaction.amount();
    }

    public int debit() {
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
