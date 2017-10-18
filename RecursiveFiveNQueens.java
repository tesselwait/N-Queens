import java.util.ArrayList;
// Author: Robert Larson
public class RecursiveFiveNQueens {
	private ArrayList<Coordinate> queens = new ArrayList<Coordinate>();
	private int size;
	private int m;
	private int[][] board;
	private Coordinate first;
	
	public RecursiveFiveNQueens(int g) {
		m=g;
		size = (int)(Math.pow(5, m));
		board = new int[size][size];
		first = new Coordinate(size/2, size/2);
	}
		
	//Template Pattern:
	// - - - * - //
	// * - - - - //
	// - - * - - //  5^m by 5^m
	// - - - - * //
	// - * - - - //
	//Recursively solves a 5^m by 5^m N-Queens board.
	public void recurBoard(Coordinate loc, int r) {
		if(r==1) {
			queens.add(loc);
			queens.add(new Coordinate(loc.getY()-1, loc.getX()-2));
			queens.add(new Coordinate(loc.getY()+2, loc.getX()-1));
			queens.add(new Coordinate(loc.getY()-2, loc.getX()+1));
			queens.add(new Coordinate(loc.getY()+1, loc.getX()+2));
		}
		else {
			int s = r-1;
			recurBoard(loc, s);
			recurBoard(new Coordinate(loc.getY()-(1*(int)Math.pow(5, s)), loc.getX()-(2*(int)Math.pow(5, s))), s);
			recurBoard(new Coordinate(loc.getY()+(2*(int)Math.pow(5, s)), loc.getX()-(1*(int)Math.pow(5, s))), s);
			recurBoard(new Coordinate(loc.getY()-(2*(int)Math.pow(5, s)), loc.getX()+(1*(int)Math.pow(5, s))), s);
			recurBoard(new Coordinate(loc.getY()+(1*(int)Math.pow(5, s)), loc.getX()+(2*(int)Math.pow(5, s))), s);
		}
	}
	
	//initializes board to all 0's then replaces queen occupied spaces with 1's
	public void setBoard() {
		for(int i=0; i<size; i++)
			for(int j=0; j<size; j++)
				board[i][j] = 0;
		for(Coordinate loc: queens)
			board[(int)loc.getY()][(int)loc.getX()] = 1;
	}
	
	//Lists coordinates of queens, total queens, and results of diagonal collision check
	public void printLocs() {
		int i = 0;
		for(Coordinate loc: queens) {
			System.out.print("[X: " + loc.getX() + ", Y: " + loc.getY()+ "]  ");
			if((i%10)-9==0)
				System.out.println();
			i++;
		}
		System.out.println();
		System.out.println("Board: "+board.length+" by "+board.length);
		System.out.println("Total Queens: "+ queens.size());
		System.out.println("Diagonal collisions == "+diagonalCollisions());
		System.out.println();
	}
	
	//Prints the grid
	public void printBoard()
	{
	   for(int i = 0; i < size; i++)
	   {
	      for(int j = 0; j < size; j++)
	      {
	    	  if(board[(int)i][(int)j]==0)
	    		  System.out.print("-");
	    	  else
	    		  System.out.print("Q");
	      }
	      System.out.println();
	   }
	}
	
	//Checks slope between all queens for collisions on diagonal
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
	
	public Coordinate getFirst() {
		return first;
	}
	
	public int getM() {
		return m;
	}
	
	//Solve board for board size 5^s
	public void runTest() {
		recurBoard(getFirst(), getM());
		setBoard();
		printLocs();
		printBoard();
	}
	
	//Simple Coordinate class
	final class Coordinate {
		private int x;
		private int y;

		public Coordinate(int a, int b) {
			y=a;
			x=b;
		}
		
		public int getY() {
			return y;
		}
		
		public int getX() {
			return x;
		}
	}
	
	public static void main(String[] args) {	
		int seed = Integer.parseInt(args[0]);
		RecursiveFiveNQueens rl = new RecursiveFiveNQueens(seed);
		rl.runTest();
	}
}
