package pkg2048;

/**
 * Class to store position of a tile in
 *
 * @author Pham Nhat Quang
 */
public class Position {

    private int rowNumber;
    private int columnNumber;
    private int data;
    private boolean updatedData;
    public static final int SAME = 0;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int UP = 3;
    public static final int DOWN = 4;

    /**
     *
     * @param rowNumber
     * @param columnNumber
     */
    public Position(int rowNumber, int columnNumber) {
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
    }

    /**
     *
     * @param rowNumber
     * @param columnNumber
     * @param data
     * @param updated
     */
    public Position(int rowNumber, int columnNumber, int data, boolean updated) {
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
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

    public int getRowNumber() {
        return rowNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public int getData() {
        return data;
    }

    public boolean isUpdatedData() {
        return updatedData;
    }

    
    public static int comparePosition(Position before, Position after) {
        if (before.getColumnNumber() == after.getColumnNumber()){
            if (before.getRowNumber()<after.getRowNumber()){
                return DOWN;
            } else if  (before.getRowNumber()>after.getRowNumber()){
                return UP;
            } else {
                return SAME;
            }
        }
        if (before.getRowNumber() == after.getRowNumber()){
            if (before.getColumnNumber()<after.getColumnNumber()){
                return RIGHT;
            } else if  (before.getColumnNumber()>after.getColumnNumber()){
                return LEFT;
            }
        }
        return SAME;
    }

}
