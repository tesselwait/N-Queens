import java.util.ArrayList;
//Author: Robert Larson
//1,000 by 1,000 board solution.
public class FortyNQueens {
private ArrayList<Coordinate> queens;
private int size;
private int m;
private int[][] board;
private Coordinate first;
private ArrayList<Coordinate> forty;
private ArrayList<Coordinate> fortyTwo;

public FortyNQueens(int l) {
m=l;
size = 1000;
board = new int[size][size];
queens = new ArrayList<Coordinate>();
forty = new ArrayList<Coordinate>();
fortyTwo = new ArrayList<Coordinate>();
}

public void addForty() {
forty.add(new Coordinate(0,0));
forty.add(new Coordinate(2,1));
forty.add(new Coordinate(4,2));
forty.add(new Coordinate(1,3));
forty.add(new Coordinate(3,4));
forty.add(new Coordinate(8,5));
forty.add(new Coordinate(10,6));
forty.add(new Coordinate(12,7));
forty.add(new Coordinate(14,8));
forty.add(new Coordinate(5,9));
forty.add(new Coordinate(7,10));
forty.add(new Coordinate(18,11));
forty.add(new Coordinate(20,12));
forty.add(new Coordinate(22,13));
forty.add(new Coordinate(29,14));
forty.add(new Coordinate(31,15));
forty.add(new Coordinate(33,16));
forty.add(new Coordinate(35,17));
forty.add(new Coordinate(37,18));
forty.add(new Coordinate(39,19));
forty.add(new Coordinate(30,20));
forty.add(new Coordinate(32,21));
forty.add(new Coordinate(34,22));
forty.add(new Coordinate(36,23));
forty.add(new Coordinate(38,24));
forty.add(new Coordinate(19,25));
forty.add(new Coordinate(21,26));
forty.add(new Coordinate(13,27));
forty.add(new Coordinate(17,28));
forty.add(new Coordinate(9,29));
forty.add(new Coordinate(11,30));
forty.add(new Coordinate(23,31));
forty.add(new Coordinate(16,32));
forty.add(new Coordinate(6,33));
forty.add(new Coordinate(27,34));
forty.add(new Coordinate(25,35));
forty.add(new Coordinate(15,36));
forty.add(new Coordinate(28,37));
forty.add(new Coordinate(26,38));
forty.add(new Coordinate(24,39));
}

public void stretchList() {
for(Coordinate b: forty)
fortyTwo.add(new Coordinate((b.getY()*25)+12, (b.getX()*25)+12));
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
FortyNQueens rl = new FortyNQueens(2);
rl.runTest();
}
