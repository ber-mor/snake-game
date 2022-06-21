//Imports fro SnakeBoard
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.SwingUtilities;

//Imports for SnakePanel
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.InputMap;
import javax.swing.ActionMap;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import java.awt.Dimension;
import java.awt.Font;

public class SnakeBoard extends JFrame{
	public JLabel scoreLabel;
	public JLabel countdownLabel;

	public SnakeBoard(){
		initComponents();	
	}

	private void initComponents(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Snake Game");

		scoreLabel = new JLabel("0");
		countdownLabel = new JLabel("9");

		scoreLabel.setForeground(new Color(0x464E47));
		countdownLabel.setForeground(new Color(0x464E47));

		JPanel menuPanel = new JPanel();
		menuPanel.setBackground(new Color(170,230,179));
		menuPanel.setPreferredSize(new Dimension(350,30));
		menuPanel.setLayout(new BorderLayout());

		SnakePanel snakePanel = new SnakePanel();
		snakePanel.setBackground(new Color(0x3EC182));
		snakePanel.setPreferredSize(new Dimension(350, 350));

		JPanel timePanel = new JPanel();
		timePanel.setBackground(new Color(170,230,179)); 

		JPanel scorePanel = new JPanel();
		scorePanel.setBackground(new Color(170,230,179));

		timePanel.add(new JLabel("Tiempo: "));
		timePanel.add(countdownLabel);

		scorePanel.add(new JLabel("Puntuación: "));
		scorePanel.add(scoreLabel);

		menuPanel.add(scorePanel,BorderLayout.WEST);
		menuPanel.add(timePanel, BorderLayout.CENTER);

		add(menuPanel, BorderLayout.NORTH);
		add(snakePanel, BorderLayout.CENTER);

		pack();
		setLocationRelativeTo(null);
	}

	public void close(){
		this.dispose();
		int score = Integer.parseInt(scoreLabel.getText());
		new LoserFrame(score).setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	        	new SnakeBoard().setVisible(true);
	        }
      	});	
	}

