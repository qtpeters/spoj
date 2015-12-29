#!/bin/bash
# Cube Summation: https://www.hackerrank.com/challenges/cube-summation
pkg=$( basename ${PWD} )
gradle clean build
cat test.txt | java -cp build/libs/${pkg}.jar ${pkg}.Solution
