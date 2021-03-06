import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Class containing the main method (main thread) which creates the title scene
 * This class allows us to choose the number of planets to create and open the new Window
 */
public class Title extends JFrame implements ActionListener{

    /**
     * Attributes
     */
    private JLabel nbPlanetLabel;
    private JButton plusButton;
    private JButton minusButton;
    private int nbInitial = 1;

    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {
        new Title();
        new Window(2);
    }

    /**
     * Constructor
     */
    public Title() {
        
        this.setTitle("Create your Solar System");
        this.setSize(1050,640);
        this.setResizable(false);
        this.setLocation(120,20);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(null);
        JPanel firstPanel = new JPanel(); //ATTRIBUT
        firstPanel.setBackground(Color.BLACK);
        firstPanel.setBounds(0,0,1050,640);
        firstPanel.setVisible(true);

        firstPanel.setLayout(null);
        JLabel affBackground = new JLabel (new ImageIcon("items/wallpaper-HD.jpg"));
        affBackground.setBounds(0,0,1050,640);
        firstPanel.add(affBackground);

        JLabel txtLabel = new JLabel(); //ATTRIBUT
        txtLabel.setText("CREATE YOUR PLANETARY SYSTEM");
        txtLabel.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,45));
        txtLabel.setForeground(Color.WHITE);
        txtLabel.setBounds(110,35,830,50);
        affBackground.add(txtLabel);

        JLabel titleLabel = new JLabel(); //ATTRIBUT
        titleLabel.setText("How many planet do you want to add ?");
        titleLabel.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,30));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(270,425,510,50);
        affBackground.add(titleLabel);

        JLabel nbPlanetLabel= new JLabel(); //ATTRIBUT
        nbPlanetLabel.setText(String.valueOf(nbInitial));
        nbPlanetLabel.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,20));
        nbPlanetLabel.setForeground(Color.WHITE);
        nbPlanetLabel.setBounds(525,495,100,35);
        // nbPlanetLabel.setHorizontalAlignment(SwingConstants.CENTER);
        affBackground.add(nbPlanetLabel);

        JButton launchButton = new JButton("Start"); //ATTRIBUT
        launchButton.setBounds(485,535,80,25);
        launchButton.setForeground(Color.WHITE);
        launchButton.setBackground(Color.GRAY);
        affBackground.add(launchButton);

        JButton plusButton = new JButton(new ImageIcon("items/logo-plus.png")); //ATTRIBUT
        plusButton.setBounds(585,500,25,25);
        plusButton.addActionListener(this);
        affBackground.add(plusButton);

        JButton minusButton = new JButton(new ImageIcon("items/logo-minus.png")); //ATTRIBUT
        minusButton.setBounds(440,500,25,25);
        affBackground.add(minusButton);

        this.add(firstPanel);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==plusButton && nbInitial<5){
            nbInitial=nbInitial+1;
        }else if(e.getSource()==minusButton && nbInitial>1){
            nbInitial= nbInitial-1;
        }else{
            //
        }
        nbPlanetLabel.setText(String.valueOf(nbInitial));
    }
}
