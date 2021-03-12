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
    private final LinkedList<CelestialObject> addedObj;
    private final LinkedList<CelestialObject> celestialObjects;

    /**
     * Constructor
     */
    public PlanetarySystem () {
        this.addedObj = new LinkedList<>();
        this.celestialObjects = new LinkedList<>();
        // Creation of the pane
        this.setBounds(0,0,780,640);
        this.setBackground(Color.BLACK);
        this.setLayout(null);
        this.setVisible(true);
        // Adding the sun, the first element of the set of celestial objects, no interactions on it in this version
        celestialObjects.add(new Star());
        // THREAD
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        this.add(new BackgroundStars());
        while(true){
            if(!celestialObjects.isEmpty()){
                for(CelestialObject c : celestialObjects){
                    if(!addedObj.contains(c)){
                        this.add(c);
                        addedObj.add(c);
                        System.out.println("New planet added: " + c);
                    }
                    c.computeDistanceToStar();
                    c.setGravitationalForce();
                    c.updateVelocity();
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

    public void addCelestialObject(CelestialObject celestialObject){
        celestialObjects.add(celestialObject);
    }

    public int getAddedSize(){
        return this.addedObj.size();
    }

}
