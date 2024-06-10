public class Confiture {
    private final String fruit;
    private int proportion;  //pourcentage de fruit dans la confiture
    private int cal;  //calories par 100g de confiture
    //10.
    private double masuc; //masse de sucre 

    //1.
    public Confiture(String fruit, int proportion, int cal) {
	this.fruit=fruit;
	this.proportion=proportion;
	this.cal=cal;
	//10.
	masuc=cal/3.87;
    }

    //2.
    public Confiture(String fruit, int cal) {
	this.fruit=fruit;
	this.proportion=50;
	this.cal=cal;
	//10.
	masuc=cal/3.87;
    }

    //3.
    String description(){
	String res="Confiture de "+this.fruit+", "+this.proportion+"% de fruit, "+this.cal+" calories aux 100 grammes, "+this.masuc+"g de sucre"; //10. : masuc
	return res;
    }

    //5.
    double gcal(int g) {
	double res=g*this.cal/100;
	return res;
        }
    
    //6.
    public boolean egal(Confiture c) {
	boolean b=((this.fruit).equals(c.fruit) && this.proportion==c.proportion && this.cal==c.cal);
	return b;
    }

    //9. famille => pas static
    String afficheFruit() {
	return this.fruit;
    }

    void modifCal(int newCal) {
	this.cal=newCal;
    }

    //11.
    void modifProp(int newProp) {
	if(newProp>0 && newProp<100) {
		this.proportion=newProp;
	}
    }
}
