public class LoVBoard implements BoardInterface {
    private GridInterface[][] board;

    public LoVBoard() {
        board = new GridInterface[Settings.DEFAULT_LEGENDS_OF_VALOR_SIZE][Settings.DEFAULT_LEGENDS_OF_VALOR_SIZE];
        populateBoard();
    }

    private void populateBoard() {
        for (int i = 0; i < board.length; i++) {
            board[i][2] = new LoVGrid(new InaccessibleSpace());
            board[i][5] = new LoVGrid(new InaccessibleSpace());
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (j == 2 || j == 5) {
                    continue;
                }
                double roll = Math.random();
                if (roll < Settings.PLAIN_SPACE_PROPORTION) {
                    board[i][j] = new LovGrid()
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
