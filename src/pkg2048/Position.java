package pkg2048;

/**
 * Class to store position of a tile in
 *
 * @author Pham Nhat Quang
 */
public class Position {

    private int rowNumber;//The row of this position
    private int columnNumber;//The column of this position
    private int data;//Data of this position
    private boolean updatedData;//Is this position updated (two tiles fused)

    /**
     * Constructor only with position info and no value
     *
     * @param rowNumber Row of position
     * @param columnNumber Column of position
     */
    public Position(int rowNumber, int columnNumber) {
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
    }

    /**
     * Constructor with full parameters
     *
     * @param rowNumber  Row of position
     * @param columnNumber Column of position
     * @param data Data value of position
     * @param updated Is this position updated (have two tiles in 2 different positions
     * moved into this position)
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

    /**
     * get the row number
     *
     * @return rowNumber
     */
    public int getRowNumber() {
        return rowNumber;
    }

    /**
     * get the column number
     *
     * @return columnNumber
     */
    public int getColumnNumber() {
        return columnNumber;
    }

    /**
     * get data of tile
     *
     * @return data
     */
    public int getData() {
        return data;
    }

    /**
     * check if there is an update
     *
     * @return updatedData
     */
    public boolean isUpdatedData() {
        return updatedData;
    }

}
