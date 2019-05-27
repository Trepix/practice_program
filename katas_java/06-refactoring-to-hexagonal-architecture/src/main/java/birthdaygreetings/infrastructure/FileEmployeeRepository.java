package birthdaygreetings.infrastructure;

import birthdaygreetings.Employee;
import birthdaygreetings.EmployeeRepository;
import birthdaygreetings.EmployeesNotAvailableException;
import birthdaygreetings.OurDate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

public class FileEmployeeRepository implements EmployeeRepository {

    private final String filename;

    public FileEmployeeRepository(String filename) {
        this.filename = filename;
    }

    @Override
    public List<Employee> getWhichIsHisBirthday(OurDate ourDate) throws EmployeesNotAvailableException, ParseException {


        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            String str = "";
            str = in.readLine(); // skip header

            List<Employee> employeesThatIsHisBirthday = new LinkedList<>();
            while ((str = in.readLine()) != null) {
                String[] employeeData = str.split(", ");
                Employee employee = new Employee(employeeData[1], employeeData[0],
                        employeeData[2], employeeData[3]);
                if (employee.isBirthday(ourDate)) {
                    employeesThatIsHisBirthday.add(employee);
                }
            }
            return employeesThatIsHisBirthday;
        } catch (IOException e) {
            throw new EmployeesNotAvailableException(e);
        }
    }
}
