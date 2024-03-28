import java.util.ArrayList;
import java.util.List;

public class Potion extends Item {
    private boolean increaseHealth;
    private boolean increaseStrength;
    private boolean increaseMana;
    private boolean increaseDexterity;
    private boolean increaseDefense;
    private boolean increaseAgility;
    private int attributeIncrease;

    public Potion(String name, int cost, int requiredLevel, int attributeIncrease,
                boolean increaseHealth, boolean increaseStrength, boolean increaseMana,
                boolean increaseDexterity, boolean increaseDefense, boolean increaseAgility) {
        super(name, cost, requiredLevel);
        this.attributeIncrease = attributeIncrease;
        this.increaseHealth = increaseHealth;
        this.increaseStrength = increaseStrength;
        this.increaseMana = increaseMana;
        this.increaseDexterity = increaseDexterity;
        this.increaseDefense = increaseDefense;
        this.increaseAgility = increaseAgility;
    }

    public void displayItemInformationMarket(int index) {
        System.out.println("Item " + index);
        System.out.println("Name: " + getName());
        System.out.println("Cost: " + getCost());
        System.out.println("Required Level: " + getRequiredLevel());
        System.out.println("Attribute Increase: " + getAttributeIncrease());
        System.out.println("Attributes Affected: " +  String.join(", ", getAffectedAttributes()));
    }

    public void displayItemInformationInventory() {
        System.out.println("Name: " + getName());
        System.out.println("Cost: " + getCost());
        System.out.println("Attribute Increase: " + getAttributeIncrease());
        System.out.println("Attributes Affected: " +  String.join(", ", getAffectedAttributes()));
    }

    public int getAttributeIncrease() {
        return attributeIncrease;
    }

    public void printEffect() {
        System.out.println("It increased " + String.join(", ", getAffectedAttributes()) + " by " + getAttributeIncrease());
    }

    public List<String> getAffectedAttributes() {
        List<String> attributes = new ArrayList<>();

        if (increaseHealth) {
            attributes.add("Health");
        }
        if (increaseStrength) {
            attributes.add("Strength");
        }
        if (increaseMana) {
            attributes.add("Mana");
        }
        if (increaseDexterity) {
            attributes.add("Dexterity");
        }
        if (increaseDefense) {
            attributes.add("Defense");
        }
        if (increaseAgility) {
            attributes.add("Agility");
        }

        return attributes;
    }
}
