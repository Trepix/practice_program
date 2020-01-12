package bankaccount.infrastructure;

import bankaccount.domain.bankingtransactions.BankingTransaction;
import bankaccount.domain.bankingtransactions.BankingTransactionRepository;
import bankaccount.domain.statement.Statement;

public class InMemoryBankingTransactionRepository implements BankingTransactionRepository {
    @Override
    public void add(BankingTransaction bankingTransaction) {

    }

    @Override
    public Statement generateStatement() {
        return new Statement();
    }
}
