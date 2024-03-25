import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PotionFactory implements ItemFactory {
    public Potion createItem() {
        List<String> potionList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("Potions.txt"))) {
            // Skip the header line
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                potionList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (potionList.isEmpty()) {
            return null;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(potionList.size());
        String[] data = potionList.get(randomIndex).split("\\s+");

        String name = data[0].replace("_", " ");
        int cost = Integer.parseInt(data[1]);
        int requiredLevel = Integer.parseInt(data[2]);
        int attributeIncrease = Integer.parseInt(data[3]);
        String attributeAffected = data[4];

        boolean increaseHealth = attributeAffected.contains("Health");
        boolean increaseStrength = attributeAffected.contains("Strength");
        boolean increaseMana = attributeAffected.contains("Mana");
        boolean increaseDexterity = attributeAffected.contains("Dexterity");
        boolean increaseDefense = attributeAffected.contains("Defense");
        boolean increaseAgility = attributeAffected.contains("Agility");

        return new Potion(name, cost, requiredLevel, attributeIncrease,
                increaseHealth, increaseStrength, increaseMana,
                increaseDexterity, increaseDefense, increaseAgility);
    }
}
