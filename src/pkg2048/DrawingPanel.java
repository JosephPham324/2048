package pkg2048;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author Pham Nhat Quang CE170036
 */
public class DrawingPanel extends JPanel {

    private int mapWidth;
    TileMap map;
    Tile[][] tiles;
    Graphics2D g;
    boolean gameOver;
    Position[][] previousState;
    Position[][] currentState;
    Position[][] stateUndo;
    boolean undoable;
    MapCoordinates coordinates;

    /**
     *
     */
    public void resetGame() {
        try {
            this.map = new TileMap(this.map.getMapWidth());
            this.map.generateNewTile();
            this.map.setScore(0);
            repaint();
        } catch (Exception e) {

        }
        gameOver = false;
    }

    /**
     *
     * @param input
     * @return
     */
    public int getNearest10_8(int input) {
        return (input / 8 / 10) * 8 * 10;
    }

    /**
     *
     * @param screenWidth
     * @param map
     */
    public DrawingPanel(int screenWidth, TileMap map) {
        this.mapWidth = screenWidth;
        this.setVisible(true);
        this.setPreferredSize(new Dimension(screenWidth, screenWidth));
        this.map = map;
        this.tiles = map.getTiles();
        this.coordinates = new MapCoordinates(screenWidth, new Coordinate(0, 0));
    }

