SVC_NAME = graph-coloring

.PHONY: run

all: run

debug:
	mvn package
	cd target && java -jar $(SVC_NAME).jar --debug

run:
	mvn package
	cd target && java -jar $(SVC_NAME).jar
