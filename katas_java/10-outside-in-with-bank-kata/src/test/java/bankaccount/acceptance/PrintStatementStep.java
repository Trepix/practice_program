package bankaccount.acceptance;

import bankaccount.domain.BankAccount;
import bankaccount.domain.Calendar;
import bankaccount.domain.Display;
import bankaccount.domain.statement.BritishStatementPrinter;
import bankaccount.infrastructure.InMemoryBankingTransactionRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PrintStatementStep {

    private Calendar calendar = Mockito.mock(Calendar.class);
    private Display display = Mockito.spy(Display.class);
    private BankAccount bankAccount = new BankAccount(calendar, new BritishStatementPrinter(display), new InMemoryBankingTransactionRepository());

    @Given("a deposit of {int} on date {string}")
    public void aDepositOf(int amount, String date) {
        Mockito.when(calendar.date()).thenReturn(parse(date));
        bankAccount.deposit(amount);
    }

    @Given("a withdrawal of {int} on date {string}")
    public void aWithdrawalOf(int amount, String date) {
        Mockito.when(calendar.date()).thenReturn(parse(date));
        bankAccount.withdraw(amount);
    }

    @When("print the statement")
    public void printTheStatement() {
        bankAccount.printStatement();
    }

    @Then("the client sees its banking transactions sorted by date from most recent to oldest")
    public void theClientSeesItsBankingTransactionsSortedByDateFromMostRecentToOldest() {
        InOrder statementOrder = Mockito.inOrder(display);
        statementOrder.verify(display).show("date || credit || debit || balance");
        statementOrder.verify(display).show("14/01/2012 || || 500.00 || 2500.00");
        statementOrder.verify(display).show("13/01/2012 || 2000.00 || || 3000.00");
        statementOrder.verify(display).show("10/01/2012 || 1000.00 || || 1000.00");
    }

    private LocalDate parse(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

}
