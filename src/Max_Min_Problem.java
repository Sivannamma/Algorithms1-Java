
public class Max_Min_Problem {
	/**
	 * this function represents an algorithm that finds max and min from array in a
	 * complexity of 2n -3 / O(n)
	 */

	public static void algorithm3_minANDmax(int[] arr) {
		int max = arr[0];
		int min = arr[1];
		int count = 1; // counter in order to sum up the comparing times.
		// it starts with 1 because we for sure do at least one comparing in the first
		// if.

		if (min > max) { // one compare that we do before going over the array
			min = arr[1];
			max = arr[0];
		}
		for (int i = 2; i < arr.length; i++) {

			if (arr[i] < min) {
				min = arr[i];
				count++;
			} else if (arr[i] > max) { // this "else if" helps in saving a comparing
				// because if arr[i] was < from min, we dont need to compare it
				// with max.
				max = arr[i];
				count += 2; // +=2 because if we came to this if, we already checked the first one and this
							// one
			} else {
				count += 2; // if the number was between, we still compared those variable.
			}
		}
		System.out.println("the min: " + min + " the max: " + max);
		System.out.println("the number of comparing is: " + count);
	}

	/**
	 * this function represents an algorithm that finds max and min from array in a
	 * complexity of 1.5n-2 / O(n)
	 */
	public static void algorithm4_minANDmax(int[] arr) {
		int max = arr[0];
		int min = arr[1];
		int count = 2; // counter in order to sum up the comparing times.
		// it starts with 2 because we for sure do at least one comparing in the first
		// if + the if of checking if the length is odd.

		if (min > max) { // one compare that we do before going over the array
			min = arr[1];
			max = arr[0];
		}
		int n = arr.length - 1;
		if (n % 2 != 0) { // means the length is odd

			if (arr[n] < min) {
				min = arr[n];
				count++;
			} else if (max < arr[n]) {
				max = arr[n];
				count += 2;
			}
		}

		for (int i = 3; i < arr.length; i += 2) {
			count++; // because each iterate we do at least one compare
			if (arr[i - 1] < arr[i]) { // checking who's greater
				if (max < arr[i])
					max = arr[i];
				if (arr[i - 1] < min)
					min = arr[i - 1];

				count += 2; // we're doing 2 comparing from the if's
			} else { // arr[i]<arr[i-1] , the simetrical case with oposite check
				if (max < arr[i] - 1)
					max = arr[i - 1];
				if (arr[i] < min)
					min = arr[i];

				count += 2; // we're doing 2 comparing from the if's
			}
		}
		System.out.println("the min: " + min + " the max: " + max);
		System.out.println("the number of comparing is: " + count);
	}

	public static void main(String[] args) {
		int[] arr = new int[99];
		for (int i = 0; i < arr.length; i++) { // inserting random number into the array
			arr[i] = (int) (Math.random() * 1000 + 1);
		}

		// algorithm 2n-2
		System.out.println("the 2n-3 algorithm:");
		algorithm3_minANDmax(arr);

		// algorithm 1.5n
		System.out.println("the 1.5n-2 algorithm:");
		algorithm4_minANDmax(arr);

	}

}
