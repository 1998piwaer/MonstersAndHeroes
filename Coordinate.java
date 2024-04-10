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

    public String toString() {
        return "(" + row + ", " + col + ")";
    }

    public void setRow(int r) {
        row = r;
    }

    public void setCol(int c) {
        col = c;
    }

    public static boolean checkIfAdjacent(Coordinate coord1, Coordinate coord2) {
        if (Math.abs(coord1.getRow() - coord2.getRow()) > 1 || Math.abs(coord1.getCol() - coord2.getCol()) > 1) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isInBounds(Coordinate coord, int rows, int cols) {
        if (coord.getRow() < 0 || coord.getCol() < 0 || coord.getRow() >= rows || coord.getCol() >= cols) {
            return false;
        } else {
            return true;
        }
    }
}
