import java.awt.*;

public class Gaseous extends CelestialObject {

    public Gaseous(int radius, Point position, Color color) {
        super(radius, position, color);
        density = 1; // Density in kg/m3
        computeMass();
        setInitialVelocity();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;
        g2D.setColor(color);
        g2D.fillOval(position.x, position.y,2*radius,2*radius);
    }

    @Override
    public String toString() {
        return "Gaseous planet of radius: " + radiusKm + " and mass: " + mass + " at " + distanceToStarKm + "km from the star." ;
    }

}
