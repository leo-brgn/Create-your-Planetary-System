import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventListener;
import java.util.Vector;

public class Window extends JFrame implements MouseListener {
    // GUI attributes
    public JPanel rightPanel;
    public JLabel nbPlanet;
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

    public Window(int p){
        nbPlanets = p;
        planetarySystem = new PlanetarySystem();
        // Creation of the window
        this.setTitle("Create your Solar System");
        this.setSize(1050,640);
        this.setResizable(false);
        this.setLocation(120,20);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setBackground(Color.BLUE);
        this.addMouseListener(this);
        this.add(planetarySystem);
        this.setVisible(true);
        // Creation of the panel
    }

    /**
     * Method to handle the action on the mouse
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        if(mouseEvent.getButton() == MouseEvent.BUTTON1){
            //System.out.println(mouseEvent.getPoint());
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {}

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {}
}
