 /*
  * Item.java
  * by Simon Kye (simonkye@bu.edu)
  * 3/30/2024
  *
  * Abstract class that contains all attributes common to entities (which are heroes and monsters)
  */

public abstract class Entity {
    protected String name;
    protected int defense;
    protected int health;
    protected CombatBehavior cb;

    protected Entity(String name, int defense, CombatBehavior cb) {
        this.name = name;
        this.defense = defense;
        this.cb = cb;
    }

    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

    public int getDefense() {
        return defense;
    }
    
    public CombatBehavior getCombatBehavior() {
        return cb;
    }
}