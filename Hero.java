import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hero extends Entity {
    private static final int DEFAULT_DEFENSE = 500;
    private Attribute strength;
    private Attribute dexterity;
    private Attribute agility;
    private int mana;
    private int gold;
    private int exp;
    private int health;
    private Attribute maxHealth;
    private Attribute maxMana;
    private int level;
    private Armor armor;
    private Weapon weapon;
    private int hands;
    private List<Item> inventory;

    public Hero(String name, int mana, int strength, int agility, int dexterity, int gold, int exp, CombatBehavior cb) {
        super(name, DEFAULT_DEFENSE, cb);
        this.strength = new Attribute("Strength", strength);
        this.maxMana = new Attribute("Max Mana", mana);
        this.agility = new Attribute("Agility", agility);
        this.dexterity = new Attribute("Dexterity", agility);;
        this.gold = gold;
        this.exp = exp;
        this.level = 1;
        this.maxHealth = new Attribute("Max Health", level * Settings.HEALTH_PER_LEVEL);
        this.health = level * 100;
        this.mana = mana;
        this.hands = 2;
        this.inventory = new ArrayList<>();
    }


    public void useOrEquipItem(Item item) {
        System.out.println("[Debug]: There is no useOrEquip defined for this item type");
    }

    public boolean useOrEquipItem(Armor armor) {
        if (armor.getRequiredLevel() > level) {
            System.out.println(name + " (lvl " + level + ") is underleveled for the item! Required Level: " + armor.getRequiredLevel());
            return false;
        } else {
            if (this.armor != null) {
                inventory.add(this.armor);
                this.armor = null;
            }
            this.armor = armor;
            inventory.remove(armor);
            defense = DEFAULT_DEFENSE + armor.getDamageReduction();
            return true;
        }
        

    }

    public Armor getArmor() {
        return armor;
    }

    public boolean useOrEquipItem(Weapon weapon) {
        if (weapon.getRequiredLevel() > level) {
            System.out.println(name + " (lvl " + level + ") is underleveled for the item! Required Level: " + armor.getRequiredLevel());
            return false;
        } else {
            int requiredHands = weapon.getRequiredHands();
            if (hands < requiredHands) {
                System.out.println("You don't have enough hands to equip this weapon!");
                return false;
            } else {
                if (this.weapon != null) {
                    inventory.add(this.weapon);
                    this.weapon = null;
                }
                this.weapon = weapon;
                inventory.remove(weapon);
                return true;
            }
        }
    }

    public boolean useOrEquipItem(Potion potion) {
        if (potion.getRequiredLevel() > level) {
            System.out.println(name + " (lvl " + level + ") is underleveled for the item! Required Level: " + armor.getRequiredLevel());
            return false;
        } else {
            int attInc = potion.getAttributeIncrease();
            List<String> affectedAttributes = potion.getAffectedAttributes();
            for (String attribute : affectedAttributes) {
                if (attribute.equals("Health")) {
                    health += attInc;
                } else if (attribute.equals("Strength")) {
                    strength.increaseValueFlat(attInc);
                } else if (attribute.equals("Mana")) {
                    mana += attInc;
                } else if (attribute.equals("Dexterity")) {
                    dexterity.increaseValueFlat(attInc);
                } else if (attribute.equals("Defense")) {
                    defense += attInc;
                } else if (attribute.equals("Agility")) {
                    agility.increaseValueFlat(attInc);
                } else {
                    System.out.println("[Debug]: Unspecified potion effect!");
                }
            }
            inventory.remove(potion);
            return true;
        }
    }

    public <T extends Item> List<T> getItemsOfType(Class<T> itemType) {
        List<T> items = new ArrayList<>();
        for (Item i : inventory) {
            if (itemType.isInstance(i)) {
                items.add(itemType.cast(i));
            }
        }
        return items;
    }

    public void sellItem(int index) {
        Item itemToSell = inventory.get(index);
        gold += itemToSell.getCost() / 2;
        inventory.remove(itemToSell);
    }

    public int getAgility() {
        return agility.getValue();
    }

    public int getDexterity() {
        return dexterity.getValue();
    }

    public int getMana() {
        return mana;
    }
    
    public int getGold() {
        return gold;
    }

    public int getExp() {
        return exp;
    }

    public int getStrength() {
        return strength.getValue();
    }

    public void gainExp(int exp) {
        this.exp += exp;
        while (this.exp >= level * 10) {
            this.exp -= level * 10;
            levelUp();
        }
    }

    public int getMaxHealth() {
        return maxHealth.getValue();
    }


    public Weapon getWeapon() {
        return weapon;
    }

    public Attribute getStrengthAttribute() {
        return strength;
    }

    public Attribute getDexterityAttribute() {
        return dexterity;
    }

    public Attribute getAgilityAttribute() {
        return agility;
    }

    private void levelUp() {
        System.out.println("Hero " + name + " leveled up! (" + level + "->" + (++level) + ")");
        maxHealth.increaseValuePercentage(Settings.LEVELUP_MULTIPLIER);
        maxMana.increaseValuePercentage(Settings.LEVELUP_MULTIPLIER);
        strength.increaseValuePercentage(Settings.LEVELUP_MULTIPLIER);
        dexterity.increaseValuePercentage(Settings.LEVELUP_MULTIPLIER);
        agility.increaseValuePercentage(Settings.LEVELUP_MULTIPLIER);

        cb.applyLevelUpBonus(this);
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public boolean displayInventory() {
        if (inventory.isEmpty()) {
            System.out.println("No items in inventory for " + name);
            return false;
        }
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println(name + "'s Inventory: ");
            System.out.print("[" + i + "] ");
            inventory.get(i).displayItemInformationInventory();
        }
        return true;
    }

    public void takeDamage(int dmg) {
        health -= dmg;
        if (health < 0) {
            health = 0;
        }
    }

    public int getLevel() {
        return level;
    }

    public int getHealth() {
        return health;
    }

    public void gainHealth(int health) {
        this.health += health;
    }

    public void gainMana(int mana) {
        this.mana += mana;
    }

    public int getMaxMana() {
        return maxMana.getValue();
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public boolean isFainted() {
        return health <= 0;
    }

    public void revive() {
        if (health <= 0) {
            health = (int) (maxHealth.getValue()* 0.5);
        }
    }

    public void deductGold(int gold) {
        this.gold -= gold;
    }

    public void gainGold(int gold) {
        this.gold += gold;
    }

    // Funcntion with assistance from Claude.Ai (regarding parsing from file)
    public static Hero createRandomHeroFromFile(CombatBehavior cb) {
        String fileName = "";
        List<String> heroes = new ArrayList<>();
        if (cb instanceof WarriorCombatBehavior) {
            fileName = "Warriors.txt";
        } else if (cb instanceof SorcererCombatBehavior) {
            fileName = "Sorcerers.txt";
        } else if (cb instanceof PaladinCombatBehavior) {
            fileName = "Paladins.txt";
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                heroes.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (heroes.isEmpty()) {
            return null;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(heroes.size());
        String[] data = heroes.get(randomIndex).split("\\s+");

        String name = data[0].replace("_", " ");
        int mana = Integer.parseInt(data[1]);
        int strength = Integer.parseInt(data[2]);
        int agility = Integer.parseInt(data[3]);
        int dexterity = Integer.parseInt(data[4]);
        int money = Integer.parseInt(data[5]);
        int exp = Integer.parseInt(data[6]);

        return new Hero(name, mana, strength, agility, dexterity, money, exp, cb);
    }
}
