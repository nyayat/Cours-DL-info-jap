import java.io.IOException;

public class Display {

  public static void main(String[] args) throws IOException {

  }


  public static int [][] getGray(String filename) throws IOException {
      Picture p = new Picture(filename);
      return p.getGray();
  }

  public static void drawGray(int [][] gray) {
    Picture p = new Picture(gray);
    p.draw();
  }


  public static void saveGray(int [][] gray, String filename) throws IOException {
    Picture p = new Picture(gray);
    p.save(filename);
  }



}
