public class Palette {
    //1.
    private Vue view;
    //8.
    private Modele modele;
    //9.
    private Controleur contr;
    
    public Palette(){
        view=new Vue();
        view.setVisible(true);
        //8.
        modele=view.modele;
        //9.
        contr=new Controleur(view);
    }
    
    public static void main(String[]args){
        new Palette();
    }
}