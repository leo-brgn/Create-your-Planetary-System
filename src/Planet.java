import java.awt.*;

public class Planet extends CelestialObject {

    private final Color[] colors;
    private long lifeTime; //attributes that will be displayed at the end of the game

    public Planet(int radius, Point position, int colorIndex, TypePlanet typePlanet) {
        super(radius, position, colorIndex);
        if (typePlanet == TypePlanet.GASEOUS) {
            density = 1; // Density in kg/m3
            typeStr = "Gaseous";
        } else if (typePlanet == TypePlanet.ROCKY) {
            density = 5; // Density in kg/m3
            typeStr = "Rocky";
        }
        colors = PlanetGradient.getTwoColors(colorIndex);
        computeMass();
        computeDistanceToStar();
        setInitialVelocity();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        float[] dist = {0.05f, 1f};
        RadialGradientPaint p = new RadialGradientPaint(position.x + radius, position.y + radius, 2 * radius, dist, colors);
        g2D.setPaint(p);
        g2D.fillOval(position.x, position.y, 2 * radius, 2 * radius);
    }

    // Method to set the initial velocity of the planet to stay in orbit
    public void setInitialVelocity(){
        //applying the fundamental law of dynamics and considering the mass of the sun much bigger
        double magnitude = Math.sqrt((G*Star.massStar)/(distanceToStarKm*1000)); //in m/s
        System.out.print(distanceToStarKm);
        double[] vectorSunToPlanet = {(position.x - 390)/distanceToStar, (position.y - 320)/distanceToStar};
        if(Math.random()<0.5) {
            velocityX = magnitude * vectorSunToPlanet[1];
            velocityY = -magnitude * vectorSunToPlanet[0];
        }else{
            velocityX = -magnitude * vectorSunToPlanet[1];
            velocityY = magnitude * vectorSunToPlanet[0];
        }

    }

    // Method to compute the distance from the celestial object to the star
    public void computeDistanceToStar(){
        this.distanceToStar = Math.sqrt((position.x-390)*(position.x-390) + (position.y-320)*(position.y-320));
        this.distanceToStarKm = scaleDst * distanceToStar;
    }

    // Method to compute the force applied on the planet at a certain position
    public void computeGravitationalForce() { // we do not use the mass of PLANET
        this.gravitationalForce = ((G * Star.massStar) / Math.pow(distanceToStarKm * 1000, 2)); // m/s^2
    }

    // Method to compute the new velocity using an approximation of the acceleration at order 1
    public void updateVelocity(float deltaT2){

        /*
        velocityX += deltaT * gravitationalForce * ((390 - position.x)/distanceToStar);
        velocityY += deltaT * gravitationalForce * ((320 - position.y)/distanceToStar);

        velocityX += deltaT2 * gravitationalForce;
        velocityY += deltaT2 * gravitationalForce;*/

    }

    // Method to compute the new position using an approximation
    public void updatePosition(float deltaT2){
        position.x = (int) (position.x + deltaT2 * (velocityX)/(1000*scaleDst)); // px
        position.y = (int) (position.y + deltaT2 * (velocityY)/(1000*scaleDst)); // px
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


}
