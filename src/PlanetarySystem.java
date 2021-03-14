import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Class for the planetary system inheriting from JPanel implementing Runnable for multi-threading practice
 */
public class PlanetarySystem extends JPanel implements Runnable{

    /**
     * Attributes
     */
    private final LinkedList<CelestialObject> addedCelestialObjects;
    private final LinkedList<CelestialObject> celestialObjects;
    private final float timeScale;

    /**
     * Constructor
     */
    public PlanetarySystem () {
        this.addedCelestialObjects = new LinkedList<>();
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
        Thread simulationThread = new Thread(this, "Simulation Thread");
        simulationThread.start();
    }

    @Override
    public void run() {
        long deltaT;
        long lastTime = System.currentTimeMillis();
        long timeNow;
        while(isRunning()) {
            timeNow = System.currentTimeMillis();
            deltaT = timeNow - lastTime;
            lastTime = timeNow;
            update(deltaT);
            render();
            try{
                Thread.sleep(50);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }

    public void update(float deltaT){
        if(!celestialObjects.isEmpty()) {
            for (CelestialObject c : celestialObjects) {
                if (!addedCelestialObjects.contains(c)) {
                    drawCelestialObject(c);
                }
                c.computeDistanceToStar();
                c.setGravitationalForce();
                c.updateVelocity(timeScale *deltaT / 1000);
                c.updatePosition(timeScale * deltaT / 1000);
                if(c.isTooFar()){
                    removeCelestialObject(c);
                }
            }
            Collections.sort(celestialObjects);
        }
    }

    public void render(){
        if(!celestialObjects.isEmpty()) {
            for (CelestialObject c : celestialObjects) {
                c.repaint();
            }
        }
    }

    public boolean isRunning(){
        return true;
    }

    public void addCelestialObject(CelestialObject celestialObject){
        celestialObjects.add(celestialObject);
    }
    public void drawCelestialObject(CelestialObject celestialObject) {
        this.add(celestialObject);
        addedCelestialObjects.add(celestialObject);
        System.out.println("New planet added: " + celestialObject);
    }
    public void removeCelestialObject(CelestialObject celestialObject){
        celestialObjects.remove(celestialObject);
        addedCelestialObjects.remove(celestialObject);
        this.remove(celestialObject);
        System.out.println("Planet got too far and removed: " + celestialObject);
    }

    public int getAddedSize(){
        return this.addedCelestialObjects.size();
    }

}
