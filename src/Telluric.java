import java.awt.*;

public class Telluric extends CelestialObject {

    public Telluric (int radius, Point position) {
        this.radius = radius;
        this.initialPosition = position;
        computeDistanceToStar();
        setLeftUpSquare();
        setRelativePosition();
        this.setLocation(leftUpSquare);
        this.setSize(2* ((int)distanceToStar+radius),2* ((int)distanceToStar+radius));
    }

    public String toString(){
        return "Telluric planet" + distanceToStar;
    }

    public void computeMass() {
    }

    @Override
    public void updatePosition() {
        velocity();
    }

    @Override
    public void velocity() {
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;
        Color color = new Color((float)1.0,(float)1.0,(float)1.0,(float)1.0);
        g2D.setColor(color);
        g2D.fillOval(relativePosition.x,relativePosition.y,2*radius,2*radius);
        //g2D.fillOval(0,0,(int)distanceToStar+radius,(int)distanceToStar+radius);
        g2D.drawLine(390, 320, 500,500);
    }

    @Override
    public int compareTo(CelestialObject celestialObject) {
        return 0;
    }
}
