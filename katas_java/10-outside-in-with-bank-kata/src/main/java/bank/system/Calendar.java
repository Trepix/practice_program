package bank.system;

import java.time.Clock;
import java.time.LocalDate;

import static java.time.LocalDate.now;

public class Calendar {
    public LocalDate date() {
        return now(Clock.systemDefaultZone());
    }
}
