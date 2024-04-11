import java.util.*;

public class LegendsOfValor implements Playable {
    private LoVBoard board;
    private Input input = Input.getSingletonInput();
    private HeroParty playerParty;
    private MonsterParty monsterParty;

    public void initalize() {
        System.out.println("Do you want to play with default settings? [T/F]");
        boolean defaultSettings = input.getBoolean();
        if (defaultSettings) {
            playerParty = new HeroParty();
            playerParty.addPartyMember(Hero.createRandomHeroFromFile(new WarriorCombatBehavior()));
            playerParty.addPartyMember(Hero.createRandomHeroFromFile(new PaladinCombatBehavior()));
            playerParty.addPartyMember(Hero.createRandomHeroFromFile(new SorcererCombatBehavior()));
            monsterParty = new MonsterParty(Settings.DEFAULT_LOV_PARTY_SIZE, 1);
            board = new LoVBoard();
            for (int i = 0; i < Settings.DEFAULT_LOV_PARTY_SIZE; i++) {
                board.setHerosNexusCoordinates(i, i);
                for (int j = 0; j < 2; j++) {
                    int r = board.getRows() - 1;
                    board.getGrid(r, i * 3 + j).getSpace();
                    Coordinate coord = new Coordinate(r, i * 3 + j);
                    playerParty.setPartyCoordinate(i, coord);
                    r = 0;
                    coord = new Coordinate(r, i * 3 + j);
                    monsterParty.setPartyCoordinate(i, coord);
                }
            }

        } else {
            board = new LoVBoard();

            // Using composite pattern was considered but the list of hero parties of size 1
            // would mean that attributes such as their coordinates do not apply to the
            // parent
            // layer and by itself doesn't hold up as a independent playerParty.
            // I therefore decided using composite pattern was not appropriate.
            playerParty = new HeroParty();
            monsterParty = new MonsterParty(Settings.DEFAULT_LOV_PARTY_SIZE, 1);

            Set<String> hs = new HashSet<>();
            hs.add("w");
            hs.add("p");
            hs.add("s");
            playerParty = new HeroParty();
            Set<Integer> availableLanes = new HashSet<>();
            for (int i = 0; i < Settings.NUM_LANES; i++) {
                availableLanes.add(i);
            }
            for (int i = 0; i < Settings.DEFAULT_LOV_PARTY_SIZE; i++) {
                Hero hero = null;
                System.out.println("What class of hero would you like? Warrior [W], Palandin [P], Sorcerer [S] ");
                String heroClass = input.getString(hs);
                if (heroClass.equals("w")) {
                    hero = Hero.createRandomHeroFromFile(new WarriorCombatBehavior());
                    playerParty.addPartyMember(hero);
                } else if (heroClass.equals("p")) {
                    hero = Hero.createRandomHeroFromFile(new PaladinCombatBehavior());
                    playerParty.addPartyMember(hero);
                } else if (heroClass.equals("s")) {
                    hero = Hero.createRandomHeroFromFile(new SorcererCombatBehavior());
                    playerParty.addPartyMember(hero);
                }

                // TODO: Avoid casting!!
                System.out.println("Which lane do you want this hero to belong to? " + availableLanes.toString());
                int heroLane = input.getInt(availableLanes);
                availableLanes.remove(heroLane);
                board.setHerosNexusCoordinates(i, heroLane);
                for (int j = 0; j < 2; j++) {
                    int r = board.getRows() - 1;
                    board.getGrid(r, heroLane * 3 + j).getSpace();
                    Coordinate coord = new Coordinate(r, heroLane * 3 + j);
                    playerParty.setPartyCoordinate(i, coord);
                    r = 0;
                    coord = new Coordinate(r, heroLane * 3 + j);
                    monsterParty.setPartyCoordinate(i, coord);
                }
            }
        }
    }

