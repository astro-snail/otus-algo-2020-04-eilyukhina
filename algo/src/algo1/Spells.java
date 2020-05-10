package algo1;

public class Spells {
	
	public static void main(String[] args) {
		
		int n = 25;
		
		// 1
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print((i < j) ? "# ": ". ");
			}
			System.out.println();
		}
		System.out.println();
		
		// 2
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print((i == j) ? "# ": ". ");
			}
			System.out.println();
		}
		System.out.println();
		
		// 3
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print((i + j == n - 1) ? "# ": ". ");
			}
			System.out.println();
		}
		System.out.println();
		
		// 4
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print((i + j - 5 < n) ? "# ": ". ");
			}
			System.out.println();
		}
		System.out.println();
		
		// 5
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print((i == j / 2) ? "# ": ". ");
			}
			System.out.println();
		}
		System.out.println();	
		
		// 6
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print((i < 10 || j < 10) ? "# ": ". ");
			}
			System.out.println();
		}
		System.out.println();	
		
		// 7
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print((i > n - 10 && j > n - 10) ? "# ": ". ");
			}
			System.out.println();
		}
		System.out.println();	
		
		// 8
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print((i * j == 0) ? "# ": ". ");
			}
			System.out.println();
		}
		System.out.println();
		
		// 9
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(Math.abs(i - j) > 10 ? "# ": ". ");
			}
			System.out.println();
		}
		System.out.println();
		
		// 10
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(j >= i + 1 && j <= 2*i + 1 ? "# ": ". ");
			}
			System.out.println();
		}
		System.out.println();
		
		// 11
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print((i - 1)*(j - 1)*(i - n + 2)*(j - n + 2 ) == 0 ? "# ": ". ");
			}
			System.out.println();
		}
		System.out.println();
		
		// 12
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(i*i + j*j <= 20*20 ? "# ": ". ");
			}
			System.out.println();
		}
		System.out.println();
		
		// 13
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(Math.abs(i + j - n + 1) < 5 ? "# ": ". ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
