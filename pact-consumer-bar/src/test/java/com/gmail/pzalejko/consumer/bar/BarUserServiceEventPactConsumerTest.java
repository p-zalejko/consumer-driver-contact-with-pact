package com.gmail.pzalejko.consumer.bar;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import au.com.dius.pact.consumer.MessagePactBuilder;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.consumer.junit5.ProviderType;
import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.core.model.messaging.Message;
import au.com.dius.pact.core.model.messaging.MessagePact;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "UserService-user-created-event", providerType = ProviderType.ASYNCH)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BarUserServiceEventPactConsumerTest {

    // it's a different contract! not the same as for the REST API
    static final String CONSUMER_NAME = "consumer-bar-user-creared-event";

    @Autowired
    UserCreatedEventConsumer userCreatedEventConsumer;

    @Pact(consumer = CONSUMER_NAME)
    MessagePact userCreated(MessagePactBuilder builder) {
        var body = new PactDslJsonBody();
        body.stringType("id", "1");
        body.stringType("name", "Frank");
        body.stringType("email", "bar@example.com");

        var metadata = new HashMap<String, Object>();
        metadata.put("Content-Type", "application/json");
        metadata.put("kafka_topic", "users");

        return builder.expectsToReceive("a user created event").withMetadata(metadata).withContent(body).toPact();
    }

    @Test
    @PactTestFor(pactMethod = "userCreated")
    void userCreatedEvent(List<Message> messages) throws JsonMappingException, JsonProcessingException {
        var mapper = new ObjectMapper();
        var event = mapper.readValue(messages.get(0).contentsAsString(), UserCreatedEvent.class);

        assertDoesNotThrow(() -> userCreatedEventConsumer.handle(event));
    }

}
