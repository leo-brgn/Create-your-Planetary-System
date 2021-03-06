import java.awt.*;

public class Star extends CelestialObject{

    public Star(){
        position = new Point(390-50, 320-50);
        this.setLocation(position);
        this.setSize(100,100);
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
        for(int i = 0; i<=4; i++){
            g.setColor(new Color(100,100,100,(255*(4-i))/5+50));
            g.fillOval(50-10*i,50-10*i,20*i,20*i);
        }
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
