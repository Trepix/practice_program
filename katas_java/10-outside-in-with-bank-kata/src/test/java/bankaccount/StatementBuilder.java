package bankaccount;

import bankaccount.domain.bankingtransactions.BankingTransaction;
import bankaccount.domain.statement.Statement;

import java.util.LinkedList;
import java.util.List;

public class StatementBuilder {

    private List<BankingTransaction> bankingTransactions = new LinkedList<>();

    private StatementBuilder() {
    }

    public static StatementBuilder with(BankingTransaction bankingTransaction) {
        StatementBuilder statementBuilder = new StatementBuilder();
        statementBuilder.bankingTransactions.add(bankingTransaction);
        return statementBuilder;
    }

    public static StatementBuilder startingWithOldest(BankingTransaction bankingTransaction) {
        StatementBuilder statementBuilder = new StatementBuilder();
        statementBuilder.bankingTransactions.add(bankingTransaction);
        return statementBuilder;
    }

    public StatementBuilder and(BankingTransaction bankingTransaction) {
        bankingTransactions.add(bankingTransaction);
        return this;
    }

    public StatementBuilder andAfter(BankingTransaction bankingTransaction) {
        bankingTransactions.add(bankingTransaction);
        return this;
    }

    public Statement build() {
        Statement statement = new Statement();
        for (BankingTransaction bankingTransaction : bankingTransactions) {
            statement.add(bankingTransaction);
        }
        return statement;
    }
}
