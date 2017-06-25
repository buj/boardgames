all:
	javac bgames/trie/*.java
	javac bgames/world/*.java
	javac bgames/world/stack/*.java
	javac bgames/world/field/*.java
	javac bgames/value/*.java

clean:
	rm bgames/trie/*.class
	rm bgames/world/*.class
	rm bgames/world/stack/*.class
	rm bgames/world/field/*.class
	rm bgames/value/*.class
