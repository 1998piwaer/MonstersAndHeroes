import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WeaponFactory implements ItemFactory {
    public Weapon createItem() {
        List<String> weaponList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("Weaponry.txt"))) {
            // Skip the header line
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                weaponList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (weaponList.isEmpty()) {
            return null;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(weaponList.size());
        String[] data = weaponList.get(randomIndex).split("\\s+");

        String name = data[0].replace("_", " ");
        int cost = Integer.parseInt(data[1]);
        int requiredLevel = Integer.parseInt(data[2]);
        int damageAmplification = Integer.parseInt(data[3]);
        int requiredHands = Integer.parseInt(data[4]);

        return new Weapon(name, cost, requiredLevel, damageAmplification, requiredHands);
    }
}
