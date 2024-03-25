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

    public void addPartyMember(Monster monster) {
        party.add(monster);
    }
}
