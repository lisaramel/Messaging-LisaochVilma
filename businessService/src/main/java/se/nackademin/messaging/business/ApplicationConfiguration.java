package se.nackademin.messaging.business;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;


@Configuration
@EnableScheduling
public class ApplicationConfiguration {

    /* TODO Uppgift 1: Konfigurera rabbitmq
    1. Producern ansvarar att det finns en exchange tillgänglig. Det är något som man oftast gör i rabbit själv.
    Dvs man kan i sitt deploy-skript eller med andra verktyg lägga till sin exchange.
    Vi ska dock göra det i kod för att snabbt komma igång.

    Kolla nedan så finns det en @Bean FanoutExchange som idag returnerar null.
    Fanout är en av flera strategier. Men fanout menas att alla Queues som är kopplade till den kommer få samma meddelanden
    Det vi behöver göra här är att returnera en FanoutExchange så kommer Spring prata med rabbitmq och sätta upp en
    sådan åt oss.

    2. Producern måste ha något som kommunicerar med rabbit. Om ni kommer ihåg hur vi gjorde med REST så använde vi en
    RestTemplate, i rabbit-världen finns det en RabbitTemplate som vi använder istället.

    Kolla nedan så har vi en @Bean RabbitTemplate som idag returnerar null.
    Se till att den returnerar en RabbitTemplate som är konfigurerad med rätt connectionFactory och message converter.

    Du vill kunna köra testet nedan	i DemoApplicationTests och få det grönt!

    @Test
	void contextLoads() {
	}
     */

    @Bean
    public AuditLogger paymentNotificationService(RabbitTemplate template) {
        return new AuditLogger(template);
    }

    @Bean
    FanoutExchange exchange() {
        return null;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory, final Jackson2JsonMessageConverter converter) {
       return null;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter(ObjectMapper objectMapper) {
        objectMapper.registerModule(new JavaTimeModule());
        return new Jackson2JsonMessageConverter(objectMapper);
    }


}
