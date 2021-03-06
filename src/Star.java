import java.awt.*;

public class Star extends CelestialObject{

    public Star(){
        position = new Point(390-40, 320-40);
        this.setBounds(position.x, position.y, 180,180);
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

    public void updatePos(float dt){
       // this.setBounds(X,Y,180,180);
    }

    public void updatePos(){
        this.setBounds(position.x, position.y, 180,180);
    }

    @Override
    public int compareTo(CelestialObject celestialObject) {
        return 0;
    }
}
