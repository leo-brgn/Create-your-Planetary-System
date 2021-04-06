import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Class called at the end of the program to display the five created planets and some of their characteristics
 */

public class FinishPanel extends JPanel implements ChangeListener {

    /**
     * Attributes
     */
    private LinkedList<Planet> planets;
    private ArrayList<JPanel> panels;
    private JSlider slider;

    /**
     * Constructor
     */
    public FinishPanel(int nbPlanets){

        this.planets = new LinkedList<>();
        this.panels = new ArrayList<>(5);
        this.setBackground(Color.BLACK);
        this.setBounds(780, 0, 270, 640);
        this.setLayout(null);
        for(int i=0; i<5; i++){ //initialize the five panel
            JPanel jPanel = new JPanel();
            jPanel.setBackground(Color.GRAY);
            jPanel.setBounds(10,60+100*i,250,90);
            JLabel jLabel = new JLabel("Planet n° " + (i+1));
            jLabel.setBounds(5 ,5,200,20);
            jLabel.setFont(new java.awt.Font(Font.DIALOG_INPUT,Font.BOLD,13));
            jLabel.setForeground(Color.WHITE);
            jPanel.add(jLabel);
            panels.add(jPanel);
            this.add(panels.get(i));
        }
        slider = new JSlider(JSlider.HORIZONTAL, 0,10,(int)PlanetarySystem.timeScale);
        slider.setBounds(10,10,100,50);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setBackground(Color.BLACK);
        slider.setForeground(Color.WHITE);
        slider.addChangeListener(this);
        this.add(slider);
        this.setVisible(true);
    }


    public void addPlanet(Planet planet){
        planets.add(planet);
    } //method to add the new planets to the current list

    public void updateCases(){ //method to update the five panels describing the planets

        for(int i=0; i<panels.size() ;i++) {

            CelestialPreview c = new CelestialPreview(35); // adding the preview of the planet
            c.setBounds(160, 0,90,90); // fixed preview at the right

            for(int j=0; j<planets.size() ;j++) {
                if(i==j) { //link the panel to the corresponding planet
                    c.setSize(planets.get(j).radius);
                    c.setColor(planets.get(j).colorIndex);

                    JLabel distance = new JLabel("Radius " + planets.get(j).radiusKm + " km");
                    distance.setBounds(5 ,20,200,20);
                    distance.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,13));
                    distance.setForeground(Color.WHITE);
                    panels.get(j).add(distance);

                }
            }
            c.setVisible(true);
            panels.get(i).add(c);
        }

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == slider){
            PlanetarySystem.timeScale = 365.5*3600*(slider.getValue() + 1);
        }
    }
}
