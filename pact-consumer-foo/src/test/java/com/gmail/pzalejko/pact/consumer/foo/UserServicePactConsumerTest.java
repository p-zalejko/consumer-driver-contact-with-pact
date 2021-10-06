package com.gmail.pzalejko.pact.consumer.foo;

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
public class UserServicePactConsumerTest {

    static final DslPart BODY = new PactDslJsonBody()
            .numberType("id") // here we compare only type!
            .stringValue("name", "Frank"); // here we compare type AND value!

    @Pact(consumer = "consumer-foo", provider = "UserService")
    public RequestResponsePact userExists(PactDslWithProvider builder) {
        // @formatter:off
        return builder
            .given("valid date received from provider")
                .uponReceiving("valid date from provider")
                .method("GET")
                .path("/users/1")
            .willRespondWith()
                .status(200)
                .body(BODY)
           .toPact();
       // @formatter:on
    }

    @Pact(consumer = "consumer-a", provider = "UserService")
    public RequestResponsePact userDoesNotExist(PactDslWithProvider builder) {
        // @formatter:off
        return builder
            .given("valid date received from provider")
                .uponReceiving("valid date from provider")
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

        assertThat(httpResponse.getStatusLine().getStatusCode()).isEqualTo(200);
        assertThat(JsonPath.read(httpResponse.getEntity().getContent(), "$.name").toString()).isEqualTo("Frank");
    }

    @Test
    @PactTestFor(pactMethod = "userDoesNotExist")
    public void testUserDoesNotExist(MockServer mockServer) throws IOException {
        var httpResponse = Request.Get(mockServer.getUrl() + "/users/2").execute().returnResponse();

        assertThat(httpResponse.getStatusLine().getStatusCode()).isEqualTo(404);
    }
}
