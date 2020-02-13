package alert_service.notify;

public interface UserRepository {
    User getBy(UserId userId);
}
