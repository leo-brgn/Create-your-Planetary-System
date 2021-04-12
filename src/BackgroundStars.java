import javax.swing.*;
import java.awt.*;

/**
 * Class to create a background with stars of different appearances
 */
public class BackgroundStars extends JComponent {

    /**
     * Attributes
     */
    private final double[] A;
    private final double[] B;
    private final double[] C;
    private final float ALPHA;

    /**
     * Constructor
     */
    public BackgroundStars(int n){
        this.setBounds(0,0,780,640);
        // Values a --> x and b --> y
        A = new double[n];
        B = new double[n];
        // Values for the size of the stars
        C = new double[n];
        // Instantiate the values with random with between 0 and 1
        for (int i = 0; i<n; i++){
            this.A[i] = Math.random();
            this.B[i] = Math.random();
            this.C[i] = Math.random();
        }
        ALPHA = 1.0f;
    }

    /**
     * Method to paint the stars with different color and sizes
     */
    public void paintComponent(Graphics g){
        // Low alpha value
        g.setColor(new Color(1f,1f,1f, 0.2f* ALPHA));
        for (int i = 0; i< A.length/4; i++){
            g.fillOval((int) (A[i]*780),(int) (B[i]*640),(int) (2* C[i]+1),(int) (4* C[i]+1));
        }
        // Middle alpha value
        g.setColor(new Color(1f,1f,1f, 0.4f* ALPHA));
        for (int i = A.length/4; i< A.length/2; i++){
            g.fillOval((int) (A[i]*780),(int) (B[i]*640),(int) (2* C[i]+1),(int) (4* C[i]+1));
        }
        // Middle alpha value
        g.setColor(new Color(1f,1f,1f, 0.7f* ALPHA));
        for (int i = A.length/2; i<3* A.length/4; i++){
            g.fillOval((int) (A[i]*780),(int) (B[i]*640),(int) (2* C[i]+1),(int) (3* C[i]+1));
        }
        // Max alpha value
        g.setColor(new Color(1f,1f,1f, ALPHA));
        for (int i = 3* A.length/4; i< A.length; i++){
            g.fillOval((int) (A[i]*780),(int) (B[i]*640),(int) (2* C[i]+1),(int) (2* C[i]+1));
        }
    }
}
