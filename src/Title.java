import javax.media.CannotRealizeException;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.media.Player;
import java.io.IOException;
import java.net.URL;

/**
 * Class containing the main method (main thread) which creates the title scene
 * This class allows us to choose the number of planets to create and open the new Window
 */
public class Title extends JFrame implements ActionListener{

    /**
     * Attributes
     */
    private Window window;
    private JTextArea nbPlanetArea;
    private JButton plusButton;
    private JButton minusButton;
    private JButton launchButton;
    private int nbInitial = 1;

    /**
     * Main method
     */
    public static void main(String[] args) {
        new Title();
    }
    /*
    public static void playSong(URL media) throws CannotRealizeException, IOException, NoPlayerException {
        Player mediaPlayer = Manager.createRealizedPlayer(media);
        mediaPlayer.start();
    }*/

    /**
     * Constructor
     */
    public Title() {

        //playSong(new URL("/items/sounds/cavern-starting-area-sans-percussion-actionloop-royalty-free-music.mp3"));
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

        JLabel affBackground = new JLabel (new ImageIcon("items/images/wallpaper-HD.jpg"));
        affBackground.setBounds(0,0,1050,640);
        firstPanel.add(affBackground);

        JLabel txtLabel = new JLabel(); //ATTRIBUT
        txtLabel.setText("CREATE YOUR PLANETARY SYSTEM");
        txtLabel.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,45));
        txtLabel.setForeground(Color.WHITE);
        txtLabel.setBounds(110,55,830,50);
        affBackground.add(txtLabel);

        JLabel titleLabel = new JLabel(); //ATTRIBUT
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
            window = new Window(nbInitial);
            System.out.println("Let's go");
        }
        nbPlanetArea.setText(String.valueOf(nbInitial));
    }
}
