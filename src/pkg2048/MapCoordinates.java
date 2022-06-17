package pkg2048;

/**
 *
 * @author Pham Nhat Quang CE170036
 */
public class MapCoordinates {
    private int mapSize;
    private Coordinate[][] tileCoordinates;

    public MapCoordinates(int mapSize) {
        this.mapSize = mapSize;
        this.setTileCoordinates(mapSize);
    }
    
    private void setTileCoordinates(int mapSize){
        
    }

    public Coordinate[][] getTileCoordinates() {
        return tileCoordinates;
    }
    
    
}
