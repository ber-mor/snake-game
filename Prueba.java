import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

public class Prueba extends JFrame{
	public Prueba(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);

		JPanel inPanel = new JPanel();
		inPanel.setBackground(Color.RED);
		inPanel.setSize(500,500);
		inPanel.setPreferredSize(new Dimension(500,500));
		inPanel.setMinimumSize(new Dimension(500,500));
		inPanel.setMaximumSize(new Dimension(500,500));

		add(panel, BorderLayout.NORTH);
		// add(inPanel, BorderLayout.CENTER);

		JPanel bpanel = new JPanel();
		bpanel.add(new JButton(new AbstractAction("name of button") {
   			public void actionPerformed(ActionEvent e) {
        		System.out.println("A kabron");
    		}
		}));

		add(bpanel, BorderLayout.CENTER);

		pack();
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		new Prueba().setVisible(true);
	}
}