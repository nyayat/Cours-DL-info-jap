import java.util.ArrayList;
public class Toboggan {
    //3.1
    FileToboggan file;
    
    Toboggan(FileToboggan file){
        this.file=file;
    }
    
    //3.2
    int jouer(){
        int res=0;
        while(file.courant!=null){
            this.file.unTour_bis();
            res++;
        }
        return res;
    }
    
    //3.3
    ArrayList<Enfant> jouer_sortis(){
        ArrayList<Enfant> res=new ArrayList<Enfant>();
        while(file.courant!=null){
            Enfant tmp=this.file.unTour_bis();
            if(tmp!=null){
                res.add(tmp);
            }
        }
        return res;
    }
}