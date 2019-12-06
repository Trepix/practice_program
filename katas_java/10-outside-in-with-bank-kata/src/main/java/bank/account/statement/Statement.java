package bank.account.statement;

import bank.account.BankingTransaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Statement implements Iterable<StatementLine>{

    private List<BankingTransaction> transactions = new ArrayList<>();

    @Override
    public Iterator<StatementLine> iterator() {
        LinkedList<StatementLine> statementLines = new LinkedList<>();
        int balance = 0;
        for (BankingTransaction transaction : transactions) {
            balance += transaction.amount();
            StatementLine line = new StatementLine(transaction, balance);
            statementLines.push(line);
        }
        return statementLines.iterator();
    }

    public void add(BankingTransaction transaction) {
        transactions.add(transaction);
    }
}
