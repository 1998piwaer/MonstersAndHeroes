public class ExoskeletonCombatBehavior implements CombatBehavior {
    public int attack(int dmg) {
        return (int) (dmg * Settings.AFFINITY_MULTIPLIER);
    }
    
    //Monsters can't cast spells
    public int castSpell(int dmg) {
        return dmg;
    }

    public int tankAttack(int dmg) {
        double roll = Math.random();
        if (roll < Settings.BASE_DODGE_CHANCE) {
            return 0;
        } else {
            return (int) (dmg / Settings.AFFINITY_MULTIPLIER);
        }
    }

    public int tankSpell(int dmg) {
        return (int) (dmg / Settings.AFFINITY_MULTIPLIER);
    }
}
