public interface CombatBehavior {
    int attack(int dmg);
    int tankAttack(int dmg);
    int castSpell(int dmg);
    int tankSpell(int dmg);
}
