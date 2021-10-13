# Consumer-Driven Contracts

### with Pact

Note: 
* who knows?
* who uses?
* who knows pact?

---

## The problem

![title](assets/img/simple-rest.png)

---

## The problem

![title](assets/img/advanced-rest.png)

---

## The problem

![title](assets/img/advanced-rest-with-events.png)


---


## Consumer-Driven Contracts
### to the rescue

![title](assets/img/pact-summary.png)

[source](https://docs.pact.io/)

Note:
* explain why - many services, many APIs, probably different release cycles (CI/CD)
* explain how it works
* consumer creates the contract
* consumer tests the contract
* providers tests the contract as well
* both sides test the contract

---

## Consumer-Driven Contracts
### What we will get

* Isolation
* Simplified End-to-End Tests (lack of e2e?)
* Feedback Time
* Stability
* Well-Fittedness
* Reveal Unused Interfaces

[source](https://reflectoring.io/7-reasons-for-consumer-driven-contracts/)

---

## Consumer-Driven Contracts
### When it does not fit

* public APIs for unknown consumers
* we have very few services (one, two...) 

Note:
* unknown services - do we know what they need?
* CDC tests explicit consumer's contracts!

---

## Pact
### https://pact.io/

> Fast, easy and reliable testing for integrating web apps, APIs and microservices

---

## Consumer

* defines the contracts (expectations)

Note:
* what the consumer needs
* matchery (type vs value) 

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

---

## Provider

* fulfils the contract
* "signs up the contract"

Note:
* explain @State and how it works (how it "finds" rest controller)
* consumerVersionSelector
* pending contracts
* testing all contracts vs one contract

---

## Broker

* centralized contract registry
* keeps validation results
* allows to determine if contracts are met
* graphs on the broker

Note:
- versioning!
- tags
- matrix table
- best practices: git hash, branch, environment name, etc.

---

## DEMO


[The source code is on GitHub](https://github.com/p-zalejko/consumer-driver-contact-with-pact)

---

## Pending contracts

![title](assets/img/pending.png)

---

## Worth reading

* [Not only Pact for CDC](https://docs.pact.io/getting_started/comparisons/)
* [Pending contracts](https://docs.pact.io/pact_broker/advanced_topics/pending_pacts/)
* [WIP contracts](https://docs.pact.io/pact_broker/advanced_topics/wip_pacts/)
* [Versioning and tags!](https://docs.pact.io/getting_started/versioning_in_the_pact_broker/)
* [Events](https://blog.testproject.io/2020/06/03/event-driven-architecture-how-to-perform-contract-testing-in-kafka-pubsub/)
* [Not only Java](https://docs.pact.io/implementation_guides/cli/)
* [CI/CD is a challenge](https://docs.pact.io/pact_nirvana/)
* [Can I Deploy](https://docs.pact.io/pact_broker/can_i_deploy/)

---

