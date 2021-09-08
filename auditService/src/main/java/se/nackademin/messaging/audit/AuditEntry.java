package se.nackademin.messaging.audit;

import java.time.Instant;

public class AuditEntry {
    private final AuditType type;
    private final long accountId;
    private final Instant timestamp;
    private final String data;

    public AuditEntry(AuditType type, long accountId, Instant timestamp, String data) {
        this.type = type;
        this.accountId = accountId;
        this.timestamp = timestamp;
        this.data = data;
    }

    public enum AuditType {
        Open,
        Deposit
    }

    public AuditType getType() {
        return type;
    }

    public long getAccountId() {
        return accountId;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getData() {
        return data;
    }
}


