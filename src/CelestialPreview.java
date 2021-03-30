import javax.swing.*;
import java.awt.*;

public class CelestialPreview extends JPanel{

    private int size;
    private Color[] colors = new Color[2];

    public CelestialPreview(float size){
        this.setBackground(new Color(28,40,51));
        this.setBounds(10, 360, 240, 170);
        this.setVisible(true);
        this.setLayout(null);
        this.size = (int) size;
        colors[1] = new Color(95,106,106);
        colors[0] = new Color(166, 189, 189);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;
        float[] dist = {0.1f,1f};
        RadialGradientPaint p = new RadialGradientPaint((this.getWidth()/2f),(this.getHeight()/2f), 2*size, dist, colors);
        g2D.setPaint(p);
        g2D.fillOval(((this.getWidth()/2)-size), ((this.getHeight()/2)-size),2*size,2*size);
    }

    public void setSize(float size){
        this.size = (int) size;
    }

    public void setColor(Color color){
        this.colors = new Color[]{color, color};
    }
}
