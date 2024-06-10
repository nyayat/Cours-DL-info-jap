public class Animal {
    //1.
    String nom, espece;
    char sexe;  //f ou m
    int age, poids;  //age en jours et poids en kg
    int faim;  //8.
    
    //2.
    public Animal(String name, char s, int a, int masse, String species){
        nom=name;
        sexe=s;
        age=a;
        poids=masse;
        espece=species;
        faim=0;
    }
    
    public Animal(String name, char s, int a, int masse, String species, int f){  //f dans 8.
        nom=name;
        sexe=s;
        age=a;
        poids=masse;
        espece=species;
        if(f>10){
            f=10;
        }
        else if(f<0){
            f=0;
        }
        faim=f;
    }
    
    //3.Dans le main :
    //Animal a1=new Animal("Marti",'m',1825,300,"zèbre",0);
    //Animal a2=new Animal("Gloria",'f',2555,1500,"hippopotame",0);
    
    //4. Dans le main : a2.poids-=50;
    
    //5. Affiche 950 (a et b partagent les mêmes références)
    
    //6.
    public static String description(Animal a){
        String res="Je m'appelle "+a.nom+", je suis un(e) "+a.espece+" ";
        /*if(a.sexe=='f'){
            res+="femelle";
        }
        else{
            res+="male";
        }*/
        String nomSexe=(a.sexe=='m')? "male" : "femelle";
        res+=nomSexe+", j'ai "+a.age+" jours et je pèse "+a.poids+" kg";
        return res;
    }
    
    //7.
    public static String descriptionBis(Animal a){
        String res="Je m'appelle "+a.nom
                +", je suis un(e) "+a.espece+" ";
        /*if(a.sexe=='f'){
            res+="femelle";
        }
        else{
            res+="male";
        }*/
        String nomSexe=(a.sexe=='m')? "male" : "femelle";
        res+=nomSexe+", j'ai "+a.age/365+" ans et "+a.age%365
                +" jours et je pèse "
                +a.poids+" kg";
        return res;
    }
    
    //9. Sans static
    public void nourrir(){
        if(this.faim==0){
            this.poids+=this.poids/10;
        }
        else{
            this.faim--;
        }
    }
    
    //ou avec static
    public static void nourrir(Animal a){
        if(a.faim==0){
            a.poids+=a.poids/10;
        }
        else{
            a.faim--;
        }
    }
    
    //10.
    public static Animal lePlusGros(Animal[]t){
        Animal max=t[0];
        for(Animal a : t){
            if(max.poids<a.poids){
                max=a;
            }
        }
        return max;
    }
    
    //11.
    public Animal reproduction(Animal a1, Animal a2){
        if(a1.sexe!=a2.sexe && a1.espece.equals(a2.espece) && a1.faim<5 && a2.faim<5){
            char newSexe='f';
            if(Math.random()*(2)==0){
                newSexe='m';
            }
            int newMasse=(int)(Math.random()*(a1.poids-a2.poids+1)+a2.poids);
            String newName="("+a1.nom+a2.nom+")";
            Animal res=new Animal(newName, newSexe, 0, newMasse,a1.espece,0);
            return res;
        }
        return null;
    }
    
}