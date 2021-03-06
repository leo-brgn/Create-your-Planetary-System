import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Title extends JFrame implements ActionListener{

    public static void main(String[] args) {
        new Title();
    }

    public Title() {
        
        this.setTitle("Create your Solar System");
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
        JLabel affBackground = new JLabel (new ImageIcon("items/wallpaper-HD.jpg"));
        affBackground.setBounds(0,0,1050,640);
        firstPanel.add(affBackground);

        JLabel txtLabel = new JLabel();
        txtLabel.setText("CREATE YOUR PLANETARY SYSTEM");
        txtLabel.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,45));
        txtLabel.setForeground(Color.WHITE);
        txtLabel.setBounds(110,35,830,50);
        affBackground.add(txtLabel);

        JLabel titleLabel = new JLabel();
        titleLabel.setText("How many planet do you want to add ?");
        titleLabel.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,30));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(270,425,510,50);
        affBackground.add(titleLabel);

        JLabel nbPlanetLabel= new JLabel();
        nbPlanetLabel.setText("1");
        nbPlanetLabel.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,20));
        nbPlanetLabel.setForeground(Color.WHITE);
        nbPlanetLabel.setBounds(525,495,100,35);
        // nbPlanetLabel.setHorizontalAlignment(SwingConstants.CENTER);
        affBackground.add(nbPlanetLabel);

        JButton launchButton = new JButton("Start");
        launchButton.setBounds(485,535,80,25);
        launchButton.setForeground(Color.WHITE);
        launchButton.setBackground(Color.GRAY);
        affBackground.add(launchButton);

        JButton plusButton = new JButton(new ImageIcon("items/logo-plus.png"));
        plusButton.setBounds(585,500,25,25);
        plusButton.addActionListener(this);
        affBackground.add(plusButton);

        JButton minusButton = new JButton(new ImageIcon("items/logo-minus.png"));
        minusButton.setBounds(440,500,25,25);
        affBackground.add(minusButton);

        this.add(firstPanel);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==plusButton){

        }
    }
}
