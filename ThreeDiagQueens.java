import java.util.ArrayList;

public class ThreeDiagQueens {
	private ArrayList<Coordinate> queens;
	private int minI;
	private int maxI;
	private int size;
	private int[][] board;
	private boolean[] validSolution;
	private int solutionCounter;
	
	//Base pattern:
	///////////////////
	// _ _ _ _ Q _ _ //
	// _ _ _ _ _ _ Q //
	// _ Q _ _ _ _ _ //  n=1, board increments by 4 for each n++;
	// _ _ _ Q _ _ _ //  Every 3rd board collides along the diagonal
	// _ _ _ _ _ Q _ //
	// Q _ _ _ _ _ _ //
	// _ _ Q _ _ _ _ //
	///////////////////
	public ThreeDiagQueens(int n, int m) {
		minI = n;
		maxI = m;
		validSolution = new boolean[maxI-minI];
		solutionCounter = 0;
		size = (minI * 4)+3;
		board = new int[size][size];
		queens = new ArrayList<Coordinate>();
	}
	
	public void placeQueens(int n) {
		queens = new ArrayList<Coordinate>();
			for(int i = 0; i<getMin()+1; i++) {
				queens.add(new Coordinate(i*2, getMin()-i));
				queens.add(new Coordinate((size-1)-(i*2), (size-1)-getMin()+i));
			}
			for(int i = 0; i<2*getMin()+1; i++) {
				queens.add(new Coordinate(1+(2*i), size-(getMin()+2)-i));
			}
	}
	
	public void setBoard() {
		for(int i=0; i<size; i++)
			for(int j=0; j<size; j++)
				board[i][j] = 0;
		for(Coordinate loc: queens)
			board[(int)loc.getY()][(int)loc.getX()] = 1;
	}

	public void printLocs() {
		int i = 0;
		for(Coordinate loc: queens) {
			System.out.print("[X: " + loc.getX() + ", Y: " + loc.getY()+ "] ");
			if((i%10)-9==0)
				System.out.println();
			i++;
		}
		System.out.println();
		System.out.println("Board: "+board.length+" by "+board.length);
		System.out.println("Total Queens: "+ queens.size());
		boolean diagColl = diagonalCollisions();
		System.out.println("Diagonal collisions == "+diagColl);
		validSolution[solutionCounter] = !diagColl;
		solutionCounter++;
		diagColl = true;
	}

	public void printBoard()
	{
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				if(board[i][j]==0)
					System.out.print("- ");
				else
					if(board[i][j]==1)
						System.out.print("Q ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public boolean diagonalCollisions(){
		for(Coordinate m: queens) {
			for(Coordinate n: queens) {
				double dy = n.getY() - m.getY();
				double dx = n.getX() - m.getX();
				double slope = dy/dx;
				if(slope==1.0||slope==-1.0)
					return true;
			}
		}
		return false;	
	}
	
	
	public int getSize() {
		return size;
	}
	
	public void incrementSize(int a) {
		size+=a;
	}
	
	public void setSize(int a) {
		size = a;
	}
	
	public void incrementBoard() {
		board = new int[size][size];
	}
	
	public void printValidSolutionSet() {
		System.out.println();
		int i = 7;
		int j = 1;
		System.out.println("Valid Solution Set: {");
		for(boolean a: validSolution) {
			System.out.print("["+i+": "+a+"] ");
			i+=4;
			if(j%3==0)
				System.out.println();
			j++;
		}
		System.out.println("}");
	}
	
	
	public void runTest() {
		for(int x=getMin(); x<getMax(); x++) {
			queens = new ArrayList<Coordinate>(); 
			placeQueens((4*x)+3);
			setBoard();
			printLocs();
			printBoard();
			incrementSize(4);
			incrementBoard();
			minI++;
		}
		printValidSolutionSet();
	}
	public int getMin() {
		return minI;
	}
	
	public int getMax() {
		return maxI;
	}
	
	final class Coordinate {
		private int x;
		private int y;

		public Coordinate(int a, int b) {
			x=a;
			y=b;
		}

		public int getY() {
			return y;
		}

		public int getX() {
			return x;
		}
	}
	
	
	public static void main(String[] args) {
		ThreeDiagQueens rl = new ThreeDiagQueens(1, 20);
		rl.runTest();
	}
}
