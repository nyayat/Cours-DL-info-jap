public class Test {
    public static void main(String[]args){
        Personne a=new Personne("GM mat", "Nom", null, null);
        Personne b=new Personne("GP mat", "Nom", null, null);
        Personne c=new Personne("GP pat", "Nom", null, null);
        Personne d=new Personne("Mère", "Nom", b, a);
        Personne e=new Personne("Père", "Nom", c, null);
        Personne f=new Personne("Moi", "Nom", e, d);
        Personne g=new Personne("Frere", "Nom", null, d);
        Personne h=new Personne("Tante", "Nom", null, a);
        Personne i=new Personne("Cousin", "Nom", null, h);
        Famille fam=new Famille(f);
        
        //1.1
        /*System.out.println(fam.estFrereOuSoeur(g));  //true
        System.out.println(fam.estFrereOuSoeur(i));  //false*/
        
        //1.2
        /*System.out.println(fam.estCousinGermain(i));  //true
        System.out.println(fam.estCousinGermain(g));  //false
        System.out.println(fam.estCousinGermain(c));  //false*/
        
        //2.3
        //System.out.println(fam.nbAscendantsVivants());  //5
        
        //2.4
        /*System.out.println(fam.possedeCommeAscendants(h));  //false
        System.out.println(fam.possedeCommeAscendants(c));  //true
        System.out.println(fam.possedeCommeAscendants(a));  //true*/
        
        //3.5
        /*System.out.println(fam.distanceDAscendance(i));  //0
        System.out.println(fam.distanceDAscendance(c));  //2
        System.out.println(fam.distanceDAscendance(d));  //1*/
        
        //3.6        
        //fam.afficheAscendantUn(i);  //pas dans l'ascendance
        /*fam.afficheAscendantUn(c);
        fam.afficheAscendantUn(b);
        fam.afficheAscendantUn(a);
        fam.afficheAscendantUn(e);
        fam.afficheAscendantUn(d);*/
        
        //3.7
        //System.out.println(fam.nbDeGenerations());  //3
        
        //4.7
        //System.out.println(fam.Verification());  //false
        Personne p1=new Personne("Père", "NP", null, null);
        Personne p2=new Personne("Mère", "NM", null, null);
        Personne p3=new Personne("Moi", "NP", p1, p2);
        Personne p4=new Personne("Soeur", "NP", p1, p2);
        Famille fam2=new Famille(p3);
        //System.out.println(fam2.Verification());  //true;
        
        //5.8
        //fam.getTousLesAscendants().affiche();
        /*Famille fam2=new Famille(new Personne("Test", "Nom", null, null));
        fam2.getTousLesAscendants().affiche();*/
        
        //5.9
        /*System.out.println(fam2.estDeMaFamille(p4));  //true
        System.out.println(fam2.estDeMaFamille(p2));  //false
        System.out.println(fam.estDeMaFamille(d));  //true*/
        
        //5.10
        System.out.println(fam.distanceDHeritage(b));  //2
        System.out.println(fam.distanceDHeritage(e));  //1
        System.out.println(fam.distanceDHeritage(h));  //3
        System.out.println(fam.distanceDHeritage(i));  //4
    }
}