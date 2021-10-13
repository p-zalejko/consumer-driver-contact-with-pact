package com.gmail.pzalejko.pactprovider.user;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import au.com.dius.pact.core.model.Interaction;
import au.com.dius.pact.core.model.Pact;
import au.com.dius.pact.provider.MessageAndMetadata;
import au.com.dius.pact.provider.PactVerifyProvider;
import au.com.dius.pact.provider.junit5.MessageTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.IgnoreNoPactsToVerify;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;

@IgnoreNoPactsToVerify
@Provider("UserService-user-created-event")
@PactBroker(host = "localhost", port = "9292")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserCreatedEventProviderTest {

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void testTemplate(Pact pact, Interaction interaction, PactVerificationContext context) {
        context.verifyInteraction();
    }

    @BeforeEach
    void before(PactVerificationContext context) {
        context.setTarget(new MessageTestTarget());
    }

    @PactVerifyProvider("a user created event")
    public MessageAndMetadata userCreatedTest() throws JsonProcessingException {
        var mapper = new ObjectMapper();
      
        var event = new UserCreatedEvent("1", "Frank", "bar@example.com");
        var eventAsBytes = mapper.writeValueAsString(event).getBytes();
        var headers = Map.of(
            "kafka_topic", "users",
            "Content-Type", "appkication/json"
        );

        return new MessageAndMetadata(eventAsBytes, headers);
    }

    static record UserCreatedEvent(String id, String name, String email) {
    }
}
