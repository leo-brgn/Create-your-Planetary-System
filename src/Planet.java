import java.awt.*;

/**
 * Class for the each planet inheriting from CelestialObject
 */
public class Planet extends CelestialObject {

    /**
     * Constructor
     */
    public Planet(int radius, Point position, int colorIndex, TypePlanet typePlanet, String name) {
        super(radius, position, colorIndex);
        this.objectName = name;
        if (typePlanet == TypePlanet.GASEOUS) {
            // Density in g/cm3
            density = 1;
            typeStr = "Gaseous";
        } else if (typePlanet == TypePlanet.ROCKY) {
            // Density in g/cm3
            density = 5;
            typeStr = "Rocky";
        }
        color = PlanetColor.getColor(colorIndex);
        computeMass();
        computeDistanceToStar();
        setInitialVelocity();
    }

    /**
     * Method to compute the distance from the celestial object to the star
     */
    public void computeDistanceToStar(){
        this.distanceToStar = Math.sqrt(Math.pow(position.x+radius-390,2)+ Math.pow(position.y+radius-320,2));
        this.distanceToStarKm = scaleDst * distanceToStar;
    }

    public double getDistanceToStar(){
        return distanceToStar;
    }

    /**
     * Method to set the initial velocity of the planet to stay in orbit
     * Applying the fundamental law of dynamics we get an approximation of the orbital velocity v^2 = G*M/r
     */
    public void computeGravitationalForce() {
        this.gravitationalForce = ((G * Star.massStar) / Math.pow(distanceToStarKm * 1000, 2)); // m/s^2
    }

    /**
     * Method to set the initial velocity of the planet to stay in orbit (orbital velocity)
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
        g2D.fillOval(position.x, position.y, 2 * radius, 2 * radius);
    }

}
