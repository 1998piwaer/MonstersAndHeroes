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
