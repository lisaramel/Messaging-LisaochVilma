package se.nackademin.messaging.business;

import java.time.Instant;

public class OpenAccountEvent extends Event {
    public OpenAccountEvent(long accountId) {
        super(AuditEventType.OPEN_ACCOUNT, accountId, Instant.now(), "");
    }
}
