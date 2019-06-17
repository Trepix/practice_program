package birthdaygreetings.infrastructure.senders;

import birthdaygreetings.core.GreetingMessage;

import java.util.List;

public interface GreetingsSender {
    void send(List<GreetingMessage> messages);
}
