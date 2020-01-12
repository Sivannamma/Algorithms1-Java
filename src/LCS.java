import java.util.Arrays;

/**
 * this class represents longest common substring problem. giving two Strings,
 * the functions returns the longest matual substring that they both consist
 * inside them.
 * 
 * 
 * 
 * first algorithm- greedy second algorithm- full search
 */

public class LCS {
	public static String LCS_greedy(String x, String y) {
		String str1 = LCS_greedy_help(x, y);
		String str2 = LCS_greedy_help(y, x);
		// returns the longest one among them
		return (str1.length() > str2.length()) ? str1 : str2;
	}

	private static String LCS_greedy_help(String x, String y) {
		String ans = "";
		int k = 0;
		for (int i = 0; i < x.length(); i++) {
			for (int j = k; j < y.length(); j++) {
				if (x.charAt(i) == y.charAt(j)) {
					ans += x.charAt(i);
					k = j; // updating the last place we have been in
					break; // because we found a math for the char
				}
			}
		}
		return ans;
	}

	private static String initialize_binary(int[] arr, String str) {
		boolean flag = true;
		String ans = "";
		for (int i = arr.length - 1; i >= 0 && flag; i--) {
			if (arr[i] == 0 && flag) {
				arr[i] = 1;
				flag = false;
			} else if (arr[i] == 1 && flag) {
				arr[i] = 0;
			}
		}

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 1)
				ans += str.charAt(i);
		}
		return ans;
	}

	public static String LCS_full_search(String x, String y) {
		String ans = "";
		int[] arr_X = new int[x.length()];
		int[] arr_Y = new int[y.length()];
		String[] binary_X = new String[(int) (Math.pow(2, x.length()))];
		String[] binary_Y = new String[(int) (Math.pow(2, y.length()))];

		for (int i = 0; i < binary_X.length; i++) { // setting the first array for string x
			binary_X[i] = initialize_binary(arr_X, x);
		}

		sort(binary_X); // sorting the array
		System.out.println(Arrays.deepToString(binary_X));

		System.out.println("********");
		for (int i = 0; i < binary_Y.length; i++) { // setting the second array for string y
			binary_Y[i] = initialize_binary(arr_Y, y);
		}

		sort(binary_Y); // sorting the array
		System.out.println(Arrays.deepToString(binary_Y));

		int max = 0;
		for (int i = 0; i < binary_X.length; i++) {
			for (int j = 0; j < binary_Y.length; j++) {
				if (binary_X[i].equals(binary_Y[j]) && binary_X[i].length() > max) {
					return binary_X[i]; // because the array is sorted, once we found a math, it return it
				} // because the comparation after the string we found gonna be equal in length
					// or the length is shorter. because we sorted the array.
			}
		}
		return ans;
	}

	private static void sort(String[] arr1) {
		for (int i = arr1.length - 1; i >= 0; i--) {
			for (int j = i; j < arr1.length - 1; j++) {
				if (arr1[j].length() < arr1[j + 1].length()) {
					String temp = arr1[j];
					arr1[j] = arr1[j + 1];
					arr1[j + 1] = temp;
				}
			}
		}
	}

	public static void main(String[] args) {
		String x = "abx";
		String y = "abcx";
		System.out.println("\n" + LCS_full_search(x, y));
	}

}
