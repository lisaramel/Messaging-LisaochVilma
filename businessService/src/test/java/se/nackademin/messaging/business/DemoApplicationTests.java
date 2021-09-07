package se.nackademin.messaging.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@Testcontainers
@ContextConfiguration(initializers = DemoApplicationTests.Lab1ApplicationTestsContextInitializer.class)
@AutoConfigureMockMvc
class DemoApplicationTests {

    @Container
    private static RabbitMQContainer rabbit = new RabbitMQContainer("rabbitmq:3.9.5");

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Autowired
    ConnectionFactory connectionFactory;

    RabbitAdmin rabbitAdmin;

    @BeforeEach
    void setUp() {
        rabbitAdmin = new RabbitAdmin(connectionFactory);
        // TODO: uppfift 2.a
        // Dags att konfa vår miljö, vi har skapat en exchange men vi behöver en queue så vi kan testa mot
        // Skapa en queue och en binding till den exchange vi skapade uppgift 1

    }

    @Test
    void shouldSendCreatedOnPaymentCreated() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/openAccount/1234")).andExpect(status().is2xxSuccessful());
        /*
		TODO: Uppgift 2.b:
            Consume message från din kö du skapade i before.
            Testet kommer att fallera då vi inte har implemeterat vår producer.
            Leta efter uppgift 3.
		 */

    }

    public static class Lab1ApplicationTestsContextInitializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {

            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
                    configurableApplicationContext,
                    "spring.rabbitmq.host=" + rabbit.getContainerIpAddress(), "spring.rabbitmq.port=" + rabbit.getMappedPort(5672));

        }
    }
}
