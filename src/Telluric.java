import java.awt.*;

public class Telluric extends CelestialObject {

    public Telluric (int radius, Point position) {
        this.radius = radius;
        this.position = position;
        this.setLocation(position);
        this.setSize(2*radius,2*radius);
    }

    public String toString(){
        return "Telluric planet";
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
        g2D.fillOval(0,0,2*radius,2*radius);
    }

    @Override
    public int compareTo(CelestialObject celestialObject) {
        return 0;
    }
}
