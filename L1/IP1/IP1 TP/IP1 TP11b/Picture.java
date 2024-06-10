import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;


public class Picture {
  private Random rand = new Random();
  private final BufferedImage img;
  public final int width, height;
  int [][] red;
  int [][] blue;
  int [][] green;

  public Picture(String filename) throws IOException {
    File file = new File(filename);
    img = ImageIO.read(file);
    width = img.getWidth();
    height = img.getHeight();

  }

  public Picture(int [][] red, int [][] blue, int [][] green) {
    width = red.length;
    height = red[0].length;
    img = new BufferedImage(width,height,
                                java.awt.image.BufferedImage.TYPE_INT_RGB);
    for (int i = 0 ; i < width; i++)
      for (int j = 0; j < height; j++)
        img.setRGB(i,j,(red[i][j]<<16)|(green[i][j]<<8)|(blue[i][j]));
  }

  public Picture(int [][] gray) {
    width = gray.length;
    height = gray[0].length;
    img = new BufferedImage(width,height,
                                java.awt.image.BufferedImage.TYPE_INT_RGB);
    for (int i = 0 ; i < width; i++)
      for (int j = 0; j < height; j++)
        img.setRGB(i,j,(gray[i][j]<<16)|(gray[i][j]<<8)|(gray[i][j]));
  }

  public void draw() {
    ImageIcon icon = new ImageIcon(img);
    JLabel d = new JLabel(icon);
    JFrame frame = new JFrame();
    frame.getContentPane().add(new JPanel());
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setSize(width,height);
    frame.setContentPane(d);
    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension screenSize = tk.getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;

    frame.setLocation(rand.nextInt(screenWidth/2), rand.nextInt(screenHeight/2));
    frame.setVisible(true);
  }

  public void save(String filename) throws IOException {
    File file = new File(filename);
    ImageIO.write(img, "png", file);
  }

  public int[][] getAlpha() {
    int [][] alpha = new int[width][height];
    for (int i = 0 ; i < width; i++)
      for (int j = 0; j < height; j++)
        alpha[i][j] = (img.getRGB(i,j)>>24)&255;
    return alpha;
  }

  public int[][] getRed() {
    if (red != null)
      return red;
    red = new int[width][height];
    for (int i = 0 ; i < width; i++)
      for (int j = 0; j < height; j++)
        red[i][j] = (img.getRGB(i,j)>>16)&255;
    return red;
  }

  public int[][] getGreen() {
    if (green != null)
      return green;
    green = new int[width][height];
    for (int i = 0 ; i < width; i++)
      for (int j = 0; j < height; j++)
        green[i][j] = (img.getRGB(i,j)>>8)&255;
    return green;
  }

  public int[][] getBlue() {
    if (blue != null)
      return blue;
    blue = new int[width][height];
    for (int i = 0 ; i < width; i++)
      for (int j = 0; j < height; j++)
        blue[i][j] = img.getRGB(i,j)&255;
    return blue;
  }

  public int[][] getGray() {
    int [][] gray = new int[width][height];
    for (int i = 0 ; i < width; i++)
      for (int j = 0; j < height; j++)
        gray[i][j] = ((img.getRGB(i,j)&255)
                            + ((img.getRGB(i,j)>>8)&255)*10
                                  + ((img.getRGB(i,j)>>16)&255)*3 ) / 14;
    return gray;
  }
}
