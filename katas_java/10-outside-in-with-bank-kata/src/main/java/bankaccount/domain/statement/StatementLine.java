package bankaccount.domain.statement;

import bankaccount.domain.bankingtransactions.BankingTransaction;

import java.time.LocalDate;

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
        return bankingTransaction.amount();
    }
}
