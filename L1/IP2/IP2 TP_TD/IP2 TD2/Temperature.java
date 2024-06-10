public class Temperature {
    //1.
    double temp;  //température
    String unite;  //unité
    
    //2.
    Temperature(){
        temp=0;
        unite="K";
    }
    
    //3.
    Temperature(double t, String u){
        temp=t;
        if(u.equals("°F") || u.equals("°C") || u.equals("Fahrenheit") || u.equals("Celsius")){
            unite=u;
        }
        else{
            unite="K";
        }
    }
    
    //4.
    Temperature(Temperature t){
        temp=t.temp;
        unite=t.unite;
    }
    
    //5.
    void affiche(){
        System.out.println(this.temp+this.unite);
    }
    
    void modifTemp(double t){
        this.temp=t;
    }
    
    void modifUnite(String u){
        if(u.equals("°F") || u.equals("°C") || u.equals("K")){
            this.unite=u;
        }
    }
    
    //6.
    private Temperature conversionKC(){
        if(this.unite.equals("K")){
            Temperature t=new Temperature(this);
            t.modifUnite("°C");
            t.modifTemp(t.temp-273.15);
            return t;
        }
        return null;
    }
    
    private Temperature conversionCK(){
        if(this.unite.equals("C")){
            Temperature t=new Temperature(this);
            t.modifUnite("K");
            t.modifTemp(t.temp+273.15);
            return t;
        }
        return null;
    }
    
    private Temperature conversionCF(){
        if(this.unite.equals("°C")){
            Temperature t=new Temperature(this);
            t.modifUnite("°F");
            t.modifTemp(t.temp*9/5+32);
            return t;
        }
        return null;
    }
    
    private Temperature conversionFC(){
        if(this.unite.equals("°F")){
            Temperature t=new Temperature(this);
            t.modifUnite("°C");
            t.modifTemp((t.temp-32)*5/9);
            return t;
        }
        return null;
    }
    
    private Temperature conversionKF(){
        if(this.unite.equals("K")){
            Temperature t=new Temperature(this);
            t.modifUnite("°F");
            t.modifTemp((t.temp-273.15)*9/5+32);
            return t;
        }
        return null;
    }
    
    private Temperature conversionFK(){
        if(this.unite.equals("°F")){
            Temperature t=new Temperature(this);
            t.modifUnite("K");
            t.modifTemp((t.temp-32)*5/9+273.15);
            return t;
        }
        return null;
    }
    
    //7.
    double get(String u){
        Temperature newT=new Temperature(this);
        if(u.equals("K")){
            newT=newT.conversionCK();
            newT=newT.conversionFK();
        }
        else if(u.equals("°C")){
            newT=newT.conversionKC();
            newT=newT.conversionFC();
        }
        else if(u.equals("°F")){
            newT=newT.conversionCF();
            newT=newT.conversionKF();
        }
        return newT.temp;
    }
    
    //8.
    boolean egalite(Temperature t2){
        double val=this.get(t2.unite);  //cherche val de this dans même unité que t2
        if(val==this.temp && !this.unite.equals(t2.unite)){  //pour unité de this incorrect
            return false;
        }
        return(val==t2.temp);
    }
    
    //9.
    boolean plusBasseQue(Temperature t2){
        double val=this.get(t2.unite);  //cherche val de this dans la même unité que t2
        if(val==this.temp && !this.unite.equals(t2.unite)){  //pour unité de this incorrect
            return false;
        }
        return(val<t2.temp);
    }
}