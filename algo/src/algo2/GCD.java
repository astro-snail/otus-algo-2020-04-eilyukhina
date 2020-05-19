package algo2;

import java.math.BigInteger;

import tester.Task;
import tester.Tester;

public class GCD implements Task {
	
	public static void main(String[] args) {
		Task task = new GCD();
		Tester tester = new Tester(task, "C:\\Users\\Elena\\Documents\\Courses\\Algorithms\\Lesson 2\\2.GCD");
		tester.runTests();
	}

	@Override
	public String[] run(String[] data) {
		BigInteger a = new BigInteger(data[0]);
		BigInteger b = new BigInteger(data[1]);
		BigInteger result = findGCD3(a, b);
		return new String[] {String.valueOf(result)};
	}

	@Override
	public String getName() {
		return "Greatest Common Divisor (Eucleadean Algorithm)";
	}
	
	private BigInteger findGCD1(BigInteger a, BigInteger b) {
		if (a.equals(BigInteger.ZERO)) return b;
	    if (b.equals(BigInteger.ZERO)) return a;
		while (!a.equals(b)) {
			if (a.compareTo(b) == 1) {
				a = a.subtract(b);
			} else {
				b = b.subtract(a);
			}
		}
		return a; 
	}

	private BigInteger findGCD2(BigInteger a, BigInteger b) {
		while (!(a.equals(BigInteger.ZERO) || b.equals(BigInteger.ZERO))) {
			if (a.compareTo(b) == 1) {
				a = a.mod(b);
			} else {
				b = b.mod(a);
			}
		}
		return (a.equals(BigInteger.ZERO) ? b : a); 
	}
	
	private BigInteger findGCD3(BigInteger a, BigInteger b) {
	    if (a.equals(BigInteger.ZERO)) return b;
	    if (b.equals(BigInteger.ZERO)) return a;

		int shift = 0;
		
	    while ((a.or(b)).and(BigInteger.ONE).equals(BigInteger.ZERO)) {
	        shift++;
	        a = a.shiftRight(1);
	        b = b.shiftRight(1);
	    }
	 
	    while (a.and(BigInteger.ONE).equals(BigInteger.ZERO))
	        a = a.shiftRight(1);
	 
	    do {
	        while (b.and(BigInteger.ONE).equals(BigInteger.ZERO))
	            b = b.shiftRight(1);
	        if (a.compareTo(b) == 1) {
	            BigInteger temp = b;
	            b = a;
	            a = temp;
	        }
	        b = b.subtract(a);
	    } while (!b.equals(BigInteger.ZERO));
	    
	    return a.shiftLeft(shift);
	}
}
