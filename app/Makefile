.DEFAULT_GOAL := build-run

run-dist: ./build/install/app/bin/app

clean:
	make -C app clean

build:
	make -C app clean build

test: ./gradlew test

.PHONY: build