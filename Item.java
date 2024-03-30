 /*
  * Item.java
  * by Simon Kye (simonkye@bu.edu)
  * 3/30/2024
  *
  * Abstract class that contains all attributes common to items (which include spell) such
  * as cost, name, required levels, and requires that anything that extends it has proper printing
  * mainly used in market and combat.
  */

public abstract class Item {
    private String name;
    private int cost;
    private int requiredLevel;

    public Item(String name, int cost, int requiredLevel) {
        this.name = name;
        this.cost = cost;
        this.requiredLevel = requiredLevel;
    }

    abstract void displayItemInformationMarket(int index);

    abstract void displayItemInformationInventory();

    abstract void printEffect();

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getRequiredLevel() {
        return requiredLevel;
    }

    public void useItem() {

    }

    public void purchaseItem() {

    }
    public void sellItem() {

    }
}
