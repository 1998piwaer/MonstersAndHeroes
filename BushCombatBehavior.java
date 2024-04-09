public class BushCombatBehavior implements CombatBehavior {
    public int attack(int dmg) {
        return dmg;
    }
    
    //Monsters can't cast spells
    public int castSpell(int dmg) {
        return dmg;
    }

    public int tank(int dmg, int defense, double dodgeChace) {
        double roll = Math.random();
        if (roll < dodgeChace * Settings.AFFINITY_MULTIPLIER) {
            System.out.println("The attack was dodged!");
            return 0;
        } else {
            return dmg - defense * Settings.DEFENSE_EFFECTIVENESS > 0 ? (int) (dmg - defense * Settings.DEFENSE_EFFECTIVENESS) : 0;
        }
    }

    public String getType() {
        return "Bush";
    }

    public void applyLevelUpBonus(Hero hero) {
        // There is no level up bonus for staying in a space
    }
}
