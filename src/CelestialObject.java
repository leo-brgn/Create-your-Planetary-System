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
    protected int colorIndex; // Color of the planet
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
        this.mass = density*Math.PI*(4f/3f)*Math.pow(radiusKm * 1000,3);
    }

    // Method to set the initial velocity of the planet to stay in orbit
    public void setInitialVelocity(){
        double magnitude = Math.sqrt((G*mass)/(distanceToStarKm*1000));
        double[] vectorSunToPlanet = {(position.x - 390) /distanceToStar, (position.y - 320)/distanceToStar};
        if(vectorSunToPlanet[0]>=0 && vectorSunToPlanet[1]<=0){ //RU
            velocityX = - magnitude * vectorSunToPlanet[0];
            velocityY = magnitude * vectorSunToPlanet[1];
        } else if(vectorSunToPlanet[0]>0 && vectorSunToPlanet[1]>0){ //RD
            velocityX = magnitude * vectorSunToPlanet[0];
            velocityY = -magnitude * vectorSunToPlanet[1];
        } else if(vectorSunToPlanet[0]<=0 && vectorSunToPlanet[1]>=0){ //LD
            velocityX = -magnitude * vectorSunToPlanet[0];
            velocityY = magnitude * vectorSunToPlanet[1];
        } else if(vectorSunToPlanet[0]<0 && vectorSunToPlanet[1]<0){ //LU
            velocityX = magnitude * vectorSunToPlanet[0];
            velocityY = -magnitude * vectorSunToPlanet[1];
        }

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
    }

    // Method to verify if the planet has gone too far, the distance chosen is not scientific
    public boolean isTooFar(){
        return distanceToStar >= 390;
    }

    // Method to verify if the planet is colliding with the sun
    public boolean isTooClose(long sunRadius){
        return distanceToStarKm <= (radiusKm+sunRadius*3);
    }
}
