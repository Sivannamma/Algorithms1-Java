import java.util.Stack;
import java.util.Iterator;
import java.util.LinkedList;

public class Max1andMax2 {

	public static void main(String[] args) {
		LinkedList<Stack<Integer>> m = new LinkedList<>();
		Stack<Integer> candidate = new Stack<>();

		for (int i = 0; i < 10; i++) {
			m.add(new Stack());
		}
		Iterator<Stack<Integer>> itr = m.iterator();
		while (itr.hasNext()) {
			itr.next().push((int) (Math.random() * 100 + 1));
		}
		itr = m.iterator();
		while (itr.hasNext()) {
			System.out.print(itr.next().peek() + "  ");
		}
		itr = m.iterator();
		Iterator<Stack<Integer>> itrH = m.iterator();
		itrH = m.iterator();
		itrH.next();
		int max = 0, max2 = 0;
		int a = 0;
		System.out.println(a);
		while (itrH.hasNext()) {
			max = itr.next().peek();
			max2 = itr.next().peek();
			boolean b = m.contains(max);
			// max=(max>max2)? max : max2;
			// candidate=(max>max2)?m.get(max):m.get(max2);

		}

	}

}
