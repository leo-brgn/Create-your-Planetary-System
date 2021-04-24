import java.awt.*;

/**
 * Class for each planet inheriting from CelestialObject
 */
public class Planet extends CelestialObject {

    /**
     * Constructor
     */
    public Planet(int radius, Point position, int colorIndex, TypePlanet typePlanet, String name) {
        super(radius, position, colorIndex);
        this.objectName = name;
        if (typePlanet == TypePlanet.GASEOUS) {
            density = 1; //approximate density of a gaseous planet
            typeStr = "Gaseous";
        } else if (typePlanet == TypePlanet.ROCKY) {
            density = 5; //approximate density of a rocky planet
            typeStr = "Rocky";
        }
        color = PlanetColor.getColor(colorIndex);
        //Calling methods to initialize the mass, the distance to the sun and the velocity
        computeMass();
        computeDistanceToStar();
        setInitialVelocity();
    }

    /**
     * Method to compute the distance from the planet to the star (in px and in km)
     */
    public void computeDistanceToStar(){
        this.distanceToStar = Math.sqrt(Math.pow(position.x-390,2)+ Math.pow(position.y-320,2)); //390 and 320 being respectively the x and y coordinates of the Sun
        this.distanceToStarKm = scaleDst * distanceToStar;
    }

    /**
     * Getter
     */
    public double getDistanceToStar(){
        return distanceToStar;
    }

    /**
     * Method to compute the gravitational force exerted on the planet
     * It is divided by the mass of the planet which simplify calculations afterwards
     */
    public void computeGravitationalForce() {
        this.gravitationalForce = ((G * Star.massStar) / Math.pow(distanceToStarKm * 1000, 2)); // in m/s^2
    }

    /**
     * Method to set the initial velocity of the planet to stay in orbit
     * Applying the fundamental law of dynamics we get an approximation of the orbital velocity v^2 = G*M/r
     */
    public void setInitialVelocity(){
        double magnitude = Math.sqrt((G*Star.massStar)/(distanceToStarKm*1000)); //in m/s
        double[] vectorSunToPlanet = {(position.x - 390)/distanceToStar, (position.y - 320)/distanceToStar}; //unit vector

        //the initial velocity is normal to the vector vectorSunToPlanet and we keep the standard unit in m/s to avoid lost of digits
        if(Math.random()<0.5) { //the direction of the velocity is arbitrarily set (either clockwise or counterclockwise)
            velocityX = magnitude * vectorSunToPlanet[1];
            velocityY = -magnitude * vectorSunToPlanet[0];
        }else{
            velocityX = -magnitude * vectorSunToPlanet[1];
            velocityY = magnitude * vectorSunToPlanet[0];
        }
    }

    /**
     * Method to compute the new velocity using an approximation of the acceleration at order 1
     */
    public void updateVelocity(float deltaT){
        velocityX += deltaT * gravitationalForce * ((390 - position.x)/distanceToStar);
        velocityY += deltaT * gravitationalForce * ((320 - position.y)/distanceToStar);
    }

    /**
     * Method to compute the new position using an approximation
     */
    public void updatePosition(float deltaT2) {
        //cast by an integer if it is negative leads to a nil value so we use the absolute value
        if (velocityX <= 0) {
            position.x += -(int) Math.abs((deltaT2 * (velocityX)) / (1000 * scaleDst)); // in px
        } else {
            position.x += (int) Math.abs((deltaT2 * (velocityX)) / (1000 * scaleDst)); // in px
        }
        if (velocityY <= 0) {
            position.y += -(int) Math.abs((deltaT2 * (velocityY)) / (1000 * scaleDst)); // in px
        } else {
            position.y += (int) Math.abs((deltaT2 * (velocityY)) / (1000 * scaleDst)); // in px
        }
    }

    /**
     * toString method to display the characteristics of the planet
     */
    @Override
    public String toString() {
        return typeStr + " planet of radius: " + radiusKm + "km and mass: " + mass + "kg at " + distanceToStarKm + "km from the star.";
    }

    /**
     * Method to paint the planet
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(color);
        //Since the Point position is the center of the planet we need to shift the drawing of the planet
        g2D.fillOval(position.x-radius, position.y-radius, 2 * radius, 2 * radius);
    }

}
