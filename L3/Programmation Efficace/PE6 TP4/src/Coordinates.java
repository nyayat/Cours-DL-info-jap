public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates(Coordinates c) {
        this(c.x, c.y);
    }

    public void set(Coordinates c) {
        this.x = c.x;
        this.y = c.y;
    }

    public int distance(Coordinates c) {
        return (int) Math.ceil(Math.sqrt((x - c.x) * (x - c.x) + (y - c.y) * (y - c.y)));
    }
}
