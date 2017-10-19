import java.util.ArrayList;
public class RecurFiveNQueensPlus {
	ArrayList<Coordinate> queens;
	int size;
	int m;
	int[][] board;
	Coordinate first;
	
	public RecurFiveNQueensPlus(int g) {
		m=g;
		size = ((int)(Math.pow(5, m+1))-(2*(int)Math.pow(5, m)+2*(int)Math.pow(5, m-1)));
		board = new int[size][size];
		first = new Coordinate(size/2, size/2);
		queens = new ArrayList<Coordinate>(size);
	}
	
	////Template////----***Method Collides***----//
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
			if(r==getM()) {
				queens.add(new Coordinate(loc.getY()+5, loc.getX()-3));
				queens.add(new Coordinate(loc.getY()+4, loc.getX()-6));
				queens.add(new Coordinate(loc.getY()-3, loc.getX()-5));
				queens.add(new Coordinate(loc.getY()-6, loc.getX()-4));
				queens.add(new Coordinate(loc.getY()-5, loc.getX()+3));
				queens.add(new Coordinate(loc.getY()-4, loc.getX()+6));
				queens.add(new Coordinate(loc.getY()+3, loc.getX()+5));
				queens.add(new Coordinate(loc.getY()+6, loc.getX()+4));
			}	
		}
		else {
			int s = r-1;
			recurBoard(loc, s);
			recurBoard(new Coordinate(loc.getY()-(1*(int)Math.pow(5, s)), loc.getX()-(2*(int)Math.pow(5, s))), s);
			recurBoard(new Coordinate(loc.getY()+(2*(int)Math.pow(5, s)), loc.getX()-(1*(int)Math.pow(5, s))), s);
			recurBoard(new Coordinate(loc.getY()-(2*(int)Math.pow(5, s)), loc.getX()+(1*(int)Math.pow(5, s))), s);
			recurBoard(new Coordinate(loc.getY()+(1*(int)Math.pow(5, s)), loc.getX()+(2*(int)Math.pow(5, s))), s);
			if(r==getM()) {
				recurBoard(new Coordinate(loc.getY()+(5*(int)Math.pow(5, s)), loc.getX()-(3*(int)Math.pow(5, s))), s);
				recurBoard(new Coordinate(loc.getY()+(4*(int)Math.pow(5, s)), loc.getX()-(6*(int)Math.pow(5, s))), s);
				recurBoard(new Coordinate(loc.getY()-(3*(int)Math.pow(5, s)), loc.getX()-(5*(int)Math.pow(5, s))), s);
				recurBoard(new Coordinate(loc.getY()-(6*(int)Math.pow(5, s)), loc.getX()-(4*(int)Math.pow(5, s))), s);
				recurBoard(new Coordinate(loc.getY()-(5*(int)Math.pow(5, s)), loc.getX()+(3*(int)Math.pow(5, s))), s);
				recurBoard(new Coordinate(loc.getY()-(4*(int)Math.pow(5, s)), loc.getX()+(6*(int)Math.pow(5, s))), s);
				recurBoard(new Coordinate(loc.getY()+(3*(int)Math.pow(5, s)), loc.getX()+(5*(int)Math.pow(5, s))), s);
				recurBoard(new Coordinate(loc.getY()+(6*(int)Math.pow(5, s)), loc.getX()+(4*(int)Math.pow(5, s))), s);
			}
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
			System.out.print("[X: " + loc.getX() + ", Y: " + loc.getY()+ "]  ");
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
	
	public Coordinate getFirst() {
		return first;
	}
	
	public int getM() {
		return m;
	}
	
	public void runTest() {
			recurBoard(getFirst(), getM());
			setBoard();
			printLocs();
			System.out.println();
			printBoard();
	}
	
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
		RecurFiveNQueensPlus rl = new RecurFiveNQueensPlus(2);
		rl.runTest();
	}
}
