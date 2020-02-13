package alert_service.notify;


import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class Notification {
    private final String email;
    private final String subject;
    private final String body;

    public Notification(String email, String subject, String body) {
        this.email = email;
        this.subject = subject;
        this.body = body;
    }
}
