package se.nackademin.messaging.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class AuditLogger {
    private static final Logger LOG = LoggerFactory.getLogger(AuditLogger.class);
    private RabbitTemplate template;

    public AuditLogger(RabbitTemplate template) {
        this.template = template;
    }

    public void notify(AuditEvent auditEvent) {
        LOG.info("Sending..");
       /*
       Uppgift 3:
          Vi är inne i audit loggern. Här är det menat att vi ska skicka iväg ett meddelande!
          Skicka iväg meddelandet med hjälp av rabbitTemplate och se till att testet från
          uppfigt 2 är grönt!
        */
    }
}
