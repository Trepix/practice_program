package birthdaygreetings.infrastructure.senders;

import birthdaygreetings.core.GreetingMessage;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GreetingsSender {

    private final String smtpHost;
    private final int smtpPort;
    private final String sender;

    public GreetingsSender(String smptHost, int smtpPort, String sender) {
        this.smtpHost = smptHost;
        this.smtpPort = smtpPort;
        this.sender = sender;
    }

    public void sendMessage(GreetingMessage greetingMessage) throws MessagingException {
        sendMessage(greetingMessage.subject(), greetingMessage.text(), greetingMessage.to());
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
