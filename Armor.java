 /*
  * Armor.java
  * by Simon Kye (simonkye@bu.edu)
  * 3/30/2024
  *
  * This class supports all methods that pertain to common attributes of armor
  * and handles displaying of information regarding armor.
  */

public class Armor extends Item {
    private int damageReduction;

    public Armor(String name, int cost, int requiredLevel, int damageReduction) {
        super(name, cost, requiredLevel);
        this.damageReduction = damageReduction;
    }

    public void displayItemInformationMarket(int index) {
        System.out.println("Item " + index);
        System.out.println("Required Level " + getRequiredLevel());
        System.out.println("Name: " + getName());
        System.out.println("Cost: " + getCost());
        System.out.println("Required Level: " + getRequiredLevel());
        System.out.println("Damage Reduction: " + getDamageReduction());
    }

    public void displayItemInformationInventory() {
        System.out.println("Name: " + getName());
        System.out.println("Cost: " + getCost());
        System.out.println("Damage Reduction: " + getDamageReduction());
    }

    public void printEffect() {
        System.out.println("It gave the user " + getDamageReduction() + " damage reduction");
    }

    public int getDamageReduction() {
        return damageReduction;
    }
}
