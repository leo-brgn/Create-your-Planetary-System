import javax.swing.*;
import java.awt.*;

/**
 * Abstract class that defines the general idea of a celestial object.
 * It allows for the creation of stars, planets and maybe later satellites.
 */
public abstract class CelestialObject extends JComponent implements Comparable<CelestialObject> {
    final public double G;
    final public long scale;
    protected int radius;
    protected double distanceToStar;
    protected long currentTime;
    protected long lastTime;
    protected long deltaT;
    protected Point velocity;
    protected Point position;
    protected double density;
    protected double mass;
    protected double gravitationalForce;

    /**
     * Contructor
     *
     * @param radius,   radius of the planet
     * @param position, initial position of the planets
     */
    public CelestialObject(int radius, Point position) {
        // Set the universe constants
        this.scale = 4_687_500; // Scale km/px
        this.G = 6.7 * Math.pow(10, -11);
        // Initialize the radius
        this.radius = radius;
        // Initialize the position
        this.position = position;
        // Set the JComponent coordinate system
        this.setLocation(0, 0);
        this.setSize(780, 640);
        computeDistanceToStar();
        this.velocity = new Point(1,1);
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
        this.mass = density*Math.PI*(4/3)*Math.pow(radius,3);
    }

    /**
     * Mother's methods
     */
    public void computeDistanceToStar(){
        this.distanceToStar = Math.sqrt((position.x - 390)*(position.x-390) + (position.y- 320)*(position.y-320));
    }

    public void setGravitationalForce(){ // we do not use the mss of the central sun because it will cancel afterward
        this.gravitationalForce = - (G *  this.mass)/Math.pow(distanceToStar*scale, 2);
    }

    public void updateVelocity(){
        velocity.x += (int)((gravitationalForce * (390 - position.x)) / distanceToStar);
        velocity.y += (int)((gravitationalForce * (320 - position.y)) / distanceToStar);
    }

    public void updatePosition(){
        currentTime = System.currentTimeMillis();
        deltaT = currentTime - lastTime;
        position.x += velocity.x;
        position.y += velocity.y;
        lastTime = currentTime;
    }
}
