package bankaccount.unit;

import bankaccount.domain.bankingtransactions.BankingTransaction;
import bankaccount.domain.bankingtransactions.BankingTransactionRepository;
import bankaccount.domain.statement.Statement;
import bankaccount.infrastructure.InMemoryBankingTransactionRepository;
import org.junit.Before;
import org.junit.Test;

import static bankaccount.DateHelper.date;
import static bankaccount.StatementBuilder.with;
import static bankaccount.domain.bankingtransactions.BankingTransaction.deposit;
import static org.hamcrest.Matchers.is;
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

        assertThat(statement, is(emptyStatement));
    }

    @Test
    public void returns_statement_with_deposit() {
        BankingTransaction deposit = deposit(date("02/02/1943"), 1000);
        Statement statement = with(deposit).build();

        bankingTransactionRepository.add(deposit);
        assertThat(bankingTransactionRepository.generateStatement(), is(statement));
    }


    @Test
    public void returns_statement_with_multiple_banking_transactions() {
        BankingTransaction withdraw = deposit(date("02/05/1943"), 1000);
        BankingTransaction deposit = deposit(date("02/02/1945"), 1000);
        Statement statement = with(deposit).and(withdraw).build();

        bankingTransactionRepository.add(withdraw);
        bankingTransactionRepository.add(deposit);

        assertThat(bankingTransactionRepository.generateStatement(), is(statement));
    }


}
