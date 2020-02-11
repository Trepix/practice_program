package alert_service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {
    public static Date date(String date) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }
    }
}