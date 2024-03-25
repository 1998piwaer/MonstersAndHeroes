import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Monster extends Entity {
    private int level;
    public Monster(String name, int level, int strength, int defense, int agility, CombatBehavior cb) {
        super(name, strength, agility, defense, cb);
        this.level = level;
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
}
