import java.util.*;

public class LegendsOfValor implements Playable {
    private Board board;
    private Input input = Input.getSingletonInput();
    private HeroParty playerParty;
    private MonsterParty monsterParty;
    public void initalize() {
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
                System.out.println("" + r + (heroLane * 3 + j));
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
        visualize();
    }
}
