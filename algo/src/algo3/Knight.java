package algo3;

import tester.Task;
import tester.Tester;

public class Knight implements Task {
	
	public static void main(String[] args) {
		Task task = new Knight();
		Tester tester = new Tester(task, "C:\\Users\\Elena\\Documents\\Courses\\Algorithms\\Lesson 3\\0.BITS\\2.Bitboard - Конь");
		tester.runTests();	
	}

	@Override
	public String[] run(String[] data) {
		int n = Integer.parseInt(data[0]);
		String[] res = new String[2];
		
		int[] b = new int[256];
		for (int i = 0; i < b.length; i++) {
			b[i] = popcnt(i);
		}
		
		long k = 1L << n;
		long kll = k & 0xFEFEFEFEFEFEFEFEL;
		long kl  = k & 0xFCFCFCFCFCFCFCFCL;
		long krr = k & 0x7F7F7F7F7F7F7F7FL;
		long kr  = k & 0x3F3F3F3F3F3F3F3FL;
		
		long mask = 
				(kll <<  15) | (krr <<  17) |
				(kl  <<  6)  | (kr  <<  10) |
				(kl  >>> 10) | (kr  >>> 6)  |
				(kll >>> 17) | (krr >>> 15);
		
		res[1] = Long.toUnsignedString(mask);
		
		int moves = 0;
		while (Long.compareUnsigned(mask, 0L) > 0) {
			moves += b[(int)(mask & 0xFFL)];
			mask >>>= 8L;
		}

		res[0] = String.valueOf(moves);
		
		return res;
	}

	@Override
	public String getName() {
		return "Bitboard - Knight";
	}

	private int popcnt(int i) {
		int n = 0;
		while (i > 0) {
			n++;
			i &= i - 1;
		}
		return n;
	}
}
