import java.awt.*;

public class Telluric extends CelestialObject {

    public Telluric (int radius, Point position) {
        super(radius, position);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;
        g2D.setColor(Color.GREEN);
        g2D.fillOval(updatedPosition.x,updatedPosition.y,2*radius,2*radius);
        g2D.drawOval((int) (390-distanceToStar),(int) (320-distanceToStar),(int) (2*distanceToStar),(int)  (2*distanceToStar));
    }

    @Override
    public String toString(){
        return "Telluric planet" + distanceToStar;
    }

    @Override
    public void computeMass() {
    }

    @Override
    public void setVelocity() {
        velocity = new Point(1,0);
    }

    @Override
    public void updatePosition(){
        updatedPosition.x += velocity.x;
        computeDistanceToStar();
    }

    @Override
    public int compareTo(CelestialObject celestialObject) {
        return 0;
    }
}
