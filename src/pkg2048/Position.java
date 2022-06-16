package pkg2048;

/**
 *
 * @author Pham Nhat Quang
 */
public class Position {
     int rowNumber;
    int columnNumber;
    int data;
    boolean updatedData;

    /**
     *
     * @param i
     * @param j
     */
    public Position(int i, int j) {
        this.rowNumber = i;
        this.columnNumber = j;
    }

    /**
     *
     * @param i
     * @param j
     * @param data
     * @param updated
     */
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
