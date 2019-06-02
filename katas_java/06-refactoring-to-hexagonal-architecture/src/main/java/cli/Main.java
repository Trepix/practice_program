package cli;

import birthdaygreetings.application.BirthdayService;
import birthdaygreetings.infrastructure.FileEmployeeRepository;

import static birthdaygreetings.infrastructure.OurDateFactory.*;

public class Main {

    private static final String DATE = "2008/10/08";
    private static final String SMTP_HOST = "localhost";
    private static final int SMTP_PORT = 25;
    private static final String FILE_WITH_EMPLOYEES = "employee_data.txt";

    public static void main(String[] args) {
        BirthdayService service = new BirthdayService(new FileEmployeeRepository(FILE_WITH_EMPLOYEES));
        try {
            service.sendGreetings(createFromDateSeparatedBySlash(DATE), SMTP_HOST, SMTP_PORT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
