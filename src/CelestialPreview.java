import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CelestialPreview extends JPanel{

    private final int size;
    private Color color;

    public CelestialPreview(float size){
        this.setBackground(new Color(28,40,51));
        this.setBounds(10, 360, 240, 170);
        this.setVisible(true);
        this.setLayout(null);
        this.size = (int) size;
        this.color = new Color(95,106,106);
    }

    public CelestialPreview(float size, Color color){
        this(size);
        this.color = color;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;
        g2D.setColor(color);
        g2D.fillOval(((this.getWidth()/2)-size), ((this.getHeight()/2)-size),2*size,2*size);
        //g2D.drawOval((int) (390-distanceToStar),(int) (320-distanceToStar),(int) (2*distanceToStar),(int)  (2*distanceToStar));
    }
}
