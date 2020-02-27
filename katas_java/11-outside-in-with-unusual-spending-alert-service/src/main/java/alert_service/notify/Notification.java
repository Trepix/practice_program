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

    public static NotificationBuilder to(String email) {
        return new NotificationBuilder(email);
    }

    public static class NotificationBuilder {

        public static final String endline = "\n";

        private String email;
        private String subject;
        private String body;

        NotificationBuilder(String email) {
            this.email = email;
        }

        public NotificationBuilder withSubject(String subject) {
            this.subject = subject;
            return this;
        }

        public NotificationBuilder withBody(String... bodyLines) {
            this.body = String.join("", bodyLines);
            return this;
        }

        public Notification build() {
            return new Notification(email, subject, body);
        }

    }

}
