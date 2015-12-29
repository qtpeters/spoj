#!/bin/bash
mvn -q clean install; cat test.txt | java -cp target/d2array-1.0.0.jar hr.d2array.Solution;
