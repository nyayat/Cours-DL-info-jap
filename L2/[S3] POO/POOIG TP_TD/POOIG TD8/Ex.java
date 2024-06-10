public class Ex {
    //1.1
    //RuntimeException hérite de la classe Exception
    
    //1.2
    //RuntimeException --> NullpointerException
    //RuntimeException --> ArrayIndexOutOfBoundsException
    //Exception --> IOException
    
    //1.3
    //Ajouter throws à la signature d'une méthode lui permet de soulever des exceptions
    
    //1.4
    //Avec la déclaration finally
    
    //2
    static class CallF extends Exception{}
    static class CallG extends Exception{}
    static class CallH extends CallF{}
    
    public static void f() throws CallF, CallG, CallH{throw new CallF();}
    public static void g() throws CallF, CallG, CallH{throw new CallG();}
    public static void h() throws CallF, CallG, CallH{throw new CallH();}
    public static void skip() throws CallF, CallH, CallG{}
    
    public static void main(String[]args) throws Exception{
        //2.1
        /*try{f();g();}
        catch(CallF e){System.out.println("Catch f()");}
        catch(CallG e){System.out.println("Catch g()");}*/
        //Affiche : Catch f()
        
        //2.2
        /*try{f();g();}
        catch(CallG e){System.out.println("Catch g()");}
        catch(CallF e){System.out.println("Catch f()");}*/
        //Affiche : Catch f()
        
        /*try{g();f();}
        catch(CallF e){System.out.println("Catch f()");}
        catch(CallG e){System.out.println("Catch g()");}*/
        //Affiche : Catch g()
        
        //2.3
        /*try{h();}
        catch(CallF e){System.out.println("Catch f()");}
        catch(CallH e){System.out.println("Catch h()");}*/
        //Affiche : Erreur de compilation, il faut inverser les catch
        
        /*try{h();}
        catch(CallH e){System.out.println("Catch h()");}
        catch(CallF e){System.out.println("Catch f()");}*/
        //Affiche : Catch h()
        
        //2.6
        /*try{skip();}
        catch(CallF e){System.out.println("Catch f()"); g();}
        catch(CallG e){System.out.println("Catch g()");}
        finally{System.out.println("Finally");}*/
        //Affiche : Finally
        
        /*try{f();}
        catch(CallF e){System.out.println("Catch f()"); g();}
        catch(CallG e){System.out.println("Catch g()");}
        finally{System.out.println("Finally");}*/
        /*Affiche : Catch f()
                    Exception de type CallG
                    Finally
        */
        
        //2.8
        /*try{f();}
        finally{System.out.println("Finally"); g();}*/
        //Affiche : Finally + Exception de type CallG
    }
}