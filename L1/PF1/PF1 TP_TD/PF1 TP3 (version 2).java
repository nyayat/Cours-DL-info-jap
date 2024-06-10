public class PF1 {
    public static String dvb(int n){
	String res="Erreur de capacité";
        if (n>=0 && n<16){
	    res="";
	    for (int i=0; i<4; i++){
		res=n%2+res;
		n=n/2;
	    }

	}
	return res;
    }

    public static int bvd (int c0, int c1, int c2, int c3){
	int res=-1;
	if((c0==1 || c0==0) && (c1==1 || c1==0) && (c2==1 || c2==0) && (c3==1 || c3==0)){
	    res=((c0*2+c1)*2+c2)*2+c3;
	}
	return res;
    }

    public static String dvbs(int n){
	String res="Erreur de capacité";
        if (n>-9 && n<8){
	    res="";
	    if (n<0){
		n=-n-1;
		for (int i=0; i<3; i++){
		    if (n%2==0){
			res='1'+res;
		    }
		    else{
			res='0'+res;
		    }
		    n=n/2;
		}
		res='1'+res;
	    }
	    else{
		for (int i=0; i<3; i++){
		    res=n%2+res;
		    n=n/2;
		}
		res='0'+res;
	    }
	}
	return res;
    }


    public static int bvds (int c0, int c1, int c2, int c3){
	int res=444;
	if(c0==1 && (c1==1 || c1==0) && (c2==1 || c2==0) && (c3==1 || c3==0)){
	    if(c1==0){
		c1=1;
	    }
	    else{
		c1=0;
	    }
	    if(c2==0){
		c2=1;
	    }
	    else {
		c2=0;
	    }
	    if(c3==0){
		c3=1;
	    }
	    else{
		c3=0;
	    }
	    res=-((c1*2+c2)*2+c3+1);
	}
	else if ((c0==0) && (c1==1 || c1==0) && (c2==1 || c2==0) && (c3==1 || c3==0)){
	    res=(c1*2+c2)*2+c3;
	}
	return res;
    }

    public static String dvbb(int n, int b){
	String res="Erreur de capacité";
        if (n>=0 && n<b*b*b*b){
	    res="";
	    for (int i=0; i<4; i++){
		res=n%b+"."+res;
		n=n/b;
	    }

	}
	return res;
    }

    public static int bvdb (int c0, int c1, int c2, int c3, int b){
	int res=-1;
	if((c0>=0 && c0<b) && (c1>=0 && c1<b) && (c2>=0 && c2<b) && (c3>=0 && c3<b)){
	    res=((c0*b+c1)*b+c2)*b+c3;
	}
	return res;
    }
		

    public static void main (String[] args){
	System.out.println(dvbb(4578,16));	
	System.out.println(bvdb(1,1,14,2,16));
    }
}
        
	    

