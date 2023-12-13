#!/usr/bin/env bash
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
(cd "$DIR"/.. && mvn verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.projectKey=c3s -Dsonar.organization=c3s -Dsonar.host.url=https://sonarcloud.io)
