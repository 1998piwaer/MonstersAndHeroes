public interface CombatBehavior {
    int attack(int dmg);
    int tank(int dmg, int defense, double dodgeChance);
    int castSpell(int dmg);
    String getType();
    void applyLevelUpBonus(Hero hero);
}
