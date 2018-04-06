import java.util.ArrayList;

public class NnQueens {
	private ArrayList<Coordinate> queens;
	private int minI;
	private int maxI;
	private int size;
	private int[][] board;
	
	public NnQueens(int n, int m) {
		minI = n;
		maxI = m;
		size = (minI * 6)-1;
		board = new int[size][size];
		queens = new ArrayList<Coordinate>();
	}
	
	public void placeQueens(int n) {
		queens = new ArrayList<Coordinate>();
			for(int i = 0; i<2*getMin(); i++) {
				queens.add(new Coordinate(i, (i*3)+1));
				queens.add(new Coordinate(2*(size/3)+i+1, (i*3)));
			}
			for(int i = 0; i<getMin(); i++) {
				queens.add(new Coordinate((size/2)+i, (size/2)-(3*i)));
				if(i!=0)
					queens.add(new Coordinate((size/2)-i, (size/2)+(3*i)));
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
		System.out.println("Diagonal collisions == "+diagonalCollisions());
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
	
	
	public void runTest() {
		for(int x=getMin(); x<getMax(); x++) {
			queens = new ArrayList<Coordinate>(); 
			placeQueens(6*x);
			setBoard();
			printLocs();
			printBoard();
			incrementSize(6);
			incrementBoard();
			minI++;
		}
		
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
		NnQueens rl = new NnQueens(1, 10);
		rl.runTest();
	}
}
