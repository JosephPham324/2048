package pkg2048;

import DataSaving.SaveOpen;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import javax.swing.JPanel;

/**
 *
 * @author Pham Nhat Quang CE170036
 */
public class DrawingPanel extends JPanel {

    private int mapWidth;
    private final TileMap map;
    private Graphics2D g;
    private boolean gameOver;
    private Position[][] previousState;
    private Position[][] currentState;
    private Position[][] stateUndo;
    private boolean undoable;
    private MapCoordinates coordinates;
    private final DataSaving.SaveOpen information;

    /**
     * Reset the game, including making the Tiles Map clear with only 1 randomly
     * generated tile left and with score reset.
     */
    public void resetGame() {
//        try {
            this.map.setTiles(new Tile[4][4]);
            this.map.setScore(0);
            this.map.generateNewTile();
            repaint();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
        gameOver = false;
    }

    /**
     * Get the nearest integer number that is divisible by 80
     *
     * @param input Integer to get result from
     * @return The nearest integer that is divisible by 80
     */
    public int getNearest80Divisible(int input) {
        return (input / 8 / 10) * 80;
    }

    /**
     * Create new DrawingPanel
     *
     * @param map The TileMap to operate game on
     */
    public DrawingPanel(TileMap map) {
        this.map = map;
        this.coordinates = new MapCoordinates(0, new Coordinate(0, 0));
        this.information = new SaveOpen();
        information.getSavedInfo();
        this.map.setTiles(information.getInfo().getGameState());
        this.map.setScore(information.getInfo().getScore());
    }

