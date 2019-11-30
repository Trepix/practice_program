package bank.statement;

import bank.BankingTransaction;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Statement {

    private List<BankingTransaction> transactions = new ArrayList<>();

    public List<StatementRow> rows() {
        LinkedList<StatementRow> statementRows = new LinkedList<>();
        int balance = 0;
        for (BankingTransaction transaction : transactions) {
            balance += transaction.amount();
            StatementRow row = StatementRow.deposit(transaction.date(), transaction.amount(), balance);
            statementRows.push(row);
        }
        return statementRows;
    }

    public void add(BankingTransaction transaction) {
        transactions.add(transaction);
    }
}
