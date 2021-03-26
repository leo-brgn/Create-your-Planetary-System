import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame implements ActionListener, MouseListener, ChangeListener {
    // GUI attributes
    private final JPanel rightPanel; // The panel on the right
    private final JPanel line;
    private final JPanel backPlanetNb; // JPanel of the planets to add
    private final JLabel planetNb;
    private final JButton rocky;
    private final JButton gaseous;
    private final JLabel size;
    private final JLabel  color;
    private final JLabel preview;
    private final JButton create;
    private final JSlider slider;
    private CelestialPreview celestialPreview;
    private JPanel sliderPanel;
    // Position of the mouse
    private Point mouseLocation;
    // System attributes
    private final int nbPlanets;
    private int currentPlanet=1;
    private final PlanetarySystem planetarySystem;
    private final JButton[] colorButtonsGaseous;
    private final JButton[] colorButtonsRocky;
    private TypePlanet typeToCreate;
    private Color colorSelected;
    private int sizeSelected;
    // Booleans
    private boolean planetToAdd = false;
    private boolean stopAdding = false;
    private boolean buttonsAdded = false;


    private final Color[] listColor={
            new Color(195, 155, 211),
            new Color(187, 143, 206),
            new Color(127, 179, 213),
            new Color(133, 193, 233),
            new Color(115, 198, 182),
            new Color(130, 224, 170),

            new Color(247, 220, 111),
            new Color(245, 176, 65),
            new Color(243, 156, 18),
            new Color(230, 126, 34),
            new Color(231, 76, 60),
            new Color(192, 57, 43)
    };

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
        size.setBounds(50, 170, 100, 25);
        size.setForeground(Color.WHITE);
        rightPanel.add(size);

        color = new JLabel("C O L O R");
        color.setFont(new java.awt.Font(Font.SANS_SERIF,Font.BOLD,15));
        color.setBounds(95, 230, 100,25);
        color.setForeground(Color.WHITE);
        rightPanel.add(color);
        colorSelected = new Color(95,106,106);

        preview = new JLabel("P R E V I E W");
        preview.setFont(new java.awt.Font(Font.SANS_SERIF,Font.BOLD,15));
        preview.setBounds(85, 330, 100,25);
        preview.setForeground(Color.WHITE);
        rightPanel.add(preview);

        celestialPreview = new CelestialPreview(25);
        rightPanel.add(celestialPreview);

        create = new JButton("CREATE");
        create.setBounds(85, 550, 100,25);
        create.setForeground(Color.WHITE);
        create.setBackground(Color.BLACK);
        create.addActionListener(this);
        rightPanel.add(create);

        slider = new JSlider(JSlider.HORIZONTAL, 0, 50, 25);
        slider.setBounds(140,160,100,50);
        slider.setMinorTickSpacing(2);
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setBackground(Color.BLACK);
        slider.setForeground(Color.WHITE);
        slider.addChangeListener(this);
        rightPanel.add(slider);

        colorButtonsGaseous = new JButton[6];
        colorButtonsRocky = new JButton[6];

        this.add(rightPanel);

    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == gaseous){
            typeToCreate = TypePlanet.ROCKY;
            updateColorButtons();
        }else if(e.getSource() == rocky){
            typeToCreate = TypePlanet.GASEOUS;
            updateColorButtons();
        }else if(e.getSource()==create && currentPlanet<nbPlanets && !planetToAdd && !stopAdding && canCreate()){
            planetToAdd = true;
        }else if(e.getSource()==create && currentPlanet==nbPlanets && !planetToAdd && !stopAdding && canCreate()){
            planetToAdd = true;
            stopAdding = true;
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
                    planetarySystem.addCelestialObject(typeToCreate, mouseEvent.getPoint(), sizeSelected, colorSelected);
                }
                planetToAdd = false;
            }
            if (currentPlanet != nbPlanets){
                currentPlanet++;
                planetNb.setText("PLANET: " + currentPlanet + "/" + nbPlanets);
            }
        }
    }

    public void updateColorButtons(){
        if (buttonsAdded) {
            if(typeToCreate == TypePlanet.GASEOUS){
                for(int i=0; i<colorButtonsRocky.length; i++){
                    rightPanel.remove(colorButtonsRocky[i]);
                }
            } else if (typeToCreate == TypePlanet.ROCKY){
                for(int i=0; i<colorButtonsGaseous.length; i++){
                    rightPanel.remove(colorButtonsGaseous[i]);
                }
            }
        }
        if (typeToCreate == TypePlanet.GASEOUS){
            for(int i=0; i<6; i++){
                colorButtonsGaseous[i] = new JButton();
                colorButtonsGaseous[i].setBounds(i*45,260,45,50);
                colorButtonsGaseous[i].setBackground(listColor[i]);
                colorButtonsGaseous[i].addActionListener(this);
                rightPanel.add(colorButtonsGaseous[i]);
                buttonsAdded=true;
            }
        } else if (typeToCreate == TypePlanet.ROCKY){
            for(int i=0; i<6; i++){
                colorButtonsRocky[i] = new JButton();
                colorButtonsRocky[i].setBounds(i*45,260,45,50);
                colorButtonsRocky[i].setBackground(listColor[(i+6)]);
                colorButtonsRocky[i].addActionListener(this);
                rightPanel.add(colorButtonsRocky[i]);
                buttonsAdded=true;
            }
        }
        this.rightPanel.repaint();
    }

    // Create popup in case something is wrong
    public boolean canCreate(){
        if (sizeSelected == 0){
            JOptionPane.showMessageDialog(this, "Planet cannot have no size.");
            return false;
        } else if (colorSelected == null){
            JOptionPane.showMessageDialog(this, "Planet cannot have no color.");
            System.out.println(colorSelected);
            return false;
        } else {
            return true;
        }
    }

    public void colorButtonClicked(ActionEvent e){
        if(typeToCreate == TypePlanet.GASEOUS){
            for(int i=0; i<colorButtonsGaseous.length; i++){
                if(e.getSource()==colorButtonsGaseous[i]){
                    colorSelected=listColor[i];
                }
            }
        }else if(typeToCreate == TypePlanet.ROCKY){
            for(int i=0; i<colorButtonsRocky.length; i++){
                if(e.getSource()==colorButtonsRocky[i]){
                    colorSelected=listColor[i+6];
                }
            }
        }
        this.celestialPreview.setColor(colorSelected);
        this.celestialPreview.repaint();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if(e.getSource() == slider){
            sizeSelected = slider.getValue();
            this.celestialPreview.setSize(sizeSelected);
            this.celestialPreview.repaint();
        }
    }

    public enum TypePlanet {
        GASEOUS, ROCKY
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