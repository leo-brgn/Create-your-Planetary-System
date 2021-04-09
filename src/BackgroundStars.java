import javax.swing.*;
import java.awt.*;

/**
 * Class to create a background with stars of different appearances
 */
public class BackgroundStars extends JComponent {

    /**
     * Attributes
     */
    private final double[] a;
    private final double[] b;
    private final double[] c;
    private final float alpha;

    /**
     * Constructor
     */
    public BackgroundStars(int n){
        this.setBounds(0,0,780,640);
        // Values a --> x and b --> y
        a = new double[n];
        b = new double[n];
        // Values for the size of the stars
        c = new double[n];
        // Instantiate the values with random with between 0 and 1
        for (int i = 0; i<n; i++){
            this.a[i] = Math.random();
            this.b[i] = Math.random();
            this.c[i] = Math.random();
        }
        alpha = 1.0f;
    }

    /**
     * Method to paint the stars with different color and sizes
     */
    public void paintComponent(Graphics g){
        // Low alpha value
        g.setColor(new Color(1f,1f,1f, 0.2f*alpha));
        for (int i = 0; i<a.length/4; i++){
            g.fillOval((int) (a[i]*780),(int) (b[i]*640),(int) (2*c[i]+1),(int) (4*c[i]+1));
        }
        // Middle alpha value
        g.setColor(new Color(1f,1f,1f, 0.4f*alpha));
        for (int i = a.length/4; i<a.length/2; i++){
            g.fillOval((int) (a[i]*780),(int) (b[i]*640),(int) (2*c[i]+1),(int) (4*c[i]+1));
        }
        // Middle alpha value
        g.setColor(new Color(1f,1f,1f, 0.7f*alpha));
        for (int i = a.length/2; i<3*a.length/4; i++){
            g.fillOval((int) (a[i]*780),(int) (b[i]*640),(int) (2*c[i]+1),(int) (3*c[i]+1));
        }
        // Max alpha value
        g.setColor(new Color(1f,1f,1f, alpha));
        for (int i = 3*a.length/4; i<a.length; i++){
            g.fillOval((int) (a[i]*780),(int) (b[i]*640),(int) (2*c[i]+1),(int) (2*c[i]+1));
        }
    }
}
