import java.util.ArrayList;

//Robert Larson
//8-Queens solution -> 25,000-Queens solution -> general N-Queens solution expander
public class NQueensExpansion {
	private ArrayList<Coordinate> queens;
	private int size;
	private int m;
	private int fiveToM;
	private int[][] board;
	private Coordinate first;
	private ArrayList<Coordinate> seedList;
	private ArrayList<Coordinate> expandedSeedList;
	
	public NQueensExpansion(int l) {
		m=l;
		fiveToM = (int)Math.pow(5, m);
		queens = new ArrayList<Coordinate>();
		seedList = new ArrayList<Coordinate>();
		expandedSeedList = new ArrayList<Coordinate>();
	}
	
	public void addSeedList() {
		seedList.add(new Coordinate(4,0));
		seedList.add(new Coordinate(1,1));
		seedList.add(new Coordinate(3,2));
		seedList.add(new Coordinate(6,3));
		seedList.add(new Coordinate(2,4));
		seedList.add(new Coordinate(7,5));
		seedList.add(new Coordinate(5,6));
		seedList.add(new Coordinate(0,7));
		initializeBoard();
	}

	/*public void generateSeedList(int[][] seedArray){
		for(int i=0; i<seedArray.length; i++)
			seedList.add(newCoordinate(seedArray[i][0], seedArray[i][1]));
	}*/
	
	public void stretchSeedList() {
		for(Coordinate b: seedList)
			expandedSeedList.add(new Coordinate(((b.getY()*fiveToM)+(fiveToM/2)), (b.getX()*fiveToM)+(fiveToM/2)));
	}
	
	public void initializeBoard() {
		size = seedList.size()*fiveToM;
		board = new int[size][size];
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
		System.out.println("Collisions on diagonal == "+diagonalCollisions());
		System.out.println("Collisions on horizontal == "+checkHorizCollisions());
		System.out.println("Collisions on vertical == "+checkVertCollisions());
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
		for(int i=0; i<queens.size(); i++) {
			for(int j=i; j<queens.size(); j++) {
				double dy = queens.get(i).getY() - queens.get(j).getY();
				double dx = queens.get(i).getX() - queens.get(j).getX();
				double slope = dy/dx;
				if(slope==1.0||slope==-1.0)
					return true;
			}
		}
		return false;	
	}
	
	public boolean checkHorizCollisions() {
		for(int i=0; i<queens.size(); i++)
			for(int j=i; j<queens.size(); j++)
				if(queens.get(i).getX()==queens.get(j).getX())
					if(queens.get(i).getY()!=queens.get(j).getY())
						return true;
		return false;
	}
	
	public boolean checkVertCollisions() {
		for(int i=0; i<queens.size(); i++)
			for(int j=i; j<queens.size(); j++)
				if(queens.get(i).getY()==queens.get(j).getY())
					if(queens.get(i).getX()!=queens.get(j).getX())
						return true;
		return false;
	}
			
	public Coordinate getFirst() {
		return first;
	}
	
	public int getM() {
		return m;
	}
	
	public void runTest() {
			addSeedList();
			stretchSeedList();
			thousandBoard(expandedSeedList);
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
		//int k = Integer.parseInt(args[0]);
		NQueensExpansion rl = new NQueensExpansion(5);//NQueensExpansion(k);
		rl.runTest();
	}
}
