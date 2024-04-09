 /*
  * MonstersAndHeroes.java
  * by Simon Kye (simonkye@bu.edu)
  * 3/30/2024
  *
  * This class supports all methods and state regarding MonstersAndHeroes.
  * Implements Playable interface to make it easy in MainMenu for setup.
  * Handles most logic regarding overworld such as visualization, traveling,
  * and controls when a party is in the overworld.
  */

import java.util.*;

public class MonstersAndHeroes implements Playable {
    private HeroParty playerParty;
    private Input input = Input.getSingletonInput();
    private MaHBoard board;
    private final static int INACCESSIBLE = -1;
    private boolean inCombat;
    public MonstersAndHeroes() {
        inCombat = false;
    }

    public void initalize() {
        // 1 Warrior 1 Sorcerer, 1 Paladin, 8 x 8 board
        System.out.println("Do you want to play with default settings? [T/F]");
        boolean defaultSettings = input.getBoolean();

        if (defaultSettings) {
            playerParty = new HeroParty();
            playerParty.addPartyMember(Hero.createRandomHeroFromFile(new WarriorCombatBehavior()));
            playerParty.addPartyMember(Hero.createRandomHeroFromFile(new PaladinCombatBehavior()));
            playerParty.addPartyMember(Hero.createRandomHeroFromFile(new SorcererCombatBehavior()));

            board = new MaHBoard();
            do {
                playerParty.initPartyCoordinates(Settings.DEFAULT_NUM_ROWS, Settings.DEFAULT_NUM_COLS);
            } while (board.getGrid(playerParty.getPartyCoordinate(0).getRow(), playerParty.getPartyCoordinate(0).getCol()).getType() == Settings.INACCESSIBLE);
        } else {
            System.out.println("How many people do you want in your party?");
            int partySize = input.getInt(Settings.MIN_PARTY_SIZE, Settings.MAX_PARTY_SIZE);
            Set<String> hs = new HashSet<>();
            hs.add("w");
            hs.add("p");
            hs.add("s");
            playerParty = new HeroParty();
            for (int i = 0; i < partySize; i++) {
                
                System.out.println("What class of hero would you like? Warrior [W], Palandin [P], Sorcerer [S] " + Settings.MIN_PARTY_SIZE + "-" + Settings.MAX_PARTY_SIZE);
                String heroClass = input.getString(hs);
                if (heroClass.equals("w")) {
                    playerParty.addPartyMember(Hero.createRandomHeroFromFile(new WarriorCombatBehavior()));
                } else if (heroClass.equals("p")) {
                    playerParty.addPartyMember(Hero.createRandomHeroFromFile(new PaladinCombatBehavior()));
                } else if (heroClass.equals("s")) {
                    playerParty.addPartyMember(Hero.createRandomHeroFromFile(new SorcererCombatBehavior()));
                }
                if (heroClass == "w") {
                    playerParty.addPartyMember(Hero.createRandomHeroFromFile(new WarriorCombatBehavior()));
                } else if (heroClass == "p") {
                    playerParty.addPartyMember(Hero.createRandomHeroFromFile(new PaladinCombatBehavior()));
                } else if (heroClass == "s") {
                    playerParty.addPartyMember(Hero.createRandomHeroFromFile(new SorcererCombatBehavior()));
                }
            }
            System.out.println("What board size would you like? " + Settings.MIN_BOARD_SIZE + "-" + Settings.MAX_BOARD_SIZE);
            int size = input.getInt(Settings.MIN_BOARD_SIZE, Settings.MAX_BOARD_SIZE);
            board = new MaHBoard(size);

            do {
                playerParty.initPartyCoordinates(size, size);
            } while (board.getGrid(playerParty.getPartyCoordinate(0).getRow(), playerParty.getPartyCoordinate(0).getCol()).getType() == Settings.INACCESSIBLE);
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
                            Coordinate coord = playerParty.getPartyCoordinate(0);
                            int currR = coord.getRow();
                            int currC = coord.getCol();
                            board.getGrid(currR, currC).getSpace().enter(playerParty);
                        }
                    } else if (action.equals("i")) {
                        valid = true;
                        playerParty.displayPartyInformation();
                    } else if (action.equals("m")) {
                        Coordinate coord = playerParty.getPartyCoordinate(0);
                        int currR = coord.getRow();
                        int currC = coord.getCol();
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
        Coordinate coord = playerParty.getPartyCoordinate(0);
        int currR = coord.getRow();
        int currC = coord.getCol();
        int newR = currR + d[0];
        int newC = currC + d[1];
        if (newR < 0 || newC < 0 || newR >= board.getRows() || newC >= board.getCols()) {
            System.out.println("This moves you out of bounds!");
        } else if (board.getGrid(newR, newC).getType() == INACCESSIBLE) {
            System.out.println("This space is inaccessible!");
        } else {
            Coordinate newCoord = new Coordinate(newR, newC);
            for (int i = 0; i < playerParty.size(); i++) {
                playerParty.setPartyCoordinate(i, newCoord);
            }
            return true;
        }
        return false;
    }

    private void visualize() {
        Settings.clearTerminal();
        
        int rows = board.getRows();
        int cols = board.getCols();
        Coordinate coord = playerParty.getPartyCoordinate(0);
        int playerRow = coord.getRow();
        int playerCol = coord.getCol();

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
        Coordinate coord = playerParty.getPartyCoordinate(0);
        int r = coord.getRow();
        int c = coord.getCol();
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
