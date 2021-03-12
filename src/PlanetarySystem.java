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
    private JLabel FPSText;
    private float FPS;
    /**
     * Constructor
     */
    public PlanetarySystem () {
        this.addedObj = new LinkedList<>();
        this.celestialObjects = new LinkedList<>();
        // Creation of the pane
        this.setBounds(0,0,780,640);
        this.setBackground(Color.BLACK);
        FPSText = new JLabel(String.valueOf(FPS));
        FPSText.setBounds(20,20,830,50);
        FPSText.setForeground(Color.WHITE);
        FPSText.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,10));
        this.add(FPSText);
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
            long timeA = System.currentTimeMillis();
            long timeB = System.currentTimeMillis();
            long deltaT = 1;
            long scaleTime = 12*30*24*3600;
            if(!celestialObjects.isEmpty()){
                for(CelestialObject c : celestialObjects){
                    if(!addedObj.contains(c)){
                        this.add(c);
                        addedObj.add(c);
                        System.out.println("New planet added: " + c);
                    }
                    c.computeDistanceToStar();
                    c.setGravitationalForce();
                    c.updateVelocity(scaleTime*(float)deltaT / 1000);
                    c.updatePosition(scaleTime*(float)deltaT / 1000);
                    c.repaint();
                }
            }
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            timeB = System.currentTimeMillis();
            deltaT = timeB - timeA;
            FPS = 1/((float)deltaT/1000);
            this.FPSText.setText(String.valueOf(FPS));
        }

    }

    public void addCelestialObject(CelestialObject celestialObject){
        celestialObjects.add(celestialObject);
    }

    public int getAddedSize(){
        return this.addedObj.size();
    }

}
