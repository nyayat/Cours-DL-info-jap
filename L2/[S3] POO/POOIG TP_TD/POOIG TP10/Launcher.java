import java.awt.EventQueue;
import java.io.*;

public class Launcher {
    public static void main(String[]args){
        //7.
        EventQueue.invokeLater(
            ()->{
                ImageEditModel iem=new ImageEditModel("/image/landscape.png");
                /*ImageIO.read(new File(chemin)) ne semblait pas vouloir marcher
                    donc j'ai changé la méthode de lecture dans le constructeur...*/
                /*Sur NetBeans, j'ai dû créé un dossier (que j'ai nommé "image")
                    dans Source Packages et j'y ai inseré l'image du TP.*/
                ImageEditView iev=new ImageEditView(iem);
                iev.pack();
                iev.setVisible(true);
            }
        );
    }
}