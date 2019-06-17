package birthdaygreetings.infrastructure.senders;

import birthdaygreetings.core.CannotSendGreetingsException;
import birthdaygreetings.core.GreetingMessage;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;

public class SmtpGreetingsSender implements GreetingsSender {

    private final String smtpHost;
    private final int smtpPort;
    private final String sender;

    public SmtpGreetingsSender(String smptHost, int smtpPort, String sender) {
        this.smtpHost = smptHost;
        this.smtpPort = smtpPort;
        this.sender = sender;
    }

    @Override
    public void send(List<GreetingMessage> messages) {
        for (GreetingMessage message : messages) {
            this.sendMessage(message);
        }
    }

    private void sendMessage(GreetingMessage greetingMessage) {
        try {
            sendMessage(greetingMessage.subject(), greetingMessage.text(), greetingMessage.to());
        }
        catch (MessagingException e) {
            throw new CannotSendGreetingsException("", e);
        }

    }

    private void sendMessage(String subject, String body, String recipient)
            throws MessagingException {
        // Create a mail session
        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.host", this.smtpHost);
        props.put("mail.smtp.port", "" + this.smtpPort);
        Session session = Session.getDefaultInstance(props, null);

        // Construct the message
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(this.sender));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(
                recipient));
        msg.setSubject(subject);
        msg.setText(body);

        // Send the message
        this.sendMessage(msg);
    }

    protected void sendMessage(Message msg) throws MessagingException {
        Transport.send(msg);
    }
}
