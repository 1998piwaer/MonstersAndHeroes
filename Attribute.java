public class Attribute {
    private String name;
    private int value;

    public Attribute(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void increaseValuePercentage(double percentage) {
        int beforeValue = value;
        value += (int) (value * percentage);
        System.out.println(name + ": (" + beforeValue + "->" + value + ")");
    }

    public void increaseValueFlat(int amount) {
        int beforeValue = value;
        value += amount;
        System.out.println(name + ": (" + beforeValue + "->" + value + ")");
    }
}