    /**
     * Return the position matrix for current map status
     *
     * @return The position matrix
     */
    public Position[][] getMapPositions() {
        Position[][] positions = new Position[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (map.getTiles()[i][j] != null) {
                    positions[i][j] = new Position(i, j,
                            map.getTiles()[i][j].getData(),
                            map.getTiles()[i][j].isUpdated());
                } else {
                    positions[i][j] = null;
                }
            }
        }
        return positions;
    }

    /**
     * Perform left movement for the entire map How movement is performed:<br>
     * Tiles are moved individually from left to right, top to bottom<br>
     * Illustration:<br>
     * 1 : 2 : 3 : 4<br>
     * 5 : 6 : 7 : 8<br>
     * 9 : 10 : 11 : 12<br>
     * 13: 14 : 15 : 16<br>
     * Tiles moved: 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16
     */
    public void mapLeftMovement() {
        Position[][] positions;
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {

                positions = getMapPositions();
                individualLeftMovement(i, j, positions);
            }
        }
    }

    /**
     * Perform right movement for the entire map How movement is performed:<br>
     * Tiles are moved individually from right to left, top to bottom<br>
     * Illustration:<br>
     * 1 : 2 : 3 : 4<br>
     * 5 : 6 : 7 : 8<br>
     * 9 : 10 : 11 : 12<br>
     * 13: 14 : 15 : 16<br>
     * Tiles moved: 4, 3, 2, 1, 8, 7, 6, 5, 12, 11, 10, 9, 16, 15, 14, 13
     */
    public void mapRightMovement() {
        System.out.println("Map right movement");
        Position[][] positions;
        for (int j = 3; j >= 0; j--) {
            for (int i = 0; i < 4; i++) {

                positions = getMapPositions();
                individualRightMovement(i, j, positions);
            }
        }
    }

    /**
     * Perform up movement for the entire map How movement is performed:<br>
     * Tiles are moved individually from left to right, top to bottom<br>
     * Illustration:<br>
     * 1 : 2 : 3 : 4<br>
     * 5 : 6 : 7 : 8<br>
     * 9 : 10 : 11 : 12<br>
     * 13: 14 : 15 : 16<br>
     * Tiles moved: 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16
     */
    public void mapUpMovment() {
        Position[][] positions;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                positions = getMapPositions();
                individualUpMovement(i, j, positions);
            }
        }
    }

    /**
     * Perform down movement for the entire map <br>
     * How movement is performed:<br>
     * Tiles are moved individually from left to right,bottom to top<br>
     * Illustration:<br>
     * 1 : 2 : 3 : 4<br>
     * 5 : 6 : 7 : 8<br>
     * 9 : 10 : 11 : 12<br>
     * 13: 14 : 15 : 16<br>
     * Tiles moved: 13,14,15,16,9,10,11,12,5,6,7,8,1,2,3,4
     *
     */
    public void MapDownMovement() {
        Position[][] positions;
        for (int i = 3; i >= 0; i--) {
            for (int j = 0; j < 4; j++) {
                positions = getMapPositions();
                individualDownMovement(i, j, positions);
            }
        }
    }

    /**
     * Update the state of game after movement of 1 tile
     * @param oldPosition old position of the tile
     * @param newPosition new position of the tile
     * @param mapPositions Current map positions
     * @param updateScore Whether the score will be updated or not
     */
    public void updateGameState(Position oldPosition, Position newPosition, Position[][] mapPositions, boolean updateScore) {
        //Update positions in the map
        mapPositions[oldPosition.getRowNumber()][oldPosition.getColumnNumber()] = newPosition;
        
        if (updateScore) {//If it's a score update movement (merge two tiles)
            this.information.getInfo().increaseScore(newPosition.getData());
            this.map.increaseScore(newPosition.getData());//Increase the score
            mapPositions[newPosition.getRowNumber()][newPosition.getColumnNumber()] = null;//Delete the information stored in new position's position
        }
        //Update tiles according to updated map position
        updateTiles(mapPositions);
        //Repaint the game
        repaint();
    }

    /**
     *
     * @param oldPositionRow
     * @param oldPositionColumn
     * @param mapPositions
     */
    public void individualLeftMovement(int oldPositionRow, int oldPositionColumn, Position[][] mapPositions) {
        Position oldPosition = mapPositions[oldPositionRow][oldPositionColumn];
        Position newPosition;

        if (oldPosition == null) {
            return;
        }

        if (oldPosition.getColumnNumber() != 0) {
            for (int j = oldPositionColumn - 1; j >= 0; j--) {

                if (!isMovementBlocked(TileMap.Movement.LEFT, oldPosition, mapPositions[oldPosition.getRowNumber()][j], mapPositions)) {

                    if (mapPositions[oldPosition.getRowNumber()][j] == null) {
                        newPosition = new Position(oldPositionRow, j, oldPosition.getData(), false);
                        updateGameState(oldPosition, newPosition, mapPositions, false);

                    } else if ((mapPositions[oldPosition.getRowNumber()][j].getData() == oldPosition.getData() && mapPositions[oldPosition.getRowNumber()][j].isUpdatedData() == false)) {
                        newPosition = new Position(oldPositionRow, j, oldPosition.getData() * 2, true);
                        updateGameState(oldPosition, newPosition, mapPositions, true);
                    }
                }
            }
        }
    }

    /**
     *
     * @param oldPositionRow
     * @param oldPositionColumn
     * @param mapPositions
     */
    public void individualRightMovement(int oldPositionRow, int oldPositionColumn, Position[][] mapPositions) {
        Position oldPosition = mapPositions[oldPositionRow][oldPositionColumn];
        Position newPosition;

        if (oldPosition == null) {
            return;
        }

        if (oldPosition.getColumnNumber() != 3) {
            for (int j = oldPositionColumn + 1; j < 4; j++) {

                if (!isMovementBlocked(TileMap.Movement.RIGHT, oldPosition, mapPositions[oldPosition.getRowNumber()][j], mapPositions)) {

                    if (mapPositions[oldPosition.getRowNumber()][j] == null) {
                        newPosition = new Position(oldPositionRow, j, oldPosition.getData(), false);
                        updateGameState(oldPosition, newPosition, mapPositions, false);

                    } else if ((mapPositions[oldPosition.getRowNumber()][j].getData() == oldPosition.getData() && mapPositions[oldPosition.getRowNumber()][j].isUpdatedData() == false)) {
                        newPosition = new Position(oldPositionRow, j, oldPosition.getData() * 2, true);
                        updateGameState(oldPosition, newPosition, mapPositions, true);
                    }
                }
            }
        }

    }

    /**
     *
     * @param oldPositionRow
     * @param oldPositionColumn
     * @param mapPositions
     */
    public void individualUpMovement(int oldPositionRow, int oldPositionColumn, Position[][] mapPositions) {
        Position oldPosition = mapPositions[oldPositionRow][oldPositionColumn];
        Position newPosition;

        if (oldPosition == null) {
            return;
        }

        if (oldPosition.getRowNumber() != 0) {
            for (int i = oldPositionRow - 1; i >= 0; i--) {

                if (!isMovementBlocked(TileMap.Movement.UP, oldPosition, mapPositions[i][oldPosition.getColumnNumber()], mapPositions)) {
                    if (mapPositions[i][oldPosition.getColumnNumber()] == null) {
                        newPosition = new Position(i, oldPositionColumn, oldPosition.getData(), false);
                        updateGameState(oldPosition, newPosition, mapPositions, false);

                    } else if ((mapPositions[i][oldPosition.getColumnNumber()].getData() == oldPosition.getData() && mapPositions[i][oldPosition.getColumnNumber()].isUpdatedData() == false)) {
                        newPosition = new Position(i, oldPositionColumn, oldPosition.getData() * 2, true);
                        updateGameState(oldPosition, newPosition, mapPositions, true);
                    }
                }
            }
        }
    }

    /**
     *
     * @param oldPositionRow
     * @param oldPositionColumn
     * @param mapPositions
     */
    public void individualDownMovement(int oldPositionRow, int oldPositionColumn, Position[][] mapPositions) {
        Position oldPosition = mapPositions[oldPositionRow][oldPositionColumn];
        Position newPosition;

        if (oldPosition == null) {
            return;
        }

        if (oldPosition.getRowNumber() != 3) {
            for (int i = oldPositionRow + 1; i < 4; i++) {

                if (!isMovementBlocked(TileMap.Movement.DOWN, oldPosition, mapPositions[i][oldPosition.getColumnNumber()], mapPositions)) {

                    if (mapPositions[i][oldPosition.getColumnNumber()] == null) {
                        newPosition = new Position(i, oldPositionColumn, oldPosition.getData(), false);
                        updateGameState(oldPosition, newPosition, mapPositions, false);

                    } else if ((mapPositions[i][oldPosition.getColumnNumber()].getData() == oldPosition.getData() && mapPositions[i][oldPosition.getColumnNumber()].isUpdatedData() == false)) {
                        newPosition = new Position(i, oldPositionColumn, oldPosition.getData() * 2, true);
                        updateGameState(oldPosition, newPosition, mapPositions, true);
                    }
                }
            }
        }
    }

    /**
     * Compare two map states
     * @param stateOne First state
     * @param stateTwo Second state
     * @return true if the two states are the same, false if not
     */
    public boolean compareState(Position[][] stateOne, Position[][] stateTwo) {
        return Arrays.deepEquals(stateOne, stateTwo);
    }

    /**
     * Update tile
     * @param mapPositions The positions and information of new tiles state
     */
    public void updateTiles(Position[][] mapPositions) {
        //Set all current tiles to null
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                map.getTiles()[i][j] = null;
            }
        }
        
        //Update tiles information according to mapPositions
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (mapPositions[i][j] != null) {
                    map.getTiles()[mapPositions[i][j].getRowNumber()][mapPositions[i][j].getColumnNumber()] = new Tile(mapPositions[i][j].getData(), mapPositions[i][j].isUpdatedData());
                }
            }
        }
    }

    /**
     *
     * @param movement
     */
    public void processMovement(TileMap.Movement movement) {
        //Get state before movement
        this.previousState = getMapPositions();

        //Perform movement
        switch (movement) {
            case LEFT:
                mapLeftMovement();
                break;
            case RIGHT:
                mapRightMovement();
                break;
            case UP:
                mapUpMovment();
                break;
            case DOWN:
                MapDownMovement();
                break;
        }

        this.currentState = getMapPositions();//Get state after movement

        //Compare two states
        if (!compareState(currentState, previousState)) {//If states are not same (movement performed)
            undoable = true; //Can undo
            stateUndo = previousState; //Set state to undo
            map.generateNewTile(); //Generate new tile
        }
        gameOver = isGameOver(); //Check if game is over

        //Reset states to unupdated
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (map.getTiles()[i][j] != null) {
                    map.getTiles()[i][j].setUpdated(false);
                }
            }
        }
    }

    /**
     * Tries to undo a previous movement
     */
    public void Undo() {
        if (undoable) {//If it's undoable
            updateTiles(this.stateUndo);//Update tiles to previous state stored
            undoable = false;//Set undoable to false (Can't undo twice in a row)
            repaint(); //Repaint game
        }
    }

    /**
     * Check if it's possible to perform movement or not
     *
     * @param movement Type of movement
     * @param startingPosition Starting position of movement
     * @param destination Destination position
     * @param positions Positions of all tiles before the movement, to check
     * @return true if blocked, false if not
     */
    public boolean isMovementBlocked(TileMap.Movement movement, Position startingPosition, Position destination, Position[][] positions) {
        if (destination == null) {//Destination is null, there is no movement to perform
            return false;
        }
        switch (movement) {//According to type of movement
            case LEFT:
                //Check from the next right tile of the destination to the next left tile of starting position 
                for (int j = destination.getColumnNumber() + 1; j < startingPosition.getColumnNumber(); j++) {
                    if (positions[startingPosition.getRowNumber()][j] != null) {//If a tile in the way is occupied, it's blocked
                        return true;
                    }
                }
                //If nothing blocks, return false
                return false;
            case RIGHT:
                //Check from the next left tile of destination to next right tile of starting position
                for (int j = destination.getColumnNumber() - 1; j > startingPosition.getColumnNumber(); j--) {
                    if (positions[startingPosition.getRowNumber()][j] != null) {//If a tile in the way is occupied, it's blocked
                        return true;
                    }
                }
                //If nothing blocks, return false
                return false;
            case UP:
                //Check from the next lower tile of destination to the next upper tile of starting position
                for (int i = destination.getRowNumber() + 1; i < startingPosition.getRowNumber(); i++) {
                    if (positions[i][startingPosition.getColumnNumber()] != null) {//If a tile in the way is occupied, it's blocked
                        return true;
                    }
                }
                //If nothing blocks, return true
                return false;
            case DOWN:
                //Check from the next upper tile of destination to next lower tile of starting position
                for (int i = destination.getRowNumber() - 1; i > startingPosition.getRowNumber(); i--) {
                    if (positions[i][startingPosition.getColumnNumber()] != null) {//If a tile in the way is occupied, it's blocked
                        return true;
                    }
                }
                //If nothing blocks, return false
                return false;
        }

        return false;
    }

    /**
     * This method centers a <code>String</code> in a bounding
     * <code>Rectangle</code>.
     *
     * @param g - The <code>Graphics</code> instance.
     * @param r - The bounding <code>Rectangle</code>.
     * @param s - The <code>String</code> to center in the bounding rectangle.
     * @param font - The display font of the <code>String</code>
     *
     * @see java.awt.Graphics
     * @see java.awt.Rectangle
     * @see java.lang.String
     */
    public void centerString(Graphics g, Rectangle r, String s,
            Font font) {
        FontRenderContext frc
                = new FontRenderContext(null, true, true);

        Rectangle2D r2D = font.getStringBounds(s, frc);
        int rWidth = (int) Math.round(r2D.getWidth());
        int rHeight = (int) Math.round(r2D.getHeight());
        int rX = (int) Math.round(r2D.getX());
        int rY = (int) Math.round(r2D.getY());

        int a = (r.width / 2) - (rWidth / 2) - rX;
        int b = (r.height / 2) - (rHeight / 2) - rY;

        g.setFont(font);
        g.drawString(s, r.x + a, r.y + b);
    }

    @Override
    public void paint(Graphics graphics) {

        super.paint(graphics);
        g = (Graphics2D) graphics;
        //Get current map width
        this.mapWidth = mapWidth = getNearest80Divisible(Math.min(this.getHeight(), this.getWidth()) * 5 / 6);
        //Get current tiles coordinates in the map
        this.coordinates = new MapCoordinates(mapWidth, new Coordinate(this.getWidth() / 2 - mapWidth / 2, this.getHeight() / 2 - mapWidth / 2));

        //Draw map border
        drawMapBorder();

        //Draw background tiles
        drawBackgroundTiles();

        //Draw tiles
        drawTiles();

    }

    /**
     * Draw the empty background tiles (all 16 tiles)
     */
    public void drawBackgroundTiles() {

        int width = mapWidth / 4;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //Get current tile coordinate
                Coordinate current = this.coordinates.getTileCoordinates()[i][j];
                int x = current.getX();
                int y = current.getY();

                //Draw outline
                g.setColor(Color.white);
                g.drawRoundRect(x, y, width, width, width / 8, width / 8);
            }
        }

    }

    /**
     * Draw border of TileMap, the border will contain all tiles that displayed
     * for the game.
     *
     */
    public void drawMapBorder() {
        this.setBackground(Color.white);
        g.setColor(Color.decode("#CCC0B3"));
        g.fillRoundRect(this.getWidth() / 2 - mapWidth / 2 - 1, this.getHeight() / 2 - mapWidth / 2 - 1, mapWidth + 3, mapWidth + 3, 3, 3);
        g.setColor(Color.blue);
        g.drawRoundRect(this.getWidth() / 2 - mapWidth / 2 - 1, this.getHeight() / 2 - mapWidth / 2 - 1, mapWidth + 3, mapWidth + 3, 3, 3);
    }

    /**
     * Draw the current tiles in the map
     */
    public void drawTiles() {
        int width = mapWidth / 4;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (map.getTiles()[i][j] != null) {
                    //Get current tile coordinate
                    Coordinate current = this.coordinates.getTileCoordinates()[i][j];
                    int x = current.getX();
                    int y = current.getY();

                    //Fill the inside square
                    g.setColor(map.getTiles()[i][j].getColor());
                    g.fillRoundRect(x, y, width, width, width / 8, width / 8);
                    //Draw the outline
                    g.setColor(Color.white);
                    g.drawRoundRect(x, y, width, width, width / 8, width / 8);
                    //Draw the content (square value)
                    centerString(g, new Rectangle(x, y, width, width), map.getTiles()[i][j].getData() + "", new Font("Sefif", Font.BOLD, 60));;
                }
            }
        }
    }

    //---------------------------------------------------\\
    //----------------NOT USABLE PART--------------------\\
    //---------------------------------------------------\\
    /**
     * Draw tiles of map
     *
     * @param previousState
     * @param currentState
     */
    public void drawTiles2(Position[][] previousState, Position[][] currentState) {
        this.coordinates = new MapCoordinates(mapWidth, new Coordinate(this.getWidth() / 2 - mapWidth / 2, this.getHeight() / 2 - mapWidth / 2));
        int width = mapWidth / 4;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (map.getTiles()[i][j] != null) {
                    Coordinate current = this.coordinates.getTileCoordinates()[i][j];
                    int x = current.getX();
                    int y = current.getY();
                    animateTile(currentState[i][j], currentState[i][j], width);
//                  g.setColor(map.getTiles()[i][j].getColor());
//                  g.fillRect(x, y, width, width);
//                  g.setColor(Color.black);
//                  g.drawRect(x, y, width, width);
//                  centerString(g, new Rectangle(x, y, width, width), map.getTiles()[i][j].getData() + "", new Font("Arial", Font.PLAIN, 24));;
                }
            }
        }
    }

    /**
     *
     * @param previousPosition
     * @param currentPosition
     * @param tileWidth
     */
    public void animateTile(Position previousPosition, Position currentPosition, int tileWidth) {
        Coordinate previous = this.coordinates.getTileCoordinates()[previousPosition.getRowNumber()][previousPosition.getColumnNumber()];
        Coordinate current = this.coordinates.getTileCoordinates()[currentPosition.getRowNumber()][currentPosition.getColumnNumber()];
        if (previousPosition.getRowNumber() == currentPosition.getRowNumber() && previousPosition.getRowNumber() == currentPosition.getRowNumber()) {
            g.setColor(new Tile(currentPosition.getData(), false).getColor());
            g.fillRect(current.getX(), current.getY(), tileWidth, tileWidth);
            g.setColor(Color.black);
            g.drawRect(current.getX(), current.getY(), tileWidth, tileWidth);
            centerString(g, new Rectangle(current.getX(), current.getY(), tileWidth, tileWidth), currentPosition.getData() + "", new Font("Sefif", Font.BOLD, 40));;
        }
    }

    /**
     *
     * @param previousPosition
     * @param currentPosition
     */
    public void slideLeft(Position previousPosition, Position currentPosition) {

    }
    //---------------------------------------------------\\

    /**
     * Check if game is over
     *
     * @return game over status
     */
    public boolean isGameOver() {
        return map.isGameOver();
    }

    public SaveOpen getInformation() {
        return information;
    }

    public Tile[][]getGameState(){
        return this.map.getTiles();
    }
    
}
