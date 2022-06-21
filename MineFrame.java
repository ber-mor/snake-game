import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class MineFrame extends MyFrame{
	private int x = 10, y = 10;
	private CellButton[][] cell;
	private JPanel grid;


	public MineFrame(){
		grid = new JPanel(new GridLayout(x,y,1,1));
		cell = new CellButton[x][y];
		addCells();
		setBombs();
		innerPanel.add(grid);
		pack(); 
		setLocationRelativeTo(null);
	}

	public void addCells(){
		for(int i=0; i<x; i++)
			for(int j=0; j<y; j++){
				cell[i][j] = new CellButton();
				cell[i][j].setX(i);
				cell[i][j].setY(j);
				addActionListeners(cell[i][j]);
				grid.add(cell[i][j]);
			}
	}

	public void addActionListeners(CellButton b){
		b.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				analizeCell(e);
			}
		});
	}

	public void setBombs(){
		int bombs = 10;
		int x, y;
		while(bombs != 0){
			x = (int) (Math.random()*10);
			y = (int) (Math.random()*10);

			if (!cell[x][y].hasBomb()){
				cell[x][y].setBomb();
				bombs--;
			}
		}
	}

	public void analizeCell(ActionEvent e){
		if(e.getSource() instanceof CellButton){
			
		}
	}

	public static void main(String[] args) {
		new MineFrame().setVisible(true);	
	}
}