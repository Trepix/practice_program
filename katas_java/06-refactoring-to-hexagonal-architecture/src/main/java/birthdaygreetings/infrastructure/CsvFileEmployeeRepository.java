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

import static birthdaygreetings.infrastructure.OurDateFactory.*;
import static java.util.stream.Collectors.*;

public class CsvFileEmployeeRepository implements EmployeeRepository {

    private final String filename;

    public CsvFileEmployeeRepository(String filename) {
        this.filename = filename;
    }

    @Override
    public List<Employee> findWhoseBirthdayIsAt(OurDate date) throws EmployeesNotRetrievableException {
        return getAllEmployees()
                .stream()
                .filter(x -> x.isBirthday(date))
                .collect(toList());
    }

    private List<Employee> getAllEmployees() throws EmployeesNotRetrievableException {
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            skipHeader(in);

            String str;
            List<Employee> allEmployees = new LinkedList<>();
            while ((str = in.readLine()) != null) {
                String[] employeeData = str.split(", ");
                Employee employee = new Employee(employeeData[1], employeeData[0],
                        createFromDateSeparatedBySlash(employeeData[2]), employeeData[3]);
                allEmployees.add(employee);
            }
            return allEmployees;
        } catch (Exception e) {
            throw new EmployeesNotRetrievableException(e);
        }
    }

    private void skipHeader(BufferedReader in) throws IOException {
        in.readLine();
    }
}
