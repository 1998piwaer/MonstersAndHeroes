 /*
  * SorcererCombatBehavior.java
  * by Simon Kye (simonkye@bu.edu)
  * 3/30/2024
  *
  * Implements the strategy pattern from CombatBehavior().
  * We apply a type's advantage during calculation with AFFINITY_MULTIPLIER. 
  * Does all caclulations regarding how much damage a hero should do or take given
  * a hero's type. This also includes their level up bonuses.
  */

public class SorcererCombatBehavior implements CombatBehavior {

    public int attack(int dmg) {
        return dmg;
    }
    
    public int castSpell(int dmg) {
        return (int) (dmg * Settings.AFFINITY_MULTIPLIER);
    }

    public int tank(int dmg, int defense, double dodgeChance) {
        double roll = Math.random();
        if (roll < dodgeChance * Settings.AFFINITY_MULTIPLIER) {
            System.out.println("The hero managed to dodge the attack!");
            return 0;
        } else {
            return (int) (dmg - defense * Settings.DEFENSE_EFFECTIVENESS) > 0 ? (int) (dmg - defense * Settings.DEFENSE_EFFECTIVENESS) : 0;
        }
    }

    public String getType() {
        return "Sorcerer";
    }

    public void applyLevelUpBonus(Hero hero) {
        System.out.println("Since this hero is a sorcerer, they gain extra dexterity and agility!");
        hero.getDexterityAttribute().increaseValuePercentage(Settings.LEVELUP_MULTIPLIER);
        hero.getAgilityAttribute().increaseValuePercentage(Settings.LEVELUP_MULTIPLIER);
    }
}
