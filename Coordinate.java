import java.util.Objects;

public class Coordinate {
    private int row;
    private int col;
    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Coordinate other = (Coordinate) obj;
        return row == other.row && col == other.col;
    }

    public int hashCode() {
        return Objects.hash(row, col);
    }

    public void setRow(int r) {
        row = r;
    }

    public void setCol(int c) {
        col = c;
    }
}
