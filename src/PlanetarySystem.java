import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class PlanetarySystem extends JPanel  {
    public LinkedList<CelestialObject> celestialObjects;
    public float deltaT;

    public PlanetarySystem (CelestialObject co) {
        celestialObjects = new LinkedList<>();
        celestialObjects.add(co);
        this.setBounds(0,0,780,640);
        this.setBackground(Color.BLACK);
        this.setLayout(null);
        this.add(celestialObjects.get(0));
    }

    public void addCelestialObj(CelestialObject co){
        celestialObjects.add(co);
    }
}
