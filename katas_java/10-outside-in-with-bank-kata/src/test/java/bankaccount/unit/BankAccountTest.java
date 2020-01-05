package bankaccount.unit;

import bankaccount.BankAccount;
import bankaccount.Calendar;
import bankaccount.Statement;
import bankaccount.StatementPrinter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class BankAccountTest {

    private StatementPrinter printer;
    private Calendar calendar;

    @Before
    public void setUp() {
        printer = Mockito.mock(StatementPrinter.class);
        calendar = Mockito.mock(Calendar.class);
    }

    @Test
    public void when_no_banking_transactions_are_done_should_print_an_empty_statement() {
        BankAccount bankAccount = new BankAccount(calendar, printer);
        Statement statement = new Statement();

        bankAccount.printStatement();

        Mockito.verify(printer).print(statement);
    }

}
