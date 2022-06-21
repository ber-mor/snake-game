import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class DotButton extends JButton{
	//Ruta del ícono del botón por defecto
	private String src;
	//Ruta del ícono del botón cuando se pase el mouse sobre él
	private String src2;

	public DotButton(String src, String src2){
		super(new ImageIcon(src));
		this.src = src;
		this.src2 = src2;	

		setContentAreaFilled(false);
		setBorder(null);

		addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e){
				setIcon(new ImageIcon(src2));
			}

			@Override
			public void mouseExited(MouseEvent e){
				setIcon(new ImageIcon(src));
			}
		});
	}
}