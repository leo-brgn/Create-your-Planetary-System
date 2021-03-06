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
    public LinkedList<CelestialObject> addedObj;
    public LinkedList<CelestialObject> celestialObjects;
    public boolean running = true;

    /**
     * Constructor
     */
    public PlanetarySystem () {
        addedObj = new LinkedList<CelestialObject>();
        // Creation of the pane
        this.setBounds(0,0,780,640);
        this.setBackground(Color.BLACK);
        this.setLayout(null);
        this.setVisible(true);
        // Creation of the set of celestial objects shown in the panel
        celestialObjects = new LinkedList<CelestialObject>();
        // Adding the sun, the first element of the set of celestial objects, no interactions on it in this version
        celestialObjects.add(new Star());
        // THREAD
        Thread t = new Thread(this);
        t.start();
    }


    @Override
    public void run() {
        while(running){
            if(!celestialObjects.isEmpty()){
                for(CelestialObject c : celestialObjects){
                    if(!addedObj.contains(c)){
                        this.add(c);
                        addedObj.add(c);
                        System.out.println("New planet added: " + c);
                    }
                    c.updatePosition();
                    c.repaint();
                }
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
