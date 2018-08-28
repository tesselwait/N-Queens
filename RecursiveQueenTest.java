import java.util.ArrayList;
import java.awt.Point;

//In place testing of n-Queens solutions for recursive expansion.\
//If a valid n-Queens solution passes the test than that n-Queens solution can be used\
//to recursively expand any known n-Queens solution via substituting\
//every point in the solution with the tested solution.
public class RecursiveQueenTest {
	ArrayList<Point> basePattern;
	
	public RecursiveQueenTest(ArrayList<Point> base) {
		basePattern = base;
	}
	
	//manual entry of a known recursively expandable n-Queens solution
	public RecursiveQueenTest() {
		basePattern = new ArrayList<Point>();
		basePattern.add(new Point(0,2));
		basePattern.add(new Point(10,8));
		basePattern.add(new Point(2,1));
		basePattern.add(new Point(8,9));
		basePattern.add(new Point(4,0));
		basePattern.add(new Point(6,10));
		basePattern.add(new Point(1,7));
		basePattern.add(new Point(3,6));
		basePattern.add(new Point(5,5));
		basePattern.add(new Point(7,4));
		basePattern.add(new Point(9,3));
	}
	
	//alternative 2d int array initialization
	public void generateSeedList(int[][] seedArray){
		for(int i=0; i<seedArray.length; i++)
		basePattern.add(new Point(seedArray[i][0], seedArray[i][1]));
	}
	
	//Test algo runs the two diagonals of every point simulating a check for diagonals collisions\
	//with the adjacent vertical and horizontal copies of base grid.  A necessary and sufficient condition\
	//for recursive expandability.
	public boolean recursiveTest(ArrayList<Point> base) {
		for(Point n: base) {
			for(int i=1; i<base.size(); i++) {
				for(Point m: base)
					if(((n.getX()+i)%base.size()==m.getX() && (n.getY()+i)%base.size()==m.getY()) || ((n.getX()-i)%base.size()==m.getX() && (n.getY()-i)%base.size()==m.getY())) {
						System.out.println("N: x: "+n.getX()+" y: "+ n.getY());
						System.out.println("M: x: "+m.getX()+" y: "+ m.getY());
						System.out.println("i: "+i);
						System.out.println("Board size: "+base.size());
						return false;
					}
			}
		}
		return true;
	}
	
	public ArrayList<Point> getBasePattern(){
		return basePattern;
	}
	
	public static void main(String[] args) {
		RecursiveQueenTest one = new RecursiveQueenTest();
		//generateSeedList(args 0);
		System.out.println(one.recursiveTest(one.getBasePattern()));
	}
}
