// Time-stamp: <SimpleApplet.java  22 Mar 2004 09:09:11>

import java.awt.*;
import java.awt.event.*;
import javax.swing.JApplet;

/**
 * Petite Applette très simple
 * L'applette peut être utilisée dans une page HTML par une balise APPLET
 * de la forme : 
 *     <APPLET CODE="SimpleApplet.class"> </APPLET>
 * @author O. Carton
 * @version 1.0
 */
public class SimpleApplet extends JApplet implements ActionListener {
    Button button;
    public void init() {
	button = new Button(" Go ");
	button.addActionListener(this);
	getContentPane().add(button);
    }
    public void actionPerformed(ActionEvent event) {
	button.setLabel("Gone");
    }
}

