

import java.util.*;

// WordLadder.java creates a ladder from the start to end word using a list of words provided by the user.
public class WordLadder {

	LinkedList<Node> list = new LinkedList<Node>();
	LinkedList<Node> queue = new LinkedList<Node>();
	Node start;
	Node end;
	int letterCount;
	String ladder;

	// Constructor, is the only method that is called for
	public WordLadder() {
		input();
		findChilds(start);
	}

	// Receives the input for the Program
	private void input() {
		Scanner scan = new Scanner(System.in);
		String word = "";

		System.out.println("\nEnter the letter count for the words in the ladder. \n" +
							"For Example, Enter '4' for words like lead, dead, head.");
		letterCount = scan.nextInt();


		// start word
		System.out.println("\nEnter a " + letterCount + " letter word for the start of the ladder." +
							" If the letter count is not correct, you will be prompted again.");
		// consider varying letter count
		while (word.length() != letterCount) {
			word = scan.nextLine();
		}
		start = new Node(word);
		word = "";


		// end word
		System.out.println("\nEnter a " + letterCount + " letter word for the end of the ladder." +
							" If the letter count is not correct, you will be prompted again.");
		// consider varying letter count
		while (word.length() != letterCount) {
			word = scan.nextLine();
		}
		end = new Node(word);


		// steps for the Ladder
		System.out.println("\nType a " + letterCount + " letter word and press Enter to include it. \n" +
							"Words that are not letterCount letters will not be included.\n" +
							"Enter 'break' when you are done entering words.\n");

		while (!word.equals("break")) {

			word = scan.nextLine();

			// Consider varying letter count
			if(word.length() == letterCount) {
				// testing purposes, comment out when done
				System.out.println("Added: " + word);
				list.addLast(new Node(word));
			}
		}
	}

	// Determines what should be run next in the que. Exits the program if there is no connection between the two words.
	private void runQueue() {
		if (queue.peek() != null)
			findChilds(queue.removeFirst());
		else {
			System.out.println("The start and end word can not be connected using a ladder");
			System.exit(0);
		}
	}

	// Takes care of finding the childs and adding and removing items from the list
	private void findChilds(Node parent) {
		ListIterator<Node> iterator = list.listIterator(0);
		Node child;

		if (isChild(parent, end)) {
				end.parent = parent;
				printLadder();
			}

		while(iterator.hasNext()) {

			child = iterator.next();
			if (isChild(parent, child)) {
				// Used for testing purposes.
				//System.out.println("Parent: " + parent.word + ", Child: " + child.word);
				child.parent = parent;

				//adds the child to the que and removes it from the list so it doesn't get checked again
				queue.addLast(child);
				iterator.remove();
			}
		}
		runQueue();
	}

	// Checks if the two words have a one letter difference
	private boolean isChild(Node parent, Node child) {
		int sameLetter = 0;

		for (int i = 0; i < letterCount; i++) {
			if(sameLetter < 2) {
				if(parent.word.charAt(i) != child.word.charAt(i)) {
					sameLetter++;
				}
			}
		}

		if(sameLetter == 1)
			return true;
		else
			return false;
	}

	// Prints the ladder once it connects start to end. Exits the program when done.
	private void printLadder() {
		StringBuilder s = new StringBuilder();
		Node step = end;

		while (step != start) {
			s.append(step.word);
			s.append("-");
			step = step.parent;
		}
		s.append(start.word);


		ladder = s.toString();
		System.out.println("\nWord Ladder: " + ladder);
		System.exit(0);
	}

	public static void main(String[] args) {
		WordLadder ladder = new WordLadder();

		//test case for start and end nodes.
		/*
		System.out.println(ladder.start.word);
		System.out.println(ladder.end.word);
		*/

		//test case for isChild
		//System.out.println(isChild(new Node("joke"), new Node("joke")));
	}

}
