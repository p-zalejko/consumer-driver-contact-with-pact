#!/bin/bash

GIT_HASH_SHORT=`git rev-parse --short HEAD`
export GIT_COMMIT_HASH_SHORT=$GIT_HASH_SHORT
mvn clean test pact:publish -f ./pact-consumer-foo/pom.xml
mvn clean test pact:publish -f ./pact-consumer-bar/pom.xml 