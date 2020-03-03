public class LIS_Problem {
	/**
	 * this class represents LIS problem. Giving an array with randoms numbers in
	 * each function we need to return : return the number of the longest increasing
	 * subsequence return the string that represents the numbers themself of the
	 * increasing numbers
	 * 
	 **/
//returns the size of the longest increasing subsquence 
	public static int algorithm_4(int[] arr) {
		int ans = 1;
		int pointer = 1;
		int[] help = new int[arr.length];
		help[0] = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (help[pointer - 1] < arr[i]) {
				help[pointer++] = arr[i];
				ans++;
			} else {
				int index = binarySearch(help, arr[i], pointer);
				help[index] = arr[i];
			}

		}
		// System.out.println(Arrays.toString(help));
		return ans;
	}

	private static int binarySearch(int[] help, int value, int pointer) {
		int index = 0;
		int temp = pointer;
		int mid = (pointer + index) / 2;
		while (index < pointer) {
			mid = (pointer + index) / 2;
			if (help[mid] > value)
				return mid;
			else
				index = mid + 1;
		}
		return temp;
	}

//returns the sub matrix that countains the longest increasting subsquence
	public static String algorithm_5(int[] arr) {
		String ans = "";
		int pointer = 0; // points to the end of the diagonal
		int[][] help = new int[arr.length][arr.length]; // initializing the matrix for the help
		help[0][0] = arr[0];
		for (int i = 1; i < arr.length; i++) { // going over all the numbers in the main array
			if (help[pointer][pointer] < arr[i]) {
				pointer++;
				int j = 0;
				for (j = 0; j < pointer; j++) {
					help[pointer][j] = help[pointer - 1][j];
				}
				help[j][j] = arr[i];
			} else { // we need to do binary search in order to find the right place
				int index = binarySearch(help, arr[i], pointer);
				help[index][index] = arr[i];
				int j = 0;
				for (j = 0; j < index; j++) {
					help[index][j] = help[index - 1][j];
				}
			}
		}
		for (int i = 0; i <= pointer; i++) { // returning the last line(which the pointer is on)
												// this line is the answer of the LIS
			ans += help[pointer][i] + " ";
		}

		return ans;
	}

	private static int binarySearch(int[][] help, int value, int pointer) {
		int index = 0;
		int temp = pointer;
		int mid = (pointer + index) / 2;
		while (index < pointer) {
			mid = (pointer + index) / 2;
			if (help[mid][mid] > value)
				return mid;
			else
				index = mid + 1;
		}
		return temp;
	}

	public static int AB_arrProblem(int[] A, int[] B) {
		int n = A.length;
		int[] mat = new int[n];
		int k = 0, ind = 0;
		for (int i = 0; i < n; i++) {
			if (B[i] == 1) {
				ind = i;
				break;
			}
		}
		mat[0] = A[ind];
		for (int i = ind + 1; i < mat.length; i++) {
			if (B[i] == 1) {
				if (mat[k] < A[i])
					mat[++k] = A[i];
				else
					mat[binarySearch(mat, A[i], k)] = A[i];
			}
		}
		return k + 1;
	}

	public static void main(String[] args) {
		int[] A = { 3,2,8,10,7,4,1,5};
		int[] B = { 1, 0, 0, 1, 0, 0, 0, 1, 1, 1 };
		System.out.println(algorithm_5(A));
	}

}