///////////////////////////////////////////////////////////////////////
	class SnakePanel extends JPanel{
		//Initial message w/instructions / countdownLabel
		private JLabel messageLabel;
		//Repaints JPanel with new values
		private ActionListener movement;
		//This saves the nodes of the snake 
		private List<Node> nodes = new ArrayList<>();
		//Space between every move
		private final int jump = 10;
		//Initial speed of the timer. The lower the faster. 
		private int speed = 100;
		//Score based on every point captured from the 4 initial
		private int score = 0;
		//Initial change for move on x axis
		private int dx = jump; 
		//Initial change for move on y axis
		private int dy = 0;
		//Timer for movement and countdown
		private Timer timer, countTimer;
		//Variable for countdown
		private int countdown=9;
		//Initial coordinates of the first point. It is not random
		private int xPoint = 200, yPoint = 200;
		//Validates that dx/dy are changed after the snake is drawn
		private boolean done = false;
		private boolean paused;

		public SnakePanel(){			
			addInitialNodes();
			setTimer();
			setCountTimer();
			addKeyBinginds();
			setMessage("Presione ENTER para comenzar");			
		}

		public void setMessage(String message){
			messageLabel = new JLabel(message);
			messageLabel.setHorizontalAlignment(JLabel.CENTER);
			messageLabel.setForeground(Color.WHITE);
			messageLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
			add(messageLabel);
		}

		public void removeMessage(){
			remove(messageLabel);
		}

		public void addKeyBinginds(){
			addKeyBinding(this, KeyEvent.VK_UP, "go_up", (evt)->{
				if (dy == jump) return;
				if (done){ dy = -jump; dx = 0;} 
				done = false;
			});

			addKeyBinding(this, KeyEvent.VK_DOWN, "go_down", (evt)->{
				if(dy == -jump) return;
				if (done){ dy = jump; dx = 0;}
				done = false;
			});

			addKeyBinding(this, KeyEvent.VK_RIGHT, "go_right", (evt)->{
				if(dx == -jump) return;
				if (done){ dy = 0; dx = jump;} 
				done = false;
			});

			addKeyBinding(this, KeyEvent.VK_LEFT, "go_left", (evt)->{
				if(dx == jump) return;
				if (done){ dy = 0;  dx = -jump;}
				done = false; 
			});

			addKeyBinding(this, KeyEvent.VK_ENTER, "start", (evt)->{
				timer.start();
				countTimer.start();
				paused = false;
				removeMessage();
			});

			addKeyBinding(this, KeyEvent.VK_P, "pause", (evt)->{
				if(paused){
					timer.start();
					countTimer.start();
					paused = false;
				}
				else{
					timer.stop();
					countTimer.stop();
					paused = true;
				}
			});
		}

		public void addKeyBinding(JComponent comp, int keyCode, String id, ActionListener action){
			InputMap im = comp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
			ActionMap ap = comp.getActionMap();

			im.put(KeyStroke.getKeyStroke(keyCode, 0, false), id);
			ap.put(id, new AbstractAction(){
				@Override
				public void actionPerformed(ActionEvent e){
					action.actionPerformed(e);
				}
			});
		}

		public void setTimer(){
			ActionListener movement = new ActionListener(){
				@Override 
				public void actionPerformed(ActionEvent e){
					repaint();
				}
			};
			timer = new Timer(speed, movement);
		}

		public void setCountTimer(){
			ActionListener countdownAction = new ActionListener(){
				@Override 
				public void actionPerformed(ActionEvent e){
					countdown--;
					if(countdown == -1) close();
					countdownLabel.setText(String.valueOf(countdown));
				}
			};
			countTimer = new Timer(1000, countdownAction);
		}

		@Override
		public void paintComponent(Graphics g){
			super.paintComponent(g);

			//Draw point
			g.setColor(Color.RED);
			g.fillOval(xPoint, yPoint, jump, jump);

			//Draw snake
			g.setColor(new Color(0xFBFFC0));
			drawSnake(g);
			
			//Stop timer when Skake eats itself
			for(Node n : nodes){
				Node node = nodes.get(0);
				if(n != nodes.get(0))
					if(n.getX() == node.getX() && n.getY() == node.getY()){						
						timer.stop();
						countTimer.stop();
						close();
					}
			}

			//Grow when eats a new point
			if(nodes.get(0).getX() == xPoint && nodes.get(0).getY() == yPoint){
				//Adds the new node in the coordinates of the current last one
				Node lastNode = nodes.get(nodes.size()-1);
				nodes.add(new Node(lastNode.getX(), lastNode.getY()));
				//Adds a new random point
				setPoint();	
				//Sets score based on the time the player delays in the capture of a new point
				score += countdown;
				scoreLabel.setText(String.valueOf(score));
				//Increases the speed by 2 
				if(speed>40){
					speed = speed-2;
					timer.setDelay(speed);
				}
				countdown = 9;
				countTimer.restart();
				countdownLabel.setText("9");
			}		
		}

		private void drawSnake(Graphics g){
			for (int i=nodes.size()-1; i>=0; i--){
				Node node = nodes.get(i);
				//nodes.get(0) is the only node that moves regarding dx and dy
				if(i==0){
					node.setCoordinates(node.getX()+dx, node.getY()+dy);
					//If the snake passes the border of the panel, it comes out for the opossite side	
					if(node.getX() > getWidth()-10) node.setX(0);
					if(node.getX() < 0) node.setX(getWidth()-10);
					if(node.getY() > getHeight()-10) node.setY(0);
					if(node.getY() < 0) node.setY(getHeight()-10);
				//The rest of them takes the coordinates of the previous one
				}else{
					Node nextNode = nodes.get(i-1);				
					node.setCoordinates(nextNode.getX(), nextNode.getY());	
				}
					//Draws the node
					g.fillOval(node.getX(), node.getY(), jump, jump);	
			}
			done = true;	
		}

		private void addInitialNodes(){
			nodes.add(new Node(60, 50));
			nodes.add(new Node(50, 50));
			nodes.add(new Node(40, 50));
			nodes.add(new Node(30, 50));
		}

		private void setPoint(){
			//Sets the new point on random and multiple of 10 coordinates
			do{
				xPoint = (int)(Math.random()*getWidth());
			}while(xPoint % jump != 0);
			do{
				yPoint = (int)(Math.random()*getHeight());
			}while(yPoint % jump != 0);

			//If the new point matches the coordinates of any of the nodes, 
			//the method setPoint is called again
			for (Node n : nodes)
				if(n.getX() == xPoint && n.getY() == yPoint)
					setPoint();
		}
	}
}

	