    private void visualize() {
        int rows = board.getRows();
        int cols = board.getCols();
        Map<Coordinate, String> heroHashMap = new HashMap<>();
        Map<Coordinate, String> monsterHashMap = new HashMap<>();
        for (int i = 0; i < playerParty.size(); i++) {
            heroHashMap.put(playerParty.getPartyCoordinate(i), "H" + i);
        }
        for (int i = 0; i < monsterParty.size(); i++) {
            monsterHashMap.put(monsterParty.getPartyCoordinate(i), "M" + i);
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board.getGrid(i, j).printGrid();
                System.out.print(" - ");
                board.getGrid(i, j).printGrid();
                System.out.print(" - ");
                board.getGrid(i, j).printGrid();
                System.out.print("  ");
            }
            System.out.println();
            for (int j = 0; j < cols; j++) {
                System.out.print("| ");
                System.out.print(heroHashMap.getOrDefault(new Coordinate(i, j), "  "));
                System.out.print(" ");
                System.out.print(monsterHashMap.getOrDefault(new Coordinate(i, j), "  "));
                System.out.print(" |");
                System.out.print("  ");
            }
            System.out.println();
            for (int j = 0; j < cols; j++) {
                board.getGrid(i, j).printGrid();
                System.out.print(" - ");
                board.getGrid(i, j).printGrid();
                System.out.print(" - ");
                board.getGrid(i, j).printGrid();
                System.out.print("  ");
            }
            System.out.println();
        }
    }

    public void playGame() {
        while (!input.isQuit() && !playerParty.isWin() && !monsterParty.isWin()) {
            for (int i = 0; i < playerParty.size(); i++) {
                boolean valid = false;
                while (!valid) {
                    visualize();
                    System.out.println("Hero " + i + "'s Turn!");
                    System.out.println(
                            "Up [W], Left [A], Down [S], Right [D], Teleport [T], Recall [R], Enter Market/Nexus [M], Equip/Use [E], See Party Information [I]");
                    Coordinate currCoord = playerParty.getPartyCoordinate(i);
                    String action = getNoncombatInput();
                    if (action.equals("w") || action.equals("a") || action.equals("s") || action.equals("d")
                            || action.equals("t")) {
                        valid = moveEntity(i, action, playerParty, monsterParty);
                        if (valid) {
                            Coordinate coord = playerParty.getPartyCoordinate(i);
                            int currR = coord.getRow();
                            int currC = coord.getCol();
                            board.getGrid(currR, currC).getSpace().enter(playerParty.get(i));

                            // judge if the hero is in the nexus of monster
                            if (coord.getRow() == 0) {
                                // should set game status to win
                                playerParty.setWin(true);
                                System.out.println("Hero " + i + " has entered the monster's nexus!");
                                break;
                            }
                        }
                    } else if (action.equals("m")) {
                        if (currCoord.getRow() != board.getRows() - 1) {
                            System.out.println("You're not standing on a nexus!");
                        } else if (board.getHerosNexusCoordinates(i).contains(playerParty.getPartyCoordinate(i))) {
                            board.getGrid(currCoord.getRow(), currCoord.getCol()).getSpace()
                                    .interact(playerParty.get(i));
                            System.out.println("Entered Nexus!");
                        } else {
                            System.out.println("You can only enter your own nexus!");
                        }
                    } else if (action.equals("e")) {
                        playerParty.equipOrUse(playerParty.get(i));
                    } else if (action.equals("i")) {
                        playerParty.displayPartyInformation();
                        monsterParty.displayPartyInformation();
                    }
                }
            }
            for (int i = 0; i < monsterParty.size(); i++) {
                moveEntity(i, "s", monsterParty, playerParty);
                if (monsterParty.getPartyCoordinate(i).getRow() == board.getRows() - 1) {
                    // should set game status to win
                    monsterParty.setWin(true);
                    System.out.println("Monster " + i + " has entered the hero's nexus!");
                    break;
                }
            }
        }

        // show win status
        if (playerParty.isWin()) {
            System.out.println("Congratulations! You have won!");
        } else if (monsterParty.isWin()) {
            System.out.println("You have lost!");
        }
    }

    private String getNoncombatInput() {
        Set<String> hs = new HashSet<>();
        hs.add("w");
        hs.add("a");
        hs.add("s");
        hs.add("d");
        hs.add("t");
        hs.add("m");
        hs.add("e");
        hs.add("i");
        String s = input.getString(hs);
        return s;
    }

    private boolean moveEntity(int index, String movement, PartyInterface party, PartyInterface opponentParty) {
        Coordinate currCoord = party.getPartyCoordinate(index);
        if (!movement.equals("t") && !movement.equals("r")) {
            Map<String, int[]> dir = new HashMap<>();
            dir.put("w", new int[] { -1, 0 });
            dir.put("s", new int[] { 1, 0 });
            dir.put("a", new int[] { 0, -1 });
            dir.put("d", new int[] { 0, 1 });
            int[] d = dir.get(movement);

            int currR = currCoord.getRow();
            int currC = currCoord.getCol();
            int newR = currR + d[0];
            int newC = currC + d[1];
            Coordinate newCoord = new Coordinate(newR, newC);
            if (newR < 0 || newC < 0 || newR >= board.getRows() || newC >= board.getCols()) {
                System.out.println("This moves you out of bounds!");
            } else if (board.getGrid(newR, newC).getType() == Settings.INACCESSIBLE_SPACE_TYPE) {
                System.out.println("This space is inaccessible!");
            } else if (!moveCheck(index, newCoord, party, opponentParty)) {
                if (party instanceof HeroParty) {
                    System.out.println("There is a monster blocking your path!");
                } else if (party instanceof MonsterParty) {
                    System.out.println("[DEBUG]: Monster now attacks the hero!");
                }
            } else {
                party.setPartyCoordinate(index, newCoord);
                return true;
            }
            return false;
        } else if (movement.equals("t")) {
            Set<Integer> availableHeroes = new HashSet<>();
            for (int i = 0; i < playerParty.size(); i++) {
                if (i != index) {
                    availableHeroes.add(i);
                }
            }
            System.out.println("Which hero would you like to teleport to? " + availableHeroes.toString());
            int tpTargetHero = input.getInt(availableHeroes);
            Coordinate targetHeroCoord = playerParty.getPartyCoordinate(tpTargetHero);

            if (getLane(targetHeroCoord) == getLane(currCoord)) {
                System.out.println("You can't teleport to a hero in the same lane!");
                return false;
            }

            Set<Coordinate> validCoordinates = getValidTeleportPositions(targetHeroCoord);
            teleport(index, tpTargetHero, validCoordinates, targetHeroCoord);

            return true;
            // Written by Houjie
        } else if (movement.equals("r")) {
            /*
             * Coordinate nexusCoord = playerParty.getPartyCoordinate(index);
             * party.setPartyCoordinate(index, nexusCoord);
             * return true;
             */
            // Assuming lanes are indexed from 0 and each hero is assigned a lane
            // Nexus for heroes is at the bottom row, which is board.getRows() - 1
            // Assuming lanes are divided equally, calculate column based on hero's lane
            // Assuming lanes in a round-robin fashion
            for (int i = 0; i < playerParty.size(); i++) {
                int lane = i % Settings.NUM_LANES;
                int baseCol = lane * (board.getCols() / Settings.NUM_LANES);
                Coordinate nexusCoord = new Coordinate(board.getRows() - 1, baseCol);
                playerParty.setPartyCoordinate(i, nexusCoord);
            }
            return true;
        } else {
            return false;
        }
    }

    private <T extends PartyInterface> boolean moveCheck(int index, Coordinate newCoord, T selectedParty,
            T opponentParty) {
        Coordinate currCoord = selectedParty.getPartyCoordinate(index);
        Set<Coordinate> partyCoordinates = opponentParty.getAllCoordinates();
        boolean moveDeeper = false;

        // If hero or monster is moving closer to their opponent (monster or hero
        // respectively)'s nexus
        if (selectedParty instanceof HeroParty && currCoord.getRow() - newCoord.getRow() > 0) {
            moveDeeper = true;
        } else if (selectedParty instanceof MonsterParty && currCoord.getRow() - newCoord.getRow() < 0) {
            moveDeeper = true;
        }

        for (Coordinate opponentCoord : partyCoordinates) {
            // if opponent and entity is in same row and they're attempting to move deeper,
            // return false
            if (currCoord.getRow() == opponentCoord.getRow() && moveDeeper) {
                return false;
            }
        }
        return true;
    }

    private void teleport(int heroIndex, int targetHeroIndex, Set<Coordinate> validNeighbor, Coordinate centerCoord) {
        int counter = 0;
        int[][] dir = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 0 }, { 0, 1 }, { 1, -1 }, { 1, 0 },
                { 1, 1 } };
        Map<Integer, Coordinate> hm = new HashMap<>();

        for (int i = 0; i < dir.length; i++) {
            Coordinate adjCoord = new Coordinate(centerCoord.getRow() + dir[i][0], centerCoord.getCol() + dir[i][1]);
            if (!Coordinate.isInBounds(adjCoord, board.getRows(), board.getCols())) {
                continue;
            }
            if (validNeighbor.contains(adjCoord)) {
                hm.put(counter, adjCoord);
                System.out.print("|");
                System.out.print(counter++);
                System.out.print(" |");
            } else if (centerCoord.equals(adjCoord)) {
                System.out.print("|H" + targetHeroIndex + "|");
            } else {
                System.out.print("|X |");
            }
            if (i % 3 == 2) {
                System.out.println();
            }
        }
        System.out.println("Which spot would you like to teleport to? " + hm.keySet().toString());
        int location = input.getInt(hm.keySet());
        playerParty.setPartyCoordinate(heroIndex, hm.get(location));
    }

    private Set<Coordinate> getValidTeleportPositions(Coordinate targetHeroCoord) {
        // Left, Down Left, Down, Down Right, Right
        int[][] dir = { { 0, -1 }, { 1, -1 }, { 1, 0 }, { 1, 1 }, { 0, 1 } };
        int currR = targetHeroCoord.getRow();
        int currC = targetHeroCoord.getCol();

        Set<Coordinate> validCoord = new HashSet<>();
        Set<Coordinate> heroOccupiedCoord = new HashSet<>();
        for (int i = 0; i < playerParty.size(); i++) {
            heroOccupiedCoord.add(playerParty.getPartyCoordinate(i));
        }

        for (int[] d : dir) {
            int newR = currR + d[0];
            int newC = currC + d[1];
            Coordinate newCoord = new Coordinate(newR, newC);
            if (!Coordinate.isInBounds(newCoord, board.getRows(), board.getCols())) {
                continue;
            }
            if (board.getGrid(newR, newC).getType() != Settings.INACCESSIBLE_SPACE_TYPE) {
                if (!heroOccupiedCoord.contains(newCoord)) {
                    validCoord.add(newCoord);
                }
            }
        }

        return validCoord;
    }

    private int getLane(Coordinate coord) {
        if (coord.getCol() % 3 == 2) {
            return -1;
        }
        return coord.getCol() / 3;
    }
}