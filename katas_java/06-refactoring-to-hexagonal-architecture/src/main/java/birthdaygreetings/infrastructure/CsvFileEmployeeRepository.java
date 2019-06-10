package birthdaygreetings.infrastructure;

import birthdaygreetings.domain.Employee;
import birthdaygreetings.domain.EmployeeRepository;
import birthdaygreetings.domain.EmployeesNotRetrievableException;
import birthdaygreetings.domain.OurDate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

import static birthdaygreetings.infrastructure.OurDateFactory.createFromDateSeparatedBySlash;
import static java.util.stream.Collectors.toList;

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

            String row;
            List<Employee> employees = new LinkedList<>();
            while ((row = in.readLine()) != null) {
                Employee employee = createEmployeeFromRow(row);
                employees.add(employee);
            }
            return employees;
        } catch (Exception e) {
            throw new EmployeesNotRetrievableException(e);
        }
    }

    private void skipHeader(BufferedReader in) throws IOException {
        in.readLine();
    }

    private Employee createEmployeeFromRow(String row) throws ParseException {
        CsvEmployee dto = new CsvEmployee(row);
        return new Employee(dto.firstName(), dto.lastName(),
                createFromDateSeparatedBySlash(dto.birthdayDate()), dto.email());
    }

    private static class CsvEmployee {
        private final String[] employeeData;

        CsvEmployee(String row) {
            this.employeeData = row.split(", ");
        }

        String firstName() {
            return employeeData[1];
        }

        String lastName() {
            return employeeData[0];
        }

        String birthdayDate() {
            return employeeData[2];
        }

        String email() {
            return employeeData[3];
        }

    }
}
