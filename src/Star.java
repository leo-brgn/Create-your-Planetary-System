import java.awt.*;

public class Star extends CelestialObject{
    private final Color[] colors = {
            new Color(255,255,255,255),
            new Color(255, 228, 132, 255),
            new Color(255, 204, 51,255),
            new Color(252, 150, 1,255),
            new Color(209, 64, 9,255),
            new Color(209, 64, 9,12),
    };

    public Star(){
        super(25, new Point(390,320));
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;
        float[] dist = {0.2f, 0.4f, 0.7f, 0.8f, 0.9f, 1.0f};
        RadialGradientPaint p = new RadialGradientPaint(initialPosition, radius, dist, colors);
        g2D.setPaint(p);
        g2D.fillOval(initialPosition.x-radius-15,initialPosition.y-radius-15,2*radius + 30,2*radius + 30);
    }

    @Override
    public String toString() {
        return "Sun of mass: ";
    }

    @Override
    public void computeMass() {
    }

    @Override
    public void updatePosition() {
    }

    @Override
    public void setVelocity() {

    }

    @Override
    public int compareTo(CelestialObject celestialObject) {
        return 0;
    }
}
