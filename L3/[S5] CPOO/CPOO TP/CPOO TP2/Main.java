public class Main{
    public static void main(String[] args){
        Bac bac=new Bac("S", Mention.BIEN, 2015);
        Licence licence=new Licence("SVT", Mention.ASSEZ_BIEN, 2018);
        DiplomeInge dI=new DiplomeInge("SVT", Mention.PASSABLE, 2020);
        Master master=new Master("SVT", Mention.BIEN, 2020);
        Doctorat doctorat=new Doctorat("SVT", Mention.FELICITATIONS, 2023);
        
        /*CV1 cv11=new CV1(bac, licence, master);
        System.out.println(cv11);
        
        //erreur :
        CV1 cv12=new CV1(bac, licence, null, doctorat);
        System.out.println(cv12);*/
        
        
        /*CV3 cv31=new CV3();
        cv31.setBac(bac);
        cv31.setLicence(licence);
        System.out.println(cv31);
        
        //erreur : 
        CV3 cv32=new CV3();
        cv32.setLicence(licence);
        System.out.println(cv32);*/
        
        
        /*CV5 cv51=CV5.builder()
            .bac(bac)
            .licence(licence)
            .master(master)
            .build();
        System.out.println(cv51);
        
        //erreur :
        CV5 cv52=CV5.builder()
            .bac(bac)
            .licence(licence)
            .doctorat(doctorat)
            .build();
        System.out.println(cv52);*/
    }
}