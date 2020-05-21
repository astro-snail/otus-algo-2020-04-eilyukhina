package algo1;

import tester.Task;
import tester.Tester;

public class TicketsDP implements Task {
	
	public static void main(String[] args) {
		Task task = new TicketsDP();
		Tester tester = new Tester(task, "C:\\Users\\Elena\\Documents\\Courses\\Algorithms\\Lesson 1\\A01_Счастливые_билеты\\1.Tickets");
		tester.runTests();
	}

	@Override
	public String[] run(String[] data) {
		int n = Integer.parseInt(data[0]);
		return new String[]{String.valueOf(getNumberOfCombinations(n))};
	}
	
	@Override
	public String getName() {
		return "Tickets - Dynamic Programming";
	}

	public long getNumberOfCombinations(int n) {
		long count = 0;
		long[] num = {1};
		for (int k = 1; k <= n; k++) {
			long[] res = new long[9 * k + 1];
			for (int i = 0; i <= 9 * k; i++) {
				for (int j = Math.max(0, i - 9 * (k - 1)); j <= Math.min(9, i); j++) {
					res[i] += num[i - j];
				}
				/*for (int j = 0; j <= 9; j++) {
					if ((i - j) >= 0 & (i - j) <= 9 * (k - 1)) {
						res[i] += num[i - j];
					}
				}*/
				if (k == n)
					count += res[i] * res[i];
			}
			num = res;
		}
		return count;
	}
}
