import javax.swing.*;
import java.awt.*;

public class CelestialPreview extends JPanel{

    private int size;
    private Color color;

    public CelestialPreview(float size){
        this.setBackground(new Color(28,40,51));
        this.setBounds(10, 360, 270, 300);
        this.setVisible(true);
        this.setLayout(null);
        this.size = (int) size;
        this.color = new Color(123,125,125);
    }

    public CelestialPreview(float size, Color color){
        this(size);
        this.color = color;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;
        g2D.setColor(Color.GREEN);
        g2D.fillOval(20, 20,2*size,2*size);
        //g2D.drawOval((int) (390-distanceToStar),(int) (320-distanceToStar),(int) (2*distanceToStar),(int)  (2*distanceToStar));
    }
}
