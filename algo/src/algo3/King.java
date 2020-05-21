package algo3;

import tester.Task;
import tester.Tester;

public class King implements Task {
	
	public static void main(String[] args) {
		Task task = new King();
		Tester tester = new Tester(task, "C:\\Users\\Elena\\Documents\\Courses\\Algorithms\\Lesson 3\\0.BITS\\1.Bitboard - Король");
		tester.runTests();	
	}

	@Override
	public String[] run(String[] data) {
		int n = Integer.parseInt(data[0]);
		String[] res = new String[2];
		
		long k = 1L << n;
		long kl = k & 0xFEFEFEFEFEFEFEFEL;
		long kr = k & 0x7F7F7F7F7F7F7F7FL;
		
		long mask = 
				(kl <<  7) | (k <<  8) | (kr <<  9) |
				(kl >>> 1) | 		     (kr <<  1) |
				(kl >>> 9) | (k >>> 8) | (kr >>> 7);
		
		res[1] = Long.toUnsignedString(mask);
		
		int moves = 0;
		while (Long.compareUnsigned(mask, 0L) > 0) {
			//moves += mask & 1L;
			//mask >>>= 1L;
			moves++;
			mask &= mask - 1L; 
		}

		res[0] = String.valueOf(moves);
		
		return res;
	}

	@Override
	public String getName() {
		return "Bitboard - King";
	}
}
