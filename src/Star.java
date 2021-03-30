import java.awt.*;

public class Star extends CelestialObject{
    private final Color[] colors = {
            new Color(255,255,255,255),
            new Color(255, 228, 132, 255),
            new Color(255, 204, 51,255),
            new Color(252, 110, 1, 226),
            new Color(101, 44, 20, 233),
            new Color(209, 112, 9, 12),
    };
    private float alpha;

    public Star(){
        super(150, new Point(390,320));
        alpha = 1.0f;
        typeStr = "Star";
    }

    public void paintComponent(Graphics g){
        //super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;
        float[] dist = {0.05f*alpha, 0.1f*alpha, 0.17f*alpha, 0.22f * alpha, 0.35f*alpha, 1*alpha};
        RadialGradientPaint p = new RadialGradientPaint(position, radius, dist, colors);
        g2D.setPaint(p);
        g2D.fillOval(position.x-radius-15,position.y-radius-15,2*radius + 30,2*radius + 30);
    }


    public void updateSun(float deltaT){
        alpha = (float) Math.abs(Math.cos(deltaT/10000));
    }
    @Override
    public String toString() {
        return "Sun of radius: " + radiusKm;
    }

    @Override
    public void computeMass() {
        //No need for mass
    }

    public boolean isTooClose(){
        return false;
    }

    @Override
    public void updatePosition(float deltaT) {
        // No need for updating position
    }

    @Override
    public int compareTo(CelestialObject celestialObject) {
        return 1;
    }
}
