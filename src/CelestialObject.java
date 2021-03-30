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
    final private double G; // Universal gravity constant in m3/kg/s2
    final private long scaleDst; // Scale of distances km/px
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
    protected Color color; // Color of the planet
    protected Point position; // Position of the planet in px
    protected String typeStr;

    public CelestialObject(int radius, Point position) {
        // Set the universe constants
        this.scaleDst = 4_687_500; // Scale km/px
        // Scale of the sizes km/px
        long scaleSizes = 34_817; // Scale km/px
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
        this.count = 0;
    }

    public CelestialObject(int radius, Point position, Color color) {
        this(radius, position);
        this.color = color;
    }

    // Method to sort the planets
    public int compareTo(CelestialObject celestialObject){
        return Double.compare(this.distanceToStar, celestialObject.distanceToStar);
    }

    // Method to compute the mass as a function of the density
    public void computeMass() {
        this.mass = 1000*density*Math.PI*(4f/3f)*Math.pow(radiusKm * 1000,3);
    }

    // Method to compute the distance from the celestial object to the star
    public void computeDistanceToStar(){
        this.distanceToStar = Math.sqrt((position.x-390)*(position.x-390) + (position.y-320)*(position.y-320));
        this.distanceToStarKm = scaleDst * distanceToStar;
    }

    // Method to compute the force applied on the planet at a certain position
    public void setGravitationalForce(){ // we do not use the mss of the central sun because it will cancel afterward
        this.gravitationalForce = (G*mass)/Math.pow(distanceToStarKm*1000,2); // Around 10-5 since we don't have mass of sun
    }

    // Method to compute the new velocity using an approximation of the acceleration at order 1
    public void updateVelocity(float deltaT){
        velocityX += deltaT * gravitationalForce * ((390 - position.x)/distanceToStar);
        velocityY += deltaT * gravitationalForce * ((320 - position.y)/distanceToStar);
    }

    // Method to compute the new position using an approximation
    public void updatePosition(float deltaT){
        position.x = (int) (position.x + deltaT * (velocityX / (scaleDst*1000)));
        position.y = (int) (position.y + deltaT * (velocityY / (scaleDst*1000)));
        // Checks if the variation in position is smaller that 0 to force a displacement if it has been to long since last movement
        if(Math.abs(position.x + deltaT * (velocityX / (scaleDst*1000))) < 1.0 && Math.abs(position.y + deltaT * (velocityY / (scaleDst*1000))) < 1.0){
            count++;
            if (count >= 10){
                position.x = (int) (position.x + deltaT * (velocityX / (scaleDst*1000)) + Math.signum(velocityX));
                count = 0;
            }
        } else {
            count = 0; // once the movement is made, there is no counting
        }
    }

    // Method to verify if the planet has gone too far, the distance chosen is not scientific
    public boolean isTooFar(){
        return distanceToStar >= 700;
    }

    // Method to verify if the planet is colliding with the sun
    public boolean isTooClose(long sunRadius){
        System.out.println(distanceToStarKm + " " + (radiusKm+sunRadius));
        return distanceToStarKm <= (radiusKm+sunRadius*3);
    }

    //
    public void setInitialVelocity(){
        double magnitude = Math.sqrt((G*mass)/(distanceToStarKm*1000));
        double[] unitaryVec = {(position.x - 390) /distanceToStar, (position.y - 320)/distanceToStar};
        if(Math.random()>0.5){
            velocityX = magnitude * unitaryVec[1];
            velocityY = -magnitude * unitaryVec[0];
        } else {
            velocityX = -magnitude * unitaryVec[1];
            velocityY = magnitude * unitaryVec[0];
        }
    }
}
