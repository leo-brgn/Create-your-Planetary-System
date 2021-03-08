import javax.swing.*;
import java.awt.*;

public class BackgroundStars extends JComponent {
    private double[] a = new double[100];
    private double[] b = new double[100];
    private double[] c = new double[100];
    public BackgroundStars(){
        this.setBounds(0,0,780,640);
        for (int i = 0; i<100; i++){
            this.a[i] = Math.random();
            this.b[i] = Math.random();
            this.c[i] = Math.random();
        }

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        for (int i = 0; i<100; i++){
            g.fillOval((int) (a[i]*780),(int) (b[i]*640),(int) (2*c[i]+1),(int) (2*c[i]+1));
        }
    }
}
