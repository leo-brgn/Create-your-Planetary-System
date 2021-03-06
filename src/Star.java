import java.awt.*;

public class Star extends CelestialObject{

    public Star(){
        this.setBounds(390-50,320-50,200,200);
    }
    @Override
    public String toString() {
        return null;
    }

    @Override
    public void computeMass() {

    }

    public void paint(Graphics g){
        g.setColor(Color.YELLOW);
        g.fillOval(0,0,100,100);
    }
}
