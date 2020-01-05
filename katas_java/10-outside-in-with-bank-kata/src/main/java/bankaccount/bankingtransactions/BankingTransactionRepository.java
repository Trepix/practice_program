package bankaccount.bankingtransactions;

import bankaccount.statement.Statement;

public interface BankingTransactionRepository {
    void add(BankingTransaction bankingTransaction);
    Statement generateStatement();
}
