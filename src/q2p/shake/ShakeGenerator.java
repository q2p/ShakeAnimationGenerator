package q2p.shake;

import java.util.Random;

public final class ShakeGenerator {
	private static final Random random = new Random();
	private static final int points = 100;
	private static final String varName = "$shiftDifferance";
	private static final int longestOffset = varName.length()+3+1;
	
	public static void main(final String[] args) {
		int[] perc = timings();
		int[][] pos = positions();
		muckUp(perc, pos);
	}

	private static int[][] positions() {
		final int[][] ret = new int[points][2];
		
		for(int i = points-2; i != -1; i--) {
			ret[i][0] = random.nextInt(21)-10;
			ret[i][1] = random.nextInt(21)-10;
		}

		ret[points-1] = ret[0];
		
		return ret;
	}

	private static final void muckUp(final int[] perc, final int[][] pos) {
		for(int i = 0; i < points; i++) {
			int t = perc[i];
			
			if(t < 10) {
				System.out.print(t);
				System.out.print("%   ");
			} else if(t == 100) {
				System.out.print("100% ");
			} else {
				System.out.print(t);
				System.out.print("%  ");
			}
			
			System.out.print("{left: ");
			
			muckPos(pos[i][0]);
			
			System.out.print("; top: ");
			
			muckPos(pos[i][1]);
			
			System.out.print(" }\n");
		}
	}
	
	private static final void muckPos(int pos) {
		
		if(pos == 0) {
			System.out.print(" 0");
			
			muckOffset(1);
		} else if(pos == 10) {
			System.out.print("     ");
			System.out.print(varName);
			
			muckOffset(4+varName.length());
		} else if(pos == -10) {
			System.out.print("    -");
			System.out.print(varName);
			
			muckOffset(4+varName.length());
		} else {
			System.out.print(pos < 0 ? '-' : ' ');
			if(pos < 0)
				pos = -pos;
			
			System.out.print("0.");
			System.out.print(pos);
			System.out.print('*');
			System.out.print(varName);
			
			muckOffset(4+varName.length());
		}
	}

	private static final void muckOffset(int i) {
		for(i = longestOffset - i; i != 0; i--)
			System.out.print(' ');
	}
	
	private static final int[] timings() {
		final int[] ret = new int[points];
		ret[0] = 0;
		ret[points-1] = 100;
		
		for(int i = points-2; i != 0; i--)
			ret[i] = 100*i/points;
		
		return ret;
	}
}