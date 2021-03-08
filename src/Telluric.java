import java.awt.*;

public class Telluric extends CelestialObject {

    public Telluric (int radius, Point position) {
        this.radius = radius;
        this.initialPosition = position;
        this.updatedPosition = position;
        this.velocity = new Point(0,0);
        setVelocity();
        computeDistanceToStar();
        this.setLocation(0,0);
        this.setSize(780,640);
    }

    public String toString(){
        return "Telluric planet" + distanceToStar;
    }

    public void computeMass() {
    }

    @Override
    public void setVelocity() {
        velocity.x = 1;
    }

    public void updatePosition(){
        //updatedPosition.x += velocity.x;
        computeDistanceToStar();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;
        Color color = new Color((float)1.0,(float)1.0,(float)1.0,(float)1.0);
        g2D.setColor(color);
        g2D.fillOval(updatedPosition.x,updatedPosition.y,2*radius,2*radius);
        //g2D.fillOval(0,0,(int)distanceToStar+radius,(int)distanceToStar+radius);
        //g2D.drawLine(390, 320, updatedPosition.x + radius ,updatedPosition.y + radius);
        //g2D.drawOval((int) (390-distanceToStar),(int) (320-distanceToStar),(int) (2*distanceToStar),(int)  (2*distanceToStar));
    }

    @Override
    public int compareTo(CelestialObject celestialObject) {
        return 0;
    }
}
