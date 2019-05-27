package birthdaygreetings;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface EmployeeRepository {
    List<Employee> getWhichIsHisBirthday(OurDate ourDate) throws IOException, ParseException;
}
