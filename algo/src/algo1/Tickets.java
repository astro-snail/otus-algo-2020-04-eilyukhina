package algo1;

import tester.Task;
import tester.Tester;

public class Tickets implements Task {
	
	public static void main(String[] args) {
		Task task = new Tickets();
		Tester tester = new Tester(task, "C:\\Users\\Elena\\Documents\\Courses\\Algorithms\\Lesson 1\\A01_Счастливые_билеты\\1.Tickets");
		tester.runTests();
	}

	@Override
	public String run(String[] data) {
		int n = Integer.parseInt(data[0]);
		return String.valueOf(getNumberOfCombinations(n));
	}

	private long getNumberOfCombinations(int n) {
		long count = 0;
		
		for (int i = 0; i <= 9 * n; i++) {
			long countSum = getNumberOfCombinations(n, i);
			count += countSum * countSum; 
		}
		return count;
	}
	
	private long getNumberOfCombinations(int n, int sum) {
		if (sum < 0 || sum > n * 9)
			return 0;
		if (n == 1)
			return 1;
		long count = 0;
		for (int i = 9; i >= 0; i--) {
			count += getNumberOfCombinations(n - 1, sum - i);
		}	
		return count;
	}
}
