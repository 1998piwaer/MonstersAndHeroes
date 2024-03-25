public abstract class Item {
    private String name;
    private int cost;
    private int requiredLevel;

    public Item(String name, int cost, int requiredLevel) {
        this.name = name;
        this.cost = cost;
        this.requiredLevel = requiredLevel;
    }

    abstract void displayItemInformation();

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
