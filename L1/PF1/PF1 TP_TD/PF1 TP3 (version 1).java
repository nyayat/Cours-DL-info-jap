public class Ex1{

    public static String dvb(int n){
	String res="";
	if (n<16 && n>=0){
	    for (int i=0;i<4;i++){
		res=n%2+res;
		n=n/2;
	    }
	}
	else{
	    res="Impossible";
	}
	return res;
    }


    public static int bvd(int a, int b, int c, int d){
	int res=-1;
	if ((a==0 || a==1) && (b==0 || b==1) && (c==0 || c==1) && (d==0 || d==1)){
	    res=((a*2+b)*2+c)*2+d;
	}
	return res;
    }


    public static String dvbSS(int n){
	String res="0";
	String tmp="";
	boolean b=false;
	if (n>=-8 && n<=7){
	    if (n<0){
		res="1";
		n=-n-1;
		b=true;
	    }
	    for (int i=0;i<3;i++){
		tmp=n%2+tmp;
		n=n/2;
	    }
	    if (b==true){
		for (int i=0;i<tmp.length();i++){
		    if (tmp.charAt(i)=='1'){
			res=res+'0';
		    }
		    else {
			res=res+'1';
		    }
		}
	    }
	    else {
		res=res+tmp;
	    }
	}
	return res;
    }


    public static int bvdSS(int a, int b, int c, int d){
	int res=-1111;
	if ((a==0 || a==1) && (b==0 || b==1) && (c==0 || c==1) && (d==0 || d==1)){
	    if (a==0){
		res=(b*2+c)*2+d;
	    }
	    else {
		if (b==0){
		    b=1;
		}
		else {
		    b=0;
		}
		if (c==0){
		    c=1;
		}
		else {
		    c=0;
		}
		if (d==0){
		    d=1;
		}
		else {
		    d=0;
		}
		res=-((b*2+c)*2+d+1);
	    }
	}
	return res;
    }


    public static String dvBB(int n, int b){
	String res="Impossible";
	if (n>=0 && n<b*b*b*b){
	    res="";
	    for (int i=0;i<4;i++){
		res=n%b+"."+res;
		n=n/b;
	    }
	}
	return res;
    }


    public static int BBvd(int c3, int c2, int c1, int c0, int b){
	int res=-1;
	if ((c3>=0 && c3<b) && (c2>=0 && c2<b) && (c1>=0 && c1<b) && (c0>=0 && c0<b)){
	    res=((c3*b+c2)*b+c1)*b+c0;
	}
	return res;
    }
    
    public static void main(String[] args) {
	System.out.println(BBvd(0,2,13,10,16));
    }

}
