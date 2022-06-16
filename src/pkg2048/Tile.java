package pkg2048;

import java.awt.Color;

/**
 *
 * @author Pham Nhat Quang CE170036
 */
public class Tile {

    private int data;
    private boolean updated;
    private Color color;

    @Override
    public String toString() {
        return data + "";
    }

    /**
     *
     */
    public Tile() {
        this.data = (int) (Math.random() * (2 - 1 + 1) + 1) * 2;
        setColor();
    }

    /**
     *
     * @param data
     * @param updated
     */
    public Tile(int data, boolean updated) {
        this.data = data;
        this.setColor();
        this.updated = updated;
    }

    /**
     *
     * @return
     */
    public int getData() {
        return data;
    }

    /**
     *
     * @param data
     */
    public void setData(int data) {
        this.data = data;
        this.setColor();
    }

    /**
     *
     * @return
     */
    public boolean isUpdated() {
        return updated;
    }

    /**
     *
     * @param updated
     */
    public void setUpdated(boolean updated) {
        this.updated = updated;
    }

    /**
     *
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
     *
     * @return
     */
    public Color getColor() {
        return color;
    }
    
}
