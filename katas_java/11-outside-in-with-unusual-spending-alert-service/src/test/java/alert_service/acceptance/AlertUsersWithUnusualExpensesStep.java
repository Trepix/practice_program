package alert_service.acceptance;

import alert_service.UnusualExpensesAlerterService;
import alert_service.detection.*;
import alert_service.notify.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.val;

import java.time.LocalDate;

import static alert_service.DateHelper.date;
import static org.mockito.Mockito.*;

public class AlertUsersWithUnusualExpensesStep {

    private UserRepository userRepository = mock(UserRepository.class);
    private PaymentsRepository paymentsRepository = mock(PaymentsRepository.class);
    private Calendar calendar = mock(Calendar.class);
    private NotificationSender notificationSender = mock(NotificationSender.class);
    private LocalDate now;

    @Given("a user with id {string} and email {string}")
    public void a_user(String id, String email) {
        UserId userId = UserId.of(id);
        when(userRepository.getBy(userId)).thenReturn(new User(userId, email));
    }

    @And("being today {string}")
    public void beingToday(String now) {
        this.now = date(now);
        when(calendar.today()).thenReturn(this.now);
    }

    @And("a list of payments for the user:")
    public void aListOfPayments(DataTable dataTable) {
        UserId userId = UserId.of("1234");
        Payments payments = new Payments(userId, dataTable.asList(Payment.class));
        LocalDate firstDayOfMonthBefore = this.now.minusMonths(1).withDayOfMonth(1);
        when(paymentsRepository.getBy(userId, new DateRange(firstDayOfMonthBefore, this.now))).thenReturn(payments);
    }


    @When("execute de detection of unusual expenses of user {string}")
    public void executeDeDetectionOfUnusualExpenses(String userId) {
        val service = new UnusualExpensesAlerterService(paymentsRepository, userRepository, notificationSender, calendar);
        service.execute(UserId.of(userId));
    }

    @Then("send a mail to {string} with subject {string} with this body")
    public void sendAMailWithThisMessage(String mail, String subject, String body) {
        verify(notificationSender).send(new Notification(mail, subject, body));
    }

}
