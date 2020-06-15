package algo2;

import java.math.BigDecimal;
import java.math.BigInteger;

import tester.Task;
import tester.Tester;

public class FibonacciNumber implements Task {
	
	public static void main(String[] args) {		
		Task task = new FibonacciNumber();
		Tester tester = new Tester(task, "C:\\Users\\Elena\\Documents\\Courses\\Algorithms\\Lesson 2\\4.Fibo");
		tester.runTests();
	}

	@Override
	public String[] run(String[] data) {
		int n = Integer.parseInt(data[0]);
		BigInteger result = getFibonacciNumber4(n);
		return new String[] {String.valueOf(result)};
	}
	
	@Override
	public String getName() {
		return "N-th Fibonacci Number";
	}
	
	private BigInteger getFibonacciNumber1(int n) {
		if (n == 0)
			return BigInteger.ZERO;
		if (n == 1 || n == 2)
			return BigInteger.ONE;
		return (getFibonacciNumber1(n - 1).add(getFibonacciNumber1(n - 2)));		
	}
	
	private BigInteger getFibonacciNumber2(int n) {
		if (n == 0)
			return BigInteger.ZERO;
		
		BigInteger a = BigInteger.ONE, b = BigInteger.ONE, res = BigInteger.ONE;
		
		for (int i = 3; i <= n; i++) {
			res = a.add(b);
			a = b;
			b = res;
		}
		return res;		
	}
	
	public BigInteger getFibonacciNumber3(int n) {
		if (n == 0)
			return BigInteger.ZERO;
		
		BigDecimal squareRootOf5 = BigDecimal.valueOf(Math.sqrt(5));

		BigDecimal n1 = power(BigDecimal.ONE.add(squareRootOf5), n);
		BigDecimal n2 = power(BigDecimal.ONE.subtract(squareRootOf5), n);
		BigDecimal n3 = power(BigDecimal.valueOf(2), n).multiply(squareRootOf5);
		
		BigDecimal res = n1.subtract(n2).divide(n3);
				
		return res.toBigInteger();
	}
	
	public BigInteger getFibonacciNumber4(int n) {
		if (n == 0)
			return BigInteger.ZERO;
		Matrix2D res = Matrix2D.IDENTITY;
		Matrix2D base = Matrix2D.BASE;
		while (n > 1) {
			if ((n & 1) == 1) {
				res = res.multiply(base);
			}
			base = base.multiply(base);
			n >>= 1;
		}
		return res.multiply(base).a21;
	}
	
	private BigDecimal power(BigDecimal a, int b) {
		if (a.equals(BigDecimal.ZERO)) return BigDecimal.ZERO;
		
		BigDecimal res = BigDecimal.ONE;
		int p = b;
		while (p > 1) {
			if (p % 2 == 1) {
				res = res.multiply(a);
			}
			a = a.multiply(a);
			p /= 2;
		}
		if (p > 0) {
			res = res.multiply(a);
		}
		return res;
	}
	
	static class Matrix2D {
		
		static Matrix2D IDENTITY = new Matrix2D(BigInteger.ONE, BigInteger.ZERO, BigInteger.ZERO, BigInteger.ONE);
		static Matrix2D BASE = new Matrix2D(BigInteger.ONE, BigInteger.ONE, BigInteger.ONE, BigInteger.ZERO);
		
		private BigInteger a11;
		private BigInteger a12;
		private BigInteger a21;
		private BigInteger a22;
		
		public Matrix2D(BigInteger ... a) {
			a11 = a[0];
			a12 = a[1];
			a21 = a[2];
			a22 = a[3];			
		}
		
		public Matrix2D multiply(Matrix2D b) {
			// 3 multiplications for a symmetric matrix (a12 = a21)
			BigInteger res11 = a11.multiply(b.a11).add(a12.multiply(b.a21));
			BigInteger res12_21 = a11.multiply(b.a12).add(a12.multiply(b.a22));
			BigInteger res22 = a21.multiply(b.a12).add(a22.multiply(b.a22));
			
			return new Matrix2D(res11, res12_21, res12_21, res22);
			
			// 4 multiplications for a general case
			/*
			return new Matrix2D(
				a11.multiply(b.a11).add(a12.multiply(b.a21)),
				a11.multiply(b.a12).add(a12.multiply(b.a22)),
				a21.multiply(b.a11).add(a22.multiply(b.a21)),
				a21.multiply(b.a12).add(a22.multiply(b.a22)));
			*/
		}
	}
}
