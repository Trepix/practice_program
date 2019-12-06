package bank;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateHelper {

    public static LocalDate date(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
