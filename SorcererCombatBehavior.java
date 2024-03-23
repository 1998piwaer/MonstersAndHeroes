public class SorcererCombatBehavior implements CombatBehavior {

    public int attack(int dmg) {
        return dmg;
    }
    
    public int castSpell(int dmg) {
        return (int) (dmg * Settings.AFFINITY_MULTIPLIER);
    }

    // TODO: Implement armor (should take it in)
    public int tankAttack(int dmg) {
        double roll = Math.random();
        if (roll < Settings.BASE_DODGE_CHANCE * Settings.AFFINITY_MULTIPLIER) {
            return 0;
        } else {
            return dmg;
        }
    }

    // Monsters can't cast spells yet
    public int tankSpell(int dmg) {
        return dmg;
    }
}
