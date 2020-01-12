public class LinkedList {

	class Node {
		private int data;
		Node next;

		public Node(int data) {
			this.data = data;
			this.next = null;
		}

		public int getData() {
			return data;
		}

		public void setData(int data) {
			this.data = data;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

	}

	private Node head;
	private int size;

	public LinkedList() {
		head = null;
		size = 0;
	}

	public Node getHead() {
		return head;
	}

	public int getSize() {
		return size;
	}

	public void add(int data) {

		if (head == null) {
			head = new Node(data);
			size++;
		} else {
			Node n = head;

			while (n.next != null) {
				n = n.next;
			}
			n.next = new Node(data);

		}
		size++;

	}

	public String toString() {
		String str = "[";
		Node n = this.head;
		if (n == null)
			str += "]";
		while (n != null) {
			if (n.next == null)
				str += n.data + "]";
			else
				str += n.data + "->";
			n = n.next;
		}

		return str;
	}

	/**
	 * m= arm circle= the roundabout this function gets two linkedlist, the first
	 * one is the arm the second one we connect to be a circle linkedlist than we
	 * connect the first list to the circle and we create the path we want.
	 */

	public static void rabitAndTurtle(LinkedList m, LinkedList circle) {
		Node temp = circle.head;
		while (temp.next != null) {
			temp = temp.next;
		}
		temp.next = circle.head;

		temp = m.head;

		while (temp.next != null) {
			temp = temp.next;
		}
		temp.next = circle.head;

		Node rabit = m.head;
		Node turtle = m.head;
		/**
		 * searching for the first time they meet, we promote them until they meet. the
		 * rabit 2 steps, the turtle one step.
		 */
		do {
			rabit = rabit.next.next;
			turtle = turtle.next;
		} while (rabit != turtle);

		/**
		 * once they meet, we send one back to the start, and than we can count how many
		 * steps occurs until they meet at the start of the circul.
		 */
		Node tempR = rabit;
		rabit = m.head;
		int countM = 0;
		while (rabit != turtle) {
			countM++;
			rabit = rabit.next;
			turtle = turtle.next;
		}
		System.out.println("the length of the arm is: " + countM);

		/**
		 * counting how many steps the turtle is away from the first spot that they met
		 * before.
		 */
		int countMeet = countM; // the size of the arm + the size of the circle
		while (tempR != turtle) {
			turtle = turtle.next;
			countMeet++;
		}

		System.out.println("the index of meeting is: " + countMeet);
	}

}