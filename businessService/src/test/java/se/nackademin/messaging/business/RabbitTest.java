package se.nackademin.messaging.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class RabbitTest {

    @Container
    private static RabbitMQContainer rabbit = new RabbitMQContainer("rabbitmq:3.9.5");

    RabbitAdmin rabbitAdmin;
    RabbitTemplate rabbitTemplate;

    @BeforeEach
    void setUp() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(rabbit.getContainerIpAddress(), rabbit.getMappedPort(5672));
        rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitTemplate = new RabbitTemplate(connectionFactory);
    }

    @Test
    void uppgift_1_skicka_och_ta_emot_ett_meddelande() {

        // Skapa en exhange
        // rabbitAdmin.declareExchange(new FanoutExchange("my-exchange-1"));
        // Skapa en queue
        // rabbitAdmin.declareQueue(new Queue("for-test-only-1"));
        // Skapa en binding
        // rabbitAdmin.declareBinding(new Binding("for-test-only-1", Binding.DestinationType.QUEUE, "my-exchange-1", "not-used-for-fanout", Map.of()));
        // Produce message på exchange
        // rabbitTemplate.convertAndSend("my-exchange-1", "", "Hej Hej");
        // Consume message på queue
        // Message message = rabbitTemplate.receive("for-test-only-1", 4000);
    }

    @Test
    void uppgift_2_skicka_och_ta_emot_ett_meddelande_på_fler_köer() {
        // Vi använder oss av en FanoutExchange dvs alla köer vi kopplar på får samma meddelanden
        // Skapa en FanoutExchange
        // Skapa två Queues med olika namn
        // Skapa en binding för varje queue till exchangen
        // Skicka ett meddelande
        // ta emot ett på varje kö och se att de är samma.
    }

    @Test
    void uppgift_3_skicka_och_ta_emot_ett_meddelande_på_olika_köer() {
        // En kö ska endast få de meddelanden som den är ämnad för.
        // Skapa två FanoutExchange med olika namn
        // Skapa två Queues med olika namn
        // Skapa en binding för varje queue till vardera exchange
        // Skicka ett meddelande på vardera exchange
        // ta emot ett på varje kö och se att de är olika.
    }

    @Test
    void uppgift_3_ta_emot_meddelanden_från_flera_exchanger() {
        // En kö kan få meddelanden från flera exchanges
        // Skapa två FanoutExchange med olika namn
        // Skapa en Queue
        // Skapa en binding för queue till vardera exchange
        // Skicka ett meddelande på vardera exchange
        // ta emot ett meddelande och se att det var första som skickades
        // ta emot ett meddelande och se att det var andra som skickades
    }
}
