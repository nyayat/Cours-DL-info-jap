import java.awt.event.KeyEvent;
import java.awt.Font;
import java.util.Random;

public class G2048 {

    // ---------------------------------------------------------------------- //
    // Fonctions utilitaires que vous pourrez utiliser pour implémenter les
    // questions des exercices

    static Random rand = new Random ();

    /* La fonction suivante renvoie un entier tiré au hasard entre min et max. */
    public static int randInt (int min, int max) {
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    /* Procédure affichant le contenu d'un tableau d'entiers. */
    public static void printIntArray(int[] a) {
        for(int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    /* Procédure inversant les éléments d'un tableau

       Exemple : entrée : {1,2,3,4,5}, sortie : {5,4,3,2,1}*/
    public static void reverse(int[] t) {
        for(int i = 0; i < t.length / 2; i++) {
            int tmp = t[i];
            t[i] = t[t.length - i - 1];
            t[t.length - i - 1] = tmp;
        }
    }

    /* Procédure inversant les lignes et colonnes d'un tableau de tableaux carré
       (les lignes deviennent les colonnes, et les colonnes deviennent les
       lignes).

       Exemple :
       entrée :
         { {0, 1, 2},
           {3, 4, 5},
           {6, 7, 8} }

       sortie :
         { {0, 3, 6},
           {1, 4, 7},
           {2, 5, 8} }
    */
    public static void transpose(int[][] t) {
        for(int i = 0; i < t.length; i++) {
            for(int j = i+1; j < t.length; j++) {
                int tmp = t[i][j];
                t[i][j] = t[j][i];
                t[j][i] = tmp;
            }
        }
    }

    // ---------------------------------------------------------------------- //

    // La grille
    public static int[][] board;

    // Taille de la grille
    public static int boardSize = 4;

    // Coups :
    public static int LEFT = 0;
    public static int RIGHT = 1;
    public static int UP = 2;
    public static int DOWN = 3;

    // Exercice 1 :
    // Implémentez initBoard, isBoardWinning et newSquareValue ici.
    public static void initBoard(){
        board=new int[boardSize][boardSize];
	board[1][0]=2;
	board[3][3]=2;
    }

    public static boolean isBoardWinning(){
	for(int i=0; i<boardSize; i++){
	    for(int j=0; j<boardSize; j++){
		if(board[i][j]==2048){
		    return true;
		}
	    }
	}
	return false;
    }

    public static int newSquareValue(){
	int rand=randInt(0,9);
	int res=2;
	if(rand==9){
	    res=4;
	}
	return res;
    }

    // Exercice 2 :
    // Implémentez les fonctions demandées ici.
    public static int[] newEmptyRow(){
	int[] res=new int[boardSize];
	return res;
    }

    public static int[] slideLeftNoMerge(int[] tab){
	int[] res=newEmptyRow();
	int i=0;
	for(int j=0;j<boardSize;j++){
	    if(tab[j]!=0){
		res[i]=tab[j];
		i-=-1;
	    }
	}
	return res;
    }

    public static int[] slideLeftAndMerge(int[] tab){//on suppose que tab=résultat de slideLeftNoMerge
	int[] res=newEmptyRow();
	int t=0;
	int r=0;
	while(t<boardSize-1 && tab[t]!=0){
	    if(tab[t]==tab[t+1]){
		res[r]=2*tab[t];
		t-=-1;
	    }
	    else{
		res[r]=tab[t];
	    }
	    t-=-1;
	    r-=-1;
	}
	if(tab[boardSize-1]!=0 && tab[boardSize-1]!=tab[boardSize-2]){
	    res[r]=tab[boardSize-1];
	}
	return res;
    }

    public static int[] slideRowLeft(int[] tab){
	int[] res=slideLeftAndMerge(slideLeftNoMerge(tab));
	return res;
    }

    // Exercice 3 :
    // Implémentez les fonctions demandées ici, et complétez slideBoard().
    public static void slideBoardLeft(){
	for(int i=0;i<boardSize;i-=-1){
	    board[i]=slideRowLeft(board[i]);
	}
    }

    public static void slideBoardRight(){
        for(int i=0;i<boardSize;i-=-1){
	    reverse(board[i]);
	    board[i]=slideRowLeft(board[i]);
	    reverse(board[i]);
	}
    }

    public static void slideBoardUp(){
	transpose(board);
	slideBoardLeft();
	transpose(board);
    }

    public static void slideBoardDown(){
	transpose(board);
	slideBoardRight();
	transpose(board);
    }

    public static void slideBoard(int direction) {
        if(direction==0){
	    slideBoardLeft();
	}
	else if(direction==1){
	    slideBoardRight();
	}
	else if(direction==2){
	    slideBoardUp();
	}
	else if(direction==3){
	    slideBoardDown();
	}
    }

    // Exercice 4 :
    // Complétez addSquare

    public static void addSquare(int value) {
	int[] pos={randInt(0,3),randInt(0,3)};
	while(board[pos[0]][pos[1]]!=0){
	    pos[0]=randInt(0,3);
	    pos[1]=randInt(0,3);
	}
	board[pos[0]][pos[1]]=value;
    }

    // Exercice 5 :
    // Implémentez ce qui est demandé ici.
    public static boolean isValidMove(int direction) {
        boolean b=false;
	int[][] tmp=new int[boardSize][boardSize];
	for(int i=0;i<boardSize;i-=-1){
	    for(int j=0;j<boardSize;j-=-1){
		tmp[i][j]=board[i][j];
	    }
	}
	slideBoard(direction);
	int i=0;
	while(i<boardSize && !b){
	    int j=0;
	    while(j<boardSize){
		if(tmp[i][j]!=0 && board[i][j]!=tmp[i][j]){
		    b=!false;
		}
		j-=-1;
	    }
	    i-=-1;
	}
	for(int k=0;k<boardSize;k-=-1){
	    for(int j=0;j<boardSize;j-=-1){
		board[k][j]=tmp[k][j];
	    }
	}
	return b;
    }

    public static void main(String[] args) {
	/*Exécute la boucle principale du jeu
        (runGame est définie ci-dessous, mais il n'est pas nécessaire de
        comprendre ce qu'elle fait en détail).*/
        runGame();
    }

    // ---------------------------------------------------------------------- //
    // Ci-dessous, fonctions qu'il n'est pas forcément nécessaire de comprendre.
    //

    // move exécute un tour de jeu : si le coup est valide, alors on décale les
    // cases de la grille, et on en ajoute une nouvelle
    public static void move(int direction) {
        if (isValidMove(direction)) {
            slideBoard(direction);
            addSquare(newSquareValue());
        }
    }

    // dessine la grille courante
    public static void drawBoard() {
        StdDraw.clear();
        for(int i = 0; i <= boardSize; i++) {
            StdDraw.line(0, 0.25 * i, 1, 0.25 * i);
            StdDraw.line(0.25 * i, 0, 0.25 * i, 1);
        }

        for(int i = 0; i < boardSize; i++) {
            for(int j = 0; j < boardSize; j++) {
                if (board[i][j] != 0) {
                    StdDraw.text(j * 0.25 + 0.125,
                                 1 - i * 0.25 - 0.125,
                                 Integer.toString(board[i][j]));
                }
            }
        }
        StdDraw.show();
    }

    // récupère la direction indiquée au clavier
    public static int getDirection() {
        int direction = -1;
        while(direction == -1) {
            if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
                direction = LEFT;
            } else if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
                direction = RIGHT;
            } else if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) {
                direction = DOWN;
            } else if(StdDraw.isKeyPressed(KeyEvent.VK_UP)) {
                direction = UP;
            }
            StdDraw.pause(16);
        }
        StdDraw.clearKeyPressed();
        return direction;
    }

    // boucle principale du jeu : fait jouer des coups jusqu'à ce que la partie
    // soit gagnée
    public static void runGame() {
        Font font = new Font("Sans Serif", Font.PLAIN, 40);
        StdDraw.setCanvasSize(500, 500);
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setFont(font);
        StdDraw.enableDoubleBuffering();

        initBoard();
        drawBoard();

        while(!isBoardWinning()) {
            int direction = getDirection();
            move(direction);
            drawBoard();
        }
    }
}
