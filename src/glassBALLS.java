import java.util.Arrays;

public class glassBALLS {

	public static int numberOfChecking2(int n) {
		int[] f = new int[n + 1];
		f[0] = 0;
		f[1] = 1;
		f[2] = 2;
		for (int i = 3; i <= n; i++) {
			int min = n;
			for (int j = 1; j < i; j++) {
				int x = Math.max(j, f[i - j] + 1);
				if (x < min)
					min = x;
			}
			f[i] = min;
		}
		return f[n];
	}

	public static void main(String[] args) {
		System.out.println(numberOfChecking2(6));
	}
}
