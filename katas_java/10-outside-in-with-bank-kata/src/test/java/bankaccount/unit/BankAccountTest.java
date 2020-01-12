package bankaccount.unit;

import bankaccount.domain.BankAccount;
import bankaccount.domain.Calendar;
import bankaccount.domain.bankingtransactions.BankingTransactionRepository;
import bankaccount.domain.statement.Statement;
import bankaccount.domain.statement.StatementPrinter;
import org.junit.Before;
import org.junit.Test;

import static bankaccount.DateHelper.date;
import static bankaccount.StatementBuilder.with;
import static bankaccount.domain.bankingtransactions.BankingTransaction.deposit;
import static bankaccount.domain.bankingtransactions.BankingTransaction.withdrawal;
import static org.mockito.Mockito.*;

public class BankAccountTest {

    private StatementPrinter printer;
    private Calendar calendar;
    private BankingTransactionRepository bankingTransactionRepository;

    @Before
    public void setUp() {
        printer = mock(StatementPrinter.class);
        calendar = mock(Calendar.class);
        bankingTransactionRepository = mock(BankingTransactionRepository.class);
    }

    @Test
    public void when_a_deposit_is_done_its_added_to_repository() {
        BankAccount bankAccount = new BankAccount(calendar, printer, bankingTransactionRepository);
        when(calendar.date()).thenReturn(date("01/05/1886"));

        bankAccount.deposit(1000);

        verify(bankingTransactionRepository).add(deposit(date("01/05/1886"), 1000));
    }

    @Test
    public void when_a_withdraw_is_done_its_added_to_repository() {
        BankAccount bankAccount = new BankAccount(calendar, printer, bankingTransactionRepository);
        when(calendar.date()).thenReturn(date("03/05/1886"));

        bankAccount.withdraw(500);

        verify(bankingTransactionRepository).add(withdrawal(date("03/05/1886"), 500));
    }


    @Test
    public void when_no_banking_transactions_are_done_should_print_an_empty_statement() {
        BankAccount bankAccount = new BankAccount(calendar, printer, bankingTransactionRepository);
        Statement statement = new Statement();
        when(bankingTransactionRepository.generateStatement()).thenReturn(statement);

        bankAccount.printStatement();

        verify(printer).print(statement);
    }


    @Test
    public void prints_the_statement_with_banking_transactions() {
        BankAccount bankAccount = new BankAccount(calendar, printer, bankingTransactionRepository);
        Statement statement = with(deposit(date("01/05/1886"), 1000))
                .and(withdrawal(date("03/05/1886"), 500))
                .build();
        when(bankingTransactionRepository.generateStatement()).thenReturn(statement);

        bankAccount.printStatement();

        verify(printer).print(statement);
    }

}
