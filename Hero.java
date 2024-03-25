import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hero extends Entity {
    private static final int DEFAULT_DEFENSE = 500;
    private int dexterity;
    private int mana;
    private int gold;
    private int exp;
    private int health;
    private int level;

    public Hero(String name, int mana, int strength, int agility, int dexterity, int gold, int exp, CombatBehavior cb) {
        super(name, strength, agility, DEFAULT_DEFENSE, cb);
        this.mana = mana;
        this.dexterity = dexterity;
        this.gold = gold;
        this.exp = exp;
        this.level = 1;
        this.health = level * 100;
    }

    public int getDexterity() {
        return dexterity;
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

    public void takeDamage(int dmg) {
        health -= dmg;
        if (health < 0) {
            health = 0;
        }
    }

    public int getHealth() {
        return health;
    }

    public boolean isFainted() {
        return health <= 0;
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
