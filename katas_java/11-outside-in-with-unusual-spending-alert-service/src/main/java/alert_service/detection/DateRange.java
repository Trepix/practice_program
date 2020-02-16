package alert_service.detection;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@EqualsAndHashCode
public class DateRange {
    private final LocalDate start;
    private final LocalDate end;

    public DateRange(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }
}
