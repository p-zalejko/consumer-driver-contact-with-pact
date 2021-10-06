#!/bin/bash

mvn clean test pact:publish -f ./pact-consumer-foo/pom.xml
mvn clean test pact:publish -f ./pact-consumer-bar/pom.xml