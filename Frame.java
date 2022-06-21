import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Point;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.geom.RoundRectangle2D;
import javax.swing.BorderFactory;
import java.awt.Dimension;

/*
Panel hierarchy:
parentPanel (BorderLayout)
	topPanel (NORTH, BorderLayout)
		buttonsPanel (EAST, default layout)
	centralPanel (CENTER, BorderLayout)
		innerPanel (BorderLayout)
*/

public class Frame extends JFrame{
	protected JPanel innerPanel;

	public Frame(){
		initComponents();
	}

	private void initComponents(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		setTitle("Juegos mamalones");
		setResizable(false);

		innerPanel = new JPanel();
		innerPanel.setLayout(new BorderLayout());

		add(innerPanel);

		pack();
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		new Frame().setVisible(true);
	}
}