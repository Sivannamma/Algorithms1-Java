import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Flight_Problem {

//	// Function to print the route taken
//	public static void printPath(String path, Node_flight last) {
//		for (Node_flight i : path) {
//			System.out.print(i.pred + " - ");
//		}
//		System.out.println(last.pred);
//	}
//
//	public static void findPaths(Node_flight[][] mat, String path, int i, int j) {
//		int M = mat.length;
//		int N = mat[0].length;
//
//		// if we have reached the last cell, print the route
//		if (i == M - 1 && j == N - 1) {
//			printPath(path, mat[i][j]);
//			return;
//		}
//		// include current cell in path
//		path.add((mat[i][j]));
//		// move right
//		if ((i >= 0 && i < M && j + 1 >= 0 && j + 1 < N)) {
//			findPaths(mat, path, i, j + 1);
//		}
//		// move down
//		if ((i + 1 >= 0 && i + 1 < M && j >= 0 && j < N)) {
//			findPaths(mat, path, i + 1, j);
//		}
//		// remove current cell from path
//		path.remove(path.size() - 1);
//	}

	public static int min_for_path(int up, int left) {
		return (up < left) ? up : left; // returns the min
	}

	// this functions sets at each node true if the value+value reverse are equals
	// to the shortest path cost- than means this node is on the shortest root
	public static void placeTrue(Node_flight[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				// if equals, means on path

				if (arr[i][j].value + arr[i][j].value_rev == arr[0][0].value + arr[0][0].value_rev)
					arr[i][j].onPath = true;
				else
					arr[i][j].onPath = false;
			}
		}
	}

	public static int flight_algo_reverse(Node_flight[][] arr) {
		// initializing the values int the top column:
		for (int col = arr[0].length - 2; col >= 0; col--) {
			arr[arr.length - 1][col].value_rev = arr[arr.length - 1][col + 1].value_rev
					+ arr[arr.length - 1][col].right;
		}
		// initializing the values on the rows:
		for (int row = arr.length - 2; row >= 0; row--) {
			arr[row][arr[0].length - 1].value_rev = arr[row][arr[0].length - 1].down
					+ arr[row + 1][arr[0].length - 1].value_rev;
		}

		// initializing the values of the inner matrix
		for (int row = arr.length - 2; row >= 0; row--) {
			for (int col = arr[0].length - 2; col >= 0; col--) {
				// asking whos value should the node get
				int valueRight = arr[row][col + 1].value_rev + arr[row][col].right;
				int valueDown = arr[row + 1][col].value_rev + arr[row][col].down;
				if (valueRight < valueDown) {
					arr[row][col].value_rev = valueRight;
				} else if (valueRight > valueDown) {
					arr[row][col].value_rev = valueDown;

				} else {
					arr[row][col].value_rev = valueDown; // they are equals, no matter which one
				}
			}
		}
		return arr[0][0].value_rev;
	}

	public static int flight_algo(Node_flight[][] arr) {

		// initializing the values of the edges in the right
		for (int col = 1; col < arr[0].length; col++) {
			arr[0][col].value = arr[0][col - 1].value + arr[0][col - 1].right;
			arr[0][col].pred = "right";
		}

		// initializing the values of the edges in the down
		for (int row = 1; row < arr.length; row++) {
			arr[row][0].value = arr[row - 1][0].value + arr[row - 1][0].down;
			arr[row][0].pred = "down";
		}

		// initializing the values of the inner matrix
		for (int row = 1; row < arr.length; row++) {
			for (int col = 1; col < arr[0].length; col++) {
				// asking whos value should the node get
				int valueRight = arr[row][col - 1].value + arr[row][col - 1].right;
				int valueDown = arr[row - 1][col].value + arr[row - 1][col].down;
				if (valueRight < valueDown) {
					arr[row][col].value = valueRight;
					arr[row][col].pred = "right "; // from which path it came
					arr[row][col].Allpaths = arr[row][col - 1].Allpaths;
				} else if (valueRight > valueDown) {
					arr[row][col].value = valueDown;
					arr[row][col].pred = "down "; // from which path it came
					arr[row][col].Allpaths = arr[row - 1][col].Allpaths;

				} else {
					arr[row][col].value = valueDown; // they are equals, no matter which one
					arr[row][col].pred = "down/right "; // can come from two roads
					arr[row][col].Allpaths = arr[row - 1][col].Allpaths + arr[row][col - 1].Allpaths;

				}
			}
		}
		return arr[arr.length - 1][arr[0].length - 1].value;
	}

	public static void initialize_matrix(Node_flight[][] arr, String[] initialize) {
		int down = 0;
		int right = 0;
		int ind = 0; // going over the initialize array

		// initializing the paths of the matrix
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (j + 1 == arr[i].length && i + 1 == arr.length) {
					arr[i][j] = new Node_flight(down, right);
					break;
				}
				if (j + 1 == arr[i].length) { // we need only down
					down = Integer.parseInt(initialize[ind++]);
					arr[i][j] = new Node_flight(down, right);
				} else if (i + 1 == arr.length) { // we need only right
					right = Integer.parseInt(initialize[ind++]);
					arr[i][j] = new Node_flight(down, right);
				} else // we need both
				{
					down = Integer.parseInt(initialize[ind++]);
					right = Integer.parseInt(initialize[ind++]);
					arr[i][j] = new Node_flight(down, right);
				}
			}
		}

	}

	public static void main(String[] args) {
		Node_flight[][] arr = new Node_flight[4][4];

		File file = new File("matrixTwoPath.txt");
		String line = null;
		String[] initialize = null;

		// reading the matrix from file
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				initialize = line.split(" ");
			}

			br.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + file);
		} catch (IOException e) {
			System.out.println("Unable to read file: " + file);
		}
		initialize_matrix(arr, initialize);
		System.out.println(flight_algo(arr));
		System.out.println(flight_algo_reverse(arr));
		placeTrue(arr);
		List<Node_flight> list = new ArrayList<Node_flight>();
		// findPaths(arr, list, 0, 0);

	}
}
