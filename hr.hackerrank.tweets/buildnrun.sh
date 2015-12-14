#!/bin/bash
package=$(ls -R | grep src | tail -1 | sed -e "s|.*java/\(.*\):|\1|" | sed -e "s|/|\.|")
mvn clean install
cat test.txt | java -cp target/*.jar ${package}.Solution
