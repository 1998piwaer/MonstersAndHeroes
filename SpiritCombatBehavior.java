 /*
  * SpiritCombatBehavior.java
  * by Simon Kye (simonkye@bu.edu)
  * 3/30/2024
  *
  * Implements the strategy pattern from CombatBehavior().
  * Monsters can only attack so castSpell() should never be called, but
  * this can easily be changed so that monsters could cast spells.
  * Monsters can't level up either but can also easily be changed as well.
  * We apply a type's advantage during calculation with AFFINITY_MULTIPLIER. 
  */

public class SpiritCombatBehavior implements CombatBehavior {
    public int attack(int dmg) {
        return (int) (dmg * Settings.AFFINITY_MULTIPLIER);
    }
    
    //Monsters can't cast spells
    public int castSpell(int dmg) {
        return dmg;
    }

    public int tank(int dmg, int defense, double dodgeChance) {
        double roll = Math.random();
        if (roll < dodgeChance * Settings.AFFINITY_MULTIPLIER) {
            System.out.println("The monster managed to dodge the attack!");
            return 0;
        } else {
            return (int) (dmg - defense * Settings.DEFENSE_EFFECTIVENESS) > 0 ? (int) (dmg - defense * Settings.DEFENSE_EFFECTIVENESS) : 0;
        }
    }
    
    public String getType() {
        return "Spirit";
    }

    public void applyLevelUpBonus(Hero hero) {
        System.out.println("[Debug]: Monsters can't level up! This shouldn't be printing");
    }
}
