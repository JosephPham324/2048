package pkg2048;

/**
 * Store a coordinate (in a 2-dimensional panel)
 * @author Pham Nhat Quang CE170036
 */
public class Coordinate {
    private int x; //x coordinate
    private int y; //y coordinate

    /**
     * Create new Coordinate
     * @param x X coordinate
     * @param y Y coordinate
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Set x coordinate
     * @param x value to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Set y coordinate
     * @param y value to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Get x coordinate
     * @return value of x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Get y coordinate
     * @return value of y coordinate
     */
    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Coordinate{" + "x=" + x + ", y=" + y + '}';
    }
    
    
}
