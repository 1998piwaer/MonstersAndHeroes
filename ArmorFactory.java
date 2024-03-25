import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArmorFactory implements ItemFactory {
    public Armor createItem() {
        List<String> armorList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("Armory.txt"))) {
            // Skip the header line
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                armorList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (armorList.isEmpty()) {
            return null;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(armorList.size());
        String[] data = armorList.get(randomIndex).split("\\s+");

        String name = data[0].replace("_", " ");
        int cost = Integer.parseInt(data[1]);
        int requiredLevel = Integer.parseInt(data[2]);
        int damageReduction = Integer.parseInt(data[3]);

        return new Armor(name, cost, requiredLevel, damageReduction);
    }
}
