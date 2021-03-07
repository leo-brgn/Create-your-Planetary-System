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
    private LinkedList<CelestialObject> addedObj;
    private LinkedList<CelestialObject> celestialObjects;
    private boolean stared = false;

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
        while(true){
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

    public void addCelestialObject(CelestialObject celestialObject){
        celestialObjects.add(celestialObject);
    }
    /*
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(!stared){
            Graphics2D g2D = (Graphics2D) g;
            g2D.setColor(Color.WHITE);
            for (int i = 0; i<100; i++){
                double a = Math.random();
                double b = Math.random();
                double c = Math.random();
                g2D.fillOval((int) (a*780),(int) (b*640),(int) (2*c+1),(int) (2*c+1));
            }
            stared = true;
        }

    }*/
}
