import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Monster extends Entity {
    private int attackDamage;
    private int level;
    private int health;
    private int dodgeChance;
    public Monster(String name, int level, int attackDamage, int defense, int dodgeChance, CombatBehavior cb) {
        super(name, defense, cb);
        this.attackDamage = attackDamage;
        this.level = level;
        this.dodgeChance = dodgeChance;
        this.health = this.level * 100;
    }

    public void tankFire() {
        defense *= 0.8;
    }

    public void tankLightning() {
        dodgeChance *= 0.8;
    }

    public void tankIce() {
        attackDamage *= 0.8;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public static Monster createRandomMonsterFromFile(CombatBehavior cb) {
        String fileName = "";
        List<String> monsters = new ArrayList<>();
        if (cb instanceof ExoskeletonCombatBehavior) {
            fileName = "Exoskeletons.txt";
        } else if (cb instanceof SpiritCombatBehavior) {
            fileName = "Spirits.txt";
        } else if (cb instanceof DragonCombatBehavior) {
            fileName = "Dragons.txt";
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                monsters.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (monsters.isEmpty()) {
            return null;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(monsters.size());
        String[] data = monsters.get(randomIndex).split("\\s+");

        String name = data[0];
        int level = Integer.parseInt(data[1]);
        int damage = Integer.parseInt(data[2]);
        int defense = Integer.parseInt(data[3]);
        int dodgeChance = Integer.parseInt(data[4]);

        return new Monster(name, level, damage, defense, dodgeChance, cb);
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }

    public int getLevel() {
        return level;
    }

    public int getDodgeChance() {
        return dodgeChance;
    }

    public int getHealth() {
        return health;
    }

    public boolean isFainted() {
        return health <= 0;
    }
}
