all: trie other stack field value thing
	javac bgames/*.java

trie:
	javac bgames/trie/*.java

other:	
	javac bgames/other/*.java

stack: expressions	
	javac bgames/stack/*.java

expressions: unary binary
	javac bgames/stack/expressions/*.java

unary:
	javac bgames/stack/expressions/unary/*.java

binary:
	javac bgames/stack/expressions/binary/*.java

field:
	javac bgames/field/*.java

value:
	javac bgames/value/*.java

thing:
	javac bgames/thing/*.java

clean:
	rm bgames/trie/*.class
	rm bgames/other/*.class
	rm bgames/stack/*.class
	rm bgames/stack/expressions/*.class
	rm bgames/stack/expressions/binary/*.class
	rm bgames/field/*.class
	rm bgames/value/*.class
	rm bgames/thing/*.class
	rm bgames/*.class
