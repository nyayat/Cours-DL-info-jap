import java.net.*;
import java.io.*;
import java.lang.*;

public class CodeConsommateur implements Runnable{

    private VariablePartagee var;
    
    public CodeConsommateur(VariablePartagee _var){
        this.var=_var;
    }
    
    public void run(){
        for(int i=0; i<100; i++){
            System.out.println(var.lire());
        }
    }
}
