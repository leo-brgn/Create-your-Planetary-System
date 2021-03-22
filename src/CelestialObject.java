import javax.swing.*;
import java.awt.*;

/**
 * Abstract class that defines the general idea of a celestial object.
 * It allows for the creation of stars, planets and maybe later satellites.
 */
public abstract class CelestialObject extends JComponent implements Comparable<CelestialObject> {
    final private double G; // Universal gravity constant in m3/kg/s2
    final private long scaleDst; // Scale of distances km/px
    final private long scaleSizes; // Scale of the sizes km/px
    protected int radius; // Radius of the planet in px
    protected long radiusKm; // Radius of the planet in px
    protected double density; // Density of the planet in kg/m3
    protected double mass; // Mass of the planet in kg
    protected double gravitationalForce; // Gravitational force in N
    protected double distanceToStar; // Distance to star in px
    protected double distanceToStarKm; // Distance to star in km
    protected double velocityX; // Speed of the planet in m/s
    protected double velocityY;
    protected Color color;
    protected Point position; // Position of the planet in px

    public CelestialObject(int radius, Point position) {
        // Set the universe constants
        this.scaleDst = 4_687_500; // Scale km/px
        this.scaleSizes = 34_817; // Scale km/px
        this.G = 6.674 * Math.pow(10, -11);
        // Initialize the radius
        this.radius = radius;
        radiusKm = scaleSizes * radius;
        // Initialize the position
        this.position = position;
        // Set the JComponent coordinate system
        this.setLocation(0, 0);
        this.setSize(780, 640);
        // Compute the distance to the star
        computeDistanceToStar();
        // Set the initial velocity
        this.velocityX = 10; // m/s
        this.velocityY = 10;
    }

    public CelestialObject(int radius, Point position, Color color) {
        this(radius, position);
        this.color = color;
    }

    /**
     * Abstract methods
     */
    public abstract String toString(); // Method to return a string when we call the class directly

    public int compareTo(CelestialObject celestialObject){
        if(this.distanceToStar < celestialObject.distanceToStar){
            return -1;
        } else if (this.distanceToStar > celestialObject.distanceToStar){
            return 1;
        } else {
            return 0;
        }
    }

    public void computeMass() {
        this.mass = density*Math.PI*(4f/3f)*Math.pow(radius* scaleSizes * 1000,3);
    }

    public void computeDistanceToStar(){
        this.distanceToStar = Math.sqrt((position.x-390)*(position.x-390) + (position.y-320)*(position.y-320));
        this.distanceToStarKm = scaleDst * distanceToStar;
    }

    public void setGravitationalForce(){ // we do not use the mss of the central sun because it will cancel afterward
        this.gravitationalForce = (G*this.mass)/Math.pow(distanceToStar*1000*scaleDst,2); // Around 10-5 since we don't have mass of sun
    }

    public void updateVelocity(float deltaT){
        velocityX += deltaT * gravitationalForce * ((390 - position.x)/distanceToStar);
        velocityY += deltaT * gravitationalForce * ((320 - position.y)/distanceToStar);
    }

    public void updatePosition(float deltaT){
        if (Math.abs(deltaT * (velocityX / (scaleDst*1000))) >= 1){
            position.x = (int) (position.x + deltaT * (velocityX / (scaleDst*1000)));
        } else {
            position.x = (int) (position.x + deltaT * (velocityX / (scaleDst*1000))) + (int)Math.signum(velocityX);
        }
        if (Math.abs(deltaT * (velocityY / (scaleDst*1000))) >= 1){
            position.y = (int) (position.y + deltaT * (velocityY / (scaleDst*1000)));
        } else {
            position.y = (int) (position.y + deltaT * (velocityY / (scaleDst*1000))) + (int)Math.signum(velocityY);
        }
    }

    public boolean isTooFar(){
        return distanceToStar >= 700;
    }
}
