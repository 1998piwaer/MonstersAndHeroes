 /*
  * Attribute.java
  * by Simon Kye (simonkye@bu.edu)
  * 3/30/2024
  *
  * Handles attributes of heroes that have special properties used when
  * leveling up (but could also be used for other reasons). So far, 
  * only strength, agility, dexterity, max health, and max mana are attributes.
  * Without this class, we would need to redefine methods such as increaseValuePercentage()
  * and increaseValueFlat() for each of these traits.
  */

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
