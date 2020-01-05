package bankaccount.unit;

import bankaccount.BankAccount;
import bankaccount.Calendar;
import bankaccount.bankingtransactions.BankingTransactionRepository;
import bankaccount.statement.Statement;
import bankaccount.statement.StatementPrinter;
import org.junit.Before;
import org.junit.Test;

import static bankaccount.DateHelper.date;
import static bankaccount.bankingtransactions.BankingTransaction.deposit;
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
    public void when_no_banking_transactions_are_done_should_print_an_empty_statement() {
        BankAccount bankAccount = new BankAccount(calendar, printer, bankingTransactionRepository);
        Statement statement = new Statement();

        when(bankingTransactionRepository.generateStatement()).thenReturn(statement);

        bankAccount.printStatement();

        verify(printer).print(statement);
    }

    @Test
    public void when_a_deposit_is_done_its_added_to_repository() {
        BankAccount bankAccount = new BankAccount(calendar, printer, bankingTransactionRepository);
        when(calendar.date()).thenReturn(date("01/05/1886"));

        bankAccount.deposit(1000);

        verify(bankingTransactionRepository).add(deposit(date("01/05/1886"), 1000));
    }

}
