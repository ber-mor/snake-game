import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

public class CellButton extends JButton{
	private boolean hasBomb = false; 
	private int number;
	private boolean hasNumber = false;
	private boolean isEmpty = false;
	private int x,y;

	public CellButton(){
		setPreferredSize(new Dimension(30,30));
		setBackground(Color.WHITE);
		setFocusable(false);
	}

	public void setX(int x){
		this.x = x;
	}

	public void setY(int y){
		this.y = y;
	}

	public int getXc(){
		return y;
	}

	public int getYc(){
		return y;
	}

	public void setBomb(){
		hasBomb = true;
	}

	public void setNumber(int number){
		this.number = number;
	}

	public void setEmpty(){
		isEmpty = true; 
	}

	public boolean hasBomb(){
		return hasBomb;
	}

	public boolean isEmpty(){
		return !(hasBomb || hasNumber);
	}

	public int getNumber(){
		return number;
	}

	public boolean hasNumber(){
		return hasNumber;
	}
}