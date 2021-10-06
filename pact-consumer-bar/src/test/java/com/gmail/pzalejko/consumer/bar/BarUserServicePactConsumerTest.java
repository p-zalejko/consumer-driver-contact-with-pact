package com.gmail.pzalejko.consumer.bar;

import org.apache.http.client.fluent.Request;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import com.jayway.jsonpath.JsonPath;

@ExtendWith(PactConsumerTestExt.class)
public class BarUserServicePactConsumerTest {

    static final String CONSUMER_NAME = "consumer-bar";
    static final String PROVIDER_NAME = "UserService";

    static final DslPart BODY = new PactDslJsonBody().numberType("id") // here we compare only type!
            .stringValue("name", "Frank") // here we compare type AND value!
            .stringValue("email", "bar@example.com"); // here we compare type AND value!

    @Pact(consumer = CONSUMER_NAME, provider = PROVIDER_NAME)
    public RequestResponsePact userExists(PactDslWithProvider builder) {
        // @formatter:off
        return builder
            .given("User 1 exists")
                .uponReceiving("validate 200 when the user exists")
                .method("GET")
                .path("/users/1")
            .willRespondWith()
                .status(200)
                .body(BODY)
           .toPact();
       // @formatter:on
    }

    @Pact(consumer = CONSUMER_NAME, provider = PROVIDER_NAME)
    public RequestResponsePact userDoesNotExist(PactDslWithProvider builder) {
        // @formatter:off
        return builder
            .given("User 2 does not exist")
                .uponReceiving("valid 404 when a user does not exist")
                .method("GET")
                .path("/users/2")
            .willRespondWith()
                .status(404)
           .toPact();
       // @formatter:on
    }

    @Test
    @PactTestFor(pactMethod = "userExists")
    public void testUserExists(MockServer mockServer) throws IOException {
        var httpResponse = Request.Get(mockServer.getUrl() + "/users/1").execute().returnResponse();
        var responseAsJson = JsonPath.parse(httpResponse.getEntity().getContent());

        assertThat(httpResponse.getStatusLine().getStatusCode()).isEqualTo(200);

        assertThat(responseAsJson.read("$.id", Long.class)).isNotNull();
        assertThat(responseAsJson.read("$.name", String.class)).isEqualTo("Frank");
        assertThat(responseAsJson.read("$.email", String.class)).isEqualTo("bar@example.com");
    }

    @Test
    @PactTestFor(pactMethod = "userDoesNotExist")
    public void testUserDoesNotExist(MockServer mockServer) throws IOException {
        var statusCode = Request.Get(mockServer.getUrl() + "/users/2")
                .execute()
                .returnResponse()
                .getStatusLine()
                .getStatusCode();

        assertThat(statusCode).isEqualTo(404);
    }
}
