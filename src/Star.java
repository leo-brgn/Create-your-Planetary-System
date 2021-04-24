import java.awt.*;

/**
 * Class inheriting from CelestialObject to create the central star
 */
public class Star extends CelestialObject{

    /**
     * Attributes
     */
    private final Color[] colors = {

            //different colors to create a color gradient
            new Color(255,255,255,255),
            new Color(255, 228, 132, 255),
            new Color(255, 204, 51,255),
            new Color(252, 110, 1, 226),
            new Color(101, 44, 20, 233),
            new Color(209, 112, 9, 12),

    };
    static double massStar = 0; //mass of the star called in the class planet

    /**
     * Constructor
     */
    public Star(int rad){
        //Initialize the characteristics of the central star
        super(rad, new Point(390,320));
        radiusKm = radius * scaleSunSize;
        typeStr = "Star";
        density = 1.41; //same as our central star
        objectName = "Sun";
        computeMass();
        massStar = mass;
    }

    /**
     * Method to draw the sun and its gradient
     */
    public void paintComponent(Graphics g){
        Graphics2D g2D = (Graphics2D)g;
        float[] dist = {0.05f, 0.1f, 0.17f, 0.22f, 0.35f, 1};
        RadialGradientPaint p = new RadialGradientPaint(position, 3*radius, dist, colors);
        g2D.setPaint(p);
        g2D.fillOval(position.x-3*radius-15,position.y-3*radius-15,3*2*radius + 30,3*2*radius + 30);
        g2D.setColor(new Color(1f,1f,1f,0.1f));
        g2D.drawOval(position.x-radius,position.y-radius,2*radius,2*radius); //demarcation to see the limit of the sun for collisions
    }

    @Override
    public String toString() {
        return "Sun of radius: " + radiusKm +"km and of mass " + mass +"kg";
    }

    /**
     * Method to sort the sun
     */
    @Override
    public int compareTo(CelestialObject celestialObject) {
        return 1;
    }
}
