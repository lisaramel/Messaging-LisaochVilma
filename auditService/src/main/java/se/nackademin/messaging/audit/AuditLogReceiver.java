package se.nackademin.messaging.audit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuditLogReceiver {
    private static final Logger LOG = LoggerFactory.getLogger(AuditLogReceiver.class);

    @Autowired
    AuditLogRepository auditLogRepository;

    @RabbitListener(queues = "audit-log")
    public void receiveMessage(AuditEvent event) {
        LOG.info("Received message! {}", event);
        /* TODO: Uppgift 2: Spara eventet!
            För att lyssna på events räcker det med @RabbitListner!

            När vi får in ett event så vill vi spara det i auditLogRepository.

            Översätt DTOn AuditEvent till vårat domänobjekt AuditEntry och spara det i databasen.

            För att se att allt fungerar kör testet AuditApplicationTest
         */
    }
}
