import java.util.*;

public class TerminalEmulator {
    //3.5
    public static void main(String[]args){
        Dossier racine=new Dossier(null);
        Shell s=new Shell(racine);
        s.interagir(System.in);
    }
}