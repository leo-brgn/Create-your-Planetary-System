import java.awt.*;

/**
 * Class inheriting from CelestialObject to create the central star
 */
public class Star extends CelestialObject{

    /**
     * Attributes
     */
    private final Color[] colors = {
            new Color(255,255,255,255),
            new Color(255, 228, 132, 255),
            new Color(255, 204, 51,255),
            new Color(252, 110, 1, 226),
            new Color(101, 44, 20, 233),
            new Color(209, 112, 9, 12),

    };
    private float alpha;
    static double massStar = 0;
    /**
     * Constructor
     */
    public Star(int rad){
        super(rad, new Point(390,320));
        alpha = 1.0f;
        radiusKm = radius * scaleSunSize;
        typeStr = "Star";
        density = 1.41;//same as our central star
        objectName = "Sun";
        computeMass();
        massStar = mass;
    }

    public void paintComponent(Graphics g){
        //super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;
        float[] dist = {0.05f*alpha, 0.1f*alpha, 0.17f*alpha, 0.22f * alpha, 0.35f*alpha, 1*alpha};
        RadialGradientPaint p = new RadialGradientPaint(position, radius, dist, colors);
        g2D.setPaint(p);
        g2D.fillOval(position.x-3*radius-15,position.y-3*radius-15,3*2*radius + 30,3*2*radius + 30);
    }

    @Override
    public String toString() {
        return "Sun of radius: " + radiusKm +"km and of mass " + mass +"kg";
    }

    @Override
    public int compareTo(CelestialObject celestialObject) {
        return 1;
    }
}
