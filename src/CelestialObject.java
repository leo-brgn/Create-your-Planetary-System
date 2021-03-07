import javax.swing.*;
import java.awt.*;

/**
 * Abstract class that defines the general idea of a celestial object.
 * It allows for the creation of stars, planets and maybe later satellites.
 */
public abstract class CelestialObject extends JComponent implements Comparable<CelestialObject> {
    final public float G = (float) (6.67430 * Math.pow(10, -11));
    protected int radius;
    protected double distanceToStar;
    protected Point initialVelocity;
    protected Point velocity;
    protected Point position;
    protected float mass;

    /**
     * Abstract methods
     */
    public abstract String toString();
    public abstract void computeMass();
    public abstract void updatePosition();
    public abstract void velocity();
    public void computeDistanceToStar(){
        distanceToStar = Math.sqrt((position.x - 365)*(position.x-365) + (position.y- 295)*(position.y-295));
    }

}
