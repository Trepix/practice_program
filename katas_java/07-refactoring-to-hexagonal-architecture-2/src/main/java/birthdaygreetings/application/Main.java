package birthdaygreetings.application;

import birthdaygreetings.core.OurDate;
import birthdaygreetings.infrastructure.repositories.FileEmployeesRepository;
import birthdaygreetings.infrastructure.senders.SmtpGreetingsSender;

import java.util.Date;

public class Main {

    private static final String EMPLOYEES_FILE_PATH = "employee_data.txt";
    private static final String SENDER_EMAIL_ADDRESS = "sender@here.com";
    private static final String HOST = "localhost";
    private static final int SMTP_PORT = 25;

    public static void main(String[] args) {
        BirthdayService service = new BirthdayService(
                new SmtpGreetingsSender(HOST, SMTP_PORT, SENDER_EMAIL_ADDRESS),
                new FileEmployeesRepository(EMPLOYEES_FILE_PATH));
        try {
            OurDate today = new OurDate(new Date());
            service.sendGreetings(today);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
