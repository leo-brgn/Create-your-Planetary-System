import java.awt.*;

public class Gazeous extends CelestialObject {

    public Gazeous(int radius, Point position) {
        this.radius = radius;
        this.initialPosition = position;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public void computeMass() {

    }

    @Override
    public void updatePosition() {
        velocity();
    }

    @Override
    public void velocity() {

    }

    @Override
    public int compareTo(CelestialObject celestialObject) {
        return 0;
    }
}
