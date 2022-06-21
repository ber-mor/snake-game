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

/*
Panel hierarchy:
parentPanel (BorderLayout)
	topPanel (NORTH, BorderLayout)
		buttonsPanel (EAST, default layout)
	centralPanel (CENTER, BorderLayout)
		innerPanel (BorderLayout)
*/

public class MyFrame extends JFrame{
	private Point initialClick;
	protected JPanel innerPanel;
	protected RoundPanel topPanel;

	public MyFrame(){
		initComponents();
		setFocusable(true);
	}

	private void initComponents(){
		setUndecorated(true);
		setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		setTitle("Juegos mamalones");

		RoundPanel parentPanel = new RoundPanel();
		parentPanel.setLayout(new BorderLayout());
		
		topPanel = new RoundPanel();
		topPanel.setLayout(new BorderLayout());

		RoundPanel buttonsPanel = new RoundPanel();

		RoundPanel centralPanel = new RoundPanel();
		centralPanel.setLayout(new BorderLayout());

		innerPanel = new JPanel(new BorderLayout());
		innerPanel.setBackground(new Color(0x0BA291));

		DotButton closeButton = new DotButton("images/red.png", "images/red_l.png");
		DotButton minButton = new DotButton("images/yellow.png", "images/yellow_l.png");

		parentPanel.add(topPanel, BorderLayout.NORTH);
		parentPanel.add(centralPanel, BorderLayout.CENTER);

		topPanel.add(buttonsPanel, BorderLayout.EAST);

		buttonsPanel.add(minButton);
		buttonsPanel.add(closeButton);

		centralPanel.add(new RoundPanel(), BorderLayout.NORTH);
		centralPanel.add(new RoundPanel(), BorderLayout.SOUTH);
		centralPanel.add(new RoundPanel(), BorderLayout.EAST);
		centralPanel.add(new RoundPanel(), BorderLayout.WEST);
		centralPanel.add(innerPanel, BorderLayout.CENTER);

		add(parentPanel);

		closeButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});

		minButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				setState(MyFrame.ICONIFIED);
			}
		});

	    addMouseListener(new MouseAdapter() {
	        @Override
	        public void mousePressed(MouseEvent e) {
	            initialClick = e.getPoint();
	        }
	    });

	    addMouseMotionListener(new MouseMotionAdapter() {
	        @Override
	        public void mouseDragged(MouseEvent e) {           
	            int xi = getLocation().x;
	            int yi = getLocation().y;

	            int dx = e.getX() - initialClick.x;
	            int dy = e.getY() - initialClick.y;

	            int xf = xi + dx;
	            int yf = yi + dy;
	            setLocation(xf, yf);
	        }
	    });

	    pack();
	    setLocationRelativeTo(null);
	}
	public static void main(String[] args) {
		new MyFrame().setVisible(true);
	}
}