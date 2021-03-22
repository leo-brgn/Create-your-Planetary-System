import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CelestialPreview {

    private JPanel previewWindow;

    public CelestialPreview(){

        previewWindow = new JPanel();
        previewWindow.setBackground(new Color(28,40,51));
        previewWindow.setBounds(10, 360, 270, 300);
        previewWindow.setVisible(true);
        previewWindow.setLayout(null);
    }
}
