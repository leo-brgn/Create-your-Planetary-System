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
    public static float timeScale;
    private final Star star;

    /**
     * Constructor
     */
    public PlanetarySystem () {
        this.addedCelestialObjects = new LinkedList<>();
        this.celestialObjects = new LinkedList<>();

        // Creation of the black panel
        this.setBounds(0,0,780,640);
        this.setBackground(Color.BLACK);
        this.setLayout(null);
        BackgroundStars backgroundStars = new BackgroundStars(500); //creation of the background stars
        this.star = new Star(); //creation of the central star
        this.add(backgroundStars);
        this.setVisible(true);
        // Adding the sun, the first element of the set of celestial objects, no interactions on it in this version
        celestialObjects.add(star);
        timeScale = 30*24*3600; //one month in seconds
        // THREAD
        Thread simulationThread = new Thread(this, "Simulation Thread");
        simulationThread.start();
    }

    @Override
    public void run() {
        long deltaT;
        long lastTime = System.currentTimeMillis();
        long timeNow;
        while(true) {
            timeNow = System.currentTimeMillis();
            deltaT = timeNow - lastTime; //calculation of the difference of time to find the derivative
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
                    this.setComponentZOrder(c, 0);
                }
                if (!(c instanceof Star)) {
                    Planet p = (Planet) c;

                    p.computeDistanceToStar();
                    p.computeGravitationalForce();

                    p.updatePosition(timeScale * deltaT /1000);
                    p.updateVelocity(timeScale * deltaT /1000);


//                    p.updatePosition( deltaT/1000);
//                    p.updateVelocity( deltaT/1000);

                    if (p.isTooFar()) {
                        removeCelestialObject(c);
                    }
                    if (p.isTooClose(star.radiusKm)) {
                        removeCelestialObject(c);
                        JOptionPane.showMessageDialog(this, "The planet collided with the sun !");
                    }

                } else {
                    ((Star) c).updateSun(deltaT / 1000);
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

    public void addCelestialObject(TypePlanet typePlanet, Point position, float size, int colorIndex){
        celestialObjects.add(new Planet((int) size,position, colorIndex, typePlanet));
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
