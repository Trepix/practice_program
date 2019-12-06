package bank.account.statement;

import bank.account.BankingTransaction;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Statement {

    private List<BankingTransaction> transactions = new ArrayList<>();

    public List<StatementLine> lines() {
        LinkedList<StatementLine> statementLines = new LinkedList<>();
        int balance = 0;
        for (BankingTransaction transaction : transactions) {
            balance += transaction.amount();
            StatementLine line = new StatementLine(transaction, balance);
            statementLines.push(line);
        }
        return statementLines;
    }

    public void add(BankingTransaction transaction) {
        transactions.add(transaction);
    }
}
