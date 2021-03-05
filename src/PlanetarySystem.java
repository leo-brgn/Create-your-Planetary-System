import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class PlanetarySystem extends JPanel  {
    public LinkedList<Planet> planets;
    public float deltaT;

    public PlanetarySystem () {
        this.setBounds(0,0,200,200);
        this.setLayout(null);
    }

}
