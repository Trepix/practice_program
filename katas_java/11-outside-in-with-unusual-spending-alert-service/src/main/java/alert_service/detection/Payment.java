package alert_service.detection;

import lombok.ToString;

@ToString
public class Payment {
    private final Integer amount;
    private final String category;
    private final String date;

    public Payment(Integer amount, String category, String date) {
        this.amount = amount;
        this.category = category;
        this.date = date;
    }
}
