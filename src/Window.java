import javax.swing.*;

public class Window extends JFrame {
    public int[] dimensions;
    public JPanel rightPanel;
    public JLabel nbPlanet;
    public JButton telluric;
    public JButton gazeous;
    public JLabel size;
    public JLabel  color;
    public JLabel preview;
    public JButton validate;
    public int nbPlanets;
    public PlanetarySystem planetarySystem;

    public Window(int p){
        nbPlanets = p;
        planetarySystem = new PlanetarySystem(new Star());
        this.setTitle("Create your Solar System");
        this.setSize(1050,640);
        this.setResizable(false);
        this.setLocation(120,20);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.add(planetarySystem);
        this.setVisible(true);
    }
}
