import java.net.*;
import java.io.*;
import java.lang.*;

public class CodeProducteur implements Runnable{

    private VariablePartagee var;
    
    public CodeProducteur(VariablePartagee _var){
        this.var=_var;
    }
    
    public void run(){
        for(int i=0; i<100; i++){
            var.ecrire(i);
        }
    }
}
