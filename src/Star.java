import javax.swing.*;
import java.awt.*;

public class Star extends CelestialObject{
    public boolean shinning = true;

    public Star(){
        super(25, new Point(390,320));
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;
        float[] dist = {0.7f, 1.0f};
        Color[] colors = {Color.WHITE, Color.BLACK};
        RadialGradientPaint p = new RadialGradientPaint(radius, radius, radius, dist, colors);
        g2D.setPaint(Color.WHITE);
        g2D.fillOval(initialPosition.x-radius,initialPosition.y-radius,2*radius,2*radius);
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

    @Override
    public int compareTo(CelestialObject celestialObject) {
        return 0;
    }
}
