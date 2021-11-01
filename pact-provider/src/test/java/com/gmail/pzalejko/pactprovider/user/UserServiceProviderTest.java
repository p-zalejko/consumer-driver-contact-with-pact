package com.gmail.pzalejko.pactprovider.user;

import static org.mockito.ArgumentMatchers.anyLong;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.IgnoreNoPactsToVerify;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;

@IgnoreNoPactsToVerify
@Provider("UserService")
@PactBroker(host = "localhost", port = "9292")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceProviderTest {

    @LocalServerPort
    int port;

    @MockBean
    UserService userService;

    @BeforeEach
    void setup(PactVerificationContext context) {
        context.setTarget(new HttpTestTarget("localhost", port));
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void verifyPact(PactVerificationContext context) {
        context.verifyInteraction();
    }














    /*

    @State("User 1 exists")
    public void testGetUser1() {
        var user = new User(1L, "Frank", "bar@example.com");
        Mockito.when(userService.findUserById(1L)).thenReturn(Optional.of(user));
    }

    @State({ "User 2 does not exist", "User 20 does not exist" })
    public void testGetUserThatDosNotExist() {
        Mockito.when(userService.findUserById(1L)).thenReturn(Optional.empty());
    }

    @State("User 10 exists")
    public void testGetUser10() {
        var user = new User(1L, "Frank", "bar@example.com");
        Mockito.when(userService.findUserById(anyLong())).thenReturn(Optional.of(user));
    }

    */

}
