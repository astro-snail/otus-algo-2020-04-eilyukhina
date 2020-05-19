package algo1;

import tester.Task;
import tester.Tester;

public class StringLength implements Task {
	
	public static void main(String[] args) {
		Task task = new StringLength();
		Tester tester = new Tester(task, "C:\\Users\\Elena\\Documents\\Courses\\Algorithms\\Lesson 1\\A01_Счастливые_билеты\\0.String");
		tester.runTests();
	}

	@Override
	public String[] run(String[] data) {
		return new String[] {(data != null && data.length > 0) ? String.valueOf(data[0].length()) : String.valueOf(0)};
	}

	@Override
	public String getName() {
		return "String Length";
	}
}
