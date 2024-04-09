import java.util.*;

public class LoVBoard implements Board {
    private LoVGrid[][] board;
    private Map<Integer, Coordinate> nexusCoordinates;

    public LoVBoard() {
        board = new LoVGrid[Settings.DEFAULT_LEGENDS_OF_VALOR_SIZE][Settings.DEFAULT_LEGENDS_OF_VALOR_SIZE];
        nexusCoordinates = new HashMap<>();
        populateBoard();
    }

    private void populateBoard() {
        for (int i = 0; i < board.length; i++) {
            board[i][2] = new LoVGrid(new InaccessibleSpace());
            board[i][5] = new LoVGrid(new InaccessibleSpace());
        }
        for (int i = 1; i < board.length - 1; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (j == 2 || j == 5) {
                    continue;
                }
                double roll = Math.random();
                if (roll < Settings.PLAIN_SPACE_PROPORTION) {
                    board[i][j] = new LoVGrid(new PlainSpace());
                } else if (roll < Settings.PLAIN_SPACE_PROPORTION + Settings.CAVE_SPACE_PROPORTION) {
                    board[i][j] = new LoVGrid(new CaveSpace());
                } else if (roll < Settings.PLAIN_SPACE_PROPORTION + Settings.CAVE_SPACE_PROPORTION + Settings.BUSH_SPACE_PROPORTION) {
                    board[i][j] = new LoVGrid(new BushSpace());
                } else if (roll < Settings.PLAIN_SPACE_PROPORTION + Settings.CAVE_SPACE_PROPORTION + Settings.BUSH_SPACE_PROPORTION + Settings.KOULOU_SPACE_PROPORTION) {
                    board[i][j] = new LoVGrid(new KoulouSpace());
                }
            }
        }
        for (int i = 0; i < board[0].length; i++) {
            if (i == 2 || i == 5) {
                continue;
            }
            board[0][i] = new LoVGrid(new MarketNexusSpace());
            board[board.length - 1][i] = new LoVGrid(new MarketNexusSpace());
        }
    }

    public LoVGrid getGrid(int r, int c) {
        return board[r][c];
    }

    public int getRows() {
        return board.length;
    }

    public int getCols() {
        return board[0].length;
    }
}
