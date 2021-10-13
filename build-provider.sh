#!/bin/bash

GIT_HASH_SHORT=`git rev-parse --short HEAD`
export GIT_COMMIT_HASH_SHORT=$GIT_HASH_SHORT
mvn clean -Dpact.verifier.publishResults=true -Dpact.provider.version=$GIT_COMMIT_HASH_SHORT -Dpact.provider.tag=$GIT_COMMIT_HASH_SHORT package -f ./pact-provider/pom.xml