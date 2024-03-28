public class Settings {
    public static final float AFFINITY_MULTIPLIER = 1.5f;

    public static final float STRENGTH_TO_ATTACK_DAMAGE_RATIO = 0.05f;

    public static final float ENCOUNTER_CHANCE = 0.2f;

    public static final float DODGE_CHANCE_RATIO = 0.005f;
    public static final float AGILITY_TO_DODGE_CHANCE_RATIO = 0.0002f;

    public static final float LEVELUP_MULTIPLIER = 0.05f;

    public static final int MIN_PARTY_SIZE = 1;
    public static final int MAX_PARTY_SIZE = 3;

    public static final String DEFAULT_COLOR = "\u001B[0m";
    public static final String HERO_COLOR = "\u001B[34m";
    public static final String MARKET_COLOR = "\u001B[32m";
    public static final String INACCESSIBLE_COLOR = "\u001B[31m";

    // Must add up to 1, otherwise code may crash.
    public static final float INACCESSIBLE_SPACE_PROPORTION = 0.2f;
    public static final float MARKET_SPACE_PROPORTION = 0.3f;
    public static final float COMMON_SPACE_PROPORTION = 0.5f;

    // Must add up to 1, otherwise code may crash.
    public static final float EXOSKELETON_CHANCE = 0.3f;
    public static final float DRAGON_CHANCE = 0.4f;
    public static final float SPIRIT_CHANCE = 0.3f;

    public static final int DEFAULT_NUM_ROWS = 8;
    public static final int DEFAULT_NUM_COLS = 8;

    public final static int COMMON = 0;
    public final static int INACCESSIBLE = -1;
    public final static int MARKET = 1;

    public static void clearTerminal() {
        System.out.print("\033[H\033[2J");
    }
}
