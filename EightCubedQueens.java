import java.util.ArrayList;
//Robert Larson
//Novel 8-Queens fundamental solution -> 1,000-Queens solution
public class EightCubedQueens {
	private ArrayList<Coordinate> queens;
	private int size;
	private int m;
	private int[][] board;
	private Coordinate first;
	private ArrayList<Coordinate> forty;
	private ArrayList<Coordinate> fortyTwo;
	
	public EightCubedQueens(int g) {
		m=g;
		size = 1000;
		board = new int[size][size];
		queens = new ArrayList<Coordinate>();
		forty = new ArrayList<Coordinate>();
		fortyTwo = new ArrayList<Coordinate>();
	}
	
	public void addForty() {
		forty.add(new Coordinate(4,0));
		forty.add(new Coordinate(1,1));
		forty.add(new Coordinate(3,2));
		forty.add(new Coordinate(6,3));
		forty.add(new Coordinate(2,4));
		forty.add(new Coordinate(7,5));
		forty.add(new Coordinate(5,6));
		forty.add(new Coordinate(0,7));
	}
	
	
	public void stretchList() {
		for(Coordinate b: forty)
			fortyTwo.add(new Coordinate((b.getY()*125)+62, (b.getX()*125)+62));
	}

	
	public void thousandBoard(ArrayList<Coordinate> list) {	
		for(Coordinate a:list)
			recurBoard(a, getM());
		}
	
	
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
	
	public void setBoard() {
		for(int i=0; i<size; i++)
			for(int j=0; j<size; j++)
				board[i][j] = 0;
		for(Coordinate loc: queens)
			board[loc.getY()][loc.getX()] = 1;
	}
	
	public void printLocs() {
		int i = 0;
		for(Coordinate loc: queens) {
			System.out.print("[x: " + loc.getX() + " y: " + loc.getY()+ "]  ");
			if((i%10)-9==0)
				System.out.println();
			if((i%100)-99==0)
				System.out.println();
			i++;
		}
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
			addForty();
			stretchList();
			thousandBoard(fortyTwo);
			setBoard();
			printLocs();
			//printBoard();
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
		EightCubedQueens rl = new EightCubedQueens(3);
		rl.runTest();
	}
}
