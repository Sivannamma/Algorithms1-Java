import java.util.HashMap;

class Node_f {
	int wins;
	int percent;

	public Node_f(int percent) {
		this.wins = 0;
		this.percent = percent;
	}
}

public class ThreeBattle {

	// this function returns a string based on the int we sent
	// the string represents the order of whos turn is it after who.
	public static String turns(int ind) {
		switch (ind) {
		case 0:
			return "ABC";
		case 1:
			return "ACB";
		case 2:
			return "BAC";
		case 3:
			return "BCA";
		case 4:
			return "CAB";
		case 5:
			return "CBA";
		}
		return "";
	}

	public static void Three_Battle(int A, int B, int C) {
		HashMap<Character, Node_f> map = new HashMap<Character, Node_f>();
		map.put('A', new Node_f(A));
		map.put('B', new Node_f(B));
		map.put('C', new Node_f(C));
		int count = 3;
		int million = 1000000;
		while (million > 0) {
			int rand = (int) (Math.random() * 6);
			String players = turns(rand);
			while (count == 3) {
				rand = (int) (Math.random() * 101);
				// if the current has the least percent of winning among the other two, he gives
				// up his turn to win and goes to the end of the line
				if ((map.get(players.charAt(0)).percent < map.get(players.charAt(1)).percent)
						&& (map.get(players.charAt(0)).percent < map.get(players.charAt(2)).percent))
					players = "" + players.charAt(1) + "" + players.charAt(2) + "" + players.charAt(0) + "";
				// if he got in the randome more than his chance to win, means he didnt
				// succedded and he goes to the end of the line
				else if (rand > (map.get(players.charAt(0)).percent))
					players = "" + players.charAt(1) + "" + players.charAt(2) + "" + players.charAt(0) + "";
				else {
					// count -- because it means he defetead one of those we check down
					count--;
					// checking which one the current is defeating: it goes on the rival with the
					// highest percent of win amont the two rivals
					if (map.get(players.charAt(1)).percent > map.get(players.charAt(2)).percent) {
						players = "" + players.charAt(2) + "" + players.charAt(0) + "";
					} else {
						players = "" + players.charAt(1) + "" + players.charAt(0) + "";
					}
				}
			}
			// when theres only two rivals : no one gives up : they try to knock each other
			// down.
			while (count == 2) {
				rand = (int) (Math.random() * 101);
				if (map.get(players.charAt(0)).percent < rand) {
					players = "" + players.charAt(1) + players.charAt(0) + "";
				} else {
					count--;
					// the one who wins, we do ++ to his wins counter.
					// means he defeated them both and the last one standing.
					map.get(players.charAt(0)).wins++;
				}
			}
			// count back to three because they r all fighting again for the next
			// simulation.
			count = 3;
			million--;
		}
		System.out.println("A won : " + map.get('A').wins);
		System.out.println("B won : " + map.get('B').wins);
		System.out.println("C won : " + map.get('C').wins);
	}

	public static void main(String[] args) {
		Three_Battle(80, 70, 50);
	}

}
