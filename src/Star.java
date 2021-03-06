import javax.swing.*;
import java.awt.*;

public class Star extends CelestialObject{
    public boolean shinning = true;
    private int size;

    public Star(){
        position = new Point(390-25, 320-25);
        size = 50;
        this.setLocation(position);
        this.setSize(size,size);
    }
    @Override
    public String toString() {
        return null;
    }

    @Override
    public void computeMass() {
    }

    @Override
    public void velocity() {
        velocity.x = 1;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;
        Color color = new Color((float)1.0,(float)1.0,(float)1.0,(float)1.0);
        g2D.setColor(color);
        g2D.fillOval(0,0,size,size);
    }

    public void updatePosition(){
        size = 50 + (int)(10 * Math.cos(100_000_000 * System.currentTimeMillis()));
        this.setBounds(position.x - size/2, position.y - size /2 , size,size);
        //velocity();
    }

    @Override
    public int compareTo(CelestialObject celestialObject) {
        return 0;
    }
}
