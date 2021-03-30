import java.awt.*;

public class Planet extends CelestialObject {

    private Color[] colors;

    public Planet(int radius, Point position, Color color, TypePlanet typePlanet) {
        super(radius, position, color);
        if (typePlanet == TypePlanet.GASEOUS) {
            density = 1; // Density in kg/m3
            typeStr = "Gaseous";
        } else if (typePlanet == TypePlanet.ROCKY){
            density = 5; // Density in kg/m3
            typeStr = "Rocky";
        }
        colors = new Color[2];
        colors[0] = color;
        colors[1] = Color.WHITE;
        computeMass();
        setInitialVelocity();
    }

    public void paintComponent(Graphics g){
        Graphics2D g2D = (Graphics2D)g;
        float[] dist = {0.5f,1f};
        RadialGradientPaint p = new RadialGradientPaint(position, 2*radius, dist, colors);
        g2D.setPaint(p);
        g2D.fillOval(position.x, position.y,2*radius,2*radius);
    }

    @Override
    public String toString(){
        return typeStr + " planet of radius: " + radiusKm + " and mass: " + mass + " at " + distanceToStarKm + "km from the star." ;
    }

}
