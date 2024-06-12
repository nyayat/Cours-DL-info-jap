// Time-stamp: <SimpleFrame.java  25 fév 2003 16:10:01>

import java.awt.*;
import java.awt.event.*;

/**
 * Programme AWT qui ouvre une vraie fenêtre
 * @author O. Carton
 * @version 1.0
 */
class SimpleFrame extends Frame {
    // Constructeur de la fenêtre
    SimpleFrame(String title) { 
	super(title); 
    }
    public static void main(String [] args)
    {
	SimpleFrame f = new SimpleFrame("Simple fenêtre");
	f.addWindowListener(new Closer());
	f.pack();
	f.show();
    }
}

/**
 * Classe chargée de terminer l'application lorsque la fenêtre est fermée
 */
class Closer extends WindowAdapter {
  public void windowClosing(WindowEvent e) { System.exit(0); }
}
