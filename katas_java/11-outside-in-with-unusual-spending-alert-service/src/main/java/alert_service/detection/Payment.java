package alert_service.detection;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;
import java.time.Month;

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

    boolean wasMakeIn(Month month) {
        return date.getMonth().equals(month);
    }

    String category() {
        return this.category;
    }

    int amount() {
        return this.amount;
    }
}
