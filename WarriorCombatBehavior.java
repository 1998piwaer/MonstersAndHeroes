public class WarriorCombatBehavior implements CombatBehavior {
    // Although these are simple damage modifiers for now, we could make it do very specialized things.
    // i.e. if a warrior is hit with an attack, he could deflect it
    public int attack(int dmg) {
        return (int) (dmg * Settings.AFFINITY_MULTIPLIER);
    }
    
    public int castSpell(int dmg) {
        return dmg;
    }

    // TODO: Implement armor (should take it in)
    public int tank(int dmg, int defense, double dodgeChace) {
        double roll = Math.random();
        if (roll < dodgeChace * Settings.AFFINITY_MULTIPLIER) {
            System.out.println("The hero managed to dodge the attack!");
            return 0;
        } else {
            return (int) (dmg - defense * Settings.DEFENSE_EFFECTIVENESS) > 0 ? (int) (dmg - defense * Settings.DEFENSE_EFFECTIVENESS) : 0;
        }
    }

    public String getType() {
        return "Warrior";
    }

    public void applyLevelUpBonus(Hero hero) {
        System.out.println("Since this hero is a warrior, they gain extra strength and agility!");
        hero.getStrengthAttribute().increaseValuePercentage(Settings.LEVELUP_MULTIPLIER);
        hero.getAgilityAttribute().increaseValuePercentage(Settings.LEVELUP_MULTIPLIER);
    }
}
