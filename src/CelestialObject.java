import javax.swing.*;
import java.awt.*;

/**
 * Abstract class that defines the general idea of a celestial object.
 * It allows for the creation of stars, planets and maybe later satellites.
 */
public abstract class CelestialObject extends JComponent implements Comparable<CelestialObject> {
    protected Color[] colorRange = new Color[8];
    protected float mass;
    protected int radius;
    protected float distanceToStar;
    final public float G = (float) (6.67430 * Math.pow(10, -11));

    protected Point initialVelocity = new Point(0,0);
    protected Point velocity = new Point(initialVelocity.x,initialVelocity.y);
    protected Point position = new Point(0,0);

    /**
     * Abstract methods
     */
    public abstract String toString();
    public abstract void computeMass();
    public abstract void updatePosition();
    public abstract void velocity();

}
