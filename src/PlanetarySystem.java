import javax.swing.*;
import java.util.LinkedList;

public class PlanetarySystem extends JPanel  {
    public LinkedList<CelestialObject> planets;
    public float deltaT;

    public PlanetarySystem () {
        this.setBounds(0,0,200,200);
        this.setLayout(null);
    }

}
