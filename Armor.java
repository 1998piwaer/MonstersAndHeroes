public class Armor extends Item {
    private int damageReduction;

    public Armor(String name, int cost, int requiredLevel, int damageReduction) {
        super(name, cost, requiredLevel);
        this.damageReduction = damageReduction;
    }

    public void displayItemInformation() {
        System.out.println("Name: " + getName());
        System.out.println("Cost: " + getCost());
        System.out.println("Required Level: " + getRequiredLevel());
        System.out.println("Damage Reduction: " + getDamageReduction());
    }

    public int getDamageReduction() {
        return damageReduction;
    }
}
