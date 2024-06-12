
import java.awt.*;
import java.net.URL;
import javax.swing.JApplet;

/**
 * Petite Applette
 * L'applette peut être utilisée dans une page HTML par une balise APPLET
 * de la forme : 
 *     <APPLET CODE="ImageApplet.class" WIDTH=377 HEIGHT=517> </APPLET>
 * @author O. Carton
 * @version 1.0
 */
public class ImageApplet extends JApplet {
    private Image image;
    public void init() {
	image = getImage(getCodeBase(), "images/" + "9personnes.jpg");
    }
    public void paint(Graphics g) {
	g.drawImage(image, 0, 0, this);
    }
}
