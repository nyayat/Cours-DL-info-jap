import java.io.Serializable;

public class Bonus implements Serializable{
  private static final long serialVersionUID = 1L;
  private String name;
  private int number;

  Bonus(){
    this("",0);
  }
    
  Bonus(String name, int number){
    this.name=name;
    this.number=number;
  }

  void use(Board board, int col, int li) {
    if(this.number<1) throw new IllegalStateException("Il n y a pas assez de "+this.name);
    try{
      if(this instanceof BlockBuster) board.removeCase(col, li);
      else if(this instanceof ColumnBlaster) board.removeCol(col);
    }
    catch(IllegalArgumentException e) {};
    this.number--;
  }
  
  String getName(){
    return this.name;
  }
  
  int getNumber(){
    return this.number;
  }
  
  void add(){
    this.number++;
  }
  
  void remove() {//on ne passe pas en-dessous de 0
    if(this.number>0) this.number--;
  }
  
  boolean isNull(){
    return this.number==0;
  }

  public String toString(){
    return(this.name+" : "+this.number);
  }


  class BlockBuster extends Bonus{
    private static final long serialVersionUID = 1L;
    
    BlockBuster(){
      this(0);
    }

    BlockBuster(int n){
      super("Brise-bloc", n);
    }
  }


  class ColumnBlaster extends Bonus{
    private static final long serialVersionUID = 1L;
      
    ColumnBlaster(){
      this(0);
    }

    ColumnBlaster(int n){
      super("Brise-colonne", n);
    }
  }
}