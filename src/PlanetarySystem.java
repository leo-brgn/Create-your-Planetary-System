import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.Collections;
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
    private final int MAX_FPS = 60;
    private final int MAX_TICKS = 5;
    private final float timeScale;
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
        this.add(new BackgroundStars());
        this.setVisible(true);
        // Adding the sun, the first element of the set of celestial objects, no interactions on it in this version
        celestialObjects.add(new Star());
        this.timeScale = 12*30*24*3600;
        // THREAD
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        long timeA = System.currentTimeMillis();
        long deltaT = 0;
        while(true){
            long timeB = System.currentTimeMillis();
            deltaT = timeB - timeA;
            timeA = timeB;
            update(deltaT);
            Collections.sort(celestialObjects);
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(float deltaT){
        if(!celestialObjects.isEmpty()) {
            for (CelestialObject c : celestialObjects) {
                if (!addedObj.contains(c)) {
                    this.add(c);
                    addedObj.add(c);
                    System.out.println("New planet added: " + c);
                }
                c.computeDistanceToStar();
                c.setGravitationalForce();
                c.updateVelocity(timeScale * (float) deltaT / 1000);
                c.updatePosition(timeScale * (float) deltaT / 1000);
                c.repaint();
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
