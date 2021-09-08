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

    public void notify(Event auditEvent) {
        LOG.info("Sending..");
       /*
       TODO: Uppgift 3:
          Vi är inne i audit loggern. Här är det menat att vi ska skicka iväg ett meddelande!
          Skicka iväg meddelandet med hjälp av rabbitTemplate och se till att testet från
          uppgift 2 är grönt!

          Börja med att titta lite snabbt i BankResource och se hur den här klassen används.

          I uppgift 1 registerade vi en message converter. Så länge vi har ett jackson-annoterat objekt
          Så kan vi skicka det rakt av! Det vi behöver berätta för rabbit är vilken exchange vi vill skicka den på
          Använd samma som ni skapade i uppgift 1
          En annan sak vi måste tillhandahålla är Routing key, vilket vi kan använda för att routa till rätt kö.
          I och med att vi använder FanoutExchange kommer den inte att användas dock.
        */
    }
}
