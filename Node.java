
//Node class, for use in WordLadder.java
class Node {

	protected String word;
	protected Node parent;

	protected Node(String word) {
		this.word = word;
	}

	protected Node(String word, Node parent) {
		this.word = word;
		this.parent = parent;
	}
}
