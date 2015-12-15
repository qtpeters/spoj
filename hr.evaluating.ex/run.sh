#!/bin/bash
export GRADLE_OPTS=-Dorg.gradle.daemon=true
gradle clean build;
echo "********** OUTPUT BELOW **********"
scala -cp build/libs/hr.evaluating.ex.jar hr.eval.ex.Solution;
echo "**********  END OUTPUT  **********"
