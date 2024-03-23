import java.util.*;

public class MonstersAndHeroes implements Playable {
    private HeroParty playerParty;
    private Input input = Input.getSingletonInput();
    private Board board;
    private final static int COMMON = 0;
    private final static int INACCESSIBLE = -1;
    private final static int MARKET = 1;

    public MonstersAndHeroes() {
        
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
            playerParty.addPartyMember("Simon", new WarriorCombatBehavior());
            playerParty.addPartyMember("Simone", new SorcererCombatBehavior());
            playerParty.addPartyMember("Simeon", new PaladinCombatBehavior());

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
        
    }

    
}
