import java.awt.*;

public class Planet extends CelestialObject {

    private final Color[] colors;
    private long lifeTime; //attributes that will be displayed at the end of the game

    public Planet(int radius, Point position, int colorIndex, TypePlanet typePlanet) {
        super(radius, position, colorIndex);
        if (typePlanet == TypePlanet.GASEOUS) {
            density = 1; // Density in g/cm3
            typeStr = "Gaseous";
        } else if (typePlanet == TypePlanet.ROCKY) {
            density = 5; // Density in g/cm3
            typeStr = "Rocky";
        }
        colors = PlanetGradient.getTwoColors(colorIndex);
        computeMass();
        computeDistanceToStar();
        setInitialVelocity();

    }


    /**
     * Method to set the initial velocity of the planet to stay in orbit
     */
    public void setInitialVelocity(){
        //Applying the fundamental law of dynamics we get an approximation of the orbital velocity v^2 = G*M/r
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
     * Method to compute the distance from the celestial object to the star
     */
    public void computeDistanceToStar(){
        this.distanceToStar = Math.sqrt((position.x-390)*(position.x-390) + (position.y-320)*(position.y-320)); //in px
        this.distanceToStarKm = scaleDst * distanceToStar; //in km
    }

    /**
     * Method to compute the force applied on the planet at a certain position
     * The force computed is actually not a force but a force divided by a mass (the mass of the planet)
     * The mass of the planet is not needed since it will be divided
     */
    public void computeGravitationalForce() {
        this.gravitationalForce = ((G * Star.massStar) / Math.pow(distanceToStarKm * 1000, 2)); // m/s^2
    }
    /**
     * Method to compute the new velocity using an approximation of the acceleration at order 1
     */
    public void updateVelocity(float deltaT2){
        velocityX += deltaT2 * gravitationalForce * ((390 - position.x)/distanceToStar);
        velocityY += deltaT2 * gravitationalForce * ((320 - position.y)/distanceToStar);
    }

    /**
     * Method to compute the new position using an approximation
     */
    public void updatePosition(float deltaT2){
        //cast by an integer if it is negative leads to a nil value so we use the absolute value
        if(velocityX<=0){
            position.x += - (int) Math.abs((deltaT2 * (velocityX)) / (1000*scaleDst)); // in px
        } else {
            position.x += (int) Math.abs((deltaT2 * (velocityX)) / (1000*scaleDst)); // in px
        }
        if (velocityY<=0){
            position.y += - (int) Math.abs((deltaT2 * (velocityY))/(1000*scaleDst)); // in px
        } else {
            position.y += (int) Math.abs((deltaT2 * (velocityY))/(1000*scaleDst)); // in px
        }

    }

    // Method to verify if the planet has gone too far, the distance chosen is not scientific
    public boolean isTooFar(){
        return distanceToStar >= 390;
    }

    // Method to verify if the planet is colliding with the sun
    public boolean isTooClose(long sunRadius){
        return distanceToStarKm <= (radiusKm+sunRadius*3);
    }

    @Override
    public String toString() {
        return typeStr + " planet of radius: " + radiusKm + " and mass: " + mass + " at " + distanceToStarKm + "km from the star.";
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        float[] dist = {0.05f, 1f};
        RadialGradientPaint p = new RadialGradientPaint(position.x + radius, position.y + radius, 2 * radius, dist, colors);
        g2D.setPaint(p);
        g2D.fillOval(position.x, position.y, 2 * radius, 2 * radius);
    }

}
