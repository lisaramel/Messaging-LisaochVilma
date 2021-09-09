package se.nackademin.messaging.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
public class RabbitTest {

    @Container
    private static RabbitMQContainer rabbit = new RabbitMQContainer("rabbitmq:3.9.5");

    // RabbitAdmin är ett bra hjälpverktyg för tex tester där vi kan programatiskt skapa köer etc.
    RabbitAdmin rabbitAdmin;

    // RabbitTemplate är precis som RestTemplate vi har använt tidigare. Ett enkelt sätt att interagera med rabbit
    // Du kan tex. använda .receive för att läsa meddelanden och .convertAndSend för att serialisera och skicka meddelanden.
    RabbitTemplate rabbitTemplate;

    @BeforeEach
    void setUp() {
        // En connection factory är ett sätt att beskriva hur vi ska connecta till rabbit. I detta fall behöver vi
        // bara tillhandahålla en ip-address och en port.
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(rabbit.getContainerIpAddress(), rabbit.getMappedPort(5672));
        rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitTemplate = new RabbitTemplate(connectionFactory);
    }

    @Test
    void uppgift_1_skicka_och_ta_emot_ett_meddelande() {
        // Kommer ni ihåg från föreläsningen. En exchange är dit vi publicerar saker. På en exchange kan vi koppla en
        // eller flera Queues, köer som consumers kan beta av. För att koppla ihop en Queue med en Exchange skapar vi
        // en Binding. Låt oss skapa dessa i detta test!

        // Skapa en exhange
        // rabbitAdmin.declareExchange(new FanoutExchange("my-exchange-1"));
        // Skapa en queue
        // rabbitAdmin.declareQueue(new Queue("for-test-only-1"));
        // Skapa en binding
        // rabbitAdmin.declareBinding(new Binding("for-test-only-1", Binding.DestinationType.QUEUE, "my-exchange-1", "routing-key-is-not-used-for-fanout-but-required", Map.of()));
        // Produce message på exchange
        // rabbitTemplate.convertAndSend("my-exchange-1", "", "Hej Hej");
        // Consume message på queue
        // Message message = rabbitTemplate.receive("for-test-only-1", 4000);
        // assertEquals(new String(message.getBody()), "Hej Hej");
    }

    @Test
    void uppgift_2_skicka_och_ta_emot_ett_meddelande_på_fler_köer() {
        // Vi använder oss av en FanoutExchange dvs alla köer vi kopplar på får samma meddelanden.
        // Vi ska testa det genom att koppla två köer till samma exchage och säkerställa att meddelandet kommer
        // fram till båda köerna

        // Skapa en FanoutExchange
        // Skapa två Queues med olika namn
        // Skapa en binding för varje queue till exchangen
        // Skicka ett meddelande
        // ta emot ett på varje kö och se att de är samma.
        // asserta att meddelandet har kommit fram till båda köerna
        // asserta att meddelandet är det samma som skickades
    }

    @Test
    void uppgift_3_skicka_och_ta_emot_ett_meddelande_på_olika_köer() {
        // En kö ska endast få de meddelanden som den är ämnad för. Vi ska testa det genom att
        // skapa två exchanges och två köer och koppla en kö till vardera exchange. Nu kan vi säkerställa
        // att om vi skickar ett meddelande till en exchange så ska det bara dyka upp i en kö.

        // Skapa två FanoutExchange med olika namn
        // Skapa två Queues med olika namn
        // Skapa en binding för varje queue till vardera exchange
        // Skicka ett meddelande på vardera exchange
        // ta emot ett på varje kö och se att de är olika.
        // asserta att detta är sant
    }

    @Test
    void uppgift_4_ta_emot_meddelanden_från_flera_exchanger() {
        // En kö kan få meddelanden från flera exchanges. Vi ska testa det genom att skapa två exchanges och en kö
        // sen ska vi koppla denna kö till båda exchangesarna. Vi kan nu säkerställa att om jag skickar ett meddelande
        // till vardera exchange så ska jag ha fått bägge på min kö.

        // Skapa två FanoutExchange med olika namn
        // Skapa en Queue
        // Skapa en binding för queue till vardera exchange
        // Skicka ett meddelande på vardera exchange
        // ta emot ett meddelande och se att det var första som skickades
        // ta emot ett meddelande och se att det var andra som skickades
        // asserta att detta är sant.
    }
}
