all:
	sbt test assembly
test:
	./run_tests.sh

test-parser:
	sbt test

clean:
	rm -r target

.PHONY: all test test-parser clean
