import javax.swing.*;
import java.awt.*;

public class Star extends CelestialObject{
    public boolean shinning = true;

    public Star(){
        //super(25);
        this.radius = 25;
        this.distanceToStar = 0;
        this.initialPosition = new Point(390, 320);
        this.updatedPosition = initialPosition;
        this.setLocation(365, 295);
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
    public void updatePosition() {
    }

    @Override
    public void setVelocity() {

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;
        Color color = new Color((float)1.0,(float)1.0,(float)1.0,(float)1.0);
        g2D.setColor(color);
        g2D.fillOval(0,0,2*radius,2*radius);
    }

    @Override
    public int compareTo(CelestialObject celestialObject) {
        return 0;
    }
}
