import java.awt.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.undo.*;

public class ImageEditModel {
    //1.
    private BufferedImage image;
    //10.1
    UndoManager undoManager=new UndoManager();
    
    ImageEditModel(String chemin){
        try{
            //this.image=ImageIO.read(new File(chemin));  //ne marche pas :)
            this.image=ImageIO.read(getClass().getResource(chemin));
        }
        catch(IOException e){
            System.out.println("Le fichier n'a pas été trouvé.");
        }
    }
    
    BufferedImage getImage(){
        return this.image;
    }
    
    //2.1
    public void fillzone(Rectangle z, int[][]pixels){
        for(int i=0; i<pixels.length; i++){
            for(int j=0; j<pixels[0].length; j++){
                this.image.setRGB(j+z.x, i+z.y, pixels[i][j]);
            }
        }
    }
    
    //2.2
    public void clearzone(Rectangle z){
        Color c=Color.WHITE;
        int temp=c.getRGB();
        for(int i=z.y; i<z.y+z.height; i++){
            for(int j=z.x; j<z.x+z.width; j++){
                this.image.setRGB(j, i, temp);
            }
        }
    }
    
    //8
    class Coupe {  //début classe interne
        //8.1
        Rectangle z;
        int[][]pixels;
        
        Coupe(int x1, int y1, BufferedImage im){
            //on n'a pas besoin d'arguments en plus, à savoir hauteur et largeur
            //car la BufferedImage a une hauteur et une largeur
            pixels=new int[im.getHeight()][im.getWidth()];
            for(int i=0; i<im.getHeight(); i++){
                for(int j=0; j<im.getWidth(); j++){
                    pixels[i][j]=im.getRGB(j, i);
                }
            }
            z=new Rectangle(x1, y1, im.getWidth(), im.getHeight());
        }
        
        //8.2
        public void doit(){
            clearzone(this.z);
        }
        
        public void undo(){
            fillzone(this.z, this.pixels);
        }
    }  //fin classe interne
    
    //9.
    class CutEdit extends AbstractUndoableEdit{  //début de la classe interne
        //9.1
        Coupe c;
        
        //9.2
        CutEdit(Coupe c){
            this.c=c;
        }
        
        //9.3
        public void undo(){
            c.undo();
            super.undo();
        }
        
        public void redo(){
            c.doit();
            super.redo();
        }
    }  //fin de la classe interne
    
    //10.2
    public void saveCut(Rectangle z){
        BufferedImage sIm=image.getSubimage(z.x, z.y, z.width, z.height);
        Coupe c=new Coupe(z.x, z.y, sIm);
        c.doit();
        CutEdit cut=new CutEdit(c);
        undoManager.addEdit(cut);
    }
}