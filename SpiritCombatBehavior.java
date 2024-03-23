public class SpiritCombatBehavior implements CombatBehavior {
    public int attack(int dmg) {
        return (int) (dmg * Settings.AFFINITY_MULTIPLIER);
    }
    
    //Monsters can't cast spells
    public int castSpell(int dmg) {
        return dmg;
    }

    public int tankAttack(int dmg) {
        double roll = Math.random();
        if (roll < Settings.BASE_DODGE_CHANCE * Settings.AFFINITY_MULTIPLIER) {
            return 0;
        } else {
            return dmg;
        }
    }

    public int tankSpell(int dmg) {
        return dmg;
    }
}
