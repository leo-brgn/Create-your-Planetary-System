import java.awt.*;

public class Planet extends CelestialObject {

    public Planet(int radius, Point position, Color color, TypePlanet typePlanet) {
        super(radius, position, color);
        if (typePlanet == TypePlanet.GASEOUS) {
            density = 1; // Density in kg/m3
            typeStr = "Gaseous";
        } else if (typePlanet == TypePlanet.ROCKY){
            density = 5; // Density in kg/m3
            typeStr = "Rocky";
        }
        computeMass();
        setInitialVelocity();
    }

    public void paintComponent(Graphics g){
        Graphics2D g2D = (Graphics2D)g;
        g2D.setColor(color);
        g2D.fillOval(position.x, position.y,2*radius,2*radius);
    }

    @Override
    public String toString(){
        return typeStr + " planet of radius: " + radiusKm + " and mass: " + mass + " at " + distanceToStarKm + "km from the star." ;
    }

}
