
public class Robot_And_The_RabbitTurtle_Problems {

	// cars in a roundabout problem- with a robot that needs to count them:

	public static int sumCars(Node head) {
		int sum = 1; // because we already counting the head
		Node robo = head;
		robo.data = 1000;
		robo = robo.next;
		boolean flag = false;
		while (!flag || robo.data != 2000) {
			sum++;
			if (robo.data == 1000) {
				robo.data = 2000;
				for (int i = sum - 1; i > 0; i--) {
					robo = robo.previous;
				}
				if (robo.data == 2000) {
					flag = true;
					return sum - 1;
				} else
					sum = 1;
			}
			robo = robo.next;
		}
		return sum - 1;
	}

	public static void main(String[] args) {

		/**
		 * the robot car problem
		 */

		DoublyLinkedList s = new DoublyLinkedList();
		s.addAtStart(1);
		s.addAtEnd(2);
		s.addAtEnd(3);
		s.addAtEnd(1000);
		s.addAtEnd(1000);
		s.addAtEnd(4);
		s.addAtEnd(5);
		s.addAtEnd(1000);
		s.addAtEnd(6);
		s.addAtEnd(7);
		s.addAtEnd(1000);
		s.tail.next = s.head; // in order to make is a circle linkedlist
		s.head.previous = s.tail; // same^
		System.out.println(sumCars(s.head));

		/**
		 * the rabit and the turtle problem
		 */

		LinkedList m = new LinkedList();
		LinkedList circle = new LinkedList();

		// adding values to the arm way
		m.add(9);
		m.add(8);
		m.add(7);
		m.add(6);
		m.add(5);

		// adding values to the roundabout- circle way
		circle.add(10);
		circle.add(9);
		circle.add(8);
		circle.add(7);
		circle.add(6);
		circle.add(5);
		circle.add(4);
		circle.add(3);
		circle.add(2);
		circle.add(1);

		LinkedList.rabitAndTurtle(m, circle);

	}

}
