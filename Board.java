 /*
  * Board.java
  * by Simon Kye (simonkye@bu.edu)
  * 3/30/2024
  *
  * Handles initiazliation of board such as populating the board with the proper
  * spaces. Also has general getter methods.
  */

public class Board {
    private Grid[][] board;

    public Board() {
        board = new Grid[Settings.DEFAULT_NUM_ROWS][Settings.DEFAULT_NUM_COLS];
        populateBoard();
    }

    public Board(int size) {
        board = new Grid[size][size];
        populateBoard();
    }

    private void populateBoard() {
        int rows = board.length;
        int cols = board[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double roll = Math.random();
                if (roll < Settings.INACCESSIBLE_SPACE_PROPORTION) {
                    board[i][j] = new Grid(new InaccessibleSpace());
                } else if (roll < Settings.INACCESSIBLE_SPACE_PROPORTION + Settings.MARKET_SPACE_PROPORTION) {
                    board[i][j] = new Grid(new MarketSpace());
                } else {
                    board[i][j] = new Grid(new CommonSpace());
                }
            }
        }
    }

    public Grid getGrid(int r, int c) {
        return board[r][c];
    }

    public int getRows() {
        return board.length;
    }

    public int getCols() {
        return board[0].length;
    }
}
