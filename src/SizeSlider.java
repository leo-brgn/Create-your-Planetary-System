public class SizeSlider {
    import javax.swing.*;

    public class SizeSlider extends JFrame{
        public SizeSlider() {
            JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 50, 25);
            slider.setMinorTickSpacing(2);
            slider.setMajorTickSpacing(10);
            slider.setPaintTicks(true);
            slider.setPaintLabels(true);

            JPanel panel=new JPanel();
            panel.add(slider);
            add(panel);
        }
        public static void main(String s[]) {
            SizeSlider frame=new SizeSlider();
            frame.pack();
            frame.setVisible(true);
        }
    }
}
