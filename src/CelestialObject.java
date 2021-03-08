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
    protected Point initialPosition;
    protected Point updatedPosition;
    protected float mass;

    /**
     * Abstract methods
     */
    public abstract String toString();
    public abstract void computeMass();
    public abstract void velocity();
    public void computeDistanceToStar(){
        distanceToStar = Math.sqrt((initialPosition.x - 390)*(initialPosition.x-390) + (initialPosition.y- 320)*(initialPosition.y-320));
    }
    public void updatePosition(){
        updatedPosition.x = updatedPosition.x + velocity.x;
        updatedPosition.y = updatedPosition.y + velocity.y;
    }

}
