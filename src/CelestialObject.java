import javax.swing.*;
import java.awt.*;

/**
 * Abstract class that defines the general idea of a celestial object.
 * It allows for the creation of the central sun and planets
 */
public abstract class CelestialObject extends JComponent implements Comparable<CelestialObject> {

    /**
     * Attributes
     */
    final protected double G; // Universal gravity constant in m3/kg/s2
    final protected long scaleDst; // Scale of distances between celestial objects in km/px
    final protected long scalePlanetSizes; //Scale of the size of the planets in km/px
    final protected long scaleSunSize; //Scale of the size of the sun in km/px
    protected long radiusKm; // Radius of the planet in km
    protected double density; // Density of the planet in g/cm^3
    protected double mass; // Mass of the planet in kg
    protected double gravitationalForce; // Gravitational force in N/m
    protected double distanceToStar; // Distance to star in px
    protected double distanceToStarKm; // Distance to star in km
    protected double velocityX; // Coordinate x of the speed of the planet in m/s
    protected double velocityY; // Coordinate y of the speed of the planet in m/s
    protected int colorIndex; // Color index of the planet
    protected int radius; // Radius of the planet in px
    protected String objectName; //name of the planet
    protected String typeStr; //Type of the planet
    protected Color color; //Color of the planet
    protected Point position; // Position of the planet in px

    /**
     * Constructors
     */
    public CelestialObject(int radius, Point position) {
        // Scale the distances km/px
        this.scaleDst = 10_000_000;
        // Scale of the sizes of the planet km/px
        this.scalePlanetSizes = 4500;
        // Scale of the sizes of the sun km/px
        this.scaleSunSize = 13500;
        // Set the universal gravity constant
        this.G = 6.674 * Math.pow(10, -11);
        // Initialize the radius
        this.radius = radius;
        radiusKm = scalePlanetSizes * radius;
        // Initialize the position
        this.position = position;
        // Set the JComponent coordinate system
        this.setLocation(0, 0);
        this.setSize(780, 640);
    }

    public CelestialObject(int radius, Point position, int colorIndex) {
        this(radius, position);
        this.colorIndex = colorIndex;
    }

    /**
     * Method to sort the planets
     */
    public int compareTo(CelestialObject celestialObject){
        return Double.compare(this.distanceToStar, celestialObject.distanceToStar);
    }

    public Point getPosition(){
        return position;
    }

    public int getRadius(){
        return radius;
    }

    public String getObjectName(){
        return objectName;
    }
    // Method to compute the mass as a function of the density and the radius (in km)
    /**
     * Method to compute the mass as a function of the density and the radius (in km)
     */
    public void computeMass() {
        this.mass = 1000*density*Math.PI*(4f/3f)*Math.pow(radiusKm * 1000,3);
    }
}
