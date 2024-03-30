 /*
  * CombatBehavior.java
  * by Simon Kye (simonkye@bu.edu)
  * 3/30/2024
  *
  * Interface that utilizes the Strategy Pattern.
  * Deals with calculation of taking damage, casting spells, sending attacks,
  * and also allows for classes of specific types to have additional level up bonuses.
  */

public interface CombatBehavior {
    int attack(int dmg);
    int tank(int dmg, int defense, double dodgeChance);
    int castSpell(int dmg);
    String getType();
    void applyLevelUpBonus(Hero hero);
}
