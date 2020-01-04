package bankaccount.infrastructure;

import bankaccount.domain.Calendar;

import java.time.Clock;
import java.time.LocalDate;

import static java.time.LocalDate.now;

public class DefaultZoneCalendar implements Calendar {
    @Override
    public LocalDate date() {
        return now(Clock.systemDefaultZone());
    }
}
