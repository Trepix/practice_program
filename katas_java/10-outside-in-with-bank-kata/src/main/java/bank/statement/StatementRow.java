package bank.statement;

import bank.BankingTransaction;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class StatementRow {
    private final BankingTransaction transaction;
    private final int balance;

    public StatementRow(BankingTransaction transaction, int balance) {
        this.transaction = transaction;
        this.balance = balance;
    }
}
