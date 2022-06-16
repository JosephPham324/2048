package pkg2048;

import java.util.ArrayList;
/**
 *
 * @author
 */
class Position {

    int rowNumber;
    int columnNumber;
    int data;
    boolean updatedData;

    public Position(int i, int j) {
        this.rowNumber = i;
        this.columnNumber = j;
    }

    public Position(int i, int j, int data, boolean updated) {
        this.rowNumber = i;
        this.columnNumber = j;
        this.data = data;
        this.updatedData = updated;
    }
    
    @Override
    public String toString() {
        return "Position{" + "i=" + rowNumber + ", j=" + columnNumber + ", data=" + data + ", updated=" + updatedData + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.rowNumber;
        hash = 19 * hash + this.columnNumber;
        hash = 19 * hash + this.data;
        hash = 19 * hash + (this.updatedData ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Position other = (Position) obj;
        if (this.rowNumber != other.rowNumber) {
            return false;
        }
        if (this.columnNumber != other.columnNumber) {
            return false;
        }
        if (this.data != other.data) {
            return false;
        }
        if (this.updatedData != other.updatedData) {
            return false;
        }
        return true;
    }
    
    
    
}

/**
 *
 * @author M S I
 */
public class TileMap {
    private int score;
    private int tileWidth;
    private int MapWidth;
    private Tile[][] tiles;

    /**
     *
     * @return
     */
    public Tile[][] getTiles() {
        return tiles;
    }

    /**
     *
     */
    public enum Movement {

        /**
         *
         */
        UP,

        /**
         *
         */
        DOWN,

        /**
         *
         */
        LEFT,

        /**
         *
         */
        RIGHT
    }

    /**
     *
     * @param MapWidth
     * @throws Exception
     */
    public TileMap(int MapWidth) throws Exception {
        this.setMapWidth(MapWidth);
        this.tileWidth = MapWidth / 8;
        this.tiles = new Tile[4][4];
    }

    /**
     *
     * @param MapWidth
     * @throws Exception
     */
    public void setMapWidth(int MapWidth) throws Exception {
        if (MapWidth % 4 == 0) {
            this.MapWidth = MapWidth;
        } else {
            throw new Exception("Error: Map Width must be divisible by 8!");
        }
    }

    /**
     *
     * @return
     */
    public boolean isMapFull() {
        boolean isFull = false;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tiles[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     *
     * @param position
     * @return
     */
    public boolean isTileOccupied(Position position) {
        try {
            return tiles[position.rowNumber][position.columnNumber] != null;
        } catch (ArrayIndexOutOfBoundsException oob) {
            return true;
        }
    }

    /**
     *
     * @return
     */
    public boolean generateNewTile() {
        int i = (int) (Math.random() * (3 - 0 + 1) + 0);
        int j = (int) (Math.random() * (3 - 0 + 1) + 0);
        if (!isTileOccupied(new Position(i, j))) {
            tiles[i][j] = new Tile();
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @return
     */
    public boolean isGameOver() {
        boolean gameover = false;
        if (!isMapFull()) {
            return false;
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tiles[i][j].getData() == 2048) {
                    return true;
                }
                if (j != 0) {
                    if (tiles[i][j].getData() == tiles[i][j - 1].getData())//Check left tile
                    {
                        return false;
                    }
                }
                if (j != 3) {
                    if (tiles[i][j].getData() == tiles[i][j + 1].getData())//Check right tile
                    {
                        return false;
                    }
                }
                if (i != 0) {
                    if (tiles[i][j].getData() == tiles[i - 1][j].getData())//Check upper tile
                    {
                        return false;
                    }
                }
                if (i != 3) {
                    if (tiles[i][j].getData() == tiles[i + 1][j].getData())//Check lower tile
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     *
     * @return
     */
    public boolean[][] getUpdatedStatus() {
        boolean result[][] = new boolean[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result[i][j] = tiles[i][j].isUpdated();
            }
        }
        return result;
    }

    /**
     *
     * @param table
     */
    public void printTable(boolean[][] table) {
        System.out.println("");
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                System.out.print("[" + table[i][j] + "]");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    /**
     *
     * @return
     */
    public boolean[][] getUpdateStatusTable() {
        boolean[][] result = new boolean[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tiles[i][j] != null) {
                    result[i][j] = tiles[i][j].isUpdated();
                }
            }
        }
        return result;
    }

    /**
     *
     * @param table
     */
    public void printTable(Tile[][] table) {
        System.out.println("");
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                System.out.print("[" + table[i][j] + "]");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    /**
     *
     * @return
     */
    public int getMapWidth() {
        return MapWidth;
    }

    /**
     *
     * @return
     */
    public int getTileWidth() {
        return tileWidth;
    }
    
    /**
     *
     * @param amount
     */
    public void increaseScore(int amount){
        this.score+=amount;
    }

    /**
     *
     * @param tiles
     */
    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

    
}
