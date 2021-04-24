import java.awt.*;

/**
 * Class to list all the colors of the planets
 */
public class PlanetColor {

    /**
     * Attributes
     */
    private static final Color[] listColor={
            new Color(115, 198, 182),
            new Color(130, 224, 170),
            new Color(133, 193, 233),
            new Color(84, 153, 199),
            new Color(195, 155, 211),
            new Color(165, 105, 189),

            new Color(247, 220, 111),
            new Color(245, 176, 65),
            new Color(243, 156, 18),
            new Color(230, 126, 34),
            new Color(231, 76, 60),
            new Color(192, 57, 43)
    };

    /**
     * Method to return the color at a fixed index
     */
    public static Color getColor(int index){
        return listColor[index];
    }
}
