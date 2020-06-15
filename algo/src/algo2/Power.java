package algo2;

import tester.Task;
import tester.Tester;

public class Power implements Task {
	
	public static void main(String[] args) {
		Task task = new Power();
		Tester tester = new Tester(task, "C:\\Users\\Elena\\Documents\\Courses\\Algorithms\\Lesson 2\\3.Power");
		tester.runTests();		
	}

	@Override
	public String[] run(String[] data) {
		double a = Double.parseDouble(data[0]);
		long b = Long.parseLong(data[1]);
		
		double res = power1(a, b);
		
		//System.out.println(res);
		return new String[] {String.valueOf(res)};
	}

	@Override
	public String getName() {
		return "A to B-th power";
	}
	
	private double power1(double a, long b) {
		if (a == 0) 
			return 0;
		double res = 1;
		for (long i = 1; i <= b; i++) {
			res *= a;
		}
		return res;
	}
	
	private double power2(double a, long b) {
		if (a == 0)
			return 0;
		if (b == 0) 
			return 1;
				
		double res = a;
		long p = 1;
		
		while (p << 1 <= b) {
			res *= res;
			p <<= 1;
		}
		while (p < b) {
			res *= a;
			p++;
		}
		return res;
	}
	
	private double power3(double a, long b) {
		if (a == 0) 
			return 0;
		
		double res = 1;
		long p = b;
		while (p > 1) {
			if ((p & 1) == 1) {
				res *= a;
			}
			a *= a;
			p >>= 1;
		}
		if (p > 0) {
			res *= a;
		}
		return res;
	}
}

