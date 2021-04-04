import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

/**
 * Class for the creation of the planets inheriting from JPanel
 */

public class Window extends JFrame implements ActionListener, MouseListener, ChangeListener {

    /**
     * Attributes
     */

    //GIU
    private final JPanel rightPanel; // The panel on the right
    private final JPanel line; //line at the left of the panel
    private final JPanel backPlanetNb; // JPanel of the planets to add
    private final JLabel planetNb;
    private final JButton rocky;
    private final JButton gaseous;
    private final JLabel size;
    private final JLabel realSize;
    private final JLabel  color;
    private final JButton create;
    private final JSlider slider;
    //preview of the planet before adding it to the planetary system
    private JPanel sliderPanel;
    // Position of the mouse
    private Point mouseLocation;
    // System attributes
    private double sizeKm;
    private final int nbPlanets;
    private int currentPlanet=1; // Goes from 1 to nbPlanets
    private final PlanetarySystem planetarySystem;
    private final JButton[] colorButtonsGaseous;
    private final JButton[] colorButtonsRocky;
    private JButton[] currentColorButtons=new JButton[6];
    private TypePlanet typeToCreate;
    private int colorSelected;
    private int sizeSelected;
    // Booleans
    private boolean planetToAdd = false;
    private boolean buttonsAdded = false;
    private FinishPanel finishPanel;
    private CelestialPreview celestialPreview;

    /**
     * Constructor
     */
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

        this.addWindowListener(new WindowAdapter(){ //allow the game to restart at the end
            public void windowClosing(WindowEvent e){
                dispose();
                new Title();
            }
        });

        // CREATION OF THE RIGHT PANEL + FILLINGS
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
        planetNb.setText("PLANET:" + currentPlanet + "/" + nbPlanets);
        planetNb.setFont(new java.awt.Font(Font.DIALOG_INPUT,Font.BOLD,32));
        //planetNb.setBorder(BorderFactory.createLineBorder(Color.white));
        planetNb.setBounds(30,20,165,40);
        planetNb.setForeground(Color.WHITE);
        backPlanetNb.add(planetNb);

        rocky = new JButton("ROCKY");
        rocky.setBounds(20,115,100,25);
        rocky.setForeground(Color.WHITE);
        rocky.setBackground(Color.BLACK);
        rocky.addActionListener(this);
        rightPanel.add(rocky);

        gaseous = new JButton("GASEOUS");
        gaseous.setBounds(140,115,100,25);
        gaseous.setForeground(Color.WHITE);
        gaseous.setBackground(Color.BLACK);
        gaseous.addActionListener(this);
        rightPanel.add(gaseous);

        size = new JLabel("S I Z E");
        size.setFont(new java.awt.Font(Font.SANS_SERIF,Font.BOLD,15));
        size.setBounds(50, 150, 100, 25);
        size.setForeground(Color.WHITE);
        rightPanel.add(size);

        this.sizeKm = 0;
        realSize = new JLabel(0 + " km");
        realSize.setFont(new java.awt.Font(Font.SANS_SERIF,Font.BOLD,15));
        realSize.setBounds(30, 185, 100, 25);
        realSize.setForeground(Color.WHITE);
        rightPanel.add(realSize);

        color = new JLabel("C O L O R");
        color.setFont(new java.awt.Font(Font.SANS_SERIF,Font.BOLD,15));
        color.setBounds(95, 230, 100,25);
        color.setForeground(Color.WHITE);
        rightPanel.add(color);
        colorSelected = 0;

        JLabel preview = new JLabel("P R E V I E W    x4 ");
        preview.setFont(new java.awt.Font(Font.SANS_SERIF,Font.BOLD,15));
        preview.setBounds(70, 330, 150,25);
        preview.setForeground(Color.WHITE);
        rightPanel.add(preview);

        this.sizeSelected = 1; // initialize the size of the planet and the position of the slider
        celestialPreview = new CelestialPreview(sizeSelected);

        rightPanel.add(celestialPreview);

        create = new JButton("CREATE");
        create.setBounds(85, 550, 100,25);
        create.setFont(new java.awt.Font(Font.DIALOG_INPUT,Font.BOLD,14));
        create.setForeground(Color.WHITE);
        create.setBackground(Color.BLACK);
        create.addActionListener(this);
        rightPanel.add(create);

        slider = new JSlider(JSlider.HORIZONTAL, 0, 100, sizeSelected); //slider between 0 and 100
        slider.setBounds(120,160,120,50);
        slider.setMinorTickSpacing(5);
        slider.setMajorTickSpacing(25);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setBackground(Color.BLACK);
        slider.setForeground(Color.WHITE);
        slider.addChangeListener(this);

        rightPanel.add(slider);

