import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class RoundPanel extends JPanel {
    // Stroke size. it is recommended to set it to 1 for better view 
    protected int strokeSize = 3;
    // Sets if it has an High Quality view */
    protected boolean highQuality = true;
    // Double values for Horizontal and Vertical radius of corner arcs
    protected Dimension arcs = new Dimension(20, 20);
    
    public RoundPanel() {
        super();
        setOpaque(false);
        setBackground(new Color(0x0BA291));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;

        //Sets antialiasing if HQ.
        if (highQuality) {
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        }

        //Draws the rounded opaque panel with borders.
        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, width,
        height, arcs.width, arcs.height);

        graphics.setColor(getForeground());
        graphics.setStroke(new BasicStroke(strokeSize));

        // Sets strokes to default, is better.
        graphics.setStroke(new BasicStroke());
   }   
  
} 
