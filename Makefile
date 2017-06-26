all:
	javac bgames/trie/*.java
	javac bgames/other/*.java
	javac bgames/stack/*.java
	javac bgames/field/*.java
	javac bgames/value/*.java
	javac bgames/thing/*.java
	javac bgames/*.java

clean:
	rm bgames/trie/*.class
	rm bgames/other/*.class
	rm bgames/stack/*.class
	rm bgames/field/*.class
	rm bgames/value/*.class
	rm bgames/thing/*.class
	rm bgames/*.class
