
public class Node_flight {
	int down;
	int right;
	int value_rev; // the value of the reverse way of the algorithm
	int value;
	int Allpaths;
	boolean onPath; // will be true if this path is on the shortest path
	String pred;

	Node_flight(int down, int right) {
		onPath = false;
		this.down = down;
		this.right = right;
		this.value = 0;
		value_rev = 0;
		this.Allpaths = 1;
		this.pred = "";

	}

}
