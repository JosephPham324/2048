package pkg2048;

/**
 * Class for storing Tiles
 *
 * @author Pham Nhat Quang
 */
public class TileMap {

    private int score;
    private Tile[][] tiles;

    /**
     * Supported types of movement
     */
    public enum Movement {

        /**
         * Up movement
         */
        UP,
        /**
         * Down movement
         */
        DOWN,
        /**
         * Left movement
         */
        LEFT,
        /**
         * Right movement
         */
        RIGHT
    }

    /**
     * Default constructor with no parameters
     */
    public TileMap() {
        this.tiles = new Tile[4][4];
        this.score = 0;
    }

    /**
     * Get the Tiles in the map
     *
     * @return 2D array of Tiles in the map
     */
    public Tile[][] getTiles() {
        return tiles;
    }

    /**
     * Check if this map is full
     *
     * @return true if map is full, false if not
     */
    public boolean isMapFull() {

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
     * Check if a position in the map is occupied or not
     *
     * @param position Position to check
     * @return True if occupied by a Tile, false if not
     */
    public boolean isTileOccupied(Position position) {
        try {
            return tiles[position.getRowNumber()][position.getColumnNumber()] != null;
        } catch (ArrayIndexOutOfBoundsException oob) {
            return true;
        }
    }

    /**
     * Generate a new Tile in a random position in the map
     *
     */
    public void generateNewTile() {
        int i;
        int j;
        boolean finished;
        do {
            i = (int) (Math.random() * (3 - 0 + 1) + 0);
            j = (int) (Math.random() * (3 - 0 + 1) + 0);

        } while (isTileOccupied(new Position(i, j)));
        tiles[i][j] = new Tile();
    }

    /**
     * Check if game is over, game is over if the Map is full and there is no
     * legal move left, or there is a Tile with value 2048
     *
     * @return true if game is over, false if not
     */
    public boolean isGameOver() {
        if (!isMapFull()) {//If map not full, game is not over
            return false;
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tiles[i][j].getData() == 2048) {//Check if current tile is 2048
                    return true;
                }
                //Check if there is any valid move for this tile
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
        return true;//If there is no valid moves, game is over
    }

    //-------------------------------------------------------------------------
    //---------------------------CODE FOR DEBUGGING----------------------------
    /**
     * Print boolean 2d array
     *
     * @param table Array to print
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
     * Get updated status of tiles in map
     *
     * @return table representation of updated statuses (2d array)
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
     * Print Tile 2d array
     *
     * @param table Array to print
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
     * Increase the score of this map
     *
     * @param amount Amount to increase
     */
    public void increaseScore(int amount) {
        this.score += amount;
    }

    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Set tiles of this map
     *
     * @param tiles Tiles to set
     */
    public void setTiles(Tile[][] tiles) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tiles[i][j] == null || tiles[i][j].getData() != 0) {
                    this.tiles[i][j] = tiles[i][j];
                } else if (tiles[i][j].getData() == 0) {
                    tiles[i][j] = null;
                }
            }
        }
    }

    /**
     * Get current score
     *
     * @return current score
     */
    public int getScore() {
        return score;
    }

}
