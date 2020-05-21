package algo3;

import java.util.HashMap;
import java.util.Map;

import tester.Task;
import tester.Tester;

public class LongDistance implements Task {
	
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
		Task task = new LongDistance();
		Tester tester = new Tester(task, "C:\\Users\\Elena\\Documents\\Courses\\Algorithms\\Lesson 3\\0.BITS\\4.Bitboard - Дальнобойщики");
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

		long l = 0xFEFEFEFEFEFEFEFEL;
		long r = 0x7F7F7F7F7F7F7F7FL;
		
		// calculate positions of all white and black pieces and negate
		long w = 0L;
		long b = 0L;
		for (Piece p : Piece.values()) {
			if (Character.isUpperCase(p.getValue()))
				w += board[p.ordinal()];
			else 
				b += board[p.ordinal()];
		}
		w = ~w;
		b = ~b;

		// calculate and return moves
		return new String[] {
				Long.toUnsignedString(rookMoves(board[Piece.whiteRooks.ordinal()], l, r, w, b)),
				Long.toUnsignedString(bishopMoves(board[Piece.whiteBishops.ordinal()], l, r, w, b)),
				Long.toUnsignedString(rookMoves(board[Piece.whiteQueens.ordinal()], l, r, w, b) + bishopMoves(board[Piece.whiteQueens.ordinal()], l, r, w, b))
		};
	}

	@Override
	public String getName() {
		return "Bitboard - Long Distance";
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
	
	private long rookMoves(long pos, long l, long r, long w, long b) {
		// check if initial position on left/right border - no left/right moves
		long ml = pos & l;
		long mr = pos & r;
		long mu = pos;
		long md = pos;
		
		long moves = 0L;
		
		while (Long.compareUnsigned((ml | mr | md | mu), 0L) > 0) {
			// shift and check if new place is empty - only check for whites
			ml = (ml >>> 1L) & w;
			mr = (mr <<  1L) & w;
			mu = (mu <<  8L) & w;
			md = (md >>> 8L) & w;
			// add to all moves
			moves += ml | mr | md | mu;
			// check if we reached the border or any black piece
			ml &= l & b;
			mr &= r & b;
			mu &= b;
			md &= b;
		}
		return moves;
	}
	
	private long bishopMoves(long pos, long l, long r, long w, long b) {
		// check if initial position on left/right border - no left/right moves
		long mlu = pos & l;
		long mru = pos & r;
		long mld = pos & l;
		long mrd = pos & r;
		
		long moves = 0L;
		
		while (Long.compareUnsigned((mlu | mru | mld | mrd), 0L) > 0) {
			// shift and check if new place is empty - only check for white
			mlu = (mlu <<  7L) & w;
			mru = (mru <<  9L) & w;
			mld = (mld >>> 9L) & w;
			mrd = (mrd >>> 7L) & w;
			// add to all moves
			moves += mlu | mru | mld | mrd;
			// check if we reached the border or any black piece
			mlu &= l & b;
			mru &= r & b;
			mld &= l & b;
			mrd &= r & b;
		}
		return moves;
	}
}
