package bank.acceptance;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PrintStatementStep {

    @Given("a deposit of {int} on {string}")
    public void aDepositOf(int amount, String date) {

    }

    @Given("a withdrawal of {int} on {string}")
    public void aWithdrawalOf(int amount, String date) {

    }

    @When("print the statement")
    public void printTheStatement() {

    }

    @Then("the client sees its banking transactions sorted by date from most recent to oldest")
    public void theClientSeesItsBankingTransactionsSortedByDateFromMostRecentToOldest() {
    }

}
