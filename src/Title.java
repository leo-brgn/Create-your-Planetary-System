import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class containing the main method (main thread) which creates the title scene
 * This class allows us to choose the number of planets to create and open the new Window
 */
public class Title extends JFrame implements ActionListener{

    private final JTextArea nbPlanetArea;
    private final JButton plusButton;
    private final JButton minusButton;
    private final JButton launchButton;
    private int nbInitial = 1;

    /**
     * Main method
     */
    public static void main(String[] args) {
        new Title();
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

        JPanel firstPanel = new JPanel();
        firstPanel.setBackground(Color.BLACK);
        firstPanel.setBounds(0,0,1050,640);
        firstPanel.setVisible(true);
        firstPanel.setLayout(null);

        JLabel affBackground = new JLabel (new ImageIcon("items/images/wallpaper-HD.jpg"));
        affBackground.setBounds(0,0,1050,640);
        firstPanel.add(affBackground);

        JLabel txtLabel = new JLabel();
        txtLabel.setText("CREATE YOUR PLANETARY SYSTEM");
        txtLabel.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,45));
        txtLabel.setForeground(Color.WHITE);
        txtLabel.setBounds(110,55,830,50);
        affBackground.add(txtLabel);

        JLabel titleLabel = new JLabel();
        titleLabel.setText("How many planet do you want to add ?");
        titleLabel.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,30));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(270,425,510,50);
        affBackground.add(titleLabel);

        nbPlanetArea= new JTextArea();
        nbPlanetArea.setText(String.valueOf(nbInitial));
        nbPlanetArea.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,20));
        nbPlanetArea.setBackground(Color.BLACK);
        nbPlanetArea.setForeground(Color.WHITE);
        nbPlanetArea.setBounds(520,495,10,35);
        affBackground.add(nbPlanetArea);

        launchButton = new JButton("Start");
        launchButton.setBounds(485,535,80,25);
        launchButton.setForeground(Color.WHITE);
        launchButton.setBackground(Color.GRAY);
        launchButton.addActionListener(this);
        affBackground.add(launchButton);

        plusButton = new JButton(new ImageIcon("items/images/logo-plus.png"));
        plusButton.setBounds(585,500,25,25);
        plusButton.addActionListener(this);
        affBackground.add(plusButton);

        minusButton = new JButton(new ImageIcon("items/images/logo-minus.png"));
        minusButton.setBounds(440,500,25,25);
        minusButton.addActionListener(this);
        affBackground.add(minusButton);

        this.add(firstPanel);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==plusButton && nbInitial<5){
            nbInitial++;
        } else if(e.getSource()==minusButton && nbInitial>1){
            nbInitial=nbInitial-1;
        } else if(e.getSource()==launchButton){
            new Window(nbInitial);
            System.out.println("Let's go");
        }
        nbPlanetArea.setText(String.valueOf(nbInitial));
    }
}
