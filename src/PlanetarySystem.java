import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Class for the planetary system inheriting from JPanel implementing Runnable for multi-threading practice
 */
public class PlanetarySystem extends JPanel implements Runnable, ChangeListener {

    /**
     * Attributes
     */
    private final LinkedList<CelestialObject> addedCelestialObjects;
    private final LinkedList<CelestialObject> celestialObjects;
    public double timeScale;
    private final Star star;
    private final JSlider slider;
    private final JLabel scaleTimeLabel;
    public boolean isRunning;

    /**
     * Constructor
     */
    public PlanetarySystem () {
        this.addedCelestialObjects = new LinkedList<>();
        this.celestialObjects = new LinkedList<>();
        isRunning = true;
        // Creation of the black panel
        this.setBounds(0,0,780,640);
        this.setBackground(Color.BLACK);
        this.setLayout(null);
        BackgroundStars backgroundStars = new BackgroundStars(500); //creation of the background stars
        this.star = new Star(50); //creation of the central star
        this.add(backgroundStars);
        this.setVisible(true);

        // Adding the sun, the first element of the set of celestial objects, no interactions on it in this version
        celestialObjects.add(star);
        timeScale = (365.25*24*3600)/1000f; //one year in seconds

        // THREAD
        Thread simulationThread = new Thread(this, "Simulation Thread");
        simulationThread.start();
        slider = new JSlider(JSlider.HORIZONTAL, 1,20, 10);
        slider.setBounds(10,10,100,50);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setBackground(Color.BLACK);
        slider.setForeground(Color.WHITE);
        slider.addChangeListener(this);

        //Distance scale
        JLabel scaleSizeLabel = new JLabel("405_000 km (sun) - 2 UA (distances)");
        scaleSizeLabel.setBounds(80, 565, 300,20);
        scaleSizeLabel.setForeground(Color.WHITE);


        //Time scale
        scaleTimeLabel = new JLabel("Timescale");
        scaleTimeLabel.setBounds(120, 10, 100,20);
        scaleTimeLabel.setForeground(Color.WHITE);

        this.add(slider);
        this.add(scaleSizeLabel);
        this.add(scaleTimeLabel);
    }

    @Override
    public void run() {
        long deltaT;
        long lastTime = System.currentTimeMillis();
        long timeNow;
        while(isRunning) {
            timeNow = System.currentTimeMillis();
            deltaT = timeNow - lastTime; //calculation of the difference of time to find the derivative
            lastTime = timeNow;
            update(deltaT);
            render();
            try{
                Thread.sleep(100);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * Method to update the planetary system every deltaT
     */
    public void update(float deltaT){
        if(!celestialObjects.isEmpty()) {
            for (CelestialObject c : celestialObjects) {
                if (!addedCelestialObjects.contains(c)) {
                    drawCelestialObject(c);
                }
                if (!(c instanceof Star)) { //we only consider planets
                    Planet p = (Planet) c;

                    //update of the fundamental variables of the planet
                    p.computeDistanceToStar();
                    p.computeGravitationalForce();
                    p.updatePosition((float) (timeScale * deltaT));
                    p.updateVelocity((float) (timeScale * deltaT));

                    Collections.sort(celestialObjects);
                    isColliding(p, celestialObjects.indexOf(p));
                }
            }

        }
    }

    public void render(){
        if(!celestialObjects.isEmpty()) {
            for (CelestialObject c : celestialObjects) {
                c.repaint();
            }
        }
    }

    public void addCelestialObject(TypePlanet typePlanet, Point position, float size, int colorIndex, String name){
        celestialObjects.add(new Planet((int) size,position, colorIndex, typePlanet, name));
    }

    public void drawCelestialObject(CelestialObject celestialObject) {
        this.add(celestialObject);
        addedCelestialObjects.add(celestialObject);
        this.setComponentZOrder(celestialObject, 0);
        System.out.println("New planet added: " + celestialObject);
    }

    /**
     * Method to determine collisions between celestials objects
     * Collisions between planets
     * Collisions between the sun and a planet
     */

    public void isColliding(Planet p, int index){

        Point positionP = p.getPosition();

        CelestialObject celestialObjectBefore = celestialObjects.get(index-1);
        Point positionQ = celestialObjectBefore.getPosition();

        if (index == 1 && p.getDistanceToStar() <= (star.getRadius() + p.getRadius())){
            isRunning = false;
            JOptionPane.showMessageDialog(this, p.getObjectName()+ " is colliding with the Sun !" +"\n"+ "Close the game and try again ! ");
        } else if (index > 1 && getPointDistance(positionP, positionQ) <= (p.getRadius() + celestialObjectBefore.getRadius())) {
            isRunning = false;
            JOptionPane.showMessageDialog(this, (p.getObjectName() + " is colliding with " + celestialObjectBefore.getObjectName() +"\n"+ "Close the game and try again ! "));
        }
    }

    /**
     * Method to return the distance between two points
     */
    public double getPointDistance(Point p, Point q){
        return Math.sqrt((p.getX()-q.getX())*(p.getX()-q.getX())+(p.getY()-q.getY())*(p.getY()-q.getY()));
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == slider){
            timeScale = (365.25*24*3600)/1000f *0.5* slider.getValue();
            scaleTimeLabel.setText("Timescale");
        }
    }

    /**
     * Method to paint the scale of the distances between planets
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.drawLine(40,580,70,580);
        g.drawLine(40,580,40,570);
        g.drawLine(70,580,70,570);

    }

    public LinkedList<CelestialObject> getAddedCelestialObjects(){
        return addedCelestialObjects;
    }
}
