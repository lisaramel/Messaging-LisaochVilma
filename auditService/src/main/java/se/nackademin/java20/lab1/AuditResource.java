package se.nackademin.java20.lab1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AuditResource {
    private final static Logger LOG = LoggerFactory.getLogger(AuditResource.class);

    private final AuditLogRepository auditLog;

    public AuditResource(AuditLogRepository auditLog) {
        this.auditLog = auditLog;
    }

    @GetMapping("/audit")
    public String openAccount(Model model) {
        List<AuditEntry> all = auditLog.getAll();

        LOG.info("Found {} logs", all.size());
        model.addAttribute("logs", all);
        return "audit";
    }

}
