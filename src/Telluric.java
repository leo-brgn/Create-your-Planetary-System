import java.awt.*;

public class Telluric extends CelestialObject {

    public Telluric (int radius, Point position) {
        super(radius, position);
        mass = 6 * Math.pow(10,24);
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
        currentTime = System.currentTimeMillis();
        deltaT = currentTime - lastTime;
        double b = (2.0 * G * mass) / (distanceToStar*4000);
        System.out.println(b);
        updatedPosition.x += velocity.x;
        computeDistanceToStar();
        lastTime = currentTime;
    }

    @Override
    public int compareTo(CelestialObject celestialObject) {
        return 0;
    }
}
