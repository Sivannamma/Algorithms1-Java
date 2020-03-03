import java.util.Stack;

class NodeM {
	int num;
	Stack<Integer> s;

	public NodeM(int num) {
		this.num = num;
		s = new Stack<Integer>();
	}
}

public class max1Max2_Stack {

	public static void init(NodeM[] m, int[] a_) {
		for (int i = 0; i < a_.length; i++) {
			m[i] = new NodeM(a_[i]);
		}
	}

	public static void max1_max2(int[] arr) {
		int n = arr.length;
		NodeM[] mat = new NodeM[n];
		init(mat, arr);
		int max;
		if (mat[0].num < mat[1].num) {
			max = 1;
			mat[1].s.add(mat[0].num);
		} else {
			max = 0;
			mat[0].s.add(mat[1].num);
		}
		for (int i = 2; i < mat.length - 1; i += 2) {
			if (mat[i].num < mat[i + 1].num) {
				mat[i + 1].s.add(mat[i].num);
				if (mat[i + 1].num < mat[max].num)
					mat[max].s.add(mat[i + 1].num);
				else {
					mat[i + 1].s.add(mat[max].num);
					max = i + 1;
				}
			} else {
				mat[i].s.add(mat[i + 1].num);
				if (mat[i].num < mat[max].num)
					mat[max].s.add(mat[i].num);
				else {
					mat[i].s.add(mat[max].num);
					max = i;
				}
			}
		}
		if (n % 2 != 0) { // means odd, needs to ask about the last variable we missed
			if (mat[n - 1].num < mat[max].num) {
				mat[max].s.add(mat[n - 1].num);
			} else {
				mat[n - 1].s.add(mat[max].num);
				max = n - 1;
			}
		}
		int max2 = mat[max].s.pop();
		while (!mat[max].s.isEmpty()) {
			int temp = mat[max].s.pop();
			if (temp > max2)
				max2 = temp;
		}
		System.out.println("max1: " + mat[max].num + " max2: " + max2);
	}

	public static int[] twoMaxRecurs(int arr[]) {
		int[] ans = twoMaxRecurs(arr, 0, arr.length);
		System.out.println("recurs: max1 = " + ans[0] + ", max2 = " + ans[1]);
		return ans;
	}

	public static int[] twoMaxRecurs(int arr[], int low, int high) {
		if (high == low + 2) {
			int max1 = arr[low] < arr[low + 1] ? arr[low + 1] : arr[low];
			int max2 = arr[low] > arr[low + 1] ? arr[low + 1] : arr[low];
//			if (max1 < max2) {
//				max1 = arr[low + 1];
//				max2 = arr[low];
//			}
			int maxArray[] = { max1, max2, 1 };
			return maxArray;
		} else if (high == low + 1) {
			int max1 = arr[low];
			int maxArray[] = { max1, Integer.MIN_VALUE, 0 };
			return maxArray;
		} else {
			int mid = (low + high) / 2;
			int[] mL = twoMaxRecurs(arr, low, mid);// [low, mid)
			int[] mH = twoMaxRecurs(arr, mid, high);// [mid, high)
			// maxArray[0] = max1, maxArray[1] = max2, maxArray[2] = comparisons
			int maxArray[] = new int[3];
			int comp = mL[2] + mH[2];
			int i = 0, j = 0;
			comp++;
			if (mL[i] > mH[j]) {
				maxArray[0] = mL[i++];
			} else {
				maxArray[0] = mH[j++];
			}
			comp++;
			if (mL[i] > mH[j]) {
				maxArray[1] = mL[i];
			} else {
				maxArray[1] = mH[j];
			}
			maxArray[2] = comp;
			return maxArray;
		}
	}

	public static void main(String[] args) {
		int[] arr = { 1000, 100, 5000, 25, 36, 120, 95555 };
		twoMaxRecurs(arr);
	}
}
