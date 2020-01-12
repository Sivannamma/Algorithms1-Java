
public class Fibonachi {
	/*
	 * this class represent three ways of calculating the fibonachi serie. 1.
	 * recursivly way ~O(1.8^n) 2. iterativly way O(n-2) 3. matix way, using
	 * algorithm that is kind of the one of the power x^n O(logn)
	 */

	public static int rec_fib(int n) {
		if (n == 2 || n == 1) // f1=1, f2=1 (fibonachi starting point)
			return 1;
		return rec_fib(n - 1) + rec_fib(n - 2);
	}

	public static int iterative_fib(int n) {
		int f1 = 1;
		int f2 = 1;
		int ans = 1;
		for (int i = 2; i < n; i++) {
			ans = f1 + f2;
			f1 = f2;
			f2 = ans;
		}
		return ans;
	}

	public static int matrix_fib(int n) {
		/*
		 * the matrix of fibonachi which represents |f(n+1) f(n) | |f(n+1) f(n) | |f(n)
		 * f(n-1)| |f(n) f(n-1)| f0=0, f1=1, f2=1. that way the matrix was build.
		 */
		int[][] R = { { 1, 1 }, { 1, 0 } };
		int[][] base = { { 1, 1 }, { 1, 0 } };
		int size = n - 2;
		int I0;
		int I1;
		int I2;
		int I3;
		while (size > 0) {
			if (size % 2 != 0) {
				I0 = R[0][0];
				I1 = R[0][1];
				I2 = R[1][0];
				I3 = R[1][1];
				// setting row 0;
				R[0][0] = base[0][0] * I0 + I1 * base[1][0];
				R[0][1] = base[0][1] * I0 + I1 * base[1][1];
				// setting row 1;
				R[1][0] = base[0][0] * I2 + I3 * base[1][0];
				R[1][1] = base[0][1] * I2 + I3 * base[1][1];
			}
			size /= 2;
			I0 = base[0][0];
			I1 = base[0][1];
			I2 = base[1][0];
			I3 = base[1][1];
			base[0][0] = I0 * I0 + I1 * I2;
			base[0][1] = I1 * I0 + I1 * I3;
			base[1][0] = I0 * I2 + I3 * I2;
			base[1][1] = I1 * I2 + I3 * I3;
		}
		return R[0][0];
	}

	public static void main(String[] args) {
		int n = 14;
		System.out.println(rec_fib(n));
		System.out.println(iterative_fib(n));
		System.out.println(matrix_fib(n));
	}

}
