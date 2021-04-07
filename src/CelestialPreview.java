import javax.swing.*;
import java.awt.*;

public class CelestialPreview extends JPanel{

    private int size;
    private Color color = Color.WHITE;

    public CelestialPreview(float size){
        this.setBackground(new Color(28,40,51));
        this.setBounds(10, 360, 240, 170);
        this.setVisible(true);
        this.setLayout(null);
        this.size = (int) size;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;
        g2D.setColor(color);
        g2D.fillOval(((this.getWidth()/2)-size), ((this.getHeight()/2)-size),2*size,2*size); //diameter of two times the size chosen
    }

    public void setSize(float size){
        this.size = (int) size;
    }

    public void setColor(int index){
        this.color = PlanetColor.getColor(index);
    }
}
