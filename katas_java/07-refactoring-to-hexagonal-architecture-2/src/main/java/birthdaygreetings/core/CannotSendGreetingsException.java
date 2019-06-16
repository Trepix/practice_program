package birthdaygreetings.core;

public class CannotSendGreetingsException extends RuntimeException {
    public CannotSendGreetingsException(String cause, Exception exception) {
        super(cause, exception);
    }
}
