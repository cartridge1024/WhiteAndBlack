import java.awt.*;
import javax.swing.JFrame;

public class WindowUtils {
    public static void displayOnDesktopCenter(JFrame frame){
        //make the windows is center
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dim = toolkit.getScreenSize();
        int screenWidth = dim.width;
        int screenHeight = dim.height;
        int w = frame.getWidth();
        int h = frame.getHeight();
        int x = (screenWidth - w)/2;
        int y = (screenHeight - h)/2;
        frame.setLocation(x,y);
    }
}
