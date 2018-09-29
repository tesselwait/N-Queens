import java.util.ArrayList;

//[https] [://] [www.] [sciencedirect.com/science/article/pii/S0012365X07010394]
//N-Queens board solutions for all n where n%6 != 2||3
public class TwoLineQueens {
	private ArrayList<Coordinate> queens;
	private int min;
	private int max;
	private int size;
	private int[][] board;
	
	public TwoLineQueens(int n, int m) {
		min = n;
		max = m;
		size = min;
		board = new int[size][size];
		queens = new ArrayList<Coordinate>();
	}
	
	public void placeQueens(int n) {
		queens = new ArrayList<Coordinate>();
		if(size%6==0||size%6==4) {
			for(int i = 0; i<size/2; i++) {
				queens.add(new Coordinate(i, (i*2)+1));
				queens.add(new Coordinate((size/2)+i, (i*2)));
			}
		}
		if(size%6==1||size%6==5) {
			for(int i = 0; i<size/2; i++) {
				queens.add(new Coordinate(i, (i*2)+1));
				queens.add(new Coordinate((size/2)+i, (i*2)));
			}
			queens.add(new Coordinate(size-1, size-1));
		}
		/*if(size%6==2) {
			for(int i = 0; i<size/2; i++) {
				queens.add(new Coordinate(i, (i*2)+1));
				queens.add(new Coordinate((size/2)+i, (i*2)));
			}
		}
		if(size%6==3) {
			for(int i = 0; i<size/2; i++) {
				queens.add(new Coordinate(i, (i*2)+1));
				queens.add(new Coordinate((size/2)+i, (i*2)));
			}
			queens.add(new Coordinate(size-1, size-1));
		}*/	
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
					System.out.print("-");
				else
					if(board[i][j]==1)
						System.out.print("Q");
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
	
	public void incrementBoard(int a) {
		board = new int[board.length+a][board.length+a];
	}
	
	
	public void runTest() {
		for(int x=getMin(); x<getMax(); x++) {
			queens = new ArrayList<Coordinate>();
			if(x%6!=2&&x%6!=3) {
				queens = new ArrayList<Coordinate>();
				placeQueens(x);
				setBoard();
				printLocs();
				printBoard();
				}
			incrementSize(1);
			incrementBoard(1);
		}
		
	}
	public int getMin() {
		return min;
	}
	
	public int getMax() {
		return max;
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
		TwoLineQueens rl = new TwoLineQueens(5, 45);
		rl.runTest();
	}
}
