package se.nackademin.messaging.business;

import java.time.Instant;

public class DepositEvent extends AuditEvent {
    private DepositEvent(long accountId, String data) {
        super(AuditEventType.DEPOSIT, accountId, Instant.now(), data);
    }

    public static DepositEvent build(long accountId, long amount) {
        return new DepositEvent(accountId, "Deposit amount of " + amount);
    }
}
