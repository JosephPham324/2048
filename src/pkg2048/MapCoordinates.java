package pkg2048;

/**
 * 
 * @author Pham Nhat Quang CE170036
 */
public class MapCoordinates {
    private Coordinate startingOfMap;//The coordinate where the map starts being drawn
    private final int mapSize;
    private Coordinate[][] tileCoordinates;

    /**
     * Map coordinates constructor
     * @param mapSize
     * @param startingOfMap
     */
    public MapCoordinates(int mapSize, Coordinate startingOfMap) {
        this.mapSize = mapSize;
        this.startingOfMap = startingOfMap;
        this.setTileCoordinates();
    }
    
    /**
     * set tile coordinates
     */
    private void setTileCoordinates(){
        int tileSize = mapSize / 4;
        this.tileCoordinates = new Coordinate[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tileCoordinates[i][j] = new Coordinate(startingOfMap.getX() + tileSize * j,startingOfMap.getY() + tileSize *i);
            }
        }
    }

    /**
     * get tile coordinate
     * @return tileCoordinates
     */
    public Coordinate[][] getTileCoordinates() {
        return tileCoordinates;
    }
    
 
}
