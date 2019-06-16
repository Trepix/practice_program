package test.birthdaygreetings.application;

import birthdaygreetings.application.BirthdayService;
import birthdaygreetings.core.OurDate;
import birthdaygreetings.infrastructure.repositories.FileEmployeesRepository;
import birthdaygreetings.infrastructure.senders.GreetingsSender;
import org.junit.Before;
import org.junit.Test;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static test.birthdaygreetings.helpers.OurDateFactory.ourDateFromString;

public class BirthdayServiceAcceptanceTest {

    private static final int SMTP_PORT = 25;
    private static final String FROM = "sender@here.com";
    private static final String EMPLOYEES_FILE_PATH = "src/test/resources/employee_data.txt";
    private String SMTP_HOST = "localhost";
    private List<Message> messagesSent;
    private BirthdayService service;

    @Before
    public void setUp() throws Exception {
        messagesSent = new ArrayList<>();
        GreetingsSender greetingsSender = new GreetingsSender(SMTP_HOST) {
            @Override
            public void sendMessage(Message msg) throws MessagingException {
                messagesSent.add(msg);
            }
        };
        service = new BirthdayService( greetingsSender, new FileEmployeesRepository(EMPLOYEES_FILE_PATH));
    }

    @Test
    public void baseScenario() throws Exception {
        OurDate today = ourDateFromString("2008/10/08");

        service.sendGreetings(today, SMTP_HOST, SMTP_PORT, FROM);

        assertEquals("message not sent?", 1, messagesSent.size());
        Message message = messagesSent.get(0);
        assertEquals("Happy Birthday, dear John!", message.getContent());
        assertEquals("Happy Birthday!", message.getSubject());
        assertEquals(1, message.getAllRecipients().length);
        assertEquals("john.doe@foobar.com", message.getAllRecipients()[0].toString());
    }

    @Test
    public void willNotSendEmailsWhenNobodysBirthday() throws Exception {
        OurDate today = ourDateFromString("2008/01/01");

        service.sendGreetings(today, SMTP_HOST, SMTP_PORT, FROM);

        assertEquals(0, messagesSent.size());
    }
}
