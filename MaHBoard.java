 /*
  * Board.java
  * by Simon Kye (simonkye@bu.edu)
  * 3/30/2024
  *
  * Handles initiazliation of board such as populating the board with the proper
  * spaces. Also has general getter methods.
  */

public class MaHBoard implements BoardInterface {
    private GridInterface[][] board;

    public MaHBoard() {
        board = new GridInterface[Settings.DEFAULT_NUM_ROWS][Settings.DEFAULT_NUM_COLS];
        populateBoard();
    }

    public MaHBoard(int size) {
        board = new GridInterface[size][size];
        populateBoard();
    }

    private void populateBoard() {
        int rows = board.length;
        int cols = board[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double roll = Math.random();
                if (roll < Settings.INACCESSIBLE_SPACE_PROPORTION) {
                    board[i][j] = new MaHGrid(new InaccessibleSpace());
                } else if (roll < Settings.INACCESSIBLE_SPACE_PROPORTION + Settings.MARKET_SPACE_PROPORTION) {
                    board[i][j] = new MaHGrid(new MarketSpace());
                } else {
                    board[i][j] = new MaHGrid(new CommonSpace());
                }
            }
        }
    }

    public GridInterface getGrid(int r, int c) {
        return board[r][c];
    }

    public int getRows() {
        return board.length;
    }

    public int getCols() {
        return board[0].length;
    }
}
