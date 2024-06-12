//2.
public class Electeur implements Runnable{
    final String vote;//oui ou non
    static int nbEl=0;
    final int id;
    Bureau bureau;//pas dans l'enonce mais necessaire pour voter()
    
    //2.1
    Electeur(String vote, Bureau b){
        this.vote=vote;
        id=nbEl++;
        bureau=b;
    }
    
    //2.3
    synchronized void voter(){
        bureau.choix.put(vote, bureau.choix.get(vote)+1);
    }
    
    @Override
    public synchronized void run(){
        //2.3
        /*voter();
        System.out.println(id
            + " : {oui="+bureau.choix.get("oui")
            + ", non="+bureau.choix.get("non") + "}");*/
        
        //2.5
        if(vote.equals("oui")){
            while(bureau.choix.get("non")<7){
                try{
                    wait(1000);
                }
                catch(InterruptedException e){}
            }
        }
        voter();
        System.out.println(id
            + " : {oui="+bureau.choix.get("oui")
            + ", non="+bureau.choix.get("non") + "}");
        notifyAll();
        
        if(bureau.choix.get("oui")+bureau.choix.get("non")==30)
            System.out.println("{oui="+bureau.choix.get("oui")
                + ", non="+bureau.choix.get("non") + "}");
    }    
}