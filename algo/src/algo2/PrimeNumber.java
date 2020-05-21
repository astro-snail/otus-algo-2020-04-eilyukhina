package algo2;

import tester.Task;
import tester.Tester;

public class PrimeNumber implements Task {
	
	public static void main(String[] args) {
		Task task = new PrimeNumber();
		Tester tester = new Tester(task, "C:\\Users\\Elena\\Documents\\Courses\\Algorithms\\Lesson 2\\5.Primes");
		tester.runTests();
	}

	@Override
	public String[] run(String[] data) {
		int n = Integer.parseInt(data[0]);
		int res = getNumberOfPrimes21(n);
		return new String[] {String.valueOf(res)};
	}
	
	@Override
	public String getName() {
		return "Prime Numbers";
	}
	
	private int getNumberOfPrimes11(int n) {
		int number = 0;
		int j = 0;
		
		for (int i = 2; i <= n; i++) {
			if (isPrime1(i)) {
				number++;
			}
		}
		return number;
	}
	
	private int getNumberOfPrimes12(int n) {
		int number = 0;
		int j = 0;
		
		for (int i = 2; i <= n; i++) {
			if (isPrime2(i)) {
				number++;
			}
		}
		return number;
	}
	
	private int getNumberOfPrimes13(int n) {
		int number = 0;
		int j = 0;
		
		for (int i = 2; i <= n; i++) {
			if (isPrime3(i)) {
				number++;
			}
		}
		return number;
	}
	
	private int getNumberOfPrimes14(int n) {
		int[] primes = new int[n];
		int number = 0;
		int j = 0;
		
		for (int i = 2; i <= n; i++) {
			if (isPrime4(i, primes)) {
				primes[j++] = i;
				number++;
			}
		}
		return number;
	}
	
	// решето Эратосфена - 1
	private int getNumberOfPrimes2(int n) {
		boolean[] p = new boolean[n - 1];
		int count = n - 1; // all numbers from 2 to n
		for (int i = 2; i * i <= n; i++) {
			if (!p[i - 2]) {
				for (int j = i * i; j <= n; j += i) {
					if (!p[j - 2]) {
						p[j - 2] = true;
						count--;
					}
				}
			}
		}
		return count;
	}
	
	// решето Эратосфена - 1 - with memory optimisation
	private int getNumberOfPrimes21(int n) {
		int k = (n - 1) % 32 == 0 ? (n - 1) / 32 : (n - 1) / 32 + 1;  
		int[] p = new int[k];
		int count = n - 1; // all numbers from 2 to n
		for (int i = 2; i * i <= n; i++) {
			if ((p[(i - 2) / 32] >>> (i - 2) % 32 & 1) == 0) {
				for (int j = i * i; j <= n; j += i) {
					if ((p[(j - 2) / 32] >>> (j - 2) % 32 & 1) == 0) {
						p[(j - 2) / 32] = p[(j - 2) / 32] ^ (1 << (j - 2) % 32);
						count--;
					}
				}
			}
		}
		return count;
	}
	
	// решето Эратосфена - 2
	private int getNumberOfPrimes3(int n) {
		int[] pr = new int[n];
		int[] lp = new int[n];
		
		int j = 0;
		
		for (int i = 2; i <= n; i++) {
			if (lp[i - 2] == 0) {
				lp[i - 2] = i;
				pr[j++] = i;
			}	
			for (int k = 0; k < j && pr[k] <= lp[i - 2] && pr[k] * i <= n; k++) {
				lp[pr[k] * i - 2] = pr[k];
			}
		}
		return j;
	}
	
	private boolean isPrime1(int n) {
		int q = 0;
		for (int i = 1; i <= n; i++) {
			if (n % i == 0) {
				q++;
			}
		}
		return q == 2;
	}

	private boolean isPrime2(int n) {
		for (int i = 2; i < n; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	private boolean isPrime3(int n) {
		if (n % 2 == 0)
			return n == 2;
		int s = (int) Math.sqrt(n);
		for (int i = 3; i <= s; i += 2) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	private boolean isPrime4(int n, int[] primes) {
		int s = (int) Math.sqrt(n);
		int i = 0;
		while (primes[i] > 0 && primes[i] <= s) {
			if (n % primes[i] == 0) {
				return false;
			}
			i++;
		}
		return true;
	}
}
