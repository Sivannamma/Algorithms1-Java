
public class LongestSubArrayOfOnes {

	public static int wholeSearch_algo(int[][] arr) {
		int ans = 0;
		int N = arr.length;
		for (int n = 3; n <= N; n++) { // N is the sub rectangle we want to create

			for (int i = 0; i <= arr.length - n; i++) {
				for (int j = 0; j <= arr[0].length - n; j++) {
					if (isOne(i, j, i + n - 1, j + n - 1, arr)) {
						ans = n; // we save the n because it shows how much nxn we succedded to do
					}
				}
			}
		}
		return ans;
	}

	public static boolean isOne(int i1, int i2, int j1, int j2, int[][] arr) {
		for (int i = i1; i <= j1; i++) {
			for (int j = i2; j <= j2; j++) {
				if (arr[i][j] == 0) // if one time we found its equal to zero --->false
					return false;
			}
		}

		return true;
	}

	public static int greedy_algo(int[][] arr) {
		int ans = 0;
		int max = 0;
		int i1 = 0;
		int j1 = 0;
		boolean falgZero = true;
		int N = (arr.length < arr[0].length) ? arr.length : arr[0].length;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {

				if (arr[i][j] != 1)
					continue;

				if (max < ans)
					max = ans;

				ans = 0;
				i1 = i;
				j1 = j;
				falgZero = true;
				ans = 1;

				for (int n = 1; n <= N && falgZero; n++) {
					if (i1 + 1 < arr.length && j1 + 1 < arr[0].length && arr[i1 + 1][j1 + 1] == 1) {
						i1 = i1 + 1;
						j1 = j1 + 1;

						for (int k = 1; k <= n; k++) {
							if (arr[i1 - k][j1] == 0 || arr[i1][j1 - k] == 0) {
								falgZero = false;
								break;
							}
						}
						if (falgZero)
							ans++;
					} else
						break;
				}
			}
		}

		return max;
	}

	public static void main(String[] args) {
		int[][] arr = { { 1, 1, 0, 0 }, { 0, 0, 1, 1 }, { 1, 1, 1, 0 }, { 1, 1, 1, 0 }, { 0, 0, 1, 1 } };
		int[][] brr = { { 1, 0, 0, 1, 1, 1 }, { 1, 0, 1, 1, 1, 1 }, { 0, 1, 1, 1, 1, 1 }, { 0, 1, 1, 1, 1, 1 } };
		System.out.println(wholeSearch_algo(brr));
		System.out.println(greedy_algo(brr));

	}

}
