public abstract class Entity {
    private String name;
    private int strength;
    private int defense;
    private int agility;
    private int health;
    private CombatBehavior cb;

    protected Entity(String name, int strength, int agility, int defense, CombatBehavior cb) {
        this.name = name;
        this.strength = strength;
        this.agility = agility;
        this.defense = defense;
        this.cb = cb;
        this.health = 100;
    }

    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

    public int getStrength() {
        return strength;
    }

    public int getDefense() {
        return defense;
    }

    public int getAgility() {
        return agility;
    }
}