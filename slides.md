# Consumer-Driven Contract

### with Pact

Note: 
* who knows?
* who uses?
* who knows pact?

---

## The problem

![title](assets/img/simple-rest.png)

Note:
* few services
* one team or maybe two?

---

## The problem

![title](assets/img/advanced-rest.png)

Note:
* many services, many APIs
* many teams, different release cycles (CI/CD)
* a lot of communication between teams (TALKS!)


---

## The problem

![title](assets/img/advanced-rest-with-events.png)


---


## CDC to the rescue

![title](assets/img/pact-summary.png)

[source](https://docs.pact.io/)

Note:
* why - many services, many APIs
* why - many teams, different release cycles (CI/CD)
* consumer creates the contract
* consumer tests the contract
* providers tests the contract as well
* both sides test the contract

---

## Consumer-Driven Contract
### Benefits

* isolation
* simplified end-to-end tests (lack of e2e?)
* feedback time
* stability
* well-fittedness
* reveal unused interfaces

[read more here](https://reflectoring.io/7-reasons-for-consumer-driven-contracts/)

---

## Consumer-Driven Contract
### When it doesn't fit

* public APIs for unknown consumers
* we have very few services (one, two...) 

Note:
* unknown consumers cannot define a contract
* CDC tests explicit consumer's contracts!
* few services - one team, simple f2f communication enough?
* maybe it's just a UI <> back-end app (one consumer for the API)

---

## Pact
### https://pact.io/

> Fast, easy and reliable testing for integrating web apps, APIs and microservices


Note:
so far we've seen only theory, it's time for real work ;)
 

---

## Consumer

* defines the contract (expectations)


Note:
* what the consumer needs
* contract -> matchery (type vs value) 

---


## Contract

```JSON
{
  "consumer": {
    "name": "consumer-bar"
  },
  "interactions": [
    {
      "description": "validate HTTP 200 when the user exists",
      "providerStates": [
        {
          "name": "User 1 exists"
        }
      ],
      "request": {
        "method": "GET",
        "path": "/users/1"
      },
      "response": {
        "body": {
          "email": "bar@example.com",
          "id": 1,
          "name": "Frank"
        },
        ...
```

NOTE:
* show the file later (during a live demo)


---

## Provider

* fulfils the contract
* "signs up the contract"

---

## Broker

* centralized contract registry
* keeps validation results
* allows to determine if contracts are met
* visualises the relationships between services

Note:
- helps with deployment
- allows to check which version can be deplpyed!
- best practices: git hash, branch, environment name, etc.

---

## DEMO TIME!


[The source code is on GitHub](https://github.com/p-zalejko/consumer-driver-contact-with-pact)

Note:
- an example app will be extremely boring
- the intention is to focus on Pact, not on the example app...  

---

## Pending contracts

![title](assets/img/pending.png)

---

## Worth reading

* [Pactflow](https://pactflow.io/)
* [Not only Pact](https://docs.pact.io/getting_started/comparisons/)
* [Not only Java](https://docs.pact.io/implementation_guides/cli/)
* [Spring Cloud Contract and Pact](https://cloud.spring.io/spring-cloud-static/spring-cloud-contract/2.2.0.M2/reference/html/howto.html#how-to-use-pact-broker)
* [Pending contracts](https://docs.pact.io/pact_broker/advanced_topics/pending_pacts/)
* [WIP contracts](https://docs.pact.io/pact_broker/advanced_topics/wip_pacts/)
* [Versioning and tags!](https://docs.pact.io/getting_started/versioning_in_the_pact_broker/)
* [Events](https://blog.testproject.io/2020/06/03/event-driven-architecture-how-to-perform-contract-testing-in-kafka-pubsub/)
* [CI/CD is a challenge](https://docs.pact.io/pact_nirvana/)
* [Can I Deploy](https://docs.pact.io/pact_broker/can_i_deploy/)

---

