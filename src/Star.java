import javax.swing.*;
import java.awt.*;

public class Star extends CelestialObject{
    public boolean shinning = true;

    public Star(){
        //super(25);
        radius = 25;
        distanceToStar = 0;
        position = new Point(365, 295);
        this.setLocation(position);
        this.setSize(2*radius,2*radius);
    }
    @Override
    public String toString() {
        return "Sun of mass: ";
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
        g2D.fillOval(0,0,2*radius,2*radius);
    }

    public void updatePosition(){
        //size = 50 + (int)(10 * Math.cos(5*100_000_000 * System.currentTimeMillis()));
        //this.setBounds(position.x - size/2, position.y - size /2 , size,size);
        //velocity();
    }

    @Override
    public int compareTo(CelestialObject celestialObject) {
        return 0;
    }
}
