package se.nackademin.java20.lab1;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class AuditLogRepository {
    private final AtomicReference<List<AuditEntry>> db;

    public AuditLogRepository() {
        db = new AtomicReference<>(new ArrayList<>());
    }

    public List<AuditEntry> getAll() {
        return List.copyOf(db.get());
    }

    public void add(AuditEntry auditEntry) {
        db.getAndUpdate(l -> {
            l.add(auditEntry);
            return l;
        });
    }
}
