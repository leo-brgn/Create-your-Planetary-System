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
    private final BackgroundStars backgroundStars;
    private final Star star;

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
        this.backgroundStars = new BackgroundStars(500);
        this.star = new Star();
        this.add(backgroundStars);
        this.setVisible(true);
        // Adding the sun, the first element of the set of celestial objects, no interactions on it in this version
        celestialObjects.add(star);
        this.timeScale = 6*30*24*3600;
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

    public void resize(){
        this.setBounds(0,0,1050,640);
        this.repaint();
    }

    public void update(float deltaT){
        if(!celestialObjects.isEmpty()) {
            for (CelestialObject c : celestialObjects) {
                if (!addedCelestialObjects.contains(c)) {
                    drawCelestialObject(c);
                    this.setComponentZOrder(c, 0);
                }
                c.computeDistanceToStar();
                c.setGravitationalForce();
                c.updateVelocity(timeScale *deltaT / 1000);
                c.updatePosition(timeScale * deltaT / 1000);
                if(c.isTooFar()){
                    removeCelestialObject(c);
                }
                if(c.isTooClose(star.radiusKm) && !(c instanceof Star)){
                    removeCelestialObject(c);
                    JOptionPane.showMessageDialog(this, "The planet collided with the sun !");
                }
                if(c instanceof Star){
                    ((Star) c).updateSun(deltaT);
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

    public void addCelestialObject(TypePlanet typePlanet, Point position, float size, Color color){
        if (typePlanet == TypePlanet.GASEOUS){
            celestialObjects.add(new Gaseous((int) size, position,color));
        } else if (typePlanet == TypePlanet.ROCKY){
            celestialObjects.add(new Rocky((int) size,position,color));
        }

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
