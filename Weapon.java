public class Weapon extends Item {
    private int damageAmplification;
    private int requiredHands;

    public Weapon(String name, int cost, int requiredLevel, int damageAmplification, int requiredHands) {
        super(name, cost, requiredLevel);
        this.damageAmplification = damageAmplification;
        this.requiredHands = requiredHands;
    }

    public void displayItemInformationMarket(int index) {
        System.out.println("Item " + index);
        System.out.println("Name: " + getName());
        System.out.println("Required Level: " + getRequiredLevel());
        System.out.println("Cost: " + getCost());
        System.out.println("Required Level: " + getRequiredLevel());
        System.out.println("Damage Amplification: " + getDamageAmplification());
        System.out.println("Required Hands: " + getRequiredHands());
    }

    public void displayItemInformationInventory() {
        System.out.println("Name: " + getName());
        System.out.println("Cost: " + getCost());
        System.out.println("Damage Amplification: " + getDamageAmplification());
        System.out.println("Required Hands: " + getRequiredHands());
    }

    public void printEffect() {
        System.out.println("It gives the user " + getDamageAmplification() + " damage amplification!");
    }

    public int getDamageAmplification() {
        return damageAmplification;
    }

    public int getRequiredHands() {
        return requiredHands;
    }
}
