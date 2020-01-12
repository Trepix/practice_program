package bankaccount.infrastructure;

import bankaccount.domain.bankingtransactions.BankingTransaction;
import bankaccount.domain.bankingtransactions.BankingTransactionRepository;
import bankaccount.domain.statement.Statement;

import java.util.LinkedList;

public class InMemoryBankingTransactionRepository implements BankingTransactionRepository {

    private LinkedList<BankingTransaction> bankingTransactions = new LinkedList<>();

    @Override
    public void add(BankingTransaction bankingTransaction) {
        bankingTransactions.push(bankingTransaction);
    }

    @Override
    public Statement generateStatement() {
        if (bankingTransactions.isEmpty()) return new Statement();
        Statement statement = new Statement();
        statement.add(bankingTransactions.get(0));
        return statement;
    }
}
