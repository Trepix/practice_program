package alert_service.notify;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class UserId {
    private final String id;

    private UserId(String id) {
        this.id = id;
    }

    public static UserId of(String id) {
        return new UserId(id);
    }
}
