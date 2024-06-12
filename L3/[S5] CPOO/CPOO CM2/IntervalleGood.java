package fr.uparis.dangers;
// librement inspiré par Effective Java
//contrat: debut avant fin 

// aliasing impossible grace au copies défensives
public class IntervalleG {
	private Heure debut, fin;
	
	IntervalleG(Heure d, Heure f){
		if (! Heure.avant(debut,fin))
				throw new IllegalArgumentException();
		debut=new Heure(d.hh); fin =new Heure (f.hh); //copies défensives
	}
	
	public void setDebut(Heure h) {
		debut =new Heure(h.hh); //copie défensive
	}
	
	public Heure getDebut() {
		return new Heure(debut.hh); //copie défensive
	}
}

///// Les trois attaques échouent
//Heure x=Heure(3);Heure y=Heure(5);
//IntervalleG iii=new IntervalleG(x,y);

//Attaque 1 ne passe pas
//x.hh=10; // L'intervalle reste intacte


//Attaque 2 ne passe pas
//iii.setDebut(new Heure(10)) // L'intervalle reste intacte


////Attaque 3  ne passe pas
//Heure d=iii.getDebut(); 
//d.hh=12;     // L'intervalle reste intacte


