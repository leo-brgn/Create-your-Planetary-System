import java.awt.*;

public abstract class CelestialObject {
    protected Color[] colorRange = new Color[8];
    protected float mass;
    protected int radius;
    protected float distanceToStar;
    final public float G = (float) (6.67430 * Math.pow(10, -11));

    protected float[] initialVelocity = new float[2];
    protected int[] position = new int[2];

    public abstract String toString();
    public abstract void computeMass();
}
