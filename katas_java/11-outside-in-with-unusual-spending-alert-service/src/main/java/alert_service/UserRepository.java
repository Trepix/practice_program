package alert_service;

public interface UserRepository {
    User getBy(UserId userId);
}
