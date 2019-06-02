package birthdaygreetings;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> getWhichIsHisBirthday(OurDate ourDate) throws EmployeesNotRetrievableException;
}
