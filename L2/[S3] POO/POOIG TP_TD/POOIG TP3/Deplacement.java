import java.math.*;
public class Deplacement {
    //5.
    private int x0, y0, x1, y1;  //départ=(x0,y0), arrivée=(x1,y1)
    
    Deplacement(int x0, int y0, int x1, int y1){
        this.x0=x0;
        this.y0=y0;
        this.x1=x1;
        this.y1=y1;
    }
    
    //5.A
    int valAbs(int x){
        if(x<0) x=-x;
        return x;
    }

    char typeDeplacement(){
        if(this.x0==this.x1) return 'h';
        if(this.y0==this.y1) return 'v';
        if(valAbs(this.x0-this.x1)==valAbs(this.y0-this.y1)) return 'd';
        if((valAbs(this.x0-this.x1)==1 && valAbs(this.y0-this.y1)==2)
            || (valAbs(this.x0-this.x1)==2 && valAbs(this.y0-this.y1)==1)) return 'c';
        return 'x';
    }
    
    //5.B
    int square(int x){
        return x*x;
    }
    
    int dist(){
        if (this.typeDeplacement()=='h'
         || this.typeDeplacement()=='v'
         || this.typeDeplacement()=='d'){
            return (int) Math.sqrt(square(x0-x1)+square(y0-y1));
        }
        return -1;
    }
    
    //6.1
    int getX0(){
        return this.x0;
    }
    
    int getX1(){
        return this.x1;
    }
    
    int getY0(){
        return this.y0;
    }
    
    int getY1(){
        return this.y1;
    }
}