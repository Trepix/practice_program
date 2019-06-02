package birthdaygreetings.test;

import static org.junit.Assert.*;

import birthdaygreetings.domain.Employee;

import birthdaygreetings.infrastructure.OurDateFactory;
import org.junit.Test;

import java.text.ParseException;

public class EmployeeTest {

    @Test
    public void testBirthday() throws Exception {
        Employee employee = createEmployee("foo", "bar", "1990/01/31", "a@b.c");
        assertFalse("not his birthday",
                employee.isBirthday(OurDateFactory.createFromDateSeparatedBySlash("2008/01/30")));
        assertTrue("his birthday",
                employee.isBirthday(OurDateFactory.createFromDateSeparatedBySlash("2008/01/31")));
    }

    @Test
    public void equality() throws Exception {
        Employee base = createEmployee("First", "Last", "1999/09/01",
                "first@last.com");
        Employee same = createEmployee("First", "Last", "1999/09/01",
                "first@last.com");
        Employee different = createEmployee("First", "Last", "1999/09/01",
                "boom@boom.com");

        assertFalse(base.equals(null));
        assertFalse(base.equals(""));
        assertTrue(base.equals(same));
        assertFalse(base.equals(different));
    }

    private Employee createEmployee(String firstName, String lastName, String birthDate, String email) throws ParseException {
        return new Employee(firstName, lastName, OurDateFactory.createFromDateSeparatedBySlash(birthDate), email);
    }
}
