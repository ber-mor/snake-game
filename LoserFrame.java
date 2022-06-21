import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;

public class LoserFrame extends Frame{
	private JFrame frame;
	public LoserFrame(int score, JFrame frame){
		this.frame = frame;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Chale :c");
		setSize(300,200);
		setLocationRelativeTo(null);

		JButton okayButton = new JButton("Ni modo :c");
		JLabel loserLabel = new JLabel("Has perdido");
		loserLabel.setHorizontalAlignment(JLabel.CENTER);
		JLabel scoreLabel = new JLabel("Tu puntuación es de " + score);
		scoreLabel.setHorizontalAlignment(JLabel.CENTER);

		JPanel panelB = new JPanel();
		panelB.setBackground(new Color(0x15BDAC));
		panelB.add(okayButton);

		JPanel panel = new JPanel(new GridLayout(3,1));
		panel.setBackground(new Color(0x15BDAC));

		panel.add(loserLabel);
		panel.add(scoreLabel);
		panel.add(panelB);

		innerPanel.add(panel);

		okayButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				dispose();
				frame.dispose();
				new SnakeFrame().setVisible(true);
			}
		});

		getRootPane().setDefaultButton(okayButton);
	} 

	public static void main(String[] args) {
		//new LoserFrame(50).setVisible(true);
	}
}