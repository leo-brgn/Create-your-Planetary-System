import javax.swing.*;
import java.awt.*;

public class Title extends JFrame{

    public static void main(String[] args) {
        new Title();
    }

    public Title() {
        
        this.setTitle("Create my Solar System");
        this.setSize(1050,640);
        this.setResizable(false);
        this.setLocation(120,20);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(null);
        JPanel firstPanel = new JPanel();
        firstPanel.setBackground(Color.BLACK);
        firstPanel.setBounds(0,0,1050,640);
        firstPanel.setVisible(true);

        firstPanel.setLayout(null);
        JLabel affBackground = new JLabel (new ImageIcon("items/papiers-peints-solar-system.jpg"));
        affBackground.setBounds(0,0,1050,640);
        // affBackground.setVisible(true);
        firstPanel.add(affBackground);

        JLabel txtLabel = new JLabel();
        txtLabel.setText("How many planet do you want to add?");
        txtLabel.setBounds(500,10,300,20);
        firstPanel.add(txtLabel);

        this.add(firstPanel);
        this.setVisible(true);

        JButton launchButton = new JButton("Start");


        JTextField nbPlanetLabel= new JTextField();


    }

    private void ActionPerformed(){

    }
}
