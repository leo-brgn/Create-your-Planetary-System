import java.awt.*;

public class Gaseous extends CelestialObject {

    public Gaseous(int radius, Point position, Color color) {
        super(radius, position, color);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;
        g2D.setColor(color);
        g2D.fillOval(position.x, position.y,2*radius,2*radius);
        //g2D.drawOval((int) (390-distanceToStar),(int) (320-distanceToStar),(int) (2*distanceToStar),(int)  (2*distanceToStar));
    }

    @Override
    public String toString() {
        return null;
    }

}
