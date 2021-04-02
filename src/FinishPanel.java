import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class FinishPanel extends JPanel {
    private LinkedList<Planet> planets = new LinkedList<>();
    private ArrayList<JPanel> panels = new ArrayList<>(5);

    public FinishPanel(int nbPlanets){
        this.setBackground(Color.BLACK);
        this.setBounds(780, 0, 270, 640);
        this.setLayout(null);
        for(int i=0; i<5; i++){
            JPanel jPanel = new JPanel();
            jPanel.setBackground(Color.GRAY);
            jPanel.setBounds(10,100+80*i,250,70);
            JLabel jLabel = new JLabel("Empty.");
            jLabel.setBounds(100,45,50,20);
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
        for(JPanel p: panels){
        }
    }


}
