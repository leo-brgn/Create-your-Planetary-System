import javax.swing.*;
import java.awt.*;

/**
 * Abstract class that defines the general idea of a celestial object.
 * It allows for the creation of stars, planets and maybe later satellites.
 */
public abstract class CelestialObject extends JComponent implements Comparable<CelestialObject> {
    final public double G = 6.7 * Math.pow(10, -11);
    protected int radius;
    protected double distanceToStar;
    protected long currentTime;
    protected long lastTime;
    protected long deltaT;
    protected Point initialVelocity;
    protected Point velocity;
    protected Point initialPosition;
    protected Point updatedPosition;
    protected double density = 3000;
    protected double mass;
    protected double gravitationalForce;

    /**
     * Contructor
     *
     * @param radius,   radius of the planet
     * @param position, initial position of the planets
     */
    public CelestialObject(int radius, Point position) {
        this.radius = radius;
        // Initialize the position
        this.initialPosition = position;
        this.updatedPosition = position;
        // Set the JComponent coordinate system
        this.setLocation(0, 0);
        this.setSize(780, 640);
        computeDistanceToStar();
        computeMass();
        this.velocity = new Point(100,200);
        this.currentTime = System.currentTimeMillis();
        this.lastTime = System.currentTimeMillis();
        this.deltaT = 0;
    }

    /**
     * Abstract methods
     */
    public abstract String toString(); // Method to return a string when we call the class directly
    // Method to compute the mass based on an average density for each planet type
    public void computeMass() {
        this.mass = density*Math.PI*(4f/3f)*Math.pow(radius,3);
        System.out.println("Mass");
    }

    /**
     * Mother's methods
     */
    public void computeDistanceToStar(){
        distanceToStar = Math.sqrt((initialPosition.x - 390)*(initialPosition.x-390) + (initialPosition.y- 320)*(initialPosition.y-320));
    }

    public void setGravitationalForce(){ // we do not use the mss of the central sun because it will cancel afterward
        this.gravitationalForce = - (G *  this.mass)/Math.pow(radius, 2);
    }

    public void updateVelocity(){
        setGravitationalForce();
        velocity.x += (int)(gravitationalForce * (390 - updatedPosition.x) / distanceToStar);
        velocity.y += (int)(gravitationalForce * (320 - updatedPosition.y) / distanceToStar);
    }

    public void updatePosition(){
        updateVelocity();
        currentTime = System.currentTimeMillis();
        deltaT = currentTime - lastTime;
        updatedPosition.x += velocity.x;
        updatedPosition.y += velocity.y;
        computeDistanceToStar();
        lastTime = currentTime;
    }
}
