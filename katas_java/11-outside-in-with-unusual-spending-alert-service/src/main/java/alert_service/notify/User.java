package alert_service.notify;

public class User {

    private final UserId userId;
    private final String email;

    public User(UserId userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    public String email() {
        return this.email;
    }
}