        // END RIGHT PANEL
        colorButtonsGaseous = new JButton[6];
        colorButtonsRocky = new JButton[6];
        this.add(rightPanel);
        finishPanel = new FinishPanel(nbPlanets);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == gaseous){
            typeToCreate = TypePlanet.GASEOUS;
            updateColorButtons();
        }else if(e.getSource() == rocky){
            typeToCreate = TypePlanet.ROCKY;
            updateColorButtons();
        }else if(e.getSource()==create){
            planetToAdd = true;
        }
        colorButtonClicked(e);
    }

    /**
     * Method to handle the action on the mouse
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if(mouseEvent.getButton() == MouseEvent.BUTTON1 && planetToAdd) {
            if(mouseEvent.getX() < 780){
                if (planetarySystem.getAddedSize() < 10 + 1) {
                        planetarySystem.addCelestialObject(typeToCreate, mouseEvent.getPoint(), (int)(0.15*(sizeSelected+7)), colorSelected); // the added 7 is to avoid nil values for the radius with the slider between 0 and 6 (we add 7 to the value of the slider)
                        finishPanel.addPlanet(new Planet((int) (0.15*(sizeSelected+7)), mouseEvent.getPoint(), colorSelected, typeToCreate)); //creating an object planet
                }

                planetToAdd = false;
            }
            if (currentPlanet < nbPlanets){
                currentPlanet++;
                planetNb.setText("PLANET: " + currentPlanet + "/" + nbPlanets);
            } else if (currentPlanet == nbPlanets){
                currentPlanet++;
                finishedCreating();
            }
        }
    }

    public void updateColorButtons(){
        if (buttonsAdded) {
            if(typeToCreate == TypePlanet.GASEOUS && currentColorButtons==colorButtonsRocky){
                for(int i=0; i<colorButtonsRocky.length; i++) {
                    rightPanel.remove(colorButtonsRocky[i]);
                }
            }else if(typeToCreate == TypePlanet.GASEOUS && currentColorButtons==colorButtonsGaseous){
                for(int i=0; i<colorButtonsRocky.length; i++){
                    rightPanel.remove(colorButtonsGaseous[i]);
                }
            }else if (typeToCreate == TypePlanet.ROCKY && currentColorButtons==colorButtonsGaseous) {
                for (int i = 0; i < colorButtonsGaseous.length; i++) {
                   rightPanel.remove(colorButtonsGaseous[i]);
                }
            }else if(typeToCreate == TypePlanet.ROCKY && currentColorButtons==colorButtonsRocky) {
                for (int i = 0; i < colorButtonsRocky.length; i++) {
                    rightPanel.remove(colorButtonsRocky[i]);
                }
            }
        }
        if (typeToCreate == TypePlanet.GASEOUS){
            for(int i=0; i<6; i++){
                colorButtonsGaseous[i] = new JButton();
                colorButtonsGaseous[i].setBounds(i*43,260,43,50);
                colorButtonsGaseous[i].setBackground(PlanetGradient.getColor( i));
                colorButtonsGaseous[i].addActionListener(this);
                rightPanel.add(colorButtonsGaseous[i]);
                buttonsAdded=true;
                currentColorButtons=colorButtonsGaseous;
            }
        } else if (typeToCreate == TypePlanet.ROCKY){
            for(int i=0; i<6; i++){
                colorButtonsRocky[i] = new JButton();
                colorButtonsRocky[i].setBounds(i*43,260,43,50);
                colorButtonsRocky[i].setBackground(PlanetGradient.getColor(i+6));
                colorButtonsRocky[i].addActionListener(this);
                rightPanel.add(colorButtonsRocky[i]);
                buttonsAdded=true;
                currentColorButtons=colorButtonsRocky;
            }
        }
        this.rightPanel.repaint();
    }

    public void colorButtonClicked(ActionEvent e){
        if(typeToCreate == TypePlanet.GASEOUS){
            for(int i=0; i<colorButtonsGaseous.length; i++){
                if(e.getSource()==colorButtonsGaseous[i]){
                    colorSelected=i;
                }
            }
        }else if(typeToCreate == TypePlanet.ROCKY){
            for(int i=0; i<colorButtonsRocky.length; i++){
                if(e.getSource()==colorButtonsRocky[i]){
                    colorSelected=i+6;
                }
            }
        }
        this.celestialPreview.setColor(colorSelected);
        this.celestialPreview.repaint();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if(e.getSource() == slider){
            sizeSelected = slider.getValue(); //value of the slider between 0 and 100 in pixels
            realSize.setText((int)(0.15 * (sizeSelected+7)*4500) + " km");
            this.celestialPreview.setSize((float) (0.60) * (sizeSelected+7)); //still the added 7 to have a simple relation between the preview and the game (4x)
            this.celestialPreview.repaint();
        }
    }

    public void finishedCreating(){
        finishPanel.updateCases();
        this.remove(rightPanel);
        this.repaint();
        this.add(finishPanel);

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

}