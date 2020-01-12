/**
 * this class represent a game of two players, each of them picks at turns a
 * number between the edges of an array, and at the end they sum up their
 * choices and can determine who wins.
 * 
 * the game can be excuted by three algorithms:
 * 
 * algorithm one - summing up the odd/even indexes and to determine which one to
 * choose. algorithm two - a better algorithm of 1, which each step does a
 * revalue if to keep going with the odd/even or to change to other one.
 * algorithm three - full search, (by the tree levels determine which side
 * benefit the win).
 */
public class Left_Right_Sum_Game {

	public static final int SIZE = 6; // initializing array length
	public static final int[] GAME = new int[SIZE];
	public static int edgeRight = 0;
	public static int edgeLeft = SIZE - 1;
	public static int sum_odd = 0;
	public static int sum_even = 0;

	public static void printArray(int start, int end) { // printing the array
		for (int i = start; i <= end; i++) {
			if (start == end)
				System.out.print("[" + GAME[i] + "]");
			else if (i == start)
				System.out.print("[ " + GAME[i] + " , ");
			else if (i == end)
				System.out.print(GAME[i] + " ] ");
			else
				System.out.print(GAME[i] + " , ");
		}
	}

	public static void algorithm_one() {
		int sumUser = 0;
		int sumComp = 0;
		if (sum_odd < sum_even) {
			sumComp += GAME[edgeRight];
			edgeRight++;

		} else {
			sumComp += GAME[edgeLeft];
			edgeLeft--;
		}
		while (edgeRight <= edgeLeft) {
			printArray(edgeRight, edgeLeft);
			// user picks which side he wants for his sum up variable
			int side = MyConsole.readInt("\ntype 0 for right, 1 for left");
			if (side == 1) // picked left
			{
				sumUser += GAME[edgeLeft]; // once for the user
				edgeLeft--;
				if (edgeLeft >= 0) {
					sumComp += GAME[edgeLeft]; // once for the comp
					edgeLeft--;
				}
			} else // picked right
			{
				sumUser += GAME[edgeRight]; // once for the user
				edgeRight++;
				if (edgeRight <= SIZE) {
					sumComp += GAME[edgeRight]; // once for the comp
					edgeRight++;
				}
			}
		}
		if (sumComp < sumUser)
			System.out.println("You won " + sumUser + " vs " + sumComp);
		else if (sumComp > sumUser)
			System.out.println("You lost " + sumUser + " vs " + sumComp);
		else
			System.out.println("It's a tie " + sumUser + " vs " + sumComp);
	}

	public static void caulc(int start, int end) {
		sum_odd = 0;
		sum_even = 0;
		for (int i = start; i <= end; i++) {
			if (i % 2 == 0)
				sum_even += GAME[i];
			else
				sum_odd += GAME[i];
		}
	}

	public static void algorithm_two() {
		int sumUser = 0;
		int sumComp = 0;
		caulc(0, SIZE - 1);

		if (sum_odd < sum_even) {
			sumComp += GAME[edgeRight];
			edgeRight++;

		} else {
			sumComp += GAME[edgeLeft];
			edgeLeft--;
		}
		while (edgeRight <= edgeLeft) {
			printArray(edgeRight, edgeLeft);
			// user picks which side he wants for his sum up variable
			int side = MyConsole.readInt("\ntype 0 for right, 1 for left");
			if(edgeRight == edgeLeft) {
				sumUser += GAME[edgeLeft];
				break;
			}
			if (side == 1) // picked left
			{
				sumUser += GAME[edgeLeft]; // once for the user
				edgeLeft--;
			} else // picked right
			{
				sumUser += GAME[edgeRight]; // once for the user
				edgeRight++;
			}
			caulc(edgeRight, edgeLeft);
			if (sum_odd < sum_even) {
				if (edgeRight % 2 == 0) {
					sumComp += GAME[edgeRight];
					edgeRight++;
				} else {
					sumComp += GAME[edgeLeft];
					edgeLeft--;
				}

			} else {
				if (edgeRight % 2 == 1) {
					sumComp += GAME[edgeRight];
					edgeRight++;
				} else {
					sumComp += GAME[edgeLeft];
					edgeLeft--;
				}
			}

		}

		if (sumComp < sumUser)
			System.out.println("You won " + sumUser + " vs " + sumComp);
		else if (sumComp > sumUser)
			System.out.println("You lost " + sumUser + " vs " + sumComp);
		else
			System.out.println("It's a tie " + sumUser + " vs " + sumComp);

	}

	public static void main(String[] args) {
//		for (int i = 0; i < SIZE; i++) { // inserting random numbers into the array
//			GAME[i] = (int) (Math.random() * 100 + 1);
//		}
		GAME[0] = 1;
		GAME[1] = 3;
		GAME[2] = 6;
		GAME[3] = 1;
		GAME[4] = 3;
		GAME[5] = 6;

		System.out.println("******************");
		System.out.println("FOR ME");
		printArray(0, SIZE - 1);
		System.out.println();
		caulc(0, SIZE - 1);
		System.out.println("\nthe odd sum: " + sum_odd);
		System.out.println("the even sum: " + sum_even);
		System.out.println("******************");

	//	 algorithm_one();
		 
		 
//		GAME[0] = 110;
//		GAME[1] = 5;
//		GAME[2] = 35;
//		GAME[3] = 100;
//		GAME[4] = 90;
//		GAME[5] = 80;
		algorithm_two();

	}

}
