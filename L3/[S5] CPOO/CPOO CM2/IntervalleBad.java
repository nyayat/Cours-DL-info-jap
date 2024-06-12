package fr.uparis.dangers;
//librement inspiré par Effective Java
//contrat: debut avant fin 

// plein de trous à cause d'aliasing
public class IntervalleB {
	private Heure debut, fin;
	
	IntervalleB(Heure d, Heure f){  
		if (!Heure.avant(debut,fin))
				throw new IllegalArgumentException();
		debut=d; fin =f;  //trou 1 
	}
	
	public void setDebut(Heure h) {
		debut=h;  // trou 2
	}
	
	public Heure getDebut() {
		return debut;  //trou 3
	}
}

///// Trois attaques par aliasing
//Heure x=Heure(3);Heure y=Heure(5);
//IntervalleB iii=new IntervalleB(x,y);

// Attaque 1 
//x.hh=10; // contrat violé


//Attaque 2
//iii.setDebut(new Heure(10))


//// Attaque 3
//Heure d=iii.getDebut();
//d.hh=12;
//
//
//
