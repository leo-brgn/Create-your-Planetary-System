import javax.swing.*;
import java.awt.*;

/**
 * Abstract class that defines the general idea of a celestial object.
 * It allows for the creation of stars, planets and maybe later satellites.
 */
public abstract class CelestialObject extends JComponent implements Comparable<CelestialObject> {
    final public double G = (double) (6.7 * Math.pow(10, -11));
    protected int radius;
    protected double distanceToStar;
    protected long currentTime;
    protected long lastTime;
    protected long deltaT;
    protected Point initialVelocity;
    protected Point velocity;
    protected Point initialPosition;
    protected Point updatedPosition;
    protected double mass;

    /**
     * Contructor
     * @param radius, radius of the planet
     * @param position, initial position of the planets
     */
    public CelestialObject(int radius, Point position){
        this.radius = radius;
        // Initialize the position
        this.initialPosition = position;
        this.updatedPosition = position;
        // Set the JComponent coordinate system
        this.setLocation(0,0);
        this.setSize(780,640);
        computeDistanceToStar();
        setVelocity();
        this.currentTime = System.currentTimeMillis();
        this.lastTime = System.currentTimeMillis();
        this.deltaT = 0;
    }

    /**
     * Abstract methods
     */
    public abstract String toString(); // Method to return a string when we call the class directly
    public abstract void computeMass(); // Method to compute the mass based on an average density for each planet type
    public abstract void updatePosition(); // Method to update the position with the velocity
    public abstract void setVelocity(); // Method to set the velocity

    /**
     * Mother's methods
     */
    public void computeDistanceToStar(){
        distanceToStar = Math.sqrt((initialPosition.x - 390)*(initialPosition.x-390) + (initialPosition.y- 320)*(initialPosition.y-320));
    }

}
