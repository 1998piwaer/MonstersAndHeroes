 /*
  * MonsterParty.java
  * by Simon Kye (simonkye@bu.edu)
  * 3/30/2024
  *
  * This class supports all methods that pertain to a group of monsters.
  * I create a class rather than just having a list of monsters so that
  * I can handle party wide methods such as printing.
  */


import java.util.*;

public class MonsterParty {
    List<Monster> party;
    Input input = Input.getSingletonInput();

    public MonsterParty(int size, int level) {
        party = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            double roll = Math.random();
            if (roll < Settings.SPIRIT_CHANCE) {
                party.add(Monster.createRandomMonsterFromFile(new SpiritCombatBehavior()));
            } else if (roll < Settings.SPIRIT_CHANCE + Settings.EXOSKELETON_CHANCE) {
                party.add(Monster.createRandomMonsterFromFile(new ExoskeletonCombatBehavior()));
            } else if (roll < Settings.SPIRIT_CHANCE + Settings.EXOSKELETON_CHANCE + Settings.DRAGON_CHANCE) {
                party.add(Monster.createRandomMonsterFromFile(new DragonCombatBehavior()));
            }
        }
    }

    public void displayPartyInformation() {
        Settings.clearTerminal();
        for (int i = 0; i < party.size(); i++) {
            Monster currentMonster = party.get(i);
            System.out.println("----------------------");
            System.out.println("Monster " + i + " information:");
            System.out.println("Name: " + currentMonster.getCombatBehavior().getType() + " " 
                    + currentMonster.getName() + " (Lvl " + currentMonster.getLevel() + ")");
            System.out.println("HP: " + currentMonster.getHealth());
            System.out.println("Damage: " + currentMonster.getAttackDamage() + "; Defense: "
                    + currentMonster.getDefense() + "; Dodge Chance: " + currentMonster.getDodgeChance());
        }
        System.out.println("Input any key to continue");
        input.getString();
    }

    public List<Monster> getMonsterParty() {
        return party;
    }

    public void removeMonster(int index) {
        party.remove(index);
    }

    public void printMonsterParty() {
        for (int i = 0; i < party.size(); i++) {
            Monster currMonster = party.get(i);
            System.out.println(currMonster.getName() + " [" + i + "]: HP = " + currMonster.getHealth());
        }
        
    }

    public int size() {
        return party.size();
    }
}
