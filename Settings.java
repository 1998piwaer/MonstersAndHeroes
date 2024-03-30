public class Settings {
    // Used in CombatBehaviors
    public static final float AFFINITY_MULTIPLIER = 1.5f;
    public static final float DEFENSE_EFFECTIVENESS = 0.5f;

    // Used in Entity.java
    public static final int HEALTH_PER_LEVEL = 500;

    // Used in Hero.java
    public static final float STRENGTH_TO_ATTACK_DAMAGE_RATIO = 0.05f;
    public static final float AGILITY_TO_DODGE_CHANCE_RATIO = 0.00005f;
    public static final float LEVELUP_MULTIPLIER = 0.05f;
    public static final int HERO_DEFAULT_DEFENSE = 500;

    // Used in Monster.java
    public static final float DODGE_CHANCE_RATIO = 0.002f;

    // Used in CommonSpace.java
    public static final float ENCOUNTER_CHANCE = 0.2f;
    
    // Used in Combat.java
    public static final float RUN_SUCCESS_CHANCE = 0.5f;

    // Used during intialization of non-default game
    public static final int MIN_PARTY_SIZE = 1;
    public static final int MAX_PARTY_SIZE = 3;
    public static final int MIN_BOARD_SIZE = 5;
    public static final int MAX_BOARD_SIZE = 15;

    // Used during initialization
    // Must add up to 1, otherwise code may crash.
    public static final float INACCESSIBLE_SPACE_PROPORTION = 0.2f;
    public static final float MARKET_SPACE_PROPORTION = 0.3f;
    public static final float COMMON_SPACE_PROPORTION = 0.5f;
    // Must add up to 1, otherwise code may crash.
    public static final float EXOSKELETON_CHANCE = 0.3f;
    public static final float DRAGON_CHANCE = 0.4f;
    public static final float SPIRIT_CHANCE = 0.3f;

    // Colors
    public static final String DEFAULT_COLOR = "\u001B[0m";
    public static final String HERO_COLOR = "\u001B[34m";
    public static final String MARKET_COLOR = "\u001B[32m";
    public static final String INACCESSIBLE_COLOR = "\u001B[31m";

    // Used during initialization of default game
    public static final int DEFAULT_NUM_ROWS = 8;
    public static final int DEFAULT_NUM_COLS = 8;

    public final static int COMMON = 0;
    public final static int INACCESSIBLE = -1;
    public final static int MARKET = 1;

    public static void clearTerminal() {
        System.out.print("\033[H\033[2J");
    }
}
