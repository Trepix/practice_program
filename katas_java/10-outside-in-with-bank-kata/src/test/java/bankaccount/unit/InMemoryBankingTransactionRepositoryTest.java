package bankaccount.unit;

import bankaccount.domain.bankingtransactions.BankingTransactionRepository;
import bankaccount.domain.statement.Statement;
import bankaccount.infrastructure.InMemoryBankingTransactionRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class InMemoryBankingTransactionRepositoryTest {

    private BankingTransactionRepository bankingTransactionRepository;

    @Before
    public void setUp() {
        bankingTransactionRepository = new InMemoryBankingTransactionRepository();
    }

    @Test
    public void returns_empty_statement_when_no_banking_transactions_are_stored() {
        Statement emptyStatement = new Statement();

        Statement statement = bankingTransactionRepository.generateStatement();

        assertThat(statement, Matchers.is(emptyStatement));
    }

}
