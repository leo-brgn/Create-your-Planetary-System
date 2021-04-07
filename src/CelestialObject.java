import javax.swing.*;
import java.awt.*;

/**
 * Abstract class that defines the general idea of a celestial object.
 * It allows for the creation of stars, planets and maybe later satellites.
 */
public abstract class CelestialObject extends JComponent implements Comparable<CelestialObject> {

    /**
     * Attributes
     */
    final protected double G; // Universal gravity constant in m3/kg/s2
    final protected long scaleDst; // Scale of distances km/px
    final protected long scaleSizes;
    protected int radius; // Radius of the planet in px
    protected long radiusKm; // Radius of the planet in px
    protected double density; // Density of the planet in kg/m3
    protected double mass; // Mass of the planet in kg
    protected double gravitationalForce; // Gravitational force in N
    protected double distanceToStar; // Distance to star in px
    protected double distanceToStarKm; // Distance to star in km
    protected double velocityX; // Speed of the planet in m/s
    protected double velocityY; // Speed of the planet in m/s
    protected int count; // To track the amount of time in which the planet has stopped moving
    protected int colorIndex; // Color index of the planet
    protected Color color;
    protected Point position; // Position of the planet in px
    protected String typeStr;

    /**
     * Constructor
     */
    public CelestialObject(int radius, Point position) {
        // Scale the distances km/px
        this.scaleDst = 10_000_000;
        // Scale of the sizes km/px
        this.scaleSizes = 4500;
        // Set the universal gravity constant
        this.G = 6.674 * Math.pow(10, -11);
        // Initialize the radius
        this.radius = radius;
        radiusKm = scaleSizes * radius;
        // Initialize the position
        this.position = position;
        // Set the JComponent coordinate system
        this.setLocation(0, 0);
        this.setSize(780, 640);
        this.count = 0;
    }

    public CelestialObject(int radius, Point position, int colorIndex) {
        this(radius, position);
        this.colorIndex = colorIndex;
    }

    // Method to sort the planets
    public int compareTo(CelestialObject celestialObject){
        return Double.compare(this.distanceToStar, celestialObject.distanceToStar);
    }

    // Method to compute the mass as a function of the density and the radius (in km)
    public void computeMass() {
        this.mass = 1000*density*Math.PI*(4f/3f)*Math.pow(radiusKm * 1000,3);
    }


}
