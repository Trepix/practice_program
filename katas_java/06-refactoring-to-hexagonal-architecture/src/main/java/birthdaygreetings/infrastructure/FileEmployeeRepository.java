package birthdaygreetings.infrastructure;

import birthdaygreetings.domain.Employee;
import birthdaygreetings.domain.EmployeeRepository;
import birthdaygreetings.domain.EmployeesNotRetrievableException;
import birthdaygreetings.domain.OurDate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class FileEmployeeRepository implements EmployeeRepository {

    private final String filename;

    public FileEmployeeRepository(String filename) {
        this.filename = filename;
    }

    @Override
    public List<Employee> findWhoseBirthdayIsAt(OurDate date) throws EmployeesNotRetrievableException {
        return findEmployees(date);
    }

    private List<Employee> findEmployees(OurDate date) throws EmployeesNotRetrievableException {
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            skipHeader(in);

            String str;
            List<Employee> employeesWhoseBirthdayIsAtDate = new LinkedList<>();
            while ((str = in.readLine()) != null) {
                String[] employeeData = str.split(", ");
                Employee employee = new Employee(employeeData[1], employeeData[0],
                        OurDateFactory.createFromDateSeparatedBySlash(employeeData[2]), employeeData[3]);
                if (employee.isBirthday(date)) {
                    employeesWhoseBirthdayIsAtDate.add(employee);
                }
            }
            return employeesWhoseBirthdayIsAtDate;
        } catch (Exception e) {
            throw new EmployeesNotRetrievableException(e);
        }
    }

    private void skipHeader(BufferedReader in) throws IOException {
        in.readLine();
    }
}
