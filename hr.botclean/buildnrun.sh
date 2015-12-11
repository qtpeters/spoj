#!/bin/bash
mvn clean install; cat test.txt | java -cp target/botclean-1.0.0.jar hackerrank.bsp.Solution; cat test.txt
