// Time-stamp: <MonitorApplet.java  28 Dec 2005 19:06:07>

import javax.swing.*;

/**
 * Visualisation des appels aux méthodes des applettes
 * @author O. Carton
 * @version 1.0
 */
public class MonitorApplet extends JApplet {
    // Zone de texte de 20 lignes et 60 colonnes
    private JTextArea textArea = new JTextArea(20, 60);
    // Constructeur
    public void init() {
	getContentPane().add(new JScrollPane(textArea));
 	textArea.append("Applette initialisée\n");
    }
    public void start() {
	textArea.append("Applette lancée\n");
	textArea.append("  CodeBase = " + getCodeBase() + "\n");
	textArea.append("  DocumentBase = " + getDocumentBase() + "\n");
	textArea.append("  Param1 = " + getParameter("Param1") + "\n");
	textArea.append("  Param2 = " + getParameter("Param2") + "\n");
    }
    public void stop() {
	textArea.append("Applette arrêtée\n");
    }
    public void destroy() {
	textArea.append("Applette détruite\n");
    }
}

// Le fichier HTML qui appelle l'applette contient les lignes suivantes.
// qui donne les valeurs retournées par les appels à getParameter
//
// <applet code="MonitorApplet.class" name="Moniteur" width="500" height="100">
//  <param name="Param1" value="Valeur1"></param> 
//  <param name="Param2" value="Valeur2"></param> 
// </applet> 

