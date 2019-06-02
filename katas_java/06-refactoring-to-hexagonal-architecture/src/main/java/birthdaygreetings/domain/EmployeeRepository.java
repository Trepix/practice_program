package birthdaygreetings.domain;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> findWhoseBirthdayIsAt(OurDate ourDate) throws EmployeesNotRetrievableException;
}
