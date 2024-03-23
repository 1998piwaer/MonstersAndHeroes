public class Hero extends Character {
    private int dexterity;
    private int gold;
    private int exp;

    public Hero(String name, CombatBehavior cb) {
        super(name);
        super.cb = cb;
        gold = Settings.STARTING_GOLD;
    }
}
