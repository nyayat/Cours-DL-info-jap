public class TP5{

    // Ex 2
    public static String dvHEX(int n){
	String res="";
	for (int i=0;i<2;i++){
	    int r=n%16;
	    if (r<10){
		res=r+"."+res;
	    }
	    else {
		char a='A';
		if(r==11){
		    a='B';
		}
		else if(r==12){
		    a='C';
		}
		else if(r==13){
		    a='D';
		}
		else if(r==14){
		    a='E';
		}
		else if(r==15){
		    a='F';
		}
		res=a+"."+res;
	    }
	    n=n/16;
	}
	return res;
    }
    
    public static String ASCIIhex(String s){
	String res="";
	for (int i=0;i<s.length();i++){
	    String h=dvHEX((int) s.charAt(i));
	    res = res + h + " ";
	}
	return res;
    }

    // Ex 3
    public static int[] crible(int n){
	int[] res=new int[n];
	res[0]=0;
	for (int i=2;i<=n;i++){
	    res[i-1]=i;
	    for (int j=2;j<i;j++){
		if (i%j==0){
		    res[i-1]=0;
		}
	    }
	}
	return res;
    }
    
    public static String chainepremier(int n){
	int[] c=crible(n);
	String s="";
	for (int i=0;i<n;i++){
	    if (c[i]==0){
		s=s+'0';
	    }
	    else {
		s=s+'1';
	    }
	}
	return s;
    }


    public static String occurrences (int n){
	String s=chainepremier(n);
	String zz="00 : ";
	int ss=0;
	String zu="01 : ";
	int se=0;
	String uz="10 : ";
	int es=0;
	String uu="11 : ";
	int ee=0;
	for (int i=0;i<n/2;i++){
	    char k=s.charAt(2*i);
	    char l=s.charAt(2*i+1);
	    if (k=='0'){
		if (l=='0'){
		    ss++;
		}
		else {
		    se++;
		}
	    }
	    else if (l=='0'){
		es++;
	    }
	    else {
		ee++;
	    }
	}
	String res=zz+ss+"\n"+zu+se+"\n"+uz+es+"\n"+uu+ee+"\n";
	return res;
    }


    // Ex 4
    public static String rle(String s){
	String r="";
	int c=0;
	if (s.charAt(0)=='1'){
	    r="0 ";
	}
	for(int i=0; i<s.length(); i++){
	    if(i==s.length()-1 || s.charAt(i)==s.charAt(i+1)){
		c++;
	    }
	    else{
		c++;
		r=r+c+" ";
		c=0;
	    }
	}
	r=r+c;
	return r;
    }


    public static void main(String[] s){
	System.out.println(ASCIIhex("du co"));
    }

}
