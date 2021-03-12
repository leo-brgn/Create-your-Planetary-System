import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Window extends JFrame implements MouseListener {
    // GUI attributes
    public JPanel rightPanel;
    public JLabel planetNb;
    public JButton telluric;
    public JButton gazeous;
    public JLabel size;
    public JLabel  color;
    public JLabel preview;
    public JButton validate;
    public Point mouseLocation;
    // System attributes
    public int nbPlanets;
    public PlanetarySystem planetarySystem;

    public Window(int nbPlanets){
        this.nbPlanets = nbPlanets;
        planetarySystem = new PlanetarySystem();
        // Creation of the window
        this.setTitle("Create your Solar System");
        this.setSize(1050,640);
        this.setResizable(false);
        this.setLocation(120,20);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setBackground(Color.BLUE);
        this.getContentPane().addMouseListener(this);
        new Thread().start();
        this.getContentPane().add(planetarySystem);
        this.setVisible(true);
        // Creation of the panel

        planetNb = new JLabel();
        planetNb.setText("Planet 1/" + nbPlanets);

    }


    /**
     * Method to handle the action on the mouse
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if(mouseEvent.getButton() == MouseEvent.BUTTON1) {
            if (planetarySystem.getAddedSize() < 10 + 1) {
                planetarySystem.addCelestialObject(new Rocky((int) (10 * Math.random()), mouseEvent.getPoint()));
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
    }
}
