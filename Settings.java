public class Settings {
    public static final float AFFINITY_MULTIPLIER = 1.5f;
    public static final float BASE_DODGE_CHANCE = 0.1f;

    public static final float ENCOUNTER_CHANCE = 0.2f;

    public static final int MIN_PARTY_SIZE = 1;
    public static final int MAX_PARTY_SIZE = 3;
    public static final int STARTING_GOLD = 0;

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
}
