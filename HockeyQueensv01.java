import java.util.ArrayList;

public class HockeyQueensv01 {
	ArrayList<Coordinate> queens = new ArrayList<Coordinate>();
	int size;
	int[][] board;
	Coordinate first;
	Coordinate firstSwitch;
	Coordinate second;
	Coordinate secondSwitch;
	int firstChain = 300;
	int secondChain = 201;
	
	public HockeyQueensv01() {
		size = 1000;
		board = new int[size][size];
		first = new Coordinate(799, 0);
		second = new Coordinate(200, 999);
	}
		
	public void hockeyBoard() {
		for(int i=0; i<=firstChain;i ++)
			queens.add(new Coordinate(first.getY()-(i*2),first.getX()+i));
		
		for(int i=0; i<=firstChain;i ++)
			queens.add(new Coordinate(second.getY()+(i*2), second.getX()-i));
		
		
		firstSwitch = new Coordinate(first.getY()-(2*firstChain),first.getX()+firstChain);
		secondSwitch = new Coordinate(second.getY()+(2*firstChain),second.getX()-firstChain);
		for(int i=1; i<secondChain-1; i++)
			queens.add(new Coordinate(firstSwitch.getY()-i,firstSwitch.getX()+(i*2)));
			
		for(int i=1; i<secondChain-1; i++)
			queens.add(new Coordinate(secondSwitch.getY()+i,secondSwitch.getX()-(i*2)));
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
			if(i==firstChain)
				System.out.println("END LEFT CHAIN");
			if(i==2*firstChain+1)
				System.out.println("END RIGHT CHAIN");
			if(i==((2*firstChain)+secondChain)-1)
				System.out.println("END 2nd LEFT CHAIN");
			i++;
		}
		System.out.println();
		System.out.println("Board: "+board.length+" by "+board.length);
		System.out.println("Total Queens: "+ queens.size());
		System.out.println("Diagonal collisions == "+diagonalCollisions());
		System.out.println("Collision on horizontal == "+checkHorizCollisions());
		System.out.println("Collision on vertical == "+checkVertCollisions());
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
	
	
	public boolean checkHorizCollisions() {
		for(Coordinate m: queens)
			for(Coordinate n: queens)
				if(m.getX()==n.getX())
					if(m.getY()!=n.getY())
						return true;
		return false;
	}
	
	public boolean checkVertCollisions() {
		for(Coordinate m: queens)
			for(Coordinate n: queens)
				if(m.getY()==n.getY())
					if(m.getX()!=n.getX())
						return true;
		return false;
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
		

	public void runTest() {
			hockeyBoard();
			setBoard();
			printLocs();
			System.out.println();
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
		HockeyQueensv01 rl = new HockeyQueensv01();//integer.parseint(args[0]);
		rl.runTest();
	}
}
