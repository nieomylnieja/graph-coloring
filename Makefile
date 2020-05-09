SVC_NAME = graph-coloring

.PHONY: run

all: run

run:
	mvn package
	cd target && java -jar $(SVC_NAME).jar
