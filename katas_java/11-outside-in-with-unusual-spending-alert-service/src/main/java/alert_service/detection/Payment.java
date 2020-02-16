package alert_service.detection;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@EqualsAndHashCode
public class Payment {
    private final Integer amount;
    private final String category;
    private final LocalDate date;

    public Payment(Integer amount, String category, LocalDate date) {
        this.amount = amount;
        this.category = category;
        this.date = date;
    }
}
