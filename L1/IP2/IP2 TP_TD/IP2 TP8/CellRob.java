public class CellRob {
	/*J'ai pensé qu'il serait plus judicieux de laisser tous les attributs en public, du fait qu'on a l'attribut courant
	  et ainsi on traite à chaque fois le courant (courant devient le pointeur et plus seulement un "premier". */
	  
    Robot r;
    CellRob suiv;
    CellRob prec;
    
    //2.1
    CellRob(Robot rob, CellRob suiv, CellRob prec){
        r=rob;
        this.suiv=suiv;
        this.prec=prec;
    }
}