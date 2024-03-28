public class PaladinCombatBehavior implements CombatBehavior {

    public int attack(int dmg) {
        return (int) (dmg * Settings.AFFINITY_MULTIPLIER);
    }
    
    public int castSpell(int dmg) {
        return (int) (dmg * Settings.AFFINITY_MULTIPLIER);
    }

    public int tank(int dmg, int defense, double dodgeChance) {
        double roll = Math.random();
        if (roll < dodgeChance) {
            System.out.println("The hero managed to dodge the attack!");
            return 0;
        } else {
            return dmg - defense > 0 ? dmg - defense : 0;
        }
    }
    
    public String getType() {
        return "Paladin";
    }

    public void applyLevelUpBonus(Hero hero) {
        System.out.println("Since this hero is a paladin, they gain extra strength and dexterity!");
        hero.getStrengthAttribute().increaseValuePercentage(Settings.LEVELUP_MULTIPLIER);
        hero.getDexterityAttribute().increaseValuePercentage(Settings.LEVELUP_MULTIPLIER);
    }
}
