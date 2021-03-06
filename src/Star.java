import java.awt.*;

public class Star extends CelestialObject{

    public Star(){
        this.setBounds(390-40,320-40,180,180);
    }
    @Override
    public String toString() {
        return null;
    }

    @Override
    public void computeMass() {

    }

    public void paint(Graphics g){
        long time = System.currentTimeMillis();
        g.setColor(new Color(100,100,100,80));
        g.fillOval(0,0,100,100);
        g.setColor(new Color(255,255,255,255));
        g.fillOval(0,0,80,80);
    }
}
