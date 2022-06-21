public class Node{
	private int x;
	private int y;

	public Node(int x, int y){
		this.x = x;
		this.y = y;
	}

	public void setX(int x){
		this.x = x;
	}

	public void setY(int y){
		this.y = y;
	}

	public void setCoordinates(int x, int y){
		this.x = x;
		this.y = y;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public void move(int dx, int dy){
		x = x + dx;
		y = y + dy;
	}

}