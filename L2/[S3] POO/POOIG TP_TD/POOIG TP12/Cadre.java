import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Random;

public class Cadre extends JFrame{
    //2.
    JPanel panel=new JPanel();
    //9.
    Modele[]modTab=new Modele[10];
    //13.
    JPanel etiquette=new JPanel();
    
    //1.
    Cadre(){
        this.setSize(600, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //2.
        panel.setLayout(null);
        //3.
        /*panel.add(new Carre());
        this.getContentPane().add(panel);*/
        for(int i=0; i<10; i++){
            //8.
            panel.add(new Carre(i));
            this.getContentPane().add(panel);
        }
    }
    
    //3.
    class Carre extends JPanel implements MouseInputListener{  //classe interne
        //5.
        boolean moving=false;
        //6.
        int x0, y0;
        //9.
        Modele mod;
        
        Carre(int i){  //entier i que pour ajouter dans modTab
            //3.
            /*this.setBackground(Color.blue);
            this.setBounds(100, 200, 50, 50);*/
            //4.
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
            //7.
            int r=(int)(Math.random()*256);
            int v=(int)(Math.random()*256);
            int b=(int)(Math.random()*256);
            this.setBackground(new Color(r, v, b));
            int xRand=(int)(Math.random()*550);  
                                        //sinon le carré peut être hors cadre
            int yRand=(int)(Math.random()*550);  //idem
            this.setBounds(xRand, yRand, 50, 50);
            //9.
            mod=new Modele(r, v, b);
            modTab[i]=mod;
        }
        
        //4.
        public void mouseClicked(MouseEvent e) {
            System.out.println("Click !");
            //14.
            if(gagne()){
                Cadre.this.close();
            }
            else{
                //5.
                if(moving) moving=false;
                else{
                    moving=true;
                    x0=e.getX();
                    y0=e.getY();
                }
                System.out.println(moving);
                //12.
                changeColor(0, 255, 0);
            }
        }

        //12.
        public void mouseEntered(MouseEvent e){
            changeColor(0, 0, 255);
        }

        //6.
        public void mouseMoved(MouseEvent e){
            if(moving){
                int x=e.getXOnScreen()-Cadre.this.getX()
                    -Cadre.this.getInsets().left-x0;
                int y=e.getYOnScreen()-Cadre.this.getY()
                    -Cadre.this.getInsets().top-y0;
                this.setLocation(x, y);
            }
        }

        //12.
        void changeColor(int r, int v, int b){
            mod.r=r;
            mod.v=v;
            mod.b=b;
            this.setBackground(new Color(mod.r, mod.v, mod.b));
            //11.
            Cadre.this.finJeu();
        }

        public void mousePressed(MouseEvent e){}

        public void mouseReleased(MouseEvent e){}

        public void mouseExited(MouseEvent e){}

        public void mouseDragged(MouseEvent e){}
    }  //fin classe interne Carre
    
    //10.
    boolean gagne(){
        for(int i=0; i<9; i++){
            if(modTab[i].r!=modTab[i+1].r) return false;
            if(modTab[i].v!=modTab[i+1].v) return false;
            if(modTab[i].b!=modTab[i+1].b) return false;
        }
        return true;
    }
    
    //11.
    void finJeu(){
        if(gagne()){
            //11.
            //close();
            //13.
            System.out.println("Succès !");
            /*JLabel successEt=new JLabel("Succès !");
            successEt.setHorizontalAlignment(JLabel.CENTER);
            etiquette.setLayout(new BorderLayout());
            etiquette.add(successEt, BorderLayout.CENTER);
            getContentPane().add(etiquette);
            etiquette.setVisible(true);
            panel.setVisible(false);*/
            //14.
            panel.addMouseListener(new java.awt.event.MouseAdapter(){
                public void mouseClicked(MouseEvent e){
                    if(gagne()){
                        Cadre.this.close();
                    }
                }

                public void mouseExited(MouseEvent e){
                    if(gagne()){
                        if(e.getX()<Cadre.this.getX() || e.getX()>Cadre.this.getX()+600
                        ||e.getY()<Cadre.this.getY() || e.getY()>Cadre.this.getY()+600)
                            Cadre.this.close();
                    }
                }
            });
            
        }
    }
    
    void close(){
        this.setVisible(false);
        dispose();
    }
}