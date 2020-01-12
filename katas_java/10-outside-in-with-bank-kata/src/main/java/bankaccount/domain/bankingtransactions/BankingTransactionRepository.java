package bankaccount.domain.bankingtransactions;

import bankaccount.domain.statement.Statement;

public interface BankingTransactionRepository {
    void add(BankingTransaction bankingTransaction);
    Statement generateStatement();
}
