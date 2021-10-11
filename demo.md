# consumer and the first contract

* show the test class, launch it (it will generate a contract file)
* show the contract file
* matchers (type vs value)
* publish the contract (version 1.0.0) 

# broker
* show the broker
* show the contract 
* * show a call graph
* * show versions and tags
* * explain relation between the consumer and provider (versioning + tags)
* best practices: git hash, branch, environment name, etc.

# provider and the first contract
* show an empty class for a pact test (just an initial stuff...)
* launch the test
* * test passes! explain WHY (pending contracts, explain how it works)
* * show logs, information that the contract is in the pending state
* * publish results to the broker (explain what we have there now...)
* explain `@State` - how it works, what it does
* show a sample controller class - explain that it will be tested (relation to the @State annotation)
* implement all tests for the contract
* launch tests
* publish results
* show the broker and its state
* make a change in order to BREAK the contract
* * launch tests
* * show results
* * explain how it works now - pending contracts

# Consumer publishes a new contract
* add a new field to the contract
* publish a new contract WITH the new version number (1.1.0)

# Provider
* launch tests in the provider
* * show why it passes - pending contracts
* * publish results to the broker
* adjust tests to the new contract
* * launch tests and publish new results

# In adition, explain
* `consumerVersionSelector` explain it 
* how to have a test only for one consumer!