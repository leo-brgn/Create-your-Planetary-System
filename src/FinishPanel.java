import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Class called at the end of the program to display the five created planets and some of their characteristics
 */

public class FinishPanel extends JPanel {

    private LinkedList<Planet> planets = new LinkedList<>();
    private ArrayList<JPanel> panels = new ArrayList<>(5);

    public FinishPanel(int nbPlanets){
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

        this.setVisible(true);
    }

    public void addPlanet(Planet planet){
        planets.add(planet);
    }

    public void updateCases(){
        for(int i=0; i<panels.size() ;i++) {

            CelestialPreview c = new CelestialPreview(35); // adding the preview of the planet
            c.setBounds(160, 0,90,90); // fixed preview at the right

            for(int j=0; j<planets.size() ;j++) {
                if(i==j) {
                    c.setSize(planets.get(j).radius);
                    c.setColor(planets.get(j).colorIndex);// test color
                }
            }
            c.setVisible(true);
            panels.get(i).add(c);
        }

    }

    public LinkedList getplanets() {
        return planets;
    }


}
