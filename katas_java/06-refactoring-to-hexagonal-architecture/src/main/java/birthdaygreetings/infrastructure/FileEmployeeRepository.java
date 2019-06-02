package birthdaygreetings.infrastructure;

import birthdaygreetings.Employee;
import birthdaygreetings.EmployeeRepository;
import birthdaygreetings.EmployeesNotRetriableException;
import birthdaygreetings.OurDate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

public class FileEmployeeRepository implements EmployeeRepository {

    private final String filename;

    public FileEmployeeRepository(String filename) {
        this.filename = filename;
    }

    @Override
    public List<Employee> getWhichIsHisBirthday(OurDate ourDate) throws EmployeesNotRetriableException {
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            skipHeader(in);

            String str;
            List<Employee> employeesThatIsHisBirthday = new LinkedList<>();
            while ((str = in.readLine()) != null) {
                String[] employeeData = str.split(", ");
                Employee employee = new Employee(employeeData[1], employeeData[0],
                        new OurDate(new SimpleDateFormat("yyyy/MM/dd").parse(employeeData[2])), employeeData[3]);
                if (employee.isBirthday(ourDate)) {
                    employeesThatIsHisBirthday.add(employee);
                }
            }
            return employeesThatIsHisBirthday;
        } catch (Exception e) {
            throw new EmployeesNotRetriableException(e);
        }
    }

    private void skipHeader(BufferedReader in) throws IOException {
        in.readLine();
    }
}
