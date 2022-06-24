package pkg2048;

/**
 * Class to store position of a tile in 
 * @author Pham Nhat Quang
 */
public class Position {
    private int rowNumber;
    private int getCollumnNumber;
    private int data;
    private boolean updatedData;

    /**
     *
     * @param rowNumber
     * @param columnNumber
     */
    public Position(int rowNumber, int columnNumber) {
        this.rowNumber = rowNumber;
        this.getCollumnNumber = columnNumber;
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
        this.getCollumnNumber = columnNumber;
        this.data = data;
        this.updatedData = updated;
    }
    
    @Override
    public String toString() {
        return "Position{" + "i=" + rowNumber + ", j=" + getCollumnNumber + ", data=" + data + ", updated=" + updatedData + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.rowNumber;
        hash = 19 * hash + this.getCollumnNumber;
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
        if (this.getCollumnNumber != other.getCollumnNumber) {
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
        return getCollumnNumber;
    }

    public int getData() {
        return data;
    }

    public boolean isUpdatedData() {
        return updatedData;
    }
    
    
    
}
