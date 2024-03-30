 /*
  * SpellFactory.java
  * by Simon Kye (simonkye@bu.edu)
  * 3/30/2024
  *
  * Utilizes Factory pattern and creates an instance of a potion,
  * which can easily be used in a market, as ItemFactory's createItem()
  * returns a item. Spell extends Item so this is usable.
  */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpellFactory implements ItemFactory {
    public Spell createItem() {
        String[] spellTypes = {"Fire", "Ice", "Lightning"};
        Random random = new Random();
        String randomType = spellTypes[random.nextInt(spellTypes.length)];
        String fileName = "";
        if (randomType.equals("Fire")) {
            fileName = "FireSpells.txt";
        } else if (randomType.equals("Ice")) {
            fileName = "IceSpells.txt";
        } else if (randomType.equals("Lightning")) {
            fileName = "LightningSpells.txt";
        }
        List<String> spellList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            // Skip the header line
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                spellList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (spellList.isEmpty()) {
            return null;
        }
        int randomIndex = random.nextInt(spellList.size());
        String[] data = spellList.get(randomIndex).split("\\s+");

        String name = data[0].replace("_", " ");
        int cost = Integer.parseInt(data[1]);
        int requiredLevel = Integer.parseInt(data[2]);
        int damage = Integer.parseInt(data[3]);
        int manaCost = Integer.parseInt(data[4]);

        return new Spell(name, cost, requiredLevel, damage, manaCost, randomType);
    }
}
