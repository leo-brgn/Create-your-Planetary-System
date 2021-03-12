import javax.swing.*;
import java.awt.*;

/**
 * Abstract class that defines the general idea of a celestial object.
 * It allows for the creation of stars, planets and maybe later satellites.
 */
public abstract class CelestialObject extends JComponent implements Comparable<CelestialObject> {
    final public double G; // Universal gravity constant
    final public long scale; // Scale km/px
    protected int radius; // Radius of the planet in px
    protected long currentTime;
    protected long lastTime;
    protected long deltaT;
    protected double density; // Density of the planet in kg/m3
    protected double mass; // Mass of the planet in kg
    protected double gravitationalForce; // Gravitational force in N
    protected double distanceToStar; // Distance to star in px
    protected Point velocity; // Speed of the planet in px/s
    protected Point position; // Position of the planet in px

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
        // Compute the distance to the star
        computeDistanceToStar();
        // Set the initial velocity
        this.velocity = new Point(1,1); //Speed in px/s
        this.currentTime = System.currentTimeMillis();
        this.lastTime = System.currentTimeMillis();
        this.deltaT = 0;
    }

    /**
     * Abstract methods
     */
    public abstract String toString(); // Method to return a string when we call the class directly

    public void computeMass() {
        this.mass = density*Math.PI*(4/3)*Math.pow(radius*scale*1000,3);
    }

    public void computeDistanceToStar(){
        this.distanceToStar = Math.sqrt((position.x - 390)*(position.x-390) + (position.y- 320)*(position.y-320));
    }

    public void setGravitationalForce(){ // we do not use the mss of the central sun because it will cancel afterward
        this.gravitationalForce = - (G *  this.mass)/Math.pow(distanceToStar*1000*scale, 2);
    }

    public void updateVelocity(){
        double realVelocityX = (1000 *scale* velocity.x) - gravitationalForce * ((390 - position.x)/distanceToStar);
        double realVelocityY = (1000 *scale* velocity.y) - gravitationalForce * ((320 - position.y)/distanceToStar);
        velocity.x = (int) (realVelocityX /(scale*1000));
        velocity.y = (int) (realVelocityY /(scale*1000));
    }

    public void updatePosition(){
        currentTime = System.currentTimeMillis();
        deltaT = currentTime - lastTime;
        position.x += velocity.x;
        position.y += velocity.y;
        lastTime = currentTime;
    }
}
