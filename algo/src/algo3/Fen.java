package algo3;

import java.util.HashMap;
import java.util.Map;

import tester.Task;
import tester.Tester;

public class Fen implements Task {
	
	enum Piece
	{
		whitePawns('P') ,
		whiteKnights('N'),
		whiteBishops('B'),
		whiteRooks('R'),
		whiteQueens('Q'),
		whiteKing('K'),
		blackPawns('p'),
		blackKnights('n'),
		blackBishops('b'),
		blackRooks('r'),
		blackQueens('q'),
		blackKing('k');
		
		private char value;
		
		private Piece(char value) {
			this.value = value;
		}
		
		public char getValue() {
			return value;			
		}
	}
	
	private static Map<Character, Piece> pieces = new HashMap<>();
	
	static {
		for (Piece p : Piece.values()) {
			pieces.put(p.getValue(), p);
		}
	}
	
	public static void main(String[] args) {
		Task task = new Fen();
		Tester tester = new Tester(task, "C:\\Users\\Elena\\Documents\\Courses\\Algorithms\\Lesson 3\\0.BITS\\3.Bitboard - FEN");
		tester.runTests();	
	}

	@Override
	public String[] run(String[] data) {
		long[] board = new long[Piece.values().length];
		long currentBit = 1L;		
		
		String[] rows = data[0].split("/");
		for (int i = rows.length - 1; i >= 0; i--) {
			currentBit = parseRow(rows[i], currentBit, board);
		}
		
		// change type of result array from long to String
		String[] res = new String[Piece.values().length];
		for (int i = 0; i < Piece.values().length; i++) {
			res[i] = Long.toUnsignedString(board[i]);
		}
		return res;
	}

	@Override
	public String getName() {
		return "FEN";
	}
	
	private long parseRow(final String row, long currentBit, long[] board) {
		int i = 0;
		while (i < row.length()) {
			char ch = row.charAt(i++);
			if (Character.isDigit(ch)) {
				currentBit <<= (long)Character.getNumericValue(ch);
			} else {
				board[pieces.get(ch).ordinal()] += currentBit;
				currentBit <<= 1L;
			}
		}
		return currentBit;
	}
}
