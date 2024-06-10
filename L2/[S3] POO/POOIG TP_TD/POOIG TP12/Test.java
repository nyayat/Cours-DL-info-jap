public class Test {
    public static void main(String[]args){
        javax.swing.SwingUtilities.invokeLater(
                new Runnable(){
                    public void run(){
                        Cadre c=new Cadre();
                        c.setVisible(true);
                    }
                }
        );
    }
}