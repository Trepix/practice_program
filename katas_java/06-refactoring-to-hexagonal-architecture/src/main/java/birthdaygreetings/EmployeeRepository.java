package birthdaygreetings;

import java.text.ParseException;
import java.util.List;

public interface EmployeeRepository {
    List<Employee> getWhichIsHisBirthday(OurDate ourDate) throws EmployeesNotAvailableException, ParseException;
}
