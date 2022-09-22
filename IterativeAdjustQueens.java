import java.util.Random;
public class IterativeAdjustQueens {
	int size;
	private int[][] board;
	private int[] queens;
	Random gen;

	public IterativeAdjustQueens(int x) {
		size = x;
		board = new int[size][size];
		gen = new Random();
		queens = new int[size];
	}

	public void solve() {
		final long startTime = System.currentTimeMillis();
		System.out.println(size+"-Queens Solution:");
		int count=0;
		setQueens();
		int idx=0;
		while(!solved()) {
			if(count%1000<500)
				adjustQueen(idx, false);
			else
				adjustQueen(idx, true);
			count++;
			idx++;
			if(idx==size)
				idx=0;
		}
		final long endTime = System.currentTimeMillis();
		printBoard();
		System.out.println("Time: " + (endTime - startTime)/1000.0+" seconds");
		System.out.println();
	}

	public void adjustQueen(int x, boolean adjustAll) {
		int startIdx = queens[x];
		int minCollisions=size;
		int minCollIndex=0;
		int col=0;
		for(int i=0; i<size; i++) {
			col = numCollisions(x, i);
			if(i!=startIdx || (col==0 && adjustAll == true)) {
				if(col < minCollisions){
					minCollisions = col;
					minCollIndex=i;
				}
			}
		}
		for(int i=0; i<size; i++) {
			board[x][i]=0;
		}
		board[x][minCollIndex]=1;
		queens[x]=minCollIndex;
	}

	public int numCollisions(int x, int y) {
		int collisions=0;
		for(int i = 0; i<size; i++) {
			if(board[i][y] == 1 && i != x)
				collisions++;
		}
		int t = 1;
		while(x-t >= 0 && x-t<size && y-t>-1 && y-t < size) {
			if(board[x-t][y-t]==1)
				collisions++;
			t++;
		}
		t=1;
		while(x+t >= 0 && x+t<size && y-t>-1 && y-t < size) {
			if(board[x+t][y-t]==1)
				collisions++;
			t++;
		}
		t=1;
		while(x+t >= 0 && x+t<size && y+t>-1 && y+t < size) {
			if(board[x+t][y+t]==1)
				collisions++;
			t++;
		}
		t=1;
		while(x-t >= 0 && x-t<size && y+t>-1 && y+t < size) {
			if(board[x-t][y+t]==1)
				collisions++;
			t++;
		}
		return collisions;
	}

	public boolean solved() {
		for(int i=0; i<size; i++){
			if(numCollisions(i, queens[i])>0)
				return false;
		}
		return true;
	}

	public void setQueens() {
		for(int i=0; i<size; i++) {
			int loc = gen.nextInt(size);
			board[i][loc] = 1;
			queens[i]=loc;
		}
	}

	public void printBoard()
	{
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				if(board[i][j]==0)
					System.out.print("- ");
				else
					if(board[i][j]==1)
						System.out.print("Q ");
			}
			System.out.println();
		}
	}

	public static void main(String[]args) {
		if(args.length > 0) {
			IterativeAdjustQueens solver = new IterativeAdjustQueens(Integer.parseInt(args[0]));
			solver.solve();
		}
		else {
			for(int i=8; i<=100; i++) {
				IterativeAdjustQueens solver = new IterativeAdjustQueens(i);
				solver.solve();
			}
		}
	}
}
