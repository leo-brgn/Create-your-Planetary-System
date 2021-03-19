import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame implements MouseListener {
    // GUI attributes
    private JPanel rightPanel;
    private JPanel line;
    private JPanel backPlanetNb;
    private JLabel planetNb;
    private JButton rocky;
    private JButton gazeous;
    private JLabel size;
    private JLabel  color;
    private JLabel preview;
    private JButton validate;
    private Point mouseLocation;
    // System attributes
    private int nbPlanets;
    private int currentPlanet=1;
    private PlanetarySystem planetarySystem;
    private JButton[] listButton;

    public Window(int nbPlanets){
        this.nbPlanets = nbPlanets;
        planetarySystem = new PlanetarySystem();

        // Creation of the window
        this.setTitle("Create your Solar System");
        this.setSize(1050,640);
        this.setResizable(false);
        this.setLocation(120,20);
        this.setLayout(null);
        this.setBackground(Color.BLUE);
        this.getContentPane().addMouseListener(this);
        System.out.println(Thread.activeCount());
        this.getContentPane().add(planetarySystem);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                dispose();
                new Title();
            }
        });

        // Creation of the panel
        rightPanel = new JPanel();
        rightPanel.setBackground(Color.BLACK);
        rightPanel.setBounds(780, 0, 270, 640);
        rightPanel.setVisible(true);
        rightPanel.setLayout(null);

        line = new JPanel();
        line.setBackground(Color.GRAY);
        line.setBounds(0, 0, 2, 640 );
        rightPanel.add(line);

        backPlanetNb = new JPanel();
        backPlanetNb.setBackground(new Color(51,48,51));
        backPlanetNb.setBounds(0,25,270,60);
        rightPanel.add(backPlanetNb);

        planetNb = new JLabel();
        planetNb.setText("PLANET: " + currentPlanet + "/" + nbPlanets);
        planetNb.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,32));
        //planetNb.setBorder(BorderFactory.createLineBorder(Color.white));
        planetNb.setBounds(30,20,165,40);
        planetNb.setForeground(Color.WHITE);
        backPlanetNb.add(planetNb);

        rocky = new JButton("ROCKY");
        rocky.setBounds(20,115,100,25);
        rocky.setForeground(Color.WHITE);
        rocky.setBackground(Color.BLACK);
        //telluric.addActionListener(this);
        rightPanel.add(rocky);

        gazeous = new JButton("GAZEOUS");
        gazeous.setBounds(140,115,100,25);
        gazeous.setForeground(Color.WHITE);
        gazeous.setBackground(Color.BLACK);
        //telluric.addActionListener(this);
        rightPanel.add(gazeous);

        size = new JLabel("SIZE");
        size.setFont(new java.awt.Font(Font.SANS_SERIF,Font.BOLD,15));
        size.setBounds(30, 170, 100, 25);
        size.setForeground(Color.WHITE);
        rightPanel.add(size);

        color = new JLabel("COLOR");
        color.setFont(new java.awt.Font(Font.SANS_SERIF,Font.BOLD,15));
        color.setBounds(100, 230, 100,25);
        color.setForeground(Color.WHITE);
        rightPanel.add(color);

        preview = new JLabel("PREVIEW");
        preview.setFont(new java.awt.Font(Font.SANS_SERIF,Font.BOLD,15));
        preview.setBounds(95, 320, 100,25);
        preview.setForeground(Color.WHITE);
        rightPanel.add(preview);

        validate = new JButton("CREATE");
        validate.setBounds(85, 550, 100,25);
        validate.setForeground(Color.WHITE);
        validate.setBackground(Color.BLACK);
        rightPanel.add(validate);

        listButton = new JButton[6];
        for(int i=0; i<6; i++){
            listButton[i] = new JButton();
            listButton[i].setBounds(15*i*40,260,40,50);
            listButton[i].setBackground(Color.GRAY);
            rightPanel.add(listButton[i]);
        }

        this.add(rightPanel);

    }


    /**
     * Method to handle the action on the mouse
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if(mouseEvent.getButton() == MouseEvent.BUTTON1) {
            if(mouseEvent.getX() < 780){
                if (planetarySystem.getAddedSize() < 10 + 1) {
                    planetarySystem.addCelestialObject(new Rocky((int) (5 * Math.random() +1), mouseEvent.getPoint()));
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
    }

    public void actionPerformed(ActionEvent e){

    }
}
