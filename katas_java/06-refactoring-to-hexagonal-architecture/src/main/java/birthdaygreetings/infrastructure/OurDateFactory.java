package birthdaygreetings.infrastructure;

import birthdaygreetings.OurDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class OurDateFactory {

    public static OurDate create(String date) throws ParseException {
        return new OurDate(new SimpleDateFormat("yyyy/MM/dd").parse(date));
    }
}
