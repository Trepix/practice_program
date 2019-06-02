package cli;

import birthdaygreetings.BirthdayService;
import birthdaygreetings.infrastructure.FileEmployeeRepository;
import birthdaygreetings.infrastructure.OurDateFactory;

public class Main {
    public static void main(String[] args) {
        BirthdayService service = new BirthdayService(new FileEmployeeRepository("employee_data.txt"));
        try {
            service.sendGreetings(
                    OurDateFactory.createFromDateSeparatedBySlash("2008/10/08"), "localhost", 25);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
