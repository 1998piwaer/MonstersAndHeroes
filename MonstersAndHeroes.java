import java.util.*;

public class MonstersAndHeroes implements Playable {
    private HeroParty playerParty;
    private Input input = Input.getSingletonInput();
    private Board board;
    private final static int INACCESSIBLE = -1;
    private boolean inCombat;
    public MonstersAndHeroes() {
        inCombat = false;
    }

    public void initalize() {
        // 1 Warrior 1 Sorcerer, 1 Paladin, 8 x 8 board
        System.out.println("Do you want to play with default settings? [T/F]");
        boolean defaultSettings = input.getBoolean();
        if (input.isQuit()) {
            return;
        }

        if (defaultSettings) {
            playerParty = new HeroParty();
            playerParty.addPartyMember(Hero.createRandomHeroFromFile(new WarriorCombatBehavior()));
            playerParty.addPartyMember(Hero.createRandomHeroFromFile(new PaladinCombatBehavior()));
            playerParty.addPartyMember(Hero.createRandomHeroFromFile(new SorcererCombatBehavior()));

            board = new Board();
            do {
                playerParty.initPartyCoordinates(Settings.DEFAULT_NUM_ROWS, Settings.DEFAULT_NUM_COLS);
            } while (board.getGrid(playerParty.getHeroPartyRow(), playerParty.getHeroPartyCol()).getType() == Settings.INACCESSIBLE);
        } else {
            // System.out.println("How many people do you want in your party?");
            // int partySize = input.getInt(Settings.MIN_PARTY_SIZE, Settings.MAX_PARTY_SIZE);
            // if (input.isQuit()) {
            //     return;
            // }
            // TODO: Implement non-default
        }
    }

    public void playGame() {
        while(!input.isQuit()) {
            visualize();
            printPartySpace();
            System.out.println("Up [W], Left [A], Down [S], Right [D], Equip [E], Enter Market [M], See Party Information [I]");
            if (!inCombat) {
                boolean valid = false;
                do {
                    String action = getNoncombatInput();
                    if (action.equals("w") || action.equals("a") || action.equals("s") || action.equals("d")) {
                        valid = movePlayer(action);
                        if (valid) {
                            int currR = playerParty.getHeroPartyRow();
                            int currC = playerParty.getHeroPartyCol();
                            board.getGrid(currR, currC).getSpace().enter(playerParty);
                        }
                    } else if (action.equals("i")) {
                        valid = true;
                        playerParty.displayPartyInformation();
                    } else if (action.equals("m")) {
                        int currR = playerParty.getHeroPartyRow();
                        int currC = playerParty.getHeroPartyCol();
                        board.getGrid(currR, currC).getSpace().interact(playerParty);
                        valid = true;
                    } else if (action.equals("e")) {
                        playerParty.equipOrUse();
                        valid = true;
                    }
                } while (!valid);
            }
        }
    }

    private boolean movePlayer(String movement) {
        Map<String, int[]> dir = new HashMap<>();
        dir.put("w", new int[]{-1, 0});
        dir.put("s", new int[]{1, 0});
        dir.put("a", new int[]{0, -1});
        dir.put("d", new int[]{0, 1});
        int[] d = dir.get(movement);
        int currR = playerParty.getHeroPartyRow();
        int currC = playerParty.getHeroPartyCol();
        int newR = currR + d[0];
        int newC = currC + d[1];
        if (newR < 0 || newC < 0 || newR >= board.getRows() || newC >= board.getCols()) {
            System.out.println("This moves you out of bounds!");
        } else if (board.getGrid(newR, newC).getType() == INACCESSIBLE) {
            System.out.println("This space is inaccessible!");
        } else {
            playerParty.setHeroPartyRow(newR);
            playerParty.setHeroPartyCol(newC);
            return true;
        }
        return false;
    }

    private void visualize() {
        Settings.clearTerminal();
        
        int rows = board.getRows();
        int cols = board.getCols();
        int playerRow = playerParty.getHeroPartyRow();
        int playerCol = playerParty.getHeroPartyCol();

        for (int i = 0; i < rows; i++) {
            System.out.print("+");
            for (int j = 0; j < cols; j++) {
                System.out.print("---+");
            }
            System.out.println();
            System.out.print("|");
            for (int j = 0; j < cols; j++) {
                System.out.print(" ");
                if (playerRow == i && playerCol == j) {
                    System.out.print(Settings.HERO_COLOR);
                    System.out.print("H");
                    System.out.print(Settings.DEFAULT_COLOR);
                } else {
                    board.getGrid(i, j).printGrid();
                }
                System.out.print(" ");
                System.out.print("|");
            }
            System.out.println();
        }
        System.out.print("+");
        for (int i = 0; i < rows; i++) {
            System.out.print("---+");
        }
        System.out.println();
    }

    private void printPartySpace() {
        System.out.print("You are currently standing on a ");
        int r = playerParty.getHeroPartyRow();
        int c = playerParty.getHeroPartyCol();
        int spaceType = board.getGrid(r, c).getType();
        if (spaceType == Settings.COMMON) {
            System.out.print("COMMON");
        } else if (spaceType == Settings.MARKET) {
            System.out.print("MARKET");

        // These two else's should never be printed, but it's there for debugging purposes.
        } else if (spaceType == Settings.INACCESSIBLE) {
            System.out.print("INACCESSIBLE");
        } else {
            System.out.print("UNKNOWN");
        }
        System.out.println(" space.");
    }

    private String getNoncombatInput() {
        Set<String> hs = new HashSet<>();
        hs.add("w");
        hs.add("a");
        hs.add("s");
        hs.add("d");
        hs.add("q");
        hs.add("i");
        hs.add("m");
        hs.add("e");
        String s = input.getString(hs);
        return s;
    }
}
