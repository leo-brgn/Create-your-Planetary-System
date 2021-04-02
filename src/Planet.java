import java.awt.*;

public class Planet extends CelestialObject {

    private final Color[] colors;
    private long lifeTime; //attributes that will be displayed at the end of the game

    public Planet(int radius, Point position, int colorIndex, TypePlanet typePlanet) {
        super(radius, position, colorIndex);
        if (typePlanet == TypePlanet.GASEOUS) {
            density = 1; // Density in kg/m3
            typeStr = "Gaseous";
        } else if (typePlanet == TypePlanet.ROCKY){
            density = 5; // Density in kg/m3
            typeStr = "Rocky";
        }
        colors = PlanetGradient.getTwoColors(colorIndex);
        computeMass();
        setInitialVelocity();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;
        float[] dist = {0.05f,1f};
        RadialGradientPaint p = new RadialGradientPaint(position.x + radius, position.y +radius, 2*radius, dist, colors);
        g2D.setPaint(p);
        g2D.fillOval(position.x, position.y,2*radius,2*radius);
    }

    @Override
    public String toString(){
        return typeStr + " planet of radius: " + radiusKm + " and mass: " + mass + " at " + distanceToStarKm + "km from the star." ;
    }

}
