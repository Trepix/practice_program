package alert_service;

public interface PaymentsRepository {
    Payments getByUserId(UserId userId);
}
