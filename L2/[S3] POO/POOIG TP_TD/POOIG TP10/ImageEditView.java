import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ImageEditView extends JFrame{
    //3.
    private JButton cutButton, undoButton, redoButton;
    private ImagePane imagePane;
    private ImageEditModel model;
    
    class ImagePane extends JPanel{  //début classe interne
        //4.1
        private Selection selection=new Selection();
        
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(model.getImage(), 0, 0, this);
            ((Graphics2D)g).draw(selection.getRectangle());
        }
        
        //4.2
        ImagePane(){
            this.setPreferredSize(new Dimension
                (model.getImage().getWidth(), model.getImage().getHeight()));
            this.addMouseListener(selection);
            this.addMouseMotionListener(selection);
        }
        
        //5.
        class Selection extends MouseAdapter implements MouseMotionListener{
                                                        //classe interne interne
            private int x1, x2, y1, y2;
            
            int valAbs(int x1){
                return(x1<0)?(-x1):x1;
            }
            
            Rectangle getRectangle(){
                return new Rectangle(x1, y1, x2-x1, y2-y1);
            }
            
            public void mousePressed(MouseEvent e){
                this.x1=e.getX();
                this.y1=e.getY();
                cutButton.setEnabled(false);
                imagePane.repaint();
            }
            
            public void mouseDragged(MouseEvent e){
                this.x2=e.getX();
                this.y2=e.getY();
                if(x1!=x2 || y1!=y2) cutButton.setEnabled(true);
                imagePane.repaint();
            }
            
            public void mouseMoved(){}
        }  //fin classe interne interne
    }  //fin classe interne
    
    //6.
    ImageEditView(ImageEditModel iem){
        //6.1
        this.model=iem;
        //6.2
        this.setTitle("Editeur d'images");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JMenuBar menu=new JMenuBar();
        this.setJMenuBar(menu);
        this.cutButton=new JButton("Cut");
        this.undoButton=new JButton("Undo");
        this.redoButton=new JButton("Redo");
        //6.3
        this.cutButton.setEnabled(false);
        this.undoButton.setEnabled(false);
        this.redoButton.setEnabled(false);
        menu.add(cutButton);
        menu.add(undoButton);
        menu.add(redoButton);
        //6.4
        this.imagePane=new ImagePane();
        this.setContentPane(imagePane);
        //11.
        ex11();
    }
    
    void ex11(){
        //11.1
        this.cutButton.addActionListener(
            (ActionEvent e)->{
                this.model.saveCut(imagePane.selection.getRectangle());
                this.imagePane.repaint();
                this.cutButton.setEnabled(false);
                this.undoButton.setEnabled(true);
                this.redoButton.setEnabled(true);
            }
        );
        
        //11.2
        this.undoButton.addActionListener(
            (ActionEvent e)->{
                if(this.model.undoManager.canUndo())
                    this.model.undoManager.undo();
                this.imagePane.repaint();
            }
        );
        
        //11.3
        this.redoButton.addActionListener(
            (ActionEvent e)->{
                if(this.model.undoManager.canRedo())
                    this.model.undoManager.redo();
                this.imagePane.repaint();
            }
        );
    }
}