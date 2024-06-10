public class Matrix<T> {
    //1.4
    private T[][]mat;
    
    Matrix(T[][]t) throws InvalidContentException{
        if(t.length==0 || t[0].length==0) throw new IllegalArgumentException();
        if(checkContent(t)) mat=t;
        else throw new InvalidContentException();
    }
    
    protected boolean checkContent(T[][]t){
        return true;
    }
    
    int nRows(){
        return this.mat.length;
    }
    
    int nCols(){
        return this.mat[0].length;
    }
    
    MatrixScanner rowScanner(int x, accFunction f, int valInit){
        return new MatrixScanner(x, 0, 1, 0, f, valInit);
    }
    
    MatrixScanner colScanner(int y, accFunction f, int valInit){
        return new MatrixScanner(0, y, 0, 1, f, valInit);
    }
    
    //1.5
    private class MatrixScanner<S> implements Accumulator<S>{  //classe interne
        private S acc;
        private int x, y, dx, dy;
        private accFunction<S,T> f;
        
        private boolean checkIndex(int x, int y){
            return !(x<0 || y<0 || x>nRows() || y>nCols());
        }
        
        MatrixScanner(int x, int y, int dx, int dy, accFunction<S,T>f, S acc){
            if(checkIndex(x, y)){
                this.x=x;
                this.y=y;
                this.dx=dx;
                this.dy=dy;
                this.f=f;
                this.acc=acc;
            }
            else throw new IllegalArgumentException();
        }
        
        public void accumulate(S e){
            if(checkIndex(x, y)) acc=f.apply(acc, e, mat[x][y]);
        }
        
        public boolean isOver(){
            return (x>=nRows() || y>=nCols());
        }
        
        public S read(){
            return acc;
        }
    
    }  //fin de classe interne
}