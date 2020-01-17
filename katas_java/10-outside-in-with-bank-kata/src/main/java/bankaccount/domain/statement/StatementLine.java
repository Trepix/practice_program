package bankaccount.domain.statement;

import bankaccount.domain.bankingtransactions.BankingTransaction;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@EqualsAndHashCode
public class StatementLine {

    private BankingTransaction bankingTransaction;
    private int balance;

    public StatementLine(BankingTransaction bankingTransaction, int balance) {
        this.bankingTransaction = bankingTransaction;
        this.balance = balance;
    }

    public LocalDate date() {
        return bankingTransaction.date();
    }

    public int balance() {
        return balance;
    }

    public int amount() {
        return Math.abs(bankingTransaction.amount());
    }

    public boolean isCredit() {
        return bankingTransaction.amount() > 0;
    }
}
