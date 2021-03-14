import java.awt.*;

public class Rocky extends CelestialObject {

    public Rocky(int radius, Point position) {
        super(radius, position);
        density = 5000; // Density in kg/m3
        computeMass();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;
        g2D.setColor(Color.GREEN);
        g2D.fillOval(position.x, position.y,2*radius,2*radius);
        //g2D.drawOval((int) (390-distanceToStar),(int) (320-distanceToStar),(int) (2*distanceToStar),(int)  (2*distanceToStar));
    }

    @Override
    public String toString(){
        return "Rocky planet of radius: " + radiusKm + " and mass: " + mass + " at " + distanceToStarKm + "km from the star." ;
    }

}
