#!/bin/bash
mvn clean install spring-boot:run \
	-Dlogging.console.enable=false \
    -Dautentication.password=password \
    -Dautentication.user=user \
   
#|& tee /tmp/core.log
