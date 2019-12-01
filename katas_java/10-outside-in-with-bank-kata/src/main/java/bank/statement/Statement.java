package bank.statement;

import bank.BankingTransaction;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

public class Statement {

    private List<BankingTransaction> transactions = new ArrayList<>();

    public List<StatementRow> rows() {
        LinkedList<StatementRow> statementRows = new LinkedList<>();
        int balance = 0;
        for (BankingTransaction transaction : transactions) {
            balance += transaction.amount();
            StatementRow row = generateStatementRow(transaction, balance);
            statementRows.push(row);
        }
        return statementRows;
    }

    private StatementRow generateStatementRow(BankingTransaction transaction, int balance) {
        if (transaction.amount() > 0)
            return StatementRow.deposit(transaction, balance);
        else
            return StatementRow.withdrawal(transaction, balance);
    }

    public void add(BankingTransaction transaction) {
        transactions.add(transaction);
    }
}
