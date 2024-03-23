public abstract class Character {
    protected String name;
    protected int strength;
    protected int defense;
    protected int agility;
    protected CombatBehavior cb;

    protected Character(String name) {
        this.name = name;
    }
}