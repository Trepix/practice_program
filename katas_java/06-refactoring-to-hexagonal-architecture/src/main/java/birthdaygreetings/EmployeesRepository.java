package birthdaygreetings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

public class EmployeesRepository {

    private final String filename;

    public EmployeesRepository(String filename) {
        this.filename = filename;
    }

    List<Employee> getWhichIsHisBirthday(OurDate ourDate) throws IOException, ParseException {
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
    }
}
