import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * Class for the planetary system inheriting from JPanel implementing Runnable for multi-threading practice
 */
public class PlanetarySystem extends JPanel implements Runnable{
    /**
     * Attributes
     */
    public LinkedList<CelestialObject> celestialObjects;
    public float deltaT;
    public boolean running = true;

    /**
     * Constructor
     */
    public PlanetarySystem () {
        // Creation of the pane
        this.setBounds(0,0,780,640);
        this.setBackground(Color.BLACK);
        this.setLayout(null);
        this.setVisible(true);
        // Creation of the set of celestial objects shown in the panel
        celestialObjects = new LinkedList<CelestialObject>();
        // Adding the sun, the first element of the set of celestial objects, no interactions on it in this version
        celestialObjects.add(new Star());
        // New thread for the planets to move on the side
        new Thread(this).start();
    }

    /**
     * Method to run the planetary system in real time
     */
    public void run(){
        float time = System.currentTimeMillis();
        float dt = time;
        while(running = true){
            dt = System.currentTimeMillis() - time;
            updateSystem();
            time = System.currentTimeMillis();
        }
    }

    /**
     * Method to update the system each delta of time
     */
    public void updateSystem(){
       for(CelestialObject c : celestialObjects){
           c.position.x += 1;
           this.add(c);
       }
    }
}
