package birthdaygreetings.infrastructure;

import birthdaygreetings.domain.OurDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class OurDateFactory {

    public static OurDate createFromDateSeparatedBySlash(String date) throws ParseException {
        return new OurDate(new SimpleDateFormat("yyyy/MM/dd").parse(date));
    }
}
