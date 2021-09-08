package se.nackademin.messaging.audit;

import org.awaitility.Awaitility;
import org.awaitility.Duration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.amqp.core.FanoutExchange;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@Testcontainers
@ContextConfiguration(initializers = AuditApplicationTest.Lab1ApplicationTestsContextInitializer.class)
@AutoConfigureMockMvc
class AuditApplicationTest {
    @Container
    private static RabbitMQContainer rabbit = new RabbitMQContainer("rabbitmq:3.9.5");
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ConnectionFactory connectionFactory;

    RabbitAdmin rabbitAdmin;

    @BeforeEach
    void setUp() {
        rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.declareExchange(new FanoutExchange("business-events"));
    }

    @Test
    public void canStartApp() {

    }

    @Test
    public void canPopulateAuditLog() throws Exception {
        final long accountId = 1L;
        final String data = "dsa";


        rabbitTemplate.convertAndSend("business-events", "" + accountId, new AuditEvent(accountId, data, "OPEN_ACCOUNT", Instant.now().toString()));

        Awaitility.await().atMost(Duration.FIVE_SECONDS).untilAsserted(() -> {
            final MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/audit"))
                    .andExpect(status().isOk())
                    .andReturn();
            final List<AuditEntry> logs = (List<AuditEntry>) mvcResult.getModelAndView().getModel().get("logs");
            assertEquals(1, logs.size());
        });
    }

    public static class Lab1ApplicationTestsContextInitializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {

            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
                    configurableApplicationContext,
                    "spring.rabbitmq.host=" + rabbit.getContainerIpAddress(), "spring.rabbitmq.port=" + rabbit.getMappedPort(5672)); // wiremock server

        }
    }

}