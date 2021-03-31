import com.sun.jdi.connect.spi.TransportService;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class FinishPanel extends JPanel implements ChangeListener {
    private LinkedList<Planet> planets = new LinkedList<>();
    private ArrayList<JPanel> panels = new ArrayList<>(5);
    private JSlider slider;

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

        slider = new JSlider(JSlider.HORIZONTAL, 1, 100, 10);
        slider.setBounds(100,500,100,50);
        slider.setMinorTickSpacing(10);
        slider.setMajorTickSpacing(25);
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
    }

    public void updateCases(){
        for(JPanel p: panels){
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {

    }
}
