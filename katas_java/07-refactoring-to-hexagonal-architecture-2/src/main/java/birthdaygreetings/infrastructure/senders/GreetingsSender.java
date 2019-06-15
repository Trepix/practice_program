package birthdaygreetings.infrastructure.senders;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;

public class GreetingsSender {

    public void sendMessage(Message msg) throws MessagingException {
        Transport.send(msg);
    }
}
