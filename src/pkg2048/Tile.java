package pkg2048;

import java.awt.Color;

/**
 * Class that represents a single tile in the TileMap
 *
 * @author Pham Nhat Quang CE170036
 */
public class Tile {

    private int data;        //The number to be displayed
    private boolean updated;//Has this tile's data been updated
    private Color color;    //Color of this tile

    /**
     * toString method of Tile
     *
     * @return String representation
     */
    @Override
    public String toString() {
        return data + "";
    }

    /**
     * Create new Tile with data randomly chosen between 2 and 4
     */
    public Tile() {
        this.data = (int) (Math.random() * (2 - 1 + 1) + 1) * 2;
        setColor();
    }

    /**
     * Create new Tile with customized data and updated information
     *
     * @param data Data to set initial data
     * @param updated Updated status to set
     */
    public Tile(int data, boolean updated) {
        this.data = data;
        this.setColor();
        this.updated = updated;
    }

    /**
     * Get data of Tile
     *
     * @return data value of Tile
     */
    public int getData() {
        return data;
    }

    static boolean isPowerOfTwo(int n) {
        if (n == 0) {
            return false;
        }

        return (int) (Math.ceil((Math.log(n) / Math.log(2))))
                == (int) (Math.floor(((Math.log(n) / Math.log(2)))));
    }

    /**
     * Set data of Tile, and update the color
     *
     * @param data data value to set
     */
    public void setData(int data) {
        if (isPowerOfTwo(data)) {//Data can only be power of two
            this.data = data;
            this.setColor();
        }
    }

    /**
     * Check updated status of Tile
     *
     * @return updated status of Tile
     */
    public boolean isUpdated() {
        return updated;
    }

    /**
     * Set updated status of Tile
     *
     * @param updated updated status of Tile
     */
    public void setUpdated(boolean updated) {
        this.updated = updated;
    }

    /**
     * Set color of Tile based on data value
     */
    public void setColor() {
        switch (data) {
            case 2:
                this.color = Color.decode("#82c3ba");
                break;
            case 4:
                this.color = Color.decode("#8ef481");
                break;
            case 8:
                this.color = Color.decode("#d38b42");
                break;
            case 16:
                this.color = Color.decode("#e9db41");
                break;
            case 32:
                this.color = Color.decode("#3cbcaa");
                break;
            case 64:
                this.color = Color.decode("#efed92");
                break;
            case 128:
                this.color = Color.decode("#637bf2");
                break;
            case 256:
                this.color = Color.decode("#d82fde");
                break;
            case 512:
                this.color = Color.decode("#fc554c");
                break;
            case 1024:
                this.color = Color.decode("#f73b31");
                break;
            case 2048:
                this.color = Color.decode("#fc2c21");
                break;
        }
    }

    /**
     * Get color of Tile
     * @return color of Tile
     */
    public Color getColor() {
        return color;
    }

}
