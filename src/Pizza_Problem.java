class Person {
	double slices;
	double speed_of_eating;

	public Person(double speed_of_eating) {
		this.speed_of_eating = speed_of_eating;
	}
}

public class Pizza_Problem {

	public static void Pizza_simulation(double mul) {
		Person A = new Person(mul);
		Person B = new Person(1); // normal speeding eating
		double maxSlices = 0;
		double bestSlicesNum = mul + 1;
		double counter = mul + 1;
		double temp = counter;
		while (counter < 100000) {
			temp -= A.speed_of_eating + B.speed_of_eating; // updating the count of pizza left
			A.slices += A.speed_of_eating; // setting int the counter what A ate

			if (temp == 1) {
				A.slices=0;
				counter++;
				temp = counter;
			}
			if (temp < 0) { // it means we gave A to eat too much of slices, updating that he didnt eat us
							// much as we think
				A.slices += temp; // in case we think
			}
			if (temp == 0 || temp < 0) {
				// if we got that A ate more than before, we update that this number of slices
				// is better for A to do in order to eat more
				System.out.println(A.slices / counter);
				if (A.slices / counter > maxSlices) {
					maxSlices = A.slices / counter;
					bestSlicesNum = counter;
				}
				// reseting for the next simulation of pieces
				A.slices = 0;
				counter++;
				temp = counter;

			}
		}
		System.out.println("Best slices number is: " + bestSlicesNum);

	}

	public static void main(String[] args) {
		Pizza_simulation(2);

	}
}
