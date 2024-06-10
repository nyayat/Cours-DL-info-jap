public class Controleur {
    //9.
    protected Modele modele;
    protected Vue vue;
    //12.
    private Modele mem;
    
    Controleur(Vue v){
        vue=v;
        modele=v.modele;
    }
    
    //10.
    void sliderMoved(){
        vue.modele.r=vue.rougeS.getValue();
        vue.modele.v=vue.vertS.getValue();
        vue.modele.b=vue.bleuS.getValue();
        vue.miseAJour();
    }
    
    //12.
    void memoriser(){
        mem=new Modele(modele.r, modele.v, modele.b);
    }
    
    void rappel(){
        vue.rougeS.setValue(mem.r);
        vue.vertS.setValue(mem.v);
        vue.bleuS.setValue(mem.b);
        vue.miseAJour();
    }
    
    void complementer(){
        Modele compl=new Modele(255-modele.r, 255-modele.v, 255-modele.b);
        vue.rougeS.setValue(compl.r);
        vue.vertS.setValue(compl.v);
        vue.bleuS.setValue(compl.b);
        vue.miseAJour();
    }
}