package bank.statement;

import bank.BankingTransaction;

import java.util.ArrayList;
import java.util.List;

public class Statement {

    private ArrayList<StatementRow> statementRows = new ArrayList<>();

    public List<StatementRow> rows() {
        return statementRows;
    }

    public void add(BankingTransaction transaction) {
        StatementRow row = StatementRow.deposit(transaction.date(), transaction.amount(), transaction.amount());
        statementRows.add(row);
    }
}
