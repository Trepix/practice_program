package birthdaygreetings;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class BirthdayService {

    private final EmployeesRepository employeesRepository;

    public BirthdayService(String filename) {
        this.employeesRepository = new EmployeesRepository(filename);
    }

    public void sendGreetings(OurDate ourDate,
                              String smtpHost, int smtpPort) throws IOException, ParseException,
            AddressException, MessagingException {
        List<Employee> employeesThatIsHisBirthday = employeesRepository.getWhichIsHisBirthday(ourDate);

        for (Employee employee: employeesThatIsHisBirthday) {
            sendGreetings(smtpHost, smtpPort, employee);
        }
    }

    private void sendGreetings(String smtpHost, int smtpPort, Employee employee) throws MessagingException {
        String recipient = employee.getEmail();
        String body = "Happy Birthday, dear %NAME%!".replace("%NAME%",
                employee.getFirstName());
        String subject = "Happy Birthday!";
        sendMessage(smtpHost, smtpPort, "sender@here.com", subject,
                body, recipient);
    }

    private void sendMessage(String smtpHost, int smtpPort, String sender,
            String subject, String body, String recipient)
            throws AddressException, MessagingException {
        // Create a mail session
        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", "" + smtpPort);
        Session session = Session.getDefaultInstance(props, null);

        // Construct the message
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(sender));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(
                recipient));
        msg.setSubject(subject);
        msg.setText(body);

        // Send the message
        sendMessage(msg);
    }

    // made protected for testing :-(
    protected void sendMessage(Message msg) throws MessagingException {
        Transport.send(msg);
    }

    public static void main(String[] args) {
        BirthdayService service = new BirthdayService("employee_data.txt");
        try {
            service.sendGreetings(
                    new OurDate("2008/10/08"), "localhost", 25);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
