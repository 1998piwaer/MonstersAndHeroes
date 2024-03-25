import java.util.*;

public class MonsterParty {
    List<Monster> party;

    public MonsterParty(int size) {
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

    public List<Monster> getMonsterParty() {
        return party;
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
