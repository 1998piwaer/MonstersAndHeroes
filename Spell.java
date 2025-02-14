 /*
  * Spell.java
  * by Simon Kye (simonkye@bu.edu)
  * 3/30/2024
  *
  * Extends Item.java and has all logic and state regarding a spell,
  * such as checking requirements, displaying, using, and its effects.
  * A spell's type effect is listed in Combat.java
  */

public class Spell extends Item {
    private int damage;
    private int manaCost;
    private String type;
    public Spell(String name, int cost, int requiredLevel, int damage, int manaCost, String type) {
        super(name, cost, requiredLevel);
        this.damage = damage;
        this.manaCost = manaCost;
        this.type = type;
    }

    public void displayItemInformationMarket(int index) {
        System.out.println("Item " + index);
        System.out.println("Name: " + getName());
        System.out.println("Required Level: " + getRequiredLevel());
        System.out.println("Cost: " + getCost());
        System.out.println("Damage: " + getDamage());
        System.out.println("Mana Cost: " + getManaCost());
        System.out.println("Spell Type: " + getType());
    }

    public void printEffect() {
        System.out.println("It dealt " + getDamage() + " " + getType() + " damage!");
    }

    public void displayItemInformationInventory() {
        System.out.println("Name: " + getName());
        System.out.println("Required Level: " + getRequiredLevel());
        System.out.println("Cost: " + getCost());
        System.out.println("Damage: " + getDamage());
        System.out.println("Mana Cost: " + getManaCost());
        System.out.println("Spell Type: " + getType());
    }

    public int getDamage() {
        return damage;
    }

    public int getManaCost() {
        return manaCost;
    }
    
    public String getType() {
        return type;
    }
}
