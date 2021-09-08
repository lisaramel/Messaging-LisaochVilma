package se.nackademin.messaging.business;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankResource {
    private final AuditLogger auditLogger;

    public BankResource(AuditLogger auditLogger) {
        this.auditLogger = auditLogger;
    }

    @PostMapping("/openAccount/{accountId}")
    public ResponseEntity<String> open(@PathVariable("accountId") long accountId) {
        auditLogger.notify(new OpenAccountEvent(accountId));
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/deposit/{accountId}")
    public ResponseEntity<String> deposit(@PathVariable("accountId") String accountId, @RequestParam("amount") String amount) {
        auditLogger.notify(DepositEvent.build(Long.parseLong(accountId), Long.parseLong(amount)));
        return ResponseEntity.accepted().build();
    }
}
