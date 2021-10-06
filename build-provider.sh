#!/bin/bash

mvn clean -Dpact.verifier.publishResults=true test -f ./pact-provider/pom.xml