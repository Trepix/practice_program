package test.birthdaygreetings.infrastructure.senders;

import birthdaygreetings.core.Employee;
import birthdaygreetings.core.GreetingMessage;
import birthdaygreetings.core.GreetingsSender;
import birthdaygreetings.infrastructure.senders.SmtpGreetingsSender;
import org.junit.Test;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SmtpGreetingsSenderTest {

    private static final String SMTP_HOST = "localhost";
    private static final int SMTP_PORT = 25;
    private static final String FROM = "sender@kata.com";
    private LinkedList<Message> messagesSent;

    @Test
    public void sending_greetings_by_email() throws IOException, MessagingException {
        messagesSent = new LinkedList<>();
        GreetingsSender greetingsSender = new SmtpGreetingsSender(SMTP_HOST, SMTP_PORT, FROM) {
            @Override
            protected void sendMessage(Message message) {
                messagesSent.add(message);
            }
        };

        String email = "john@employee.com";
        String name = "John";
        String lastName = "Doe";
        List<Employee> employees = singletonList(new Employee(name, lastName, null, email));
        List<GreetingMessage> messages = GreetingMessage.generateForSome(employees);

        greetingsSender.send(messages);

        assertThat("message is sent", messagesSent.size(), is(1));
        assertThatMessageIsCorrectlyBuild(name, email);
    }

    private void assertThatMessageIsCorrectlyBuild(String name, String email) throws IOException, MessagingException {
        Message message = messagesSent.getFirst();
        assertThat(message.getSubject(), is("Happy Birthday!"));
        assertThat(message.getContent(), is("Happy Birthday, dear " + name + "!"));
        assertThat("is only sent to one recipient", message.getAllRecipients().length, is(1));
        assertThat(message.getAllRecipients()[0].toString(), is(email));
    }
}

