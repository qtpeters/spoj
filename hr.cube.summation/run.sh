#!/bin/bash
pkg=$( basename ${PWD} )
gradle clean build
cat test.txt | java -cp build/libs/${pkg}.jar ${pkg}.Solution
