import javax.swing.*;

public class Title extends JFrame{

    public static void main(String[] args) {


    }

    public Title() {

        JFrame startingFrame = new JFrame();
        startingFrame.setTitle("Create my Solar System");
        startingFrame.setSize(300,200);
        startingFrame.setResizable(false);
        startingFrame.setLocation(100,200);
        startingFrame.setVisible(true);
        startingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




        JButton launchButton = new JButton("Start");


        JTextField nbPlanetLabel= new JTextField();
    }
    private void ActionPerformed(){

    }
}
