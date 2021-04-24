import javax.swing.*;
import java.awt.*;

/**
 * Class for the preview of the planet inheriting from JPanel
 */

public class CelestialPreview extends JPanel{

    /**
     * Attributes
     */
    private int size; //radius of the planet
    private Color color = Color.WHITE;

    /**
     * Constructor
     */
    public CelestialPreview(float size){
        //Initialize the JPanel parameters
        this.setBackground(new Color(28,40,51));
        this.setBounds(10, 360, 240, 170);
        this.setVisible(true);
        this.setLayout(null);
        this.size = (int) size;
    }

    /**
     * Method pain to draw the planet
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;
        g2D.setColor(color);
        g2D.fillOval(((this.getWidth()/2)-size), ((this.getHeight()/2)-size),2*size,2*size); //diameter of two times the size chosen
    }

    /**
     * Setters
     */
    public void setSize(float size){
        this.size = (int) size;
    }

    public void setColor(int index){
        this.color = PlanetColor.getColor(index);
    }
}
