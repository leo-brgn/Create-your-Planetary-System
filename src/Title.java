import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class containing the main method (main thread) which creates the title scene
 * This class allows us to choose the number of planets to create and open the new Window
 */
public class Title extends JFrame implements ActionListener{

    /**
     * Attributes
     */
    private final JTextArea nbPlanetArea;
    private final JButton plusButton;
    private final JButton minusButton;
    private final JButton launchButton;
    private int nbInitial = 1;

    /**
     * Main method
     */
    public static void main(String[] args){
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

        JLabel affBackground = new JLabel (new ImageIcon("items/images/diywallpaper-nude.jpg"));
        affBackground.setBounds(0,0,1050,640);
        firstPanel.add(affBackground);

        JLabel txtLabel = new JLabel();
        txtLabel.setText("CREATE YOUR PLANETARY SYSTEM");
        txtLabel.setFont(new java.awt.Font(Font.DIALOG_INPUT,Font.BOLD,50));
        txtLabel.setForeground(new Color (242, 243, 244));
        txtLabel.setBounds(90,65,870,50);
        affBackground.add(txtLabel);

        JLabel titleLabel = new JLabel();
        titleLabel.setText("How many planet do you want to add ?");
        titleLabel.setFont(new java.awt.Font(Font.DIALOG_INPUT,Font.BOLD,25));
        titleLabel.setForeground(new Color (242, 243, 244));
        titleLabel.setBounds(245,425,560,50);
        affBackground.add(titleLabel);

        nbPlanetArea= new JTextArea();
        nbPlanetArea.setText(String.valueOf(nbInitial));
        nbPlanetArea.setFont(new java.awt.Font(Font.DIALOG_INPUT,Font.BOLD,20));
        nbPlanetArea.setBackground(new Color(41,39,40));
        nbPlanetArea.setForeground(Color.WHITE);
        nbPlanetArea.setBounds(520,495,15,35);
        affBackground.add(nbPlanetArea);

        launchButton = new JButton("START");
        launchButton.setBounds(483,535,85,25);
        launchButton.setFont(new java.awt.Font(Font.DIALOG_INPUT,Font.BOLD,13));
        launchButton.setForeground(Color.WHITE);
        launchButton.setBackground(Color.GRAY);
        launchButton.addActionListener(this);
        affBackground.add(launchButton);

        plusButton = new JButton(new ImageIcon("items/images/logo-plus.png"));
        plusButton.setBounds(585,500,25,25);
        plusButton.setBorder(BorderFactory.createEmptyBorder());
        plusButton.setBackground(Color.GRAY);
        plusButton.addActionListener(this);
        affBackground.add(plusButton);

        minusButton = new JButton(new ImageIcon("items/images/logo-minus.png"));
        minusButton.setBounds(440,500,25,25);
        minusButton.setBorder(BorderFactory.createEmptyBorder());
        minusButton.setBackground(Color.GRAY);
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
            dispose(); // Closes the Title window
            new Window(nbInitial);
        }
        nbPlanetArea.setText(String.valueOf(nbInitial));
    }
}
