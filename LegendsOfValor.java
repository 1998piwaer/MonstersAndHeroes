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
                for (int j = 0; j < 2; j++) {
                    int r = board.getRows() - 1;
                    board.getGrid(r, i * 3 + j).getSpace();
                    MarketNexusSpace nexusSpace = (MarketNexusSpace) board.getGrid(r, i * 3 + j).getSpace();
                    nexusSpace.setOwnership(playerParty.get(i));
                    Coordinate coord = new Coordinate(r, i * 3 + j);
                    playerParty.setPartyCoordinate(i, coord);
                    r = 0;
                    nexusSpace = (MarketNexusSpace) board.getGrid(r, i * 3 + j).getSpace();
                    coord = new Coordinate(r, i * 3 + j);
                    monsterParty.setPartyCoordinate(i, coord);
                }
            } 
            
        } else {
            board = new LoVBoard();

            //Using composite pattern was considered but the list of hero parties of size 1 would mean that attributes such as their coordinates do not apply to the parent layer and by itself doesn't hold up as a independent playerParty. I therefore decided using 
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
                for (int j = 0; j < 2; j++) {
                    int r = board.getRows() - 1;
                    board.getGrid(r, heroLane * 3 + j).getSpace();
                    MarketNexusSpace nexusSpace = (MarketNexusSpace) board.getGrid(r, heroLane * 3 + j).getSpace();
                    nexusSpace.setOwnership(hero);
                    Coordinate coord = new Coordinate(r, heroLane * 3 + j);
                    playerParty.setPartyCoordinate(i, coord);
                    r = 0;
                    nexusSpace = (MarketNexusSpace) board.getGrid(r, heroLane * 3 + j).getSpace();
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
        while(!input.isQuit()) {
            for (int i = 0; i < playerParty.size(); i++) {
                visualize();
                System.out.println("Hero " + i + "'s Turn!");
                System.out.println("Up [W], Left [A], Down [S], Right [D]");
                boolean valid = false;
                do {
                    String action = getNoncombatInput();
                    if (action.equals("w") || action.equals("a") || action.equals("s") || action.equals("d") || action.equals("t")) {
                        valid = moveEntity(i, action, playerParty);
                        if (valid) {
                            Coordinate coord = playerParty.getPartyCoordinate(i);
                            int currR = coord.getRow();
                            int currC = coord.getCol();
                            board.getGrid(currR, currC).getSpace().enter(playerParty.get(i));
                        }
                    }
                } while (!valid);
            }
            for (int i = 0; i < monsterParty.size(); i++) {
                moveEntity(i, "s", monsterParty);
            }
        }
    }

    private String getNoncombatInput() {
        Set<String> hs = new HashSet<>();
        hs.add("w");
        hs.add("a");
        hs.add("s");
        hs.add("d");
        hs.add("t");
        String s = input.getString(hs);
        return s;
    }

    private boolean moveEntity(int index, String movement, PartyInterface party) {
        if (!movement.equals("t")) {
            Map<String, int[]> dir = new HashMap<>();
            dir.put("w", new int[]{-1, 0});
            dir.put("s", new int[]{1, 0});
            dir.put("a", new int[]{0, -1});
            dir.put("d", new int[]{0, 1});
            int[] d = dir.get(movement);
            Coordinate coord = party.getPartyCoordinate(index);
            int currR = coord.getRow();
            int currC = coord.getCol();
            int newR = currR + d[0];
            int newC = currC + d[1];
            if (newR < 0 || newC < 0 || newR >= board.getRows() || newC >= board.getCols()) {
                System.out.println("This moves you out of bounds!");
            } else if (board.getGrid(newR, newC).getType() == Settings.INACCESSIBLE_SPACE_TYPE) {
                System.out.println("This space is inaccessible!");
            } else {
                Coordinate newCoord = new Coordinate(newR, newC);
                party.setPartyCoordinate(index, newCoord);
                return true;
            }
            return false;
        } else {
            Set<Integer> availableHeroes = new HashSet<>();
            for (int i = 0; i < Settings.DEFAULT_LEGENDS_OF_VALOR_SIZE; i++) {
                if (i != index) {
                    availableHeroes.add(i);
                }
            }
            System.out.println("Which hero would you like to teleport to? " + availableHeroes.toString());
            int tpTargetHero = input.getInt(availableHeroes);
            return true;
        }
    }


}
