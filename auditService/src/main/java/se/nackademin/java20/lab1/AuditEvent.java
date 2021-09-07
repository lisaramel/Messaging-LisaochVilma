package se.nackademin.java20.lab1;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AuditEvent {
    @JsonProperty
    public long accountId;
    @JsonProperty
    public String data;
    @JsonProperty
    public String type;
    @JsonProperty
    public String timestamp;

    @JsonCreator
    public AuditEvent(@JsonProperty("accountId") long accountId, @JsonProperty("data") String data, @JsonProperty("type") String type, @JsonProperty("timestamp") String timestamp) {
        this.accountId = accountId;
        this.data = data;
        this.type = type;
        this.timestamp = timestamp;
    }

    public long getAccountId() {
        return accountId;
    }

    public String getData() {
        return data;
    }

    public String getType() {
        return type;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "AuditEvent{" +
                "accountId=" + accountId +
                ", data='" + data + '\'' +
                ", type='" + type + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