    /**
     *
     * @return
     */
    public Position[][] getMapPositions() {
        Position[][] positions = new Position[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (map.getTiles()[i][j] != null) {
                    positions[i][j] = new Position(i, j);
                    positions[i][j].updatedData = map.getTiles()[i][j].isUpdated();
                    positions[i][j].data = map.getTiles()[i][j].getData();
                } else {
                    positions[i][j] = null;
                }
            }
        }
        return positions;
    }

    /**
     *
     */
    public void mapLeftMovement() {
        Position[][] positions;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                positions = getMapPositions();
                individualLeftMovement(i, j, positions);
            }
        }
    }

    /**
     *
     */
    public void mapRightMovement() {
        System.out.println("Map right movement");
        Position[][] positions;
        for (int i = 0; i < 4; i++) {
            for (int j = 3; j >= 0; j--) {
                positions = getMapPositions();
                individualRightMovement(i, j, positions);
            }
        }
    }

    /**
     *
     */
    public void mapUpMovment() {
        Position[][] positions;
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                positions = getMapPositions();
                individualUpMovement(i, j, positions);
            }
        }
    }

    /**
     *
     */
    public void MapDownMovement() {
        Position[][] positions;
        for (int j = 0; j < 4; j++) {
            for (int i = 3; i >= 0; i--) {
                positions = getMapPositions();
                individualDownMovement(i, j, positions);
            }
        }
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
        if (oldPosition.columnNumber != 0) {
            for (int j = oldPositionColumn - 1; j >= 0; j--) {
                if (!isBlocked(TileMap.Movement.LEFT, oldPosition, mapPositions[oldPosition.rowNumber][j], mapPositions)) {
                    if (mapPositions[oldPosition.rowNumber][j] == null) {
                        newPosition = new Position(oldPositionRow, j, oldPosition.data, false);
                        updateGameState(oldPosition, newPosition, mapPositions, false);

                    } else if ((mapPositions[oldPosition.rowNumber][j].data == oldPosition.data && mapPositions[oldPosition.rowNumber][j].updatedData == false)) {
                        newPosition = new Position(oldPositionRow, j, oldPosition.data * 2, true);
                        updateGameState(oldPosition, newPosition, mapPositions, true);
                    }
                }
            }
        }
    }

    /**
     *
     * @param oldPosition
     * @param newPosition
     * @param mapPositions
     * @param updateScore
     */
    public void updateGameState(Position oldPosition, Position newPosition, Position[][] mapPositions, boolean updateScore) {
        mapPositions[oldPosition.rowNumber][oldPosition.columnNumber] = newPosition;
        if (updateScore) {
            this.map.increaseScore(newPosition.data);
            mapPositions[newPosition.rowNumber][newPosition.columnNumber] = null;
        }
        updateTiles(mapPositions);
        repaint();
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
        boolean updateData;
        if (oldPosition == null) {
            return;
        }
        if (oldPosition.columnNumber != 3) {
            for (int j = oldPositionColumn + 1; j < 4; j++) {
                if (!isBlocked(TileMap.Movement.RIGHT, oldPosition, mapPositions[oldPosition.rowNumber][j], mapPositions)) {
                    if (mapPositions[oldPosition.rowNumber][j] == null) {
                        updateData = false;
                        newPosition = new Position(oldPositionRow, j, oldPosition.data, updateData);
                        updateGameState(oldPosition, newPosition, mapPositions, updateData);
                    } else if ((mapPositions[oldPosition.rowNumber][j].data == oldPosition.data && mapPositions[oldPosition.rowNumber][j].updatedData == false)) {
                        updateData = true;
                        newPosition = new Position(oldPositionRow, j, oldPosition.data * 2, updateData);
                        updateGameState(oldPosition, newPosition, mapPositions, updateData);
                    }
                } else {
                    System.out.println("Is blocked");
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
        boolean updateData;
        if (oldPosition == null) {
            return;
        }
        if (oldPosition.rowNumber != 0) {
            for (int i = oldPositionRow - 1; i >= 0; i--) {
                if (!isBlocked(TileMap.Movement.UP, oldPosition, mapPositions[i][oldPosition.columnNumber], mapPositions)) {
                    if (mapPositions[i][oldPosition.columnNumber] == null) {
                        updateData = false;
                        newPosition = new Position(i, oldPositionColumn, oldPosition.data, updateData);
                        updateGameState(oldPosition, newPosition, mapPositions, updateData);
                    } else if ((mapPositions[i][oldPosition.columnNumber].data == oldPosition.data && mapPositions[i][oldPosition.columnNumber].updatedData == false)) {
                        updateData = true;
                        newPosition = new Position(i, oldPositionColumn, oldPosition.data * 2, updateData);
                        updateGameState(oldPosition, newPosition, mapPositions, updateData);
                    }
                } else {
                    System.out.println("Is blocked");
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
        boolean updateData;
        if (oldPosition == null) {
            return;
        }
        if (oldPosition.rowNumber != 3) {
            for (int i = oldPositionRow + 1; i < 4; i++) {
                if (!isBlocked(TileMap.Movement.DOWN, oldPosition, mapPositions[i][oldPosition.columnNumber], mapPositions)) {
                    if (mapPositions[i][oldPosition.columnNumber] == null) {
                        updateData = false;
                        newPosition = new Position(i, oldPositionColumn, oldPosition.data, updateData);
                        updateGameState(oldPosition, newPosition, mapPositions, updateData);
                    } else if ((mapPositions[i][oldPosition.columnNumber].data == oldPosition.data && mapPositions[i][oldPosition.columnNumber].updatedData == false)) {
                        updateData = true;
                        newPosition = new Position(i, oldPositionColumn, oldPosition.data * 2, updateData);
                        updateGameState(oldPosition, newPosition, mapPositions, updateData);
                    }
                }
            }
        }
    }

    /**
     *
     * @param stateOne
     * @param stateTwo
     * @return
     */
    public boolean compareState(Position[][] stateOne, Position[][] stateTwo) {
        return Arrays.deepEquals(stateOne, stateTwo);
        //return true;
    }

    /**
     *
     * @param mapPositions
     */
    public void updateTiles(Position[][] mapPositions) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                map.getTiles()[i][j] = null;
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (mapPositions[i][j] != null) {
                    map.getTiles()[mapPositions[i][j].rowNumber][mapPositions[i][j].columnNumber] = new Tile(mapPositions[i][j].data, mapPositions[i][j].updatedData);
                }
            }
        }
    }

    /**
     *
     * @param movement
     */
    public void processMovement(TileMap.Movement movement) {
        this.previousState = getMapPositions();
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
        this.currentState = getMapPositions();
        if (!compareState(currentState, previousState)) {
            undoable = true;
            stateUndo = previousState;
            while (!map.generateNewTile());
        }
        gameOver = isGameOver();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (map.getTiles()[i][j] != null) {
                    map.getTiles()[i][j].setUpdated(false);
                }
            }
        }
    }

    /**
     *
     */
    public void Undo() {
        if (undoable) {
            updateTiles(this.stateUndo);
            undoable = false;
            repaint();
        }
    }

    /**
     *
     * @param movement
     * @param startingPosition
     * @param destination
     * @param positions
     * @return
     */
    public boolean isBlocked(TileMap.Movement movement, Position startingPosition, Position destination, Position[][] positions) {
        switch (movement) {
            case LEFT:
                if (destination == null) {
                    return false;
                }
                for (int j = destination.columnNumber + 1; j < startingPosition.columnNumber; j++) {
                    if (positions[startingPosition.rowNumber][j] != null) {
                        System.out.println("Is blocked");
                        return true;
                    }
                }
                return false;
            case RIGHT:
                if (destination == null) {
                    return false;
                }
                for (int j = destination.columnNumber - 1; j > startingPosition.columnNumber; j--) {
                    if (positions[startingPosition.rowNumber][j] != null) {
                        return true;
                    }
                }
                return false;
            case UP:
                if (destination == null) {
                    return false;
                }
                for (int i = destination.rowNumber + 1; i < startingPosition.rowNumber; i++) {
                    if (positions[i][startingPosition.columnNumber] != null) {
                        return true;
                    }
                }
                return false;
            case DOWN:
                if (destination == null) {
                    return false;
                }
                for (int i = destination.rowNumber - 1; i > startingPosition.rowNumber; i--) {
                    if (positions[i][startingPosition.columnNumber] != null) {
                        return true;
                    }
                }
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
        this.mapWidth = mapWidth = getNearest10_8(this.getHeight() * 3 / 4);
        drawMapBorder();
        drawTiles();
    }

    /**
     *
     */
    public void drawMapBorder() {
        this.setBackground(Color.white);
        g.setColor(Color.decode("#efd671"));
        g.fillRect(this.getWidth() / 2 - mapWidth / 2, this.getHeight() / 2 - mapWidth / 2, mapWidth, mapWidth);
        g.setColor(Color.blue);
        g.drawRect(this.getWidth() / 2 - mapWidth / 2, this.getHeight() / 2 - mapWidth / 2, mapWidth, mapWidth);

    }

    /**
     *
     */
    public void drawTiles() {
        this.coordinates = new MapCoordinates(mapWidth, new Coordinate(this.getWidth() / 2 - mapWidth / 2, this.getHeight() / 2 - mapWidth / 2));
        int width = mapWidth / 4;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (map.getTiles()[i][j] != null) {
                    Coordinate current = this.coordinates.getTileCoordinates()[i][j];
                    int x = current.getX();
                    int y = current.getY();
                    g.setColor(map.getTiles()[i][j].getColor());
                    g.fillRect(x, y, width, width);
                    g.setColor(Color.black);
                    g.drawRect(x, y, width, width);
                    centerString(g, new Rectangle(x, y, width, width), map.getTiles()[i][j].getData() + "", new Font("Arial", Font.PLAIN, 24));;
                }
            }
        }
    }
    
    
    /**
     * Draw tiles of map
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
                    animateTile(currentState[i][j],currentState[i][j],width);
//                  g.setColor(map.getTiles()[i][j].getColor());
//                  g.fillRect(x, y, width, width);
//                  g.setColor(Color.black);
//                  g.drawRect(x, y, width, width);
//                  centerString(g, new Rectangle(x, y, width, width), map.getTiles()[i][j].getData() + "", new Font("Arial", Font.PLAIN, 24));;
                }
            }
        }
    }
    public void animateTile(Position previousPosition, Position currentPosition, int tileWidth){
        Coordinate previous = this.coordinates.getTileCoordinates()[previousPosition.rowNumber][previousPosition.columnNumber];
        Coordinate current = this.coordinates.getTileCoordinates()[currentPosition.rowNumber][currentPosition.columnNumber];
        if (previousPosition.rowNumber == currentPosition.rowNumber && previousPosition.rowNumber == currentPosition.rowNumber){
            g.setColor(new Tile(currentPosition.data, false).getColor());
                    g.fillRect(current.getX(), current.getY(), tileWidth, tileWidth);
                    g.setColor(Color.black);
                    g.drawRect(current.getX(), current.getY(), tileWidth, tileWidth);
                    centerString(g, new Rectangle(current.getX(), current.getY(), tileWidth, tileWidth), currentPosition.data + "", new Font("Arial", Font.PLAIN, 24));;
        }
    }
    
    public void slideLeft(Position previousPosition, Position currentPosition){
        
    }

    /**
     *
     * @return
     */
    public boolean isGameOver() {
        return map.isGameOver();
    }

}
