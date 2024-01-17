#!/bin/bash

echo -e "\e[44;4;30mI N J U R Y deployer: Deploying in Helios...\e[0m"

## Remove existing deployment
ssh -p 2222 s372799@se.ifmo.ru "rm -rf wildfly-26.1.3/standalone/deployments/eclipselink-orm-jsf-demo-1.0-SNAPSHOT.war"
# add new deployment
scp -P 2222 ./target/eclipselink-orm-jsf-demo-1.0-SNAPSHOT.war s372799@se.ifmo.ru:wildfly-26.1.3/standalone/deployments